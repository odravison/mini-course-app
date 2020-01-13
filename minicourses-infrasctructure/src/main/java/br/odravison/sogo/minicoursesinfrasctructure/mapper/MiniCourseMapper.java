package br.odravison.sogo.minicoursesinfrasctructure.mapper;

import br.odravison.sogo.minicoursesdomain.domain.MiniCourse;
import br.odravison.sogo.minicoursesinfrasctructure.entities.MiniCourseMapped;
import br.odravison.sogo.minicoursesinfrasctructure.utils.MapperUtils;

import java.util.stream.Collectors;

public class MiniCourseMapper {

    public static MiniCourse fromMiniCourseMapped(MiniCourseMapped mapped){
        return MapperUtils.mapTo(mapped, MiniCourse.class);
    }

    public static MiniCourseMapped toMiniCourseMapped(MiniCourse miniCourse){
        return MapperUtils.mapTo(miniCourse, MiniCourseMapped.class);
    }

    public static void mapMappedFields(MiniCourseMapped mapped, MiniCourse miniCourse) {
        mapped.setId(miniCourse.getId());
        mapped.setProfessorOwner(ProfessorMapper.toProfessorMapped(miniCourse.getProfessorOwner()));
        mapped.setDuration(miniCourse.getDuration());
        mapped.setName(miniCourse.getName());
        mapped.setStartDate(miniCourse.getStartDate());
        mapped.setVacanciesNumber(miniCourse.getVacanciesNumber());
        mapped.setParticipants(miniCourse
                .getParticipants()
                .stream()
                .map(StudentMapper::toStudentMapped).collect(Collectors.toList())
        );
    }
}
