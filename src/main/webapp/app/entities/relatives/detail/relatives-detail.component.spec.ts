import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { RelativesDetailComponent } from './relatives-detail.component';

describe('Relatives Management Detail Component', () => {
  let comp: RelativesDetailComponent;
  let fixture: ComponentFixture<RelativesDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelativesDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./relatives-detail.component').then(m => m.RelativesDetailComponent),
              resolve: { relatives: () => of({ id: 22367 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(RelativesDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RelativesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load relatives on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', RelativesDetailComponent);

      // THEN
      expect(instance.relatives()).toEqual(expect.objectContaining({ id: 22367 }));
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
