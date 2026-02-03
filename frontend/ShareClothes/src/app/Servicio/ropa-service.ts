// src/app/Servicio/ropa-service.ts
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Producto {
  producto_id?: number;
  titulo: string;
  descripcion?: string;
  categoria?: string;
  genero?: string;
  estado?: string;
  marca?: string;
  talla?: string;
  color?: string;
  user_id?: number;
}

@Injectable({
  providedIn: 'root'
})
export class RopaService {
  private http = inject(HttpClient);
  private apiUrl = 'https://shareclothes-application.onrender.com';

  crearRopa(product: Producto): Observable<any> {
    return this.http.post(this.apiUrl, product);
  }

  consultarRopa(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.apiUrl);
  }

  actualizarRopa(id: number, ropa: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/productos/${id}`, ropa);
  }

  eliminarRopa(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/productos/${id}`);
  }
}
