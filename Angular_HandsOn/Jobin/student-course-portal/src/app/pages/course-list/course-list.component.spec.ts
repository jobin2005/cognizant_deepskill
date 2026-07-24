import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CourseListComponent } from './course-list.component';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '../../services/course.service';
import { of } from 'rxjs';

describe('CourseListComponent', () => {
  let component: CourseListComponent;
  let fixture: ComponentFixture<CourseListComponent>;
  let store: MockStore;
  
  const mockCourseService = {
    getCourses: () => of([{ id: 10, name: 'Mock NgRx Test' }])
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseListComponent],
      providers: [
        provideMockStore({ initialState: { courses: { courses: [], error: null } } }),
        { provide: CourseService, useValue: mockCourseService },
        { provide: ActivatedRoute, useValue: {} }
      ]
    })
    .compileComponents();

    store = TestBed.inject(MockStore);
    fixture = TestBed.createComponent(CourseListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should initialize component leveraging mocked store context', () => {
    expect(component).toBeTruthy();
    expect(store).toBeTruthy();
  });
});
