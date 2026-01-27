import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  IonContent,
  IonButton,
  IonIcon,
  IonChip,
  IonLabel,
  IonAvatar,
  IonItem,
  IonList,
  IonThumbnail,
  IonCard
} from '@ionic/angular/standalone';
import { Router } from '@angular/router';
import { ApiFooterNavBarComponent } from '../../components/api-footer-nav-bar/api-footer-nav-bar.component';

@Component({
  selector: 'app-product-detail',
  standalone: true,
  templateUrl: './product-detail2.page.html',
  styleUrls: ['./product-detail2.page.scss'],
  imports: [
    CommonModule,
    IonContent,
    IonButton,
    IonIcon,
    IonChip,
    IonLabel,
    IonAvatar,
    IonItem,
    IonList,
    IonThumbnail,
    IonCard,
    ApiFooterNavBarComponent
  ]
})
export class ProductDetailPage implements OnInit {
  product: any;
  isFavorite = false;

  constructor(private router: Router) {}

  ngOnInit() {
    const nav = this.router.getCurrentNavigation();
    if (nav?.extras?.state && nav.extras.state['product']) {
      this.product = nav.extras.state['product'];
      this.product.currentImage = 0; // inicializamos carrusel
      // Ejemplo de productos relacionados
      this.product.relatedItems = [
        { title: 'Vestido Verde', img: 'assets/img/vestido_verde.webp', priceText: 'Intercambio o alquiler · 12,00€' },
        { title: 'Camiseta M&M', img: 'assets/img/camiseta_m&m.png', priceText: 'Intercambio o alquiler · 27,50€' }
      ];
    }
  }

  toggleFavorite() {
    this.isFavorite = !this.isFavorite;
  }

  addToCart(product: any) {
    console.log('Producto añadido al carrito:', product);
  }

  goToUserProfile() {
    this.router.navigate(['/user-profile']);
  }

  nextImage() {
    if (!this.product.imgs || this.product.imgs.length <= 1) return;
    this.product.currentImage = (this.product.currentImage + 1) % this.product.imgs.length;
  }

  prevImage() {
    if (!this.product.imgs || this.product.imgs.length <= 1) return;
    this.product.currentImage = (this.product.currentImage - 1 + this.product.imgs.length) % this.product.imgs.length;
  }
}
