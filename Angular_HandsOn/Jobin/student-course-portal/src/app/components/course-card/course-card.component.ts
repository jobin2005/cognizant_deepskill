import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';
import { HighlightDirective } from '../../directives/highlight.directive';
import { EnrollmentService } from '../../services/enrollment.service';

@Component({
  selector: 'app-course-card',
  imports: [CommonModule, RouterModule, CreditLabelPipe, HighlightDirective],
  templateUrl: './course-card.component.html',
  styleUrl: './course-card.component.css'
})
export class CourseCardComponent implements OnChanges {
  @Input() course!: { id: number, name: string, code: string, credits: number, gradeStatus: string, isEnrolled: boolean };
  @Output() enrollRequested = new EventEmitter<number>();
  
  isExpanded = false;

  constructor(private enrollmentService: EnrollmentService) {}

  get isEnrolled() {
    return this.enrollmentService.isEnrolled(this.course.id);
  }

  toggleEnroll() {
    if (this.isEnrolled) {
      this.enrollmentService.unenroll(this.course.id);
    } else {
      this.enrollmentService.enroll(this.course.id);
    }
  }

  get cardClasses() {
    return {
      'card--enrolled': this.isEnrolled,
      'card--full': this.course.credits >= 4,
      'expanded': this.isExpanded
    };
  }

  get borderStyle() {
    if (this.course.gradeStatus === 'passed') return '5px solid green';
    if (this.course.gradeStatus === 'failed') return '5px solid red';
    return '5px solid grey';
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['course']) {
      console.log('Course changed:', {
        prev: changes['course'].previousValue,
        curr: changes['course'].currentValue
      });
    }
  }
}
