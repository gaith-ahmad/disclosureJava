import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IManagerInfo } from '../manager-info.model';
import { ManagerInfoService } from '../service/manager-info.service';

@Component({
  templateUrl: './manager-info-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ManagerInfoDeleteDialogComponent {
  managerInfo?: IManagerInfo;

  protected managerInfoService = inject(ManagerInfoService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.managerInfoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
