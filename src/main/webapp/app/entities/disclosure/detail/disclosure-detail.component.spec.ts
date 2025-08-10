import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { DisclosureDetailComponent } from './disclosure-detail.component';

describe('Disclosure Management Detail Component', () => {
  let comp: DisclosureDetailComponent;
  let fixture: ComponentFixture<DisclosureDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DisclosureDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./disclosure-detail.component').then(m => m.DisclosureDetailComponent),
              resolve: { disclosure: () => of({ id: 10911 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(DisclosureDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisclosureDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load disclosure on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', DisclosureDetailComponent);

      // THEN
      expect(instance.disclosure()).toEqual(expect.objectContaining({ id: 10911 }));
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
