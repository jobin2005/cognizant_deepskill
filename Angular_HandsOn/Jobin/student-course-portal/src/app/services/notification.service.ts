import { Injectable } from '@angular/core';

@Injectable()
export class NotificationService {
  // Provided without 'root' so it can be scoped to a component
  private messages: string[] = [];

  addMessage(msg: string) {
    this.messages.push(msg);
  }

  getMessages() {
    return this.messages;
  }
}
