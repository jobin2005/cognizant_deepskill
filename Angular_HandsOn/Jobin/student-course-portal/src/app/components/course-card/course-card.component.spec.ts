import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CourseCardComponent } from './course-card.component';
import { EnrollmentService } from '../../services/enrollment.service';
import { ActivatedRoute } from '@angular/router';
import { HighlightDirective } from '../../directives/highlight.directive';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';

describe('CourseCardComponent', () => {
  let component: CourseCardComponent;
  let fixture: ComponentFixture<CourseCardComponent>;
  let mockEnrollmentService: any;

  beforeEach(async () => {
    mockEnrollmentService = jasmine.createSpyObj('EnrollmentService', ['isEnrolled', 'enroll', 'unenroll']);
    mockEnrollmentService.isEnrolled.and.returnValue(false);

    await TestBed.configureTestingModule({
      imports: [CourseCardComponent, HighlightDirective, CreditLabelPipe],
      providers: [
        { provide: EnrollmentService, useValue: mockEnrollmentService },
        { provide: ActivatedRoute, useValue: {} }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseCardComponent);
    component = fixture.componentInstance;
    
    // Simulate `@Input` properties
    // @ts-ignore
    component.course = { id: 1, name: 'Angular', code: 'CS1', credits: 3, gradeStatus: 'passed', isEnrolled: false };
    fixture.detectChanges();
  });

  it('should create CourseCard component successfully', () => {
    expect(component).toBeTruthy();
  });

  it('should correctly capture Enroll click toggles', () => {
    component.toggleEnroll();
    expect(mockEnrollmentService.enroll).toHaveBeenCalledWith(1);
  });
});
