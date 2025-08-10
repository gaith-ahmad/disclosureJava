import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { UserInfoDetailComponent } from './user-info-detail.component';

describe('UserInfo Management Detail Component', () => {
  let comp: UserInfoDetailComponent;
  let fixture: ComponentFixture<UserInfoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserInfoDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./user-info-detail.component').then(m => m.UserInfoDetailComponent),
              resolve: { userInfo: () => of({ id: 16592 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(UserInfoDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserInfoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load userInfo on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', UserInfoDetailComponent);

      // THEN
      expect(instance.userInfo()).toEqual(expect.objectContaining({ id: 16592 }));
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
