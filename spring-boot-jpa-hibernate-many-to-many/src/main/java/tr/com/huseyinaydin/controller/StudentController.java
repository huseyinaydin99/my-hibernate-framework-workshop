package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.model.Student;
import tr.com.huseyinaydin.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Hibernate.
* 
*/

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    // http://localhost:8080/api/v1/students
    @GetMapping("/students")
    public List<Student> getBookAll() {
        return studentRepository.findAll();
    }

    // http://localhost:8080/api/v1/students/1
    @GetMapping("/students/{id}")
    public ResponseEntity<Optional<Student>> getUserFindById(@PathVariable("id") Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        return ResponseEntity.ok().body(student);
    }
}