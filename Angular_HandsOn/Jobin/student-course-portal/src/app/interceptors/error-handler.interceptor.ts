import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { NotificationService } from '../services/notification.service';

export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {
  // Using inject to get access to NotificationService for error alerting
  // In a real app we'd map this globally, but for now we'll just log it.
  return next(req).pipe(
    catchError(err => {
      console.error('HTTP Error caught by interceptor:', err);
      // alert('HTTP Error! Check console.');
      return throwError(() => err);
    })
  );
};
