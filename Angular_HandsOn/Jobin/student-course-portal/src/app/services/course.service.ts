import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private apiUrl = 'http://localhost:3000/courses';

  constructor(private http: HttpClient) {}

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl).pipe(
      retry(2), // retry up to 2 times before failing
      tap(courses => console.log(`Fetched ${courses.length} courses from API`)),
      catchError(this.handleError<Course[]>('getCourses', []))
    );
  }

  getCourseById(id: number): Observable<Course | undefined> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`).pipe(
      tap(course => console.log(`Fetched course id=${id}`)),
      catchError(this.handleError<Course>(`getCourseById id=${id}`))
    );
  }

  addCourse(course: Course): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course).pipe(
      tap((newCourse: Course) => console.log(`Added course id=${newCourse.id}`)),
      catchError(this.handleError<Course>('addCourse'))
    );
  }

  updateCourse(course: Course): Observable<any> {
    return this.http.put(`${this.apiUrl}/${course.id}`, course).pipe(
      tap(_ => console.log(`Updated course id=${course.id}`)),
      catchError(this.handleError<any>('updateCourse'))
    );
  }

  deleteCourse(id: number): Observable<Course> {
    return this.http.delete<Course>(`${this.apiUrl}/${id}`).pipe(
      tap(_ => console.log(`Deleted course id=${id}`)),
      catchError(this.handleError<Course>('deleteCourse'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
