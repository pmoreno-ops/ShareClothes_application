import { Component, OnInit } from '@angular/core';
import {
  IonButton,
  IonButtons,
  IonHeader,
  IonIcon,
  IonSearchbar,
  IonToolbar
} from '@ionic/angular/standalone';
import { Router } from '@angular/router';

@Component({
  selector: 'app-api-header-nav-bar',
  standalone: true,
  templateUrl: './api-header-nav-bar.component.html',
  styleUrls: ['./api-header-nav-bar.component.scss'],
  imports: [
    IonHeader,
    IonToolbar,
    IonButtons,
    IonIcon,
    IonSearchbar,
    IonButton
  ]
})
export class ApiHeaderNavBarComponent implements OnInit {

  constructor(private router: Router) {}

  ngOnInit() {}

  goToProfile() {
    console.log("Entro")
    this.router.navigate(['/edit-profile']);
  }
}
