import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  IonButton,
  IonContent,
  IonHeader, IonIcon,
  IonInput,
  IonItem,
  IonLabel,
  IonTitle,
  IonToolbar
} from "@ionic/angular/standalone";

@Component({
  selector: 'edit-data',
  templateUrl: './edit-data.component.html',
  styleUrls: ['./edit-data.component.scss'],
  standalone: true,
  imports: [
    IonContent,
    ReactiveFormsModule,
    FormsModule,
    IonIcon
  ]
})
export class EditDataComponent {

  form = {
    nombre: '',
    apellidos: '',
    correo: '',
    localizacion: '',
    imagen: ''
  };

  formUsuario: FormGroup;
  previewImage: string | ArrayBuffer | null = null;
  imagenArchivo: File | null = null;

  constructor(private fb: FormBuilder) {
    this.formUsuario = this.fb.group({
      nombre: ['', Validators.required],
      apellidos: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      localizacion: ['', Validators.required]
    });
  }

  cargarImagen(event: any) {
    const file = event.target.files[0];

    if (file) {
      this.imagenArchivo = file;

      const reader = new FileReader();
      reader.onload = () => {
        this.previewImage = reader.result;
        this.form.imagen = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  guardarUsuario() {
    if (this.formUsuario.valid) {
      const datos = {
        ...this.formUsuario.value,
        imagen: this.imagenArchivo
      };

      console.log("Datos del usuario:", datos);
    }
  }
}
