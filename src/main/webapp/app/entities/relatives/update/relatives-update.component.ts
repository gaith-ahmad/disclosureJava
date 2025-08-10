import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IDisclosure } from 'app/entities/disclosure/disclosure.model';
import { DisclosureService } from 'app/entities/disclosure/service/disclosure.service';
import { IRelatives } from '../relatives.model';
import { RelativesService } from '../service/relatives.service';
import { RelativesFormGroup, RelativesFormService } from './relatives-form.service';

@Component({
  selector: 'jhi-relatives-update',
  templateUrl: './relatives-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class RelativesUpdateComponent implements OnInit {
  isSaving = false;
  relatives: IRelatives | null = null;

  disclosuresSharedCollection: IDisclosure[] = [];

  protected relativesService = inject(RelativesService);
  protected relativesFormService = inject(RelativesFormService);
  protected disclosureService = inject(DisclosureService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: RelativesFormGroup = this.relativesFormService.createRelativesFormGroup();

  compareDisclosure = (o1: IDisclosure | null, o2: IDisclosure | null): boolean => this.disclosureService.compareDisclosure(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relatives }) => {
      this.relatives = relatives;
      if (relatives) {
        this.updateForm(relatives);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const relatives = this.relativesFormService.getRelatives(this.editForm);
    if (relatives.id !== null) {
      this.subscribeToSaveResponse(this.relativesService.update(relatives));
    } else {
      this.subscribeToSaveResponse(this.relativesService.create(relatives));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRelatives>>): void {
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

  protected updateForm(relatives: IRelatives): void {
    this.relatives = relatives;
    this.relativesFormService.resetForm(this.editForm, relatives);

    this.disclosuresSharedCollection = this.disclosureService.addDisclosureToCollectionIfMissing<IDisclosure>(
      this.disclosuresSharedCollection,
      relatives.disclosure,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.disclosureService
      .query()
      .pipe(map((res: HttpResponse<IDisclosure[]>) => res.body ?? []))
      .pipe(
        map((disclosures: IDisclosure[]) =>
          this.disclosureService.addDisclosureToCollectionIfMissing<IDisclosure>(disclosures, this.relatives?.disclosure),
        ),
      )
      .subscribe((disclosures: IDisclosure[]) => (this.disclosuresSharedCollection = disclosures));
  }
}
