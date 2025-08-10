import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ManagerInfoDetailComponent } from './manager-info-detail.component';

describe('ManagerInfo Management Detail Component', () => {
  let comp: ManagerInfoDetailComponent;
  let fixture: ComponentFixture<ManagerInfoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManagerInfoDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./manager-info-detail.component').then(m => m.ManagerInfoDetailComponent),
              resolve: { managerInfo: () => of({ id: 26966 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ManagerInfoDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerInfoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load managerInfo on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ManagerInfoDetailComponent);

      // THEN
      expect(instance.managerInfo()).toEqual(expect.objectContaining({ id: 26966 }));
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
