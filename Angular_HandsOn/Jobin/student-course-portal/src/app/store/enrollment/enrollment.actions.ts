import { createAction, props } from '@ngrx/store';

export const enrollCourse = createAction(
  '[Enrollment] Enroll Course',
  props<{ courseId: number }>()
);

export const unenrollCourse = createAction(
  '[Enrollment] Unenroll Course',
  props<{ courseId: number }>()
);
