import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';
import {IonIcon} from "@ionic/angular/standalone";

import { ApiFooterNavBarComponent } from './api-footer-nav-bar.component';

describe('ApiFooterNavBarComponent', () => {
  let component: ApiFooterNavBarComponent;
  let fixture: ComponentFixture<ApiFooterNavBarComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ApiFooterNavBarComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(ApiFooterNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
