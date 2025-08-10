import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IManagerInfo } from '../manager-info.model';

@Component({
  selector: 'jhi-manager-info-detail',
  templateUrl: './manager-info-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class ManagerInfoDetailComponent {
  managerInfo = input<IManagerInfo | null>(null);

  previousState(): void {
    window.history.back();
  }
}
