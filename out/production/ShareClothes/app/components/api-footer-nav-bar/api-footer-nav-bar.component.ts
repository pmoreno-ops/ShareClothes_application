import { Component, OnInit } from '@angular/core';
import { IonFooter, IonIcon, IonLabel, IonTabBar, IonTabButton } from "@ionic/angular/standalone";
import { Router } from '@angular/router';

@Component({
  selector: 'app-api-footer-nav-bar',
  templateUrl: './api-footer-nav-bar.component.html',
  styleUrls: ['./api-footer-nav-bar.component.scss'],
  imports: [
    IonFooter,
    IonTabBar,
    IonTabButton,
    IonIcon,
    IonLabel
  ]
})
export class ApiFooterNavBarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {}

  goToMainMenu() { this.router.navigate(['/main-menu']); }
  goToStyleList() { this.router.navigate(['/style-list']); }
  goToAdd() { this.router.navigate(['/add']); }
  goToSaved() { this.router.navigate(['/saved']); }
  goToMessages() { this.router.navigate(['/messages']); }


}
