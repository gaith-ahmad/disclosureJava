import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IManagerInfo } from 'app/entities/manager-info/manager-info.model';
import { ManagerInfoService } from 'app/entities/manager-info/service/manager-info.service';
import { UserInfoService } from '../service/user-info.service';
import { IUserInfo } from '../user-info.model';
import { UserInfoFormService } from './user-info-form.service';

import { UserInfoUpdateComponent } from './user-info-update.component';

describe('UserInfo Management Update Component', () => {
  let comp: UserInfoUpdateComponent;
  let fixture: ComponentFixture<UserInfoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let userInfoFormService: UserInfoFormService;
  let userInfoService: UserInfoService;
  let managerInfoService: ManagerInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [UserInfoUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(UserInfoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserInfoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    userInfoFormService = TestBed.inject(UserInfoFormService);
    userInfoService = TestBed.inject(UserInfoService);
    managerInfoService = TestBed.inject(ManagerInfoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call managerinfo query and add missing value', () => {
      const userInfo: IUserInfo = { id: 28566 };
      const managerinfo: IManagerInfo = { id: 26966 };
      userInfo.managerinfo = managerinfo;

      const managerinfoCollection: IManagerInfo[] = [{ id: 26966 }];
      jest.spyOn(managerInfoService, 'query').mockReturnValue(of(new HttpResponse({ body: managerinfoCollection })));
      const expectedCollection: IManagerInfo[] = [managerinfo, ...managerinfoCollection];
      jest.spyOn(managerInfoService, 'addManagerInfoToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ userInfo });
      comp.ngOnInit();

      expect(managerInfoService.query).toHaveBeenCalled();
      expect(managerInfoService.addManagerInfoToCollectionIfMissing).toHaveBeenCalledWith(managerinfoCollection, managerinfo);
      expect(comp.managerinfosCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const userInfo: IUserInfo = { id: 28566 };
      const managerinfo: IManagerInfo = { id: 26966 };
      userInfo.managerinfo = managerinfo;

      activatedRoute.data = of({ userInfo });
      comp.ngOnInit();

      expect(comp.managerinfosCollection).toContainEqual(managerinfo);
      expect(comp.userInfo).toEqual(userInfo);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserInfo>>();
      const userInfo = { id: 16592 };
      jest.spyOn(userInfoFormService, 'getUserInfo').mockReturnValue(userInfo);
      jest.spyOn(userInfoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userInfo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userInfo }));
      saveSubject.complete();

      // THEN
      expect(userInfoFormService.getUserInfo).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(userInfoService.update).toHaveBeenCalledWith(expect.objectContaining(userInfo));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserInfo>>();
      const userInfo = { id: 16592 };
      jest.spyOn(userInfoFormService, 'getUserInfo').mockReturnValue({ id: null });
      jest.spyOn(userInfoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userInfo: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userInfo }));
      saveSubject.complete();

      // THEN
      expect(userInfoFormService.getUserInfo).toHaveBeenCalled();
      expect(userInfoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserInfo>>();
      const userInfo = { id: 16592 };
      jest.spyOn(userInfoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userInfo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(userInfoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareManagerInfo', () => {
      it('should forward to managerInfoService', () => {
        const entity = { id: 26966 };
        const entity2 = { id: 23884 };
        jest.spyOn(managerInfoService, 'compareManagerInfo');
        comp.compareManagerInfo(entity, entity2);
        expect(managerInfoService.compareManagerInfo).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
