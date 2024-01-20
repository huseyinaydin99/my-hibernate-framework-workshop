package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.model.Student;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Hibernate.
* 
*/

public interface StudentRepository extends JpaRepository<Student, Integer> {
}