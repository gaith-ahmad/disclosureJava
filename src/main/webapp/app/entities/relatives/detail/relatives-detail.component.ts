import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IRelatives } from '../relatives.model';

@Component({
  selector: 'jhi-relatives-detail',
  templateUrl: './relatives-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class RelativesDetailComponent {
  relatives = input<IRelatives | null>(null);

  previousState(): void {
    window.history.back();
  }
}
