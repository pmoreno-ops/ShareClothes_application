import { Routes } from '@angular/router';
import {ProfilePage} from "./pages/edit-profile/edit-profile.page";
import {EditDataComponent} from "./pages/edit-data/edit-data.component";

export const routes: Routes = [
  // Ruta por defecto: Splash
  {
    path: '',
    redirectTo: 'splash',
    pathMatch: 'full',
  },

  // Splash screen
  {
    path: 'splash',
    loadComponent: () =>
      import('./pages/splash/splash.page').then((m) => m.SplashPage),
  },

  // Autenticación
  {
    path: 'login',
    loadComponent: () =>
      import('./pages/login/login.page').then((m) => m.LoginPage),
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./pages/register/register.page').then((m) => m.RegisterPage),
  },

  // Menú principal
  {
    path: 'main-menu',
    loadComponent: () =>
      import('./pages/main-menu/main-menu.page').then((m) => m.MainMenuPage),
  },

  // Productos
  {
    path: 'product-detail',
    loadComponent: () =>
      import('./pages/product-detail/product-detail.page').then(
        (m) => m.ProductDetailPage
      ),
  },

  // Productos2
  {
    path: 'product-detail2',
    loadComponent: () =>
      import('./pages/product-detail2/product-detail2.page').then(
        (m) => m.ProductDetailPage
      ),
  },

  // Favoritos e intercambios
  {
    path: 'favourites',
    loadComponent: () =>
      import('./pages/favourites/favourites.page').then(
        (m) => m.FavouritesPage
      ),
  },
  {
    path: 'exchange',
    loadComponent: () =>
      import('./pages/exchange/exchange.page').then((m) => m.ExchangePage),
  },

  // Mensajes y chat
  {
    path: 'messages',
    loadComponent: () =>
      import('./pages/messages/messages.page').then((m) => m.MessagesPage),
  },
  {
    path: 'chat',
    loadComponent: () =>
      import('./pages/chat/chat.page').then((m) => m.ChatPage),
  },

  // Perfil de usuario
  {
    path: 'user-profile',
    loadComponent: () =>
      import('./pages/user-profile/user-profile.page').then(
        (m) => m.UserProfilePage
      ),
  },

  //Perfil personal del usuario
  {
    path: 'edit-profile',
    loadComponent: () =>
      import('./pages/edit-profile/edit-profile.page').then(m => m.ProfilePage)
  },

  //Editar Datos del Usuario
  {
    path: 'data-edit',
    loadComponent: () =>
      import('./pages/edit-data/edit-data.component').then(m => m.EditDataComponent)

  },

  // Lista de usuarios y guardarropa
  {
    path: 'style-list',
    loadComponent: () =>
      import('./pages/styles-list/style-list.page').then(
        (m) => m.StyleListPage
      ),
  },
  {
    path: 'wardrobe',
    loadComponent: () =>
      import('./pages/wardrobe/wardrobe.page').then((m) => m.WardrobePage),
  },

  //Guardados
  {
    path: 'saved',
    loadComponent: () =>
      import('./pages/saved/saved.page').then((m) => m.SavedPage),
  },

  {
    path: 'add',
    loadComponent: () =>
      import('./pages/add-product/add-product.page').then(
        (m) => m.AddProductPage
      ),
  },

];
