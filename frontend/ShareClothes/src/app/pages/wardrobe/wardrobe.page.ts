import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RopaService } from '../../Servicio/ropa-service';
import {
  IonContent, IonHeader, IonToolbar, IonTitle, IonButtons, IonButton, IonIcon,
  IonCard, IonCardHeader, IonCardTitle, IonCardContent, IonThumbnail, IonBackButton
} from '@ionic/angular/standalone';
import { ApiFooterNavBarComponent } from '../../components/api-footer-nav-bar/api-footer-nav-bar.component';

interface WardrobeItem {
  id: number;
  titulo: string;
  descripcion: string;
  categoria: string;
  marca: string;
  imagenPrincipal: string;
  talla?: string;
}

@Component({
  selector: 'app-wardrobe',
  standalone: true,
  templateUrl: './wardrobe.page.html',
  styleUrls: ['./wardrobe.page.scss'],
  imports: [
    CommonModule, FormsModule, IonContent, IonHeader, IonToolbar, IonTitle, IonButtons,
    IonButton, IonIcon, IonBackButton, IonCard, IonCardHeader, IonCardTitle, IonCardContent,
    IonThumbnail, ApiFooterNavBarComponent
  ]
})
export class WardrobePage implements OnInit {
  private ropaService = inject(RopaService);
  private router = inject(Router);

  wardrobeItems: WardrobeItem[] = [];

  ngOnInit() {
    this.cargarProductos();
  }

  cargarProductos() {
    this.ropaService.consultarRopa().subscribe({
      next: (res: any[]) => {
        this.wardrobeItems = res.map(item => ({
          id: item.id,
          titulo: item.titulo,
          descripcion: item.descripcion,
          categoria: item.categoria,
          marca: item.marca,
          imagenPrincipal: item.imagenPrincipal,
          talla: item.talla
        }));
        console.log('Productos cargados:', this.wardrobeItems);
      },
      error: (err) => console.error('Error al cargar productos:', err)
    });
  }

  onItemClick(item: WardrobeItem) {
    this.router.navigate(['/product-detail'], { state: { product: item } }).then(() => {});
  }
}
