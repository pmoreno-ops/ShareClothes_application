import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginPage } from './login.page';
import { RouterTestingModule } from '@angular/router/testing';
import { ReactiveFormsModule } from '@angular/forms';
import {
  IonContent,
  IonItem,
  IonLabel,
  IonInput,
  IonCheckbox,
  IonButton
} from '@ionic/angular/standalone';

describe('LoginPage', () => {
  let component: LoginPage;
  let fixture: ComponentFixture<LoginPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        LoginPage,
        RouterTestingModule,
        ReactiveFormsModule,
        IonContent,
        IonItem,
        IonLabel,
        IonInput,
        IonCheckbox,
        IonButton,
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(LoginPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the login page', () => {
    expect(component).toBeTruthy();
  });

  it('should have invalid form when empty', () => {
    expect(component.loginForm.valid).toBeFalse();
  });

  it('should navigate to register page', () => {
    const spy = spyOn((component as any).router, 'navigateByUrl');
    component.goToRegister();
    expect(spy).toHaveBeenCalledWith('/register');
  });
});
