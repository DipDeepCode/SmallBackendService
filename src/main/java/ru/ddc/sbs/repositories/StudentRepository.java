package ru.ddc.sbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.sbs.entities.FullName;
import ru.ddc.sbs.entities.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGroupNumber(String groupNumber);
    List<Student> findByFullName(FullName fullName);
    @Transactional
    @Modifying
    @Query("update Student s set s.fullName = ?1, s.groupNumber = ?2 where s.id = ?3")
    void updateStudentById(FullName fullName, String groupNumber, Long id);
    @Transactional
    @Modifying
    @Query("update Student s set s.isActive = ?1 where s.id = ?2")
    void deactivateStudentById(Boolean isActive, Long id);
}