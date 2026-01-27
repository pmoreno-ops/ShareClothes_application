import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonContent,
  IonCol,
  IonRow,
  IonGrid,
  IonIcon
} from '@ionic/angular/standalone';
import { Router } from '@angular/router';
import { ApiFooterNavBarComponent } from '../../components/api-footer-nav-bar/api-footer-nav-bar.component';
import { ApiHeaderNavBarComponent } from '../../components/api-header-nav-bar/api-header-nav-bar.component';

@Component({
  selector: 'app-style-list',
  standalone: true,
  templateUrl: './style-list.page.html',
  styleUrls: ['./style-list.page.scss'],
  imports: [
    CommonModule,
    FormsModule,
    IonContent,
    IonCol,
    IonRow,
    IonGrid,
    IonIcon,
    ApiFooterNavBarComponent,
    ApiHeaderNavBarComponent
  ]
})
export class StyleListPage {
  items = [
    {
      title: 'Rock/Metal Style',
      imgs: ['assets/img/chaquetaCueroMetalHombre.jpg','assets/img/chaquetaCueroMetalHombre2.jpg','assets/img/chaquetaCueroMetalMujer1.jpg','assets/img/chaquetaCueroMetalMujer2.jpg', 'assets/img/camisetaPunk1.jpg', 'assets/img/camisetaPunk2.jpg', 'assets/img/camisetaPunk3.jpg', 'assets/img/camisetaPunk4.jpg', 'assets/img/pantalonesPunkHombre1.jpg', 'assets/img/pantalonesPunkMujer1.jpg'],
      currentImage: 0,
      time: '2 días',
      liked: false
    },
    {
      title: 'Camiseta Forgotten Canela/Blanco',
      imgs: ['assets/img/ropa2.jpg','assets/img/ropa2-2.jpg'],
      currentImage: 0,
      time: '6 días',
      liked: false
    },
    {
      title: 'Leggings Verde Cammo',
      imgs: ['assets/img/ropa6.webp','assets/img/ropa6-2.webp'],
      currentImage: 0,
      time: '2 días',
      liked: false
    }
  ];

  constructor(private router: Router) {

    const savedLikes = localStorage.getItem('likedItems');
    if (savedLikes) {
      const likedItems = JSON.parse(savedLikes);
      this.items.forEach((item, i) => {
        if (likedItems[i]) item.liked = likedItems[i].liked;
      });
    }
  }

  // Cambiar imagen en carrusel
  nextImage(item: any, event: Event) {
    event.stopPropagation();
    item.currentImage = (item.currentImage + 1) % item.imgs.length;
  }

  prevImage(item: any, event: Event) {
    event.stopPropagation();
    item.currentImage = (item.currentImage - 1 + item.imgs.length) % item.imgs.length;
  }

  toggleLike(item: any, event: Event) {
    event.stopPropagation();
    item.liked = !item.liked;
    localStorage.setItem('likedItems', JSON.stringify(this.items));
  }


  // Abrir detalle del producto mostrando todas las imágenes
  openProductDetail(item: any) {
    this.router.navigate(['/product-detail2'], { state: { product: item } });
  }
}
