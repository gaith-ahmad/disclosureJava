import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IDisclosure } from '../disclosure.model';
import { DisclosureService } from '../service/disclosure.service';

@Component({
  templateUrl: './disclosure-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class DisclosureDeleteDialogComponent {
  disclosure?: IDisclosure;

  protected disclosureService = inject(DisclosureService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.disclosureService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
