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

interface WardrobeItem {
  name: string;
  image: string;
}

@Component({
  selector: 'app-wardrobe',
  templateUrl: './wardrobe.page.html',
  styleUrls: ['./wardrobe.page.scss'],
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
export class WardrobePage implements OnInit {

  wardrobeItems: WardrobeItem[] = [
    { name: 'Chaqueta Vintage', image: 'assets/img/chaqueta_Vintage.jpg' },
    { name: 'Uptempo', image: 'assets/img/uptempo_1.jpg' },
    { name: 'Camiseta Grizzlies', image: 'assets/img/cami_grizzlies.jpg' },
    { name: 'Pantalón Kaki', image: 'assets/img/pantalon_Kaki.jpeg' },
    { name: 'Gorra LA', image: 'assets/img/gorraLA.jpg' },
  ];

  constructor(private router: Router) { }

  ngOnInit() { }

  // Se dispara al hacer click en una tarjeta
  onItemClick(item: WardrobeItem) {
    console.log('Tarjeta clicada:', item);
    this.router.navigate(['/product-detail'], { state: { product: item } });
  }
}
