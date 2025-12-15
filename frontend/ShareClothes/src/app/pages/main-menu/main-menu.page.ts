import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonContent,
  IonSegment,
  IonSegmentButton,
  IonLabel,
  IonCol,
  IonRow,
  IonGrid, IonIcon,
} from '@ionic/angular/standalone';
import { Router } from '@angular/router'; //
import { ApiFooterNavBarComponent } from '../../components/api-footer-nav-bar/api-footer-nav-bar.component';
import { ApiHeaderNavBarComponent } from '../../components/api-header-nav-bar/api-header-nav-bar.component';

@Component({
  selector: 'app-main-menu',
  standalone: true,
  templateUrl: './main-menu.page.html',
  styleUrls: ['./main-menu.page.scss'],
  imports: [
    CommonModule,
    FormsModule,
    IonContent,
    IonSegment,
    IonSegmentButton,
    IonLabel,
    IonCol,
    IonRow,
    IonGrid,
    ApiFooterNavBarComponent,
    ApiHeaderNavBarComponent,
    IonIcon
  ]
})
export class MainMenuPage {
  constructor(private router: Router) {
    // Al cargar la página, recuperar los likes guardados
    const savedLikes = localStorage.getItem('likedItems');
    if (savedLikes) {
      const likedItems = JSON.parse(savedLikes);
      this.items.forEach(item => {
        // Si el item existe en likedItems, marcar liked=true
        item.liked = likedItems.some((liked: any) => liked.title === item.title);
      });
    }
  }


  //Busqueda por defecto
  selectedCategory: string = 'mujer';

  // Lista completa de productos con categoría
  items = [
    { title: 'Chandal Easy Azul/Negro', img: 'assets/img/ropa1.jpg', time: '2 días', category: 'hombre',liked: false },
    { title: 'Camiseta Forgotten Canela/Blanco', img: 'assets/img/ropa2.jpg', time: '6 días', category: 'hombre',liked: false },
    { title: 'Camiseta y Pantalon StayTrue Canela', img: 'assets/img/ropa3.jpg', time: '2 hrs', category: 'hombre',liked: false },
    { title: 'Pack Brooklyn G`s', img: 'assets/img/ropa4.jpg', time: '5 días', category: 'hombre',liked: false },
    { title: 'Chandal Nike', img: 'assets/img/nike1.jpg', time: '1 día', category: 'hombre',liked: false },
    { title: 'Chandal Adidas', img: 'assets/img/chandal_adidas_negro.webp', time: '8 días', category: 'hombre',liked: false },
    { title: 'Camisa Patron Oscuro Floral', img: 'assets/img/camisa_patron.jpg', time: '3 días', category: 'hombre',liked: false },
    { title: 'Camisa Floral', img: 'assets/img/camisa_patron2.jpg', time: '24 hrs', category: 'hombre',liked: false },
    { title: 'Chandal Nike Negro/Blanco', img: 'assets/img/chandal_nike_negro_blanco.jpg', time: '18 hrs', category: 'hombre',liked: false },
    { title: 'Leggings Verde Cammo', img: 'assets/img/ropa6.webp', time: '2 días', category: 'mujer',liked: false },
    { title: 'Pack Camiseta+Falda+Bolso', img: 'assets/img/pack_camiseta_falda.jpg', time: '5 días', category: 'mujer',liked: false },
    { title: 'Pack Top + Pantalon 3 Colores', img: 'assets/img/Top+Pantalon.jpg', time: '3 días', category: 'mujer',liked: false },
    { title: 'Pack Brooklyn Sweet G`s', img: 'assets/img/chandal_completo Brooklyn G´s.jpg', time: '1 días', category: 'mujer',liked: false },
    { title: 'Pantalon Vaquero', img: 'assets/img/pantalonB.jpg', time: '15 días', category: 'mujer',liked: false },
    { title: 'Camiseta Lady', img: 'assets/img/camiseta_mujer.jpeg', time: '24 hrs', category: 'mujer',liked: false },
    { title: 'Pack LA Classick', img: 'assets/img/LA_pack.jpg', time: '12 hrs', category: 'mujer',liked: false },
    { title: 'Falda Roja/Negra Cuadros', img: 'assets/img/falda_cuadros.jpg', time: '22 hrs', category: 'mujer',liked: false },
    { title: 'Vestido Verde Floral', img: 'assets/img/vestido_verde_floral.jpeg', time: '4 hrs', category: 'mujer',liked: false },
    { title: 'Pack Flora', img: 'assets/img/pack_flora.jpg', time: '1.5 hrs', category: 'mujer',liked: false },
    { title: 'Pack Astronauta', img: 'assets/img/pack_astronaut_niños.jpg', time: '5 días', category: 'niños',liked: false },
    { title: 'Pack Future', img: 'assets/img/pack_future_niños.jpg', time: '2 hrs', category: 'niños',liked: false },
    { title: 'Pack Little Lion', img: 'assets/img/pack_littlelion_niños.jpg', time: '10 hrs', category: 'niños',liked: false },
    { title: 'Pack SweetHeart', img: 'assets/img/pack_sweetheart_niños.jpg', time: '8 días', category: 'niños',liked: false }

  ];

  // Devuelve solo los productos de la categoría seleccionada
  get filteredItems() {
    return this.items.filter(item => item.category === this.selectedCategory);
  }

  // Cambia la categoría seleccionada
  onSegmentChanged(event: any) {
    this.selectedCategory = event.detail.value;
  }

// Función para los "me gusta"
  toggleLike(item: any) {
    // Cambia el estado liked
    item.liked = !item.liked;
    // Actualiza el array de liked items
    const likedItems = this.items.filter(i => i.liked);
    // Guardar solo los productos marcados como liked en localStorage
    localStorage.setItem('likedItems', JSON.stringify(likedItems));
  }




  //Redirige a la página de detalle con los datos del producto
  goToProductDetail(item: any) {
    this.router.navigate(['/product-detail'], { state: { product: item } });
  }
}
