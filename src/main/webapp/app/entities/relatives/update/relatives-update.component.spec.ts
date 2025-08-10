import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IDisclosure } from 'app/entities/disclosure/disclosure.model';
import { DisclosureService } from 'app/entities/disclosure/service/disclosure.service';
import { RelativesService } from '../service/relatives.service';
import { IRelatives } from '../relatives.model';
import { RelativesFormService } from './relatives-form.service';

import { RelativesUpdateComponent } from './relatives-update.component';

describe('Relatives Management Update Component', () => {
  let comp: RelativesUpdateComponent;
  let fixture: ComponentFixture<RelativesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let relativesFormService: RelativesFormService;
  let relativesService: RelativesService;
  let disclosureService: DisclosureService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RelativesUpdateComponent],
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
      .overrideTemplate(RelativesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RelativesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    relativesFormService = TestBed.inject(RelativesFormService);
    relativesService = TestBed.inject(RelativesService);
    disclosureService = TestBed.inject(DisclosureService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call Disclosure query and add missing value', () => {
      const relatives: IRelatives = { id: 23212 };
      const disclosure: IDisclosure = { id: 10911 };
      relatives.disclosure = disclosure;

      const disclosureCollection: IDisclosure[] = [{ id: 10911 }];
      jest.spyOn(disclosureService, 'query').mockReturnValue(of(new HttpResponse({ body: disclosureCollection })));
      const additionalDisclosures = [disclosure];
      const expectedCollection: IDisclosure[] = [...additionalDisclosures, ...disclosureCollection];
      jest.spyOn(disclosureService, 'addDisclosureToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ relatives });
      comp.ngOnInit();

      expect(disclosureService.query).toHaveBeenCalled();
      expect(disclosureService.addDisclosureToCollectionIfMissing).toHaveBeenCalledWith(
        disclosureCollection,
        ...additionalDisclosures.map(expect.objectContaining),
      );
      expect(comp.disclosuresSharedCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const relatives: IRelatives = { id: 23212 };
      const disclosure: IDisclosure = { id: 10911 };
      relatives.disclosure = disclosure;

      activatedRoute.data = of({ relatives });
      comp.ngOnInit();

      expect(comp.disclosuresSharedCollection).toContainEqual(disclosure);
      expect(comp.relatives).toEqual(relatives);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRelatives>>();
      const relatives = { id: 22367 };
      jest.spyOn(relativesFormService, 'getRelatives').mockReturnValue(relatives);
      jest.spyOn(relativesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ relatives });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: relatives }));
      saveSubject.complete();

      // THEN
      expect(relativesFormService.getRelatives).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(relativesService.update).toHaveBeenCalledWith(expect.objectContaining(relatives));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRelatives>>();
      const relatives = { id: 22367 };
      jest.spyOn(relativesFormService, 'getRelatives').mockReturnValue({ id: null });
      jest.spyOn(relativesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ relatives: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: relatives }));
      saveSubject.complete();

      // THEN
      expect(relativesFormService.getRelatives).toHaveBeenCalled();
      expect(relativesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRelatives>>();
      const relatives = { id: 22367 };
      jest.spyOn(relativesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ relatives });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(relativesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareDisclosure', () => {
      it('should forward to disclosureService', () => {
        const entity = { id: 10911 };
        const entity2 = { id: 26183 };
        jest.spyOn(disclosureService, 'compareDisclosure');
        comp.compareDisclosure(entity, entity2);
        expect(disclosureService.compareDisclosure).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
