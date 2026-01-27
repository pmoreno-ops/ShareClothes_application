import { ComponentFixture, TestBed } from '@angular/core/testing';
import { StyleListPage } from './style-list.page';

describe('StyleListPage', () => {
  let component: StyleListPage;
  let fixture: ComponentFixture<StyleListPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(StyleListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
