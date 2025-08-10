import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { ConflictInterestService } from '../service/conflict-interest.service';
import { IConflictInterest } from '../conflict-interest.model';
import { ConflictInterestFormService } from './conflict-interest-form.service';

import { ConflictInterestUpdateComponent } from './conflict-interest-update.component';

describe('ConflictInterest Management Update Component', () => {
  let comp: ConflictInterestUpdateComponent;
  let fixture: ComponentFixture<ConflictInterestUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let conflictInterestFormService: ConflictInterestFormService;
  let conflictInterestService: ConflictInterestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ConflictInterestUpdateComponent],
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
      .overrideTemplate(ConflictInterestUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ConflictInterestUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    conflictInterestFormService = TestBed.inject(ConflictInterestFormService);
    conflictInterestService = TestBed.inject(ConflictInterestService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should update editForm', () => {
      const conflictInterest: IConflictInterest = { id: 16902 };

      activatedRoute.data = of({ conflictInterest });
      comp.ngOnInit();

      expect(comp.conflictInterest).toEqual(conflictInterest);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IConflictInterest>>();
      const conflictInterest = { id: 26216 };
      jest.spyOn(conflictInterestFormService, 'getConflictInterest').mockReturnValue(conflictInterest);
      jest.spyOn(conflictInterestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ conflictInterest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: conflictInterest }));
      saveSubject.complete();

      // THEN
      expect(conflictInterestFormService.getConflictInterest).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(conflictInterestService.update).toHaveBeenCalledWith(expect.objectContaining(conflictInterest));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IConflictInterest>>();
      const conflictInterest = { id: 26216 };
      jest.spyOn(conflictInterestFormService, 'getConflictInterest').mockReturnValue({ id: null });
      jest.spyOn(conflictInterestService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ conflictInterest: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: conflictInterest }));
      saveSubject.complete();

      // THEN
      expect(conflictInterestFormService.getConflictInterest).toHaveBeenCalled();
      expect(conflictInterestService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IConflictInterest>>();
      const conflictInterest = { id: 26216 };
      jest.spyOn(conflictInterestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ conflictInterest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(conflictInterestService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
