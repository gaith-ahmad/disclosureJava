import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IGift } from '../gift.model';

@Component({
  selector: 'jhi-gift-detail',
  templateUrl: './gift-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class GiftDetailComponent {
  gift = input<IGift | null>(null);

  previousState(): void {
    window.history.back();
  }
}
