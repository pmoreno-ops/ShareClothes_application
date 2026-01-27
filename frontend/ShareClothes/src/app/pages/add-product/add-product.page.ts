import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { RopaService } from '../../Servicio/ropa-service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonButton, IonInput, IonItem, IonLabel } from '@ionic/angular/standalone';
import { ApiFooterNavBarComponent } from '../../components/api-footer-nav-bar/api-footer-nav-bar.component';

@Component({
  selector: 'app-add-product',
  standalone: true,
  templateUrl: './add-product.page.html',
  styleUrls: ['./add-product.page.scss'],
  imports: [CommonModule, FormsModule, IonContent, IonButton, IonInput, IonItem, IonLabel, ApiFooterNavBarComponent]
})
export class AddProductPage {
  private ropaService = inject(RopaService);
  private router = inject(Router);

  product = {
    titulo: '',
    descripcion: '',
    categoria: '',
    marca: '',
    imagenPrincipal: '',
    talla: ''
  };

  crearProducto() {
    this.ropaService.crearRopa(this.product).subscribe({
      next: () => this.router.navigate(['/wardrobe']).then(() => {}),
      error: (err) => console.error('Error al crear producto:', err)
    });
  }

  cancelar() {
    this.router.navigate(['/wardrobe']).then(() => {});
  }
}
