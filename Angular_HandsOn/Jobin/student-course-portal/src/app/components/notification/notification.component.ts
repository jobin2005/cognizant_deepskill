import { Component } from '@angular/core';

import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification',
  imports: [],
  providers: [NotificationService], // Creates a new, separate instance scoped to this component and its children
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
})
export class NotificationComponent {
  // Injecting NotificationService accesses the component-scoped instance defined in the providers array above
  constructor(public notificationService: NotificationService) {}
}
