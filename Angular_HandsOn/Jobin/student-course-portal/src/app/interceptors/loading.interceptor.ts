import { HttpInterceptorFn } from '@angular/common/http';
import { finalize, tap } from 'rxjs/operators';

export const loadingInterceptor: HttpInterceptorFn = (req, next) => {
  console.log('Loading interceptor: Spinner Show...');
  return next(req).pipe(
    tap(() => console.log('Request success')),
    finalize(() => console.log('Loading interceptor: Spinner Hide...'))
  );
};
