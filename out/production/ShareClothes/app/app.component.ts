import { Component, inject } from '@angular/core';
import { IonApp, IonRouterOutlet } from '@ionic/angular/standalone';
import { IonIcon } from '@ionic/angular/standalone';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  imports: [IonApp, IonRouterOutlet, IonIcon],
  standalone: true
})
export class AppComponent {
  // Inyectamos HttpClient usando la función inject()
  private http = inject(HttpClient);

  constructor() {
    this.testApi();
  }

  testApi() {
    this.http.get('/api/users').subscribe({
      next: (res) => console.log('Respuesta del backend:', res),
      error: (err) => console.error('Error al llamar al backend:', err)
    });
  }
}
