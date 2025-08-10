import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IUserInfo } from '../user-info.model';

@Component({
  selector: 'jhi-user-info-detail',
  templateUrl: './user-info-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class UserInfoDetailComponent {
  userInfo = input<IUserInfo | null>(null);

  previousState(): void {
    window.history.back();
  }
}
