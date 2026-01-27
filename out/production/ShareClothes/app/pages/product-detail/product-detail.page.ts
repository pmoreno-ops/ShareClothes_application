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
  templateUrl: './product-detail.page.html',
  styleUrls: ['./product-detail.page.scss'],
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
      if (!this.product.sellerId) {
        this.product.sellerId = 'julia-ahki-123';
      }
    } else {
      this.product = {
        title: 'Pantalon Japan',
        img: 'assets/img/pantalon-japan.jpg',
        time: '3h',
        category: 'Moda',
        location: 'Sevilla, España',
        size: 'Talla L',
        sellerId: 'julia-ahki-123'
      };
    }
      // Recupera los datos enviados desde wardrobe
      const state = history.state;
      if (state && state.product) {
        this.product = state.product;
        console.log('Producto recibido:', this.product);
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


}
