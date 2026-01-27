import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import {
  IonContent,
  IonItem,
  IonLabel,
  IonInput,
  IonCheckbox,
  IonButton
} from '@ionic/angular/standalone';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    IonContent,
    IonItem,
    IonLabel,
    IonInput,
    IonCheckbox,
    IonButton
  ],
})
export class LoginPage {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      rememberMe: [false],
    });
  }

  /**
   * Maneja el envío del formulario de inicio de sesión.
   * Si el formulario es válido, navega al menú principal.
   */
  onLogin() {
    if (this.loginForm.valid) {
      console.log('Login successful:', this.loginForm.value);
      // *** ESTA LÍNEA REALIZA LA REDIRECCIÓN SOLICITADA ***
      this.router.navigateByUrl('/main-menu');
    } else {
      // Marca todos los campos como tocados para mostrar errores de validación
      this.loginForm.markAllAsTouched();
    }
  }

  /**
   * Navega a la página de registro.
   */
  goToRegister() {
    this.router.navigateByUrl('/register');
  }
}
