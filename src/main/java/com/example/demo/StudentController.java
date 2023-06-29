package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student("Pham E", "9", 16, 9, "10", "10B"));
        students.add(new Student("Ngoc F", "10", 16, 10, "10", "10A"));
        students.add(new Student("Anh G", "11", 17, 11, "11", "11C"));
        students.add(new Student("Ngoc H", "12", 16, 12, "11", "11B"));
    }

    @PostMapping(value = "/students")
    public MyAsw getStudentsByClass(@RequestBody RequestStudent requestStudent) {
        String className = requestStudent.getClassName();
        MyAsw masw = new MyAsw();
        List<Student> studentsByClass = new ArrayList<>();

        if (students == null || students.size() == 0) {
            masw.setCode("01");
            masw.setMessage("Ko tim thay");
            masw.setLstStudent(studentsByClass);
            return masw;
        }

        for (Student student : students) {
            try {
                if (student.getClassName().equalsIgnoreCase(className)) {
                    studentsByClass.add(student);
                }
            } catch (Exception ex) {
                masw.setCode("99");
                masw.setMessage("gap loi");
                return masw;
            }
        }

        if (studentsByClass.isEmpty()) {
            masw.setCode("01");
            masw.setMessage("Ko tim thay");
        } else {
            masw.setCode("00");
            masw.setMessage("Thanh cong");
        }

        masw.setLstStudent(studentsByClass);
        return masw;
    }
}