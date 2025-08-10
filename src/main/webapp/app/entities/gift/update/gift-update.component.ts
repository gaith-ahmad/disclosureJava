import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IGift } from '../gift.model';
import { GiftService } from '../service/gift.service';
import { GiftFormGroup, GiftFormService } from './gift-form.service';

@Component({
  selector: 'jhi-gift-update',
  templateUrl: './gift-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class GiftUpdateComponent implements OnInit {
  isSaving = false;
  gift: IGift | null = null;

  protected giftService = inject(GiftService);
  protected giftFormService = inject(GiftFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: GiftFormGroup = this.giftFormService.createGiftFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gift }) => {
      this.gift = gift;
      if (gift) {
        this.updateForm(gift);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gift = this.giftFormService.getGift(this.editForm);
    if (gift.id !== null) {
      this.subscribeToSaveResponse(this.giftService.update(gift));
    } else {
      this.subscribeToSaveResponse(this.giftService.create(gift));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGift>>): void {
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

  protected updateForm(gift: IGift): void {
    this.gift = gift;
    this.giftFormService.resetForm(this.editForm, gift);
  }
}
