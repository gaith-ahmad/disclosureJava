import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IConflictInterest } from '../conflict-interest.model';
import { ConflictInterestService } from '../service/conflict-interest.service';

@Component({
  templateUrl: './conflict-interest-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ConflictInterestDeleteDialogComponent {
  conflictInterest?: IConflictInterest;

  protected conflictInterestService = inject(ConflictInterestService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.conflictInterestService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
