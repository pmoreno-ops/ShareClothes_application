import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonContent,
  IonButton,
  IonIcon,
  IonInput,
  IonItem,
  IonLabel
} from '@ionic/angular/standalone';
import { Router } from '@angular/router';
import { ApiFooterNavBarComponent } from '../../components/api-footer-nav-bar/api-footer-nav-bar.component';
import { RopaService } from '../../Servicio/ropa-service';

@Component({
  selector: 'app-product-detail',
  standalone: true,
  templateUrl: './product-detail.page.html',
  styleUrls: ['./product-detail.page.scss'],
  imports: [CommonModule, FormsModule, IonContent, IonButton, IonIcon, IonInput, IonItem, IonLabel, ApiFooterNavBarComponent]
})
export class ProductDetailPage implements OnInit {
  private router = inject(Router);
  private productService = inject(RopaService);

  product: any;
  isFavorite = false;

  ngOnInit() {
    const state = history.state;
    if (state && state.product) {
      this.product = state.product;
    } else {
      this.product = {
        titulo: 'Pantalon Japan',
        descripcion: '',
        categoria: '',
        genero: '',
        estado: '',
        marca: '',
        talla: '',
        color: '',
        user_id: 1
      };
    }
  }

  toggleFavorite() {
    this.isFavorite = !this.isFavorite;
  }

  updateProduct() {
    if (!this.product.id) {
      console.error('No se puede actualizar: producto sin ID');
      return;
    }

    this.productService.actualizarRopa(this.product.id, this.product).subscribe({
      next: (res) => {
        console.log('Producto actualizado:', res);
        this.router.navigate(['/wardrobe'], { state: { refresh: true } });
      },
      error: (err) => console.error('Error al actualizar producto:', err)
    });
  }
}
