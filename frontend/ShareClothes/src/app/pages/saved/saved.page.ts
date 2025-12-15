import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonBackButton,
  IonButton,
  IonButtons,
  IonContent,
  IonHeader,
  IonIcon,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import { ApiFooterNavBarComponent } from "../../components/api-footer-nav-bar/api-footer-nav-bar.component";
import { Router } from "@angular/router";

interface SavedItems {
  id: number;
  name: string;
  img: string;
  liked?: boolean;
}

@Component({
  selector: 'app-saved',
  templateUrl: './saved.page.html',
  styleUrls: ['./saved.page.scss'],
  standalone: true,
  imports: [
    IonContent,
    IonHeader,
    IonTitle,
    IonToolbar,
    CommonModule,
    FormsModule,
    IonButtons,
    IonButton,
    IonIcon,
    ApiFooterNavBarComponent,
    IonBackButton
  ]
})
export class SavedPage implements OnInit {

  savedItems: SavedItems[] = [];

  constructor(private router: Router) { }

  ngOnInit() {
    // Cargar los productos marcados como liked desde localStorage al iniciar
    const saved = localStorage.getItem('likedItems');
    this.savedItems = saved ? JSON.parse(saved) : [];
  }

  // Se dispara al hacer click en una tarjeta
  onItemClick(item: SavedItems) {
    console.log('Tarjeta clicada:', item);
    this.router.navigate(['/product-detail'], { state: { product: item } });
  }

  // Función para desmarcar favorito desde Saved
  toggleLike(item: SavedItems) {
    item.liked = false; // quitar el liked
    // Guardar los restantes
    this.savedItems = this.savedItems.filter(i => i.liked !== false);
    localStorage.setItem('likedItems', JSON.stringify(this.savedItems));
  }
}

export class SavedComponent {
}
