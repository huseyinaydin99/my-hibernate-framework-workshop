package tr.com.huseyinaydin;

import java.util.List;

import tr.com.huseyinaydin.dao.StudentDao;
import tr.com.huseyinaydin.entity.Student;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Hibernate.
* 
*/

public class AppMain {
    public static void main(String[] args) {

        StudentDao studentDao = new StudentDao();

        Student student1 = new Student("Ahmet", "Mumcu", "ahmetmumcu99@gmail.com");
        Student student2 = new Student("Bilal", "Yılmaz", "bilalyilmaz99@gmail.com");
        Student student3 = new Student("Cumali", "Yavuz", "cumaliyavuz99@gmail.com");

        studentDao.saveStudent(student1);
        studentDao.saveStudent(student2);
        studentDao.saveStudent(student3);

        List<Student> students = studentDao.getStudents();
        students.forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName() + " " + s.getEmail()));

    }
}