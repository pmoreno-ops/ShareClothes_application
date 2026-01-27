import { Component } from '@angular/core';
import { CommonModule, NgClass, NgIf, NgFor } from '@angular/common';

import {
  IonHeader,
  IonToolbar,
  IonButtons,
  IonBackButton,
  IonTitle,
  IonContent,
  IonButton,
  IonIcon,
  IonGrid,
  IonRow,
  IonCol
} from '@ionic/angular/standalone';

import { ApiFooterNavBarComponent } from 'src/app/components/api-footer-nav-bar/api-footer-nav-bar.component';
import {ApiHeaderNavBarComponent} from "../../components/api-header-nav-bar/api-header-nav-bar.component";
import {IonicModule} from "@ionic/angular";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  standalone: true,
  templateUrl: './edit-profile.page.html',
  styleUrls: ['./edit-profile.page.scss'],
  imports: [
    CommonModule,
    NgIf,
    NgFor,


    IonContent,
    IonButton,
    IonIcon,
    IonGrid,
    IonRow,
    IonCol,

    ApiFooterNavBarComponent,

  ]
})
export class ProfilePage {
  profile = {
    name: 'Natalia Gordillo',
    email: 'nataliagordillo@gmail.com',
    location: 'Madrid, Spain'
  };

  stats = {
    following: 25,
    likes: 18,
    items: 4
  };

  images: string[] = [
    'assets/img/chaqueta_Vintage.jpg',
    'assets/img/uptempo_1.jpg',
    'assets/img/cami_grizzlies.jpg',
    'assets/img/pantalon_Kaki.jpeg'
  ];

  constructor(private router: Router) {}

  editProfile() {
    console.log('Editar perfil');
    this.router.navigate(['/data-edit']);
  }

  goToWardrobe() {
    console.log('Ir al Guardarropa');
    this.router.navigate(['/wardrobe']);
  }

  goToExchange(){
    console.log('Ir a Intercambios');
    this.router.navigate(['/exchange'])
  }

  addImage() {
    console.log('Añadir nueva imagen');
  }

  openImage(index: number) {
    console.log('Abriendo imagen', index);
  }

  onImageClick(i: number) {
    this.openImage(i);
  }
}
