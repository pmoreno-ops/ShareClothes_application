import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';
import { ApiHeaderNavBarComponent } from './api-header-nav-bar.component';

describe('ApiHeaderNavBarComponent', () => {
  let component: ApiHeaderNavBarComponent;
  let fixture: ComponentFixture<ApiHeaderNavBarComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [
        IonicModule.forRoot(),
        ApiHeaderNavBarComponent
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ApiHeaderNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
