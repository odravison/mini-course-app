package br.odravison.sogo.minicoursesinfrasctructure.repositories.impl;

import br.odravison.sogo.minicoursesdomain.domain.MiniCourse;
import br.odravison.sogo.minicoursesdomain.infrasctructure.MiniCourseRepository;
import br.odravison.sogo.minicoursesdomain.presentation.minicourse.CreateMiniCourseRequest;
import br.odravison.sogo.minicoursesdomain.presentation.minicourse.ReadMiniCourseResponse;
import br.odravison.sogo.minicoursesinfrasctructure.entities.MiniCourseMapped;
import br.odravison.sogo.minicoursesinfrasctructure.entities.ProfessorMapped;
import br.odravison.sogo.minicoursesinfrasctructure.mapper.MiniCourseMapper;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate.MiniCourseMappedRepository;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate.ProfessorMappedRepository;
import br.odravison.sogo.minicoursesinfrasctructure.utils.MapperUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MiniCourseRepositoryImpl implements MiniCourseRepository {

    private MiniCourseMappedRepository miniCourseMappedRepository;

    private ProfessorMappedRepository professorRepository;

    public MiniCourseRepositoryImpl(
            MiniCourseMappedRepository miniCourseMappedRepository,
            ProfessorMappedRepository professorRepository) {
        this.miniCourseMappedRepository = miniCourseMappedRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public List<ReadMiniCourseResponse> readMinniCourses() {

        List<ReadMiniCourseResponse> response = new ArrayList<>();

        this.miniCourseMappedRepository.findAll().forEach(miniCourseMapped -> {
            response.add(miniCourseMapped.buildReadMiniCourseResponse());
        });

        return response;
    }

    @Override
    public Optional<MiniCourse> findById(Long id) {
        Optional<MiniCourse> response = Optional.empty();
        Optional<MiniCourseMapped> optionalMiniCourse = this.miniCourseMappedRepository.findById(id);

        if (optionalMiniCourse.isPresent()) {
            response = Optional.of(MapperUtils.mapTo(optionalMiniCourse.get(), MiniCourse.class));
        }

        return response;
    }

    @Override
    public ReadMiniCourseResponse create(CreateMiniCourseRequest miniCourseRequest) {

        MiniCourseMapped mapped = MapperUtils.mapTo(miniCourseRequest, MiniCourseMapped.class);

        /**
         * Set the professor of this miniCourse
         */
        ProfessorMapped professorTarget = this.professorRepository
                .findById(miniCourseRequest.getProfessorOwnerId()).get();
        mapped.setProfessorOwner(professorTarget);

        mapped = this.miniCourseMappedRepository.save(mapped);
        return MapperUtils.mapTo(mapped, ReadMiniCourseResponse.class);
    }

    @Override
    public boolean existsById(Long id) {
        return this.miniCourseMappedRepository.existsById(id);
    }

    @Override
    public MiniCourse save(MiniCourse miniCourse) {
        MiniCourseMapped mapped = this.miniCourseMappedRepository.findById(miniCourse.getId()).get();
        MiniCourseMapper.mapMappedFields(mapped, miniCourse);
        mapped = this.miniCourseMappedRepository.save(mapped);

        return MiniCourseMapper.fromMiniCourseMapped(mapped);
    }

    @Override
    public boolean isAvailableStartDate(Date startDate) {
        return this.miniCourseMappedRepository.countByStartDate(startDate) < 2;
    }


}
