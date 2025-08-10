import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { GiftService } from '../service/gift.service';
import { IGift } from '../gift.model';
import { GiftFormService } from './gift-form.service';

import { GiftUpdateComponent } from './gift-update.component';

describe('Gift Management Update Component', () => {
  let comp: GiftUpdateComponent;
  let fixture: ComponentFixture<GiftUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let giftFormService: GiftFormService;
  let giftService: GiftService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [GiftUpdateComponent],
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
      .overrideTemplate(GiftUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(GiftUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    giftFormService = TestBed.inject(GiftFormService);
    giftService = TestBed.inject(GiftService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should update editForm', () => {
      const gift: IGift = { id: 8361 };

      activatedRoute.data = of({ gift });
      comp.ngOnInit();

      expect(comp.gift).toEqual(gift);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGift>>();
      const gift = { id: 17243 };
      jest.spyOn(giftFormService, 'getGift').mockReturnValue(gift);
      jest.spyOn(giftService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ gift });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: gift }));
      saveSubject.complete();

      // THEN
      expect(giftFormService.getGift).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(giftService.update).toHaveBeenCalledWith(expect.objectContaining(gift));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGift>>();
      const gift = { id: 17243 };
      jest.spyOn(giftFormService, 'getGift').mockReturnValue({ id: null });
      jest.spyOn(giftService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ gift: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: gift }));
      saveSubject.complete();

      // THEN
      expect(giftFormService.getGift).toHaveBeenCalled();
      expect(giftService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGift>>();
      const gift = { id: 17243 };
      jest.spyOn(giftService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ gift });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(giftService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
