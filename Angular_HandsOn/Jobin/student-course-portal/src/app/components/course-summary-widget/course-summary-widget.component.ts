import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-course-summary-widget',
  imports: [],
  templateUrl: './course-summary-widget.component.html',
  styleUrl: './course-summary-widget.component.css'
})
export class CourseSummaryWidgetComponent implements OnInit {
  count = 0;
  
  constructor(public courseService: CourseService) {}

  ngOnInit() {
    this.courseService.getCourses().subscribe(courses => this.count = courses.length);
  }

  addDummyCourse() {
    this.courseService.addCourse({
      id: 99,
      name: 'Dummy AI Course',
      code: 'CS99',
      credits: 2,
      gradeStatus: 'pending',
      isEnrolled: false
    }).subscribe(() => {
       // Refresh list
       this.courseService.getCourses().subscribe(courses => this.count = courses.length);
    });
  }
}
