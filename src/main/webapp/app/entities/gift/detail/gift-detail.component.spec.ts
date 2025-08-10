import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { GiftDetailComponent } from './gift-detail.component';

describe('Gift Management Detail Component', () => {
  let comp: GiftDetailComponent;
  let fixture: ComponentFixture<GiftDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GiftDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./gift-detail.component').then(m => m.GiftDetailComponent),
              resolve: { gift: () => of({ id: 17243 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(GiftDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GiftDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load gift on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', GiftDetailComponent);

      // THEN
      expect(instance.gift()).toEqual(expect.objectContaining({ id: 17243 }));
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
