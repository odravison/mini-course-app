package br.odravison.sogo.minicoursesinfrasctructure.mapper;

import br.odravison.sogo.minicoursesdomain.domain.Student;
import br.odravison.sogo.minicoursesinfrasctructure.entities.StudentMapped;
import br.odravison.sogo.minicoursesinfrasctructure.utils.MapperUtils;

public class StudentMapper {

    public static Student fromStudentMapped(StudentMapped mapped){
        return MapperUtils.mapTo(mapped, Student.class);
    }

    public static StudentMapped toStudentMapped(Student student){
        return MapperUtils.mapTo(student, StudentMapped.class);
    }

    public static void mapMappedFields(StudentMapped mapped, Student student) {
        mapped.setId(student.getId());
        mapped.setBirthday(student.getBirthday());
        mapped.setCpf(student.getCpf());
        mapped.setEmail(student.getEmail());
        mapped.setLastLoginTime(student.getLastLoginTime());
        mapped.setName(student.getName());
        mapped.setIdRole(student.getIdRole());
        mapped.setPassword(student.getPassword());
        mapped.setDeleted(student.getDeleted());
    }

}
