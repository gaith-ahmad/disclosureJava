import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IConflictInterest } from '../conflict-interest.model';

@Component({
  selector: 'jhi-conflict-interest-detail',
  templateUrl: './conflict-interest-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class ConflictInterestDetailComponent {
  conflictInterest = input<IConflictInterest | null>(null);

  previousState(): void {
    window.history.back();
  }
}
