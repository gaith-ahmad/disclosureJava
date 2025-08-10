import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IUserInfo } from '../user-info.model';
import { UserInfoService } from '../service/user-info.service';

@Component({
  templateUrl: './user-info-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class UserInfoDeleteDialogComponent {
  userInfo?: IUserInfo;

  protected userInfoService = inject(UserInfoService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userInfoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
