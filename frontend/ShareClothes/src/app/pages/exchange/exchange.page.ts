import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonBackButton,
  IonButton,
  IonButtons,
  IonContent,
  IonHeader, IonIcon,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import {ApiFooterNavBarComponent} from "../../components/api-footer-nav-bar/api-footer-nav-bar.component";
import {Router} from "@angular/router";


interface ExchangeItems {
  name: string;
  image: string;
}
@Component({
  selector: 'app-exchange',
  templateUrl: './exchange.page.html',
  styleUrls: ['./exchange.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, ApiFooterNavBarComponent, IonBackButton, IonButton, IonButtons, IonIcon]
})
export class ExchangePage implements OnInit {

  exchangeItems: ExchangeItems[] = [
    { name: 'Zapatillas Nike Airmax', image: 'assets/img/airmax_grey.webp' },
    { name: 'Zapatillas Nike Airmax Red&Black', image: 'assets/img/airmax_red.png' },
    { name: 'Camisa Patrón Floral', image: 'assets/img/camisa_patron.jpg' },
    { name: 'Chandal Nike Black&White', image: 'assets/img/chandal_nike_negro_blanco.jpg' },
    { name: 'Falda PunkHazard Black&Red', image: 'assets/img/falda_punk.webp' },
  ];

  constructor(private router: Router) { }

  ngOnInit() { }

  // Se dispara al hacer click en una tarjeta
  onItemClick(item: ExchangeItems) {
    console.log('Tarjeta clicada:', item);
    this.router.navigate(['/product-detail'], { state: { product: item } });
  }
}
