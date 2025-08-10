import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IRelatives } from '../relatives.model';
import { RelativesService } from '../service/relatives.service';

@Component({
  templateUrl: './relatives-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class RelativesDeleteDialogComponent {
  relatives?: IRelatives;

  protected relativesService = inject(RelativesService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.relativesService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
