import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ConflictInterestDetailComponent } from './conflict-interest-detail.component';

describe('ConflictInterest Management Detail Component', () => {
  let comp: ConflictInterestDetailComponent;
  let fixture: ComponentFixture<ConflictInterestDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConflictInterestDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./conflict-interest-detail.component').then(m => m.ConflictInterestDetailComponent),
              resolve: { conflictInterest: () => of({ id: 26216 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ConflictInterestDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConflictInterestDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load conflictInterest on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ConflictInterestDetailComponent);

      // THEN
      expect(instance.conflictInterest()).toEqual(expect.objectContaining({ id: 26216 }));
    });
  });

  describe('PreviousState', () => {
    it('should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
