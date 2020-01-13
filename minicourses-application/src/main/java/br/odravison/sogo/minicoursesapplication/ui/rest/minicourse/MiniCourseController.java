package br.odravison.sogo.minicoursesapplication.ui.rest.minicourse;

import br.odravison.sogo.minicoursesapplication.ui.rest.exceptions.ResourceNotFoundException;
import br.odravison.sogo.minicoursesapplication.ui.rest.minicourse.requests.MiniCourseJSONCreationRequest;
import br.odravison.sogo.minicoursesapplication.ui.rest.minicourse.responses.MiniCourseJSONResponse;
import br.odravison.sogo.minicoursesapplication.utils.SecurityUtils;
import br.odravison.sogo.minicoursesdomain.api.MiniCourseAPI;
import br.odravison.sogo.minicoursesdomain.domain.validators.ValidationError;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseNotFoundException;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.StudentNotFoundException;
import br.odravison.sogo.minicoursesdomain.presentation.minicourse.CreateMiniCourseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("mini-courses")
public class MiniCourseController {

    public static final String GET_ALL_MINI_COURSE = "/all";
    public static final String GET_MINI_COURSE_BY_ID = "/{id}";
    public static final String POST_MINI_COURSE_SUBSCRIBE = "/{miniCourseId}/subscribe";

    private MiniCourseAPI api;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public MiniCourseController(MiniCourseAPI api) {
        this.api = api;
    }

    @GetMapping(value = MiniCourseController.GET_ALL_MINI_COURSE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MiniCourseJSONResponse> getAllMiniCourses(){
        logger.info("Getting all mini courses");
        List<MiniCourseJSONResponse> response = new ArrayList<>();
        this.api.readMiniCourses().getMiniCourses().forEach(miniCourseResponse -> {
            response.add(MiniCourseJSONResponse.createMiniCourseJSONResponse(miniCourseResponse));
        });

        return response;
    }

    @GetMapping(value = MiniCourseController.GET_MINI_COURSE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public MiniCourseJSONResponse getMiniCourseById(@PathVariable Long id) {
        logger.info("Getting mini course by id");
        return MiniCourseJSONResponse.createMiniCourseJSONResponse(this.api.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public MiniCourseJSONResponse createMiniCourse(@RequestBody MiniCourseJSONCreationRequest request)
            throws MiniCourseDomainException {

        logger.info("Creating mini course");

        validateUserLoggedInIsProfessor();
        Long professorOwnerId = SecurityUtils.getCurrentUser().getId();
        return MiniCourseJSONResponse.createMiniCourseJSONResponse(
                this.api.createMiniCourse(
                        CreateMiniCourseRequest.createMiniCourseRequest(
                                request.getName(), request.getStartDate(), request.getDuration(),
                                request.getVacanciesNumber(),
                                professorOwnerId
                        )
                )
        );

    }

    @PostMapping(value =  MiniCourseController.POST_MINI_COURSE_SUBSCRIBE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> subscribeMiniCourse(@PathVariable Long miniCourseId)
            throws MiniCourseDomainException, MiniCourseNotFoundException, StudentNotFoundException, ResourceNotFoundException {

        logger.info("Subscribing student into mini course");

        validateUserLoggedInIsStudent();
        Long studentId = SecurityUtils.getCurrentUser().getId();

        try {
            this.api.subscribeStudentInMiniCourse(miniCourseId, studentId);
        } catch (MiniCourseNotFoundException | StudentNotFoundException se) {
            throw new ResourceNotFoundException();
        }

        return ResponseEntity.ok(HttpStatus.CREATED);

    }

    private void validateUserLoggedInIsProfessor() throws MiniCourseDomainException {
        /*
         * Check if this user it's a professor
         */
        if (!SecurityUtils.getCurrentUser().isProfessor()) {
            logger.info("Error: This current user is not a professor");
            MiniCourseError error = new MiniCourseError(
                    ValidationError.IS_NOT_PROFESSOR.code,
                    null,
                    ValidationError.IS_NOT_PROFESSOR.message
            );

            throw new MiniCourseDomainException(Collections.singletonList(error));
        }
    }

    private void validateUserLoggedInIsStudent() throws MiniCourseDomainException {
        /*
         * Check if this user it's a professor
         */
        if (!SecurityUtils.getCurrentUser().isStudent()) {
            logger.info("Error: This current user is not a student");
            MiniCourseError error = new MiniCourseError(
                    ValidationError.IS_NOT_STUDENT.code,
                    null,
                    ValidationError.IS_NOT_STUDENT.message
            );

            throw new MiniCourseDomainException(Collections.singletonList(error));
        }
    }



}
