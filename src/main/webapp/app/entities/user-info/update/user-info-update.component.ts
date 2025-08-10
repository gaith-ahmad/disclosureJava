import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IManagerInfo } from 'app/entities/manager-info/manager-info.model';
import { ManagerInfoService } from 'app/entities/manager-info/service/manager-info.service';
import { IUserInfo } from '../user-info.model';
import { UserInfoService } from '../service/user-info.service';
import { UserInfoFormGroup, UserInfoFormService } from './user-info-form.service';

@Component({
  selector: 'jhi-user-info-update',
  templateUrl: './user-info-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class UserInfoUpdateComponent implements OnInit {
  isSaving = false;
  userInfo: IUserInfo | null = null;

  managerinfosCollection: IManagerInfo[] = [];

  protected userInfoService = inject(UserInfoService);
  protected userInfoFormService = inject(UserInfoFormService);
  protected managerInfoService = inject(ManagerInfoService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: UserInfoFormGroup = this.userInfoFormService.createUserInfoFormGroup();

  compareManagerInfo = (o1: IManagerInfo | null, o2: IManagerInfo | null): boolean => this.managerInfoService.compareManagerInfo(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userInfo }) => {
      this.userInfo = userInfo;
      if (userInfo) {
        this.updateForm(userInfo);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userInfo = this.userInfoFormService.getUserInfo(this.editForm);
    if (userInfo.id !== null) {
      this.subscribeToSaveResponse(this.userInfoService.update(userInfo));
    } else {
      this.subscribeToSaveResponse(this.userInfoService.create(userInfo));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserInfo>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(userInfo: IUserInfo): void {
    this.userInfo = userInfo;
    this.userInfoFormService.resetForm(this.editForm, userInfo);

    this.managerinfosCollection = this.managerInfoService.addManagerInfoToCollectionIfMissing<IManagerInfo>(
      this.managerinfosCollection,
      userInfo.managerinfo,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.managerInfoService
      .query({ filter: 'userinfo-is-null' })
      .pipe(map((res: HttpResponse<IManagerInfo[]>) => res.body ?? []))
      .pipe(
        map((managerInfos: IManagerInfo[]) =>
          this.managerInfoService.addManagerInfoToCollectionIfMissing<IManagerInfo>(managerInfos, this.userInfo?.managerinfo),
        ),
      )
      .subscribe((managerInfos: IManagerInfo[]) => (this.managerinfosCollection = managerInfos));
  }
}
