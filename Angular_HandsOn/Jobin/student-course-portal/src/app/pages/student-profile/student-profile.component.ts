import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EnrollmentService } from '../../services/enrollment.service';
import { Observable } from 'rxjs';
import { Course } from '../../models/course.model';

@Component({
  selector: 'app-student-profile',
  imports: [CommonModule],
  templateUrl: './student-profile.component.html',
  styleUrl: './student-profile.component.css'
})
export class StudentProfileComponent implements OnInit {
  enrolledCourses$!: Observable<Course[]>;
  
  constructor(public enrollmentService: EnrollmentService) {}

  ngOnInit() {
    this.enrolledCourses$ = this.enrollmentService.getEnrolledCourses();
  }
}
