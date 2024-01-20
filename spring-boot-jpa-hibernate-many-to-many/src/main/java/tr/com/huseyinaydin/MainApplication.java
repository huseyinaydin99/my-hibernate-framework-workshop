package tr.com.huseyinaydin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tr.com.huseyinaydin.model.Student;
import tr.com.huseyinaydin.model.Subject;
import tr.com.huseyinaydin.repository.StudentRepository;
import tr.com.huseyinaydin.repository.SubjectRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Hibernate.
* 
*/

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... arg0) throws Exception {
        Student ussun = new Student("Hüseyin");
        Student yasin = new Student("Yasin");

        Subject java = new Subject("Software");
        Subject computer = new Subject("Hardware");

		/*subjectRepository.save(math);
		subjectRepository.save(computer);*/

        Set<Subject> subjects = new HashSet<Subject>();
        subjects.add(java);
        subjects.add(computer);

        ussun.setSubjects(subjects);
        yasin.setSubjects(subjects);

        studentRepository.save(ussun);
        studentRepository.save(yasin);


        Set<Student> students = new HashSet<Student>();
        students.add(ussun);
        students.add(yasin);

        java.setStudents(students);
        computer.setStudents(students);

        subjectRepository.save(java);
        subjectRepository.save(computer);

        List<Student> studentList = studentRepository.findAll();
        List<Subject> subList = subjectRepository.findAll();

        System.out.println(studentList.size());
        System.out.println(subList.size());


        System.out.println("===================Öğrenci bilgi:==================");
        studentList.forEach(student->System.out.println(student.toString()));

        System.out.println("===================konu bilgi:==================");
        subList.forEach(subject->System.out.println(subject.toString()));
    }
}