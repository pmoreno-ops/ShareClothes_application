import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { IonContent, IonicModule, NavController } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface ChatMessage {
  from: 'me' | 'other';
  type: 'text' | 'image';
  text: string;
  image?: string;
}

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule],
  templateUrl: './chat.page.html',
  styleUrls: ['./chat.page.scss'],
})
export class ChatPage implements AfterViewInit {
  @ViewChild(IonContent) content!: IonContent;

  newMessage = '';

  messages: ChatMessage[] = [
    { from: 'other', type: 'text', text: 'So excited!' },
    { from: 'other', type: 'text', text: 'Hi! My name is Julia, nice to meet you!!' },
    { from: 'other', type: 'text', text: 'Well, may we discuss about price or not? jajaja' },
    { from: 'other', type: 'image', text: 'Shall we make a deal!' },
    { from: 'me', type: 'text', text: 'Sounds good!' },
  ];

  constructor(private navCtrl: NavController) {}

  ngAfterViewInit() {
    // Scroll al final del chat al cargar
    setTimeout(() => this.scrollToBottom(), 200);
  }

  goBack() {
    this.navCtrl.navigateBack('/user-profile');
  }

  sendMessage() {
    const text = this.newMessage.trim();
    if (!text) return;

    // Agrega el mensaje del usuario
    this.messages.push({ from: 'me', type: 'text', text });
    this.newMessage = '';

    // Asegura el scroll al nuevo mensaje
    setTimeout(() => this.scrollToBottom(), 100);
  }

  private scrollToBottom() {
    this.content.scrollToBottom(300); // Scroll animado
  }
}
