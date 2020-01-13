package br.odravison.sogo.minicoursesdomain.infrasctructure;

import br.odravison.sogo.minicoursesdomain.domain.MiniCourse;
import br.odravison.sogo.minicoursesdomain.presentation.minicourse.CreateMiniCourseRequest;
import br.odravison.sogo.minicoursesdomain.presentation.minicourse.ReadMiniCourseResponse;

import java.util.List;
import java.util.Optional;

public interface MiniCourseRepository {

    List<ReadMiniCourseResponse> readMinniCourses();

    Optional<MiniCourse> findById(Long id);

    ReadMiniCourseResponse create(CreateMiniCourseRequest miniCourseRequest);

    boolean existsById(Long id);

    MiniCourse save(MiniCourse miniCourse);
}
