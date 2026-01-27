import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {
  IonContent,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonButtons,
  IonButton,
  IonIcon,
  IonBackButton
} from "@ionic/angular/standalone";
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-mensajes',
  templateUrl: './messages.page.html',
  styleUrls: ['./messages.page.scss'],
  standalone: true,
  imports: [
    IonContent,
    IonHeader,
    IonToolbar,
    IonTitle,
    NgFor,
    IonButtons,
    IonButton,
    IonIcon,
    IonBackButton
  ]
})
export class MessagesPage {

  constructor(private router: Router) {}

  chats = [
    { id: 1, nombre: "Julia Ahki", mensaje: "¿Llegaste bien? Te gusto el pantalón??", hora: "9:32", noLeidos: 2, imagen: "assets/img/perfil-usuario.jpg" },
    { id: 2, nombre: "Carlos Ruiz", mensaje: "Killoo lo tiene o no??", hora: "9:10", noLeidos: 1, imagen: "assets/img/usuario2.webp" },
    { id: 3, nombre: "Sofía Pérez", mensaje: "Vienes?..😏🥵", hora: "8:50", noLeidos: 0, imagen: "assets/img/usuario5.jpg" },
    { id: 4, nombre: "María Rodríguez Garrido", mensaje: "No tardees te estoy esperandoo 👀👀", hora: "8:05", noLeidos: 4, imagen: "assets/img/usuario7.webp" },
    { id: 5, nombre: "Miguel Hernandez", mensaje: "Trae el altavoz compii", hora: "8:05", noLeidos: 4, imagen: "assets/img/usuario6.jpg" },
    { id: 6, nombre: "Sean Diddy Combs", mensaje: "My party is approaching and you can't miss it! ", hora: "8:05", noLeidos: 4, imagen: "assets/img/usuario8.jpg" }
  ];

  abrirChat(id: number) {
    this.router.navigate(['/chat']);
  }

}
