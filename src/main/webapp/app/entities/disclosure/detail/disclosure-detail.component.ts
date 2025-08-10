import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IDisclosure } from '../disclosure.model';

@Component({
  selector: 'jhi-disclosure-detail',
  templateUrl: './disclosure-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class DisclosureDetailComponent {
  disclosure = input<IDisclosure | null>(null);

  previousState(): void {
    window.history.back();
  }
}
