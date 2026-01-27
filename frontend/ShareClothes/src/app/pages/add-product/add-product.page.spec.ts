import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { AddProductPage } from './add-product.page';

describe('AddProductPageComponent', () => {
  let component: AddProductPage;
  let fixture: ComponentFixture<AddProductPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [AddProductPage],
    }).compileComponents();

    fixture = TestBed.createComponent(AddProductPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
