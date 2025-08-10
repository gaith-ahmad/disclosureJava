import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IGift } from 'app/entities/gift/gift.model';
import { GiftService } from 'app/entities/gift/service/gift.service';
import { IConflictInterest } from 'app/entities/conflict-interest/conflict-interest.model';
import { ConflictInterestService } from 'app/entities/conflict-interest/service/conflict-interest.service';
import { DisclosureService } from '../service/disclosure.service';
import { IDisclosure } from '../disclosure.model';
import { DisclosureFormGroup, DisclosureFormService } from './disclosure-form.service';

@Component({
  selector: 'jhi-disclosure-update',
  templateUrl: './disclosure-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DisclosureUpdateComponent implements OnInit {
  isSaving = false;
  disclosure: IDisclosure | null = null;

  giftsCollection: IGift[] = [];
  conflictInterestsCollection: IConflictInterest[] = [];

  protected disclosureService = inject(DisclosureService);
  protected disclosureFormService = inject(DisclosureFormService);
  protected giftService = inject(GiftService);
  protected conflictInterestService = inject(ConflictInterestService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: DisclosureFormGroup = this.disclosureFormService.createDisclosureFormGroup();

  compareGift = (o1: IGift | null, o2: IGift | null): boolean => this.giftService.compareGift(o1, o2);

  compareConflictInterest = (o1: IConflictInterest | null, o2: IConflictInterest | null): boolean =>
    this.conflictInterestService.compareConflictInterest(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ disclosure }) => {
      this.disclosure = disclosure;
      if (disclosure) {
        this.updateForm(disclosure);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const disclosure = this.disclosureFormService.getDisclosure(this.editForm);
    if (disclosure.id !== null) {
      this.subscribeToSaveResponse(this.disclosureService.update(disclosure));
    } else {
      this.subscribeToSaveResponse(this.disclosureService.create(disclosure));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDisclosure>>): void {
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

  protected updateForm(disclosure: IDisclosure): void {
    this.disclosure = disclosure;
    this.disclosureFormService.resetForm(this.editForm, disclosure);

    this.giftsCollection = this.giftService.addGiftToCollectionIfMissing<IGift>(this.giftsCollection, disclosure.gift);
    this.conflictInterestsCollection = this.conflictInterestService.addConflictInterestToCollectionIfMissing<IConflictInterest>(
      this.conflictInterestsCollection,
      disclosure.conflictInterest,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.giftService
      .query({ filter: 'disclosure-is-null' })
      .pipe(map((res: HttpResponse<IGift[]>) => res.body ?? []))
      .pipe(map((gifts: IGift[]) => this.giftService.addGiftToCollectionIfMissing<IGift>(gifts, this.disclosure?.gift)))
      .subscribe((gifts: IGift[]) => (this.giftsCollection = gifts));

    this.conflictInterestService
      .query({ filter: 'disclosure-is-null' })
      .pipe(map((res: HttpResponse<IConflictInterest[]>) => res.body ?? []))
      .pipe(
        map((conflictInterests: IConflictInterest[]) =>
          this.conflictInterestService.addConflictInterestToCollectionIfMissing<IConflictInterest>(
            conflictInterests,
            this.disclosure?.conflictInterest,
          ),
        ),
      )
      .subscribe((conflictInterests: IConflictInterest[]) => (this.conflictInterestsCollection = conflictInterests));
  }
}
