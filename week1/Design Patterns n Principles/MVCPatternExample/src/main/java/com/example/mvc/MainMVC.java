package com.example.mvc;

public class MainMVC {
    public static void main(String[] args) {
        Student model = new Student("Alice", "S123", "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);

        controller.updateView();
        controller.setStudentGrade("A+");
        controller.updateView();
    }
}
