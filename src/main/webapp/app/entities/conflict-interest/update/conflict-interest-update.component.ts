import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IConflictInterest } from '../conflict-interest.model';
import { ConflictInterestService } from '../service/conflict-interest.service';
import { ConflictInterestFormGroup, ConflictInterestFormService } from './conflict-interest-form.service';

@Component({
  selector: 'jhi-conflict-interest-update',
  templateUrl: './conflict-interest-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ConflictInterestUpdateComponent implements OnInit {
  isSaving = false;
  conflictInterest: IConflictInterest | null = null;

  protected conflictInterestService = inject(ConflictInterestService);
  protected conflictInterestFormService = inject(ConflictInterestFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ConflictInterestFormGroup = this.conflictInterestFormService.createConflictInterestFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ conflictInterest }) => {
      this.conflictInterest = conflictInterest;
      if (conflictInterest) {
        this.updateForm(conflictInterest);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const conflictInterest = this.conflictInterestFormService.getConflictInterest(this.editForm);
    if (conflictInterest.id !== null) {
      this.subscribeToSaveResponse(this.conflictInterestService.update(conflictInterest));
    } else {
      this.subscribeToSaveResponse(this.conflictInterestService.create(conflictInterest));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConflictInterest>>): void {
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

  protected updateForm(conflictInterest: IConflictInterest): void {
    this.conflictInterest = conflictInterest;
    this.conflictInterestFormService.resetForm(this.editForm, conflictInterest);
  }
}
