import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  IonAvatar,
  IonButton,
  IonCol,
  IonContent,
  IonGrid,
  IonIcon,
  IonImg,
  IonItem,
  IonLabel,
  IonList,
  IonListHeader,
  IonRow
} from "@ionic/angular/standalone";
import { Router } from '@angular/router';
import { ApiFooterNavBarComponent } from "../../components/api-footer-nav-bar/api-footer-nav-bar.component";

@Component({
  selector: 'app-user-profile',
  standalone: true,
  templateUrl: './user-profile.page.html',
  styleUrls: ['./user-profile.page.scss'],
  imports: [
    CommonModule,
    IonContent,
    IonAvatar,
    IonIcon,
    IonButton,
    IonGrid,
    IonRow,
    IonCol,
    IonImg,
    IonList,
    IonListHeader,
    IonLabel,
    IonItem,
    ApiFooterNavBarComponent
  ]
})
export class UserProfilePage implements OnInit {
  constructor(private router: Router) {}
  reviews = [
    {
      name: 'Maria Delgado',
      avatar: 'assets/img/usuario2.avif',
      excerpt: 'Es genial, ropa de calidad y trato excelente, repetiré con Julia.',
      time: 'Hace 3 min'
    },
    {
      name: 'Adela Garrido',
      avatar: 'assets/img/usuario3.jpg',
      excerpt: 'Buena calidad y atención familiar, totalmente recomendable.',
      time: 'Hace 1 semana'
    },
    {
      name: 'Esther Benítez',
      avatar: 'assets/img/usuario4.avif',
      excerpt: 'Encantada con el servicio, la ropa preciosa. Volveré sin duda.',
      time: 'Hace 2 semanas'
    }
  ];

  goToChat() {
    this.router.navigate(['/chat']);
  }

  ngOnInit() {}
}
