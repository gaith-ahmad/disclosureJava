import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IManagerInfo } from '../manager-info.model';
import { ManagerInfoService } from '../service/manager-info.service';
import { ManagerInfoFormGroup, ManagerInfoFormService } from './manager-info-form.service';

@Component({
  selector: 'jhi-manager-info-update',
  templateUrl: './manager-info-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ManagerInfoUpdateComponent implements OnInit {
  isSaving = false;
  managerInfo: IManagerInfo | null = null;

  protected managerInfoService = inject(ManagerInfoService);
  protected managerInfoFormService = inject(ManagerInfoFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ManagerInfoFormGroup = this.managerInfoFormService.createManagerInfoFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ managerInfo }) => {
      this.managerInfo = managerInfo;
      if (managerInfo) {
        this.updateForm(managerInfo);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const managerInfo = this.managerInfoFormService.getManagerInfo(this.editForm);
    if (managerInfo.id !== null) {
      this.subscribeToSaveResponse(this.managerInfoService.update(managerInfo));
    } else {
      this.subscribeToSaveResponse(this.managerInfoService.create(managerInfo));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IManagerInfo>>): void {
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

  protected updateForm(managerInfo: IManagerInfo): void {
    this.managerInfo = managerInfo;
    this.managerInfoFormService.resetForm(this.editForm, managerInfo);
  }
}
