import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { ManagerInfoService } from '../service/manager-info.service';
import { IManagerInfo } from '../manager-info.model';
import { ManagerInfoFormService } from './manager-info-form.service';

import { ManagerInfoUpdateComponent } from './manager-info-update.component';

describe('ManagerInfo Management Update Component', () => {
  let comp: ManagerInfoUpdateComponent;
  let fixture: ComponentFixture<ManagerInfoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let managerInfoFormService: ManagerInfoFormService;
  let managerInfoService: ManagerInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ManagerInfoUpdateComponent],
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
      .overrideTemplate(ManagerInfoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ManagerInfoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    managerInfoFormService = TestBed.inject(ManagerInfoFormService);
    managerInfoService = TestBed.inject(ManagerInfoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should update editForm', () => {
      const managerInfo: IManagerInfo = { id: 23884 };

      activatedRoute.data = of({ managerInfo });
      comp.ngOnInit();

      expect(comp.managerInfo).toEqual(managerInfo);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IManagerInfo>>();
      const managerInfo = { id: 26966 };
      jest.spyOn(managerInfoFormService, 'getManagerInfo').mockReturnValue(managerInfo);
      jest.spyOn(managerInfoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ managerInfo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: managerInfo }));
      saveSubject.complete();

      // THEN
      expect(managerInfoFormService.getManagerInfo).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(managerInfoService.update).toHaveBeenCalledWith(expect.objectContaining(managerInfo));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IManagerInfo>>();
      const managerInfo = { id: 26966 };
      jest.spyOn(managerInfoFormService, 'getManagerInfo').mockReturnValue({ id: null });
      jest.spyOn(managerInfoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ managerInfo: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: managerInfo }));
      saveSubject.complete();

      // THEN
      expect(managerInfoFormService.getManagerInfo).toHaveBeenCalled();
      expect(managerInfoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IManagerInfo>>();
      const managerInfo = { id: 26966 };
      jest.spyOn(managerInfoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ managerInfo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(managerInfoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
