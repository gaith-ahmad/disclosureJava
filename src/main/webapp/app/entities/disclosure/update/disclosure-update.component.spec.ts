import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IGift } from 'app/entities/gift/gift.model';
import { GiftService } from 'app/entities/gift/service/gift.service';
import { IConflictInterest } from 'app/entities/conflict-interest/conflict-interest.model';
import { ConflictInterestService } from 'app/entities/conflict-interest/service/conflict-interest.service';
import { IDisclosure } from '../disclosure.model';
import { DisclosureService } from '../service/disclosure.service';
import { DisclosureFormService } from './disclosure-form.service';

import { DisclosureUpdateComponent } from './disclosure-update.component';

describe('Disclosure Management Update Component', () => {
  let comp: DisclosureUpdateComponent;
  let fixture: ComponentFixture<DisclosureUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let disclosureFormService: DisclosureFormService;
  let disclosureService: DisclosureService;
  let giftService: GiftService;
  let conflictInterestService: ConflictInterestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [DisclosureUpdateComponent],
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
      .overrideTemplate(DisclosureUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DisclosureUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    disclosureFormService = TestBed.inject(DisclosureFormService);
    disclosureService = TestBed.inject(DisclosureService);
    giftService = TestBed.inject(GiftService);
    conflictInterestService = TestBed.inject(ConflictInterestService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call gift query and add missing value', () => {
      const disclosure: IDisclosure = { id: 26183 };
      const gift: IGift = { id: 17243 };
      disclosure.gift = gift;

      const giftCollection: IGift[] = [{ id: 17243 }];
      jest.spyOn(giftService, 'query').mockReturnValue(of(new HttpResponse({ body: giftCollection })));
      const expectedCollection: IGift[] = [gift, ...giftCollection];
      jest.spyOn(giftService, 'addGiftToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ disclosure });
      comp.ngOnInit();

      expect(giftService.query).toHaveBeenCalled();
      expect(giftService.addGiftToCollectionIfMissing).toHaveBeenCalledWith(giftCollection, gift);
      expect(comp.giftsCollection).toEqual(expectedCollection);
    });

    it('should call conflictInterest query and add missing value', () => {
      const disclosure: IDisclosure = { id: 26183 };
      const conflictInterest: IConflictInterest = { id: 26216 };
      disclosure.conflictInterest = conflictInterest;

      const conflictInterestCollection: IConflictInterest[] = [{ id: 26216 }];
      jest.spyOn(conflictInterestService, 'query').mockReturnValue(of(new HttpResponse({ body: conflictInterestCollection })));
      const expectedCollection: IConflictInterest[] = [conflictInterest, ...conflictInterestCollection];
      jest.spyOn(conflictInterestService, 'addConflictInterestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ disclosure });
      comp.ngOnInit();

      expect(conflictInterestService.query).toHaveBeenCalled();
      expect(conflictInterestService.addConflictInterestToCollectionIfMissing).toHaveBeenCalledWith(
        conflictInterestCollection,
        conflictInterest,
      );
      expect(comp.conflictInterestsCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const disclosure: IDisclosure = { id: 26183 };
      const gift: IGift = { id: 17243 };
      disclosure.gift = gift;
      const conflictInterest: IConflictInterest = { id: 26216 };
      disclosure.conflictInterest = conflictInterest;

      activatedRoute.data = of({ disclosure });
      comp.ngOnInit();

      expect(comp.giftsCollection).toContainEqual(gift);
      expect(comp.conflictInterestsCollection).toContainEqual(conflictInterest);
      expect(comp.disclosure).toEqual(disclosure);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisclosure>>();
      const disclosure = { id: 10911 };
      jest.spyOn(disclosureFormService, 'getDisclosure').mockReturnValue(disclosure);
      jest.spyOn(disclosureService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ disclosure });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: disclosure }));
      saveSubject.complete();

      // THEN
      expect(disclosureFormService.getDisclosure).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(disclosureService.update).toHaveBeenCalledWith(expect.objectContaining(disclosure));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisclosure>>();
      const disclosure = { id: 10911 };
      jest.spyOn(disclosureFormService, 'getDisclosure').mockReturnValue({ id: null });
      jest.spyOn(disclosureService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ disclosure: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: disclosure }));
      saveSubject.complete();

      // THEN
      expect(disclosureFormService.getDisclosure).toHaveBeenCalled();
      expect(disclosureService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisclosure>>();
      const disclosure = { id: 10911 };
      jest.spyOn(disclosureService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ disclosure });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(disclosureService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareGift', () => {
      it('should forward to giftService', () => {
        const entity = { id: 17243 };
        const entity2 = { id: 8361 };
        jest.spyOn(giftService, 'compareGift');
        comp.compareGift(entity, entity2);
        expect(giftService.compareGift).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareConflictInterest', () => {
      it('should forward to conflictInterestService', () => {
        const entity = { id: 26216 };
        const entity2 = { id: 16902 };
        jest.spyOn(conflictInterestService, 'compareConflictInterest');
        comp.compareConflictInterest(entity, entity2);
        expect(conflictInterestService.compareConflictInterest).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
