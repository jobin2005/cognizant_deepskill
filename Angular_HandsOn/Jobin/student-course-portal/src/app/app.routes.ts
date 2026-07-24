import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CourseListComponent } from './pages/course-list/course-list.component';
import { CourseDetailComponent } from './pages/course-detail/course-detail.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { StudentProfileComponent } from './pages/student-profile/student-profile.component';
import { authGuard } from './guards/auth.guard';
import { unsavedChangesGuard } from './guards/unsaved-changes.guard';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'courses', component: CourseListComponent },
  { path: 'courses/:id', component: CourseDetailComponent },
  { 
    path: 'enroll', 
    loadComponent: () => import('./pages/enrollment-form/enrollment-form.component').then(m => m.EnrollmentFormComponent) 
  },
  { 
    path: 'enroll-reactive', 
    loadComponent: () => import('./pages/reactive-enrollment-form/reactive-enrollment-form.component').then(m => m.ReactiveEnrollmentFormComponent),
    canDeactivate: [unsavedChangesGuard]
  },
  { path: 'profile', component: StudentProfileComponent, canActivate: [authGuard] },
  { path: '**', component: PageNotFoundComponent }
];
