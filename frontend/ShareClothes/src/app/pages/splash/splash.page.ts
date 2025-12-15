import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {IonicModule} from "@ionic/angular";

@Component({
  selector: 'app-splash',
  templateUrl: './splash.page.html',
  styleUrls: ['./splash.page.scss'],
  imports: [
    IonicModule
  ]
})
export class SplashPage implements OnInit {

  constructor(private router: Router) {}

  ngOnInit() {
    // Espera 3 segundos y luego redirige a la página principal (main-menu)
    setTimeout(() => {
      this.router.navigateByUrl('/login', { replaceUrl: true });
    }, 3000);
  }

}
