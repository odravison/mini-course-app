package br.odravison.sogo.minicoursesapplication.ui.rest.student;

import br.odravison.sogo.minicoursesapplication.ui.rest.student.requests.CreateStudentJSONRequest;
import br.odravison.sogo.minicoursesapplication.ui.rest.student.responses.ReadStudentJSONResponse;
import br.odravison.sogo.minicoursesapplication.utils.CryptoUtil;
import br.odravison.sogo.minicoursesdomain.api.StudentsAPI;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import br.odravison.sogo.minicoursesdomain.presentation.student.CreateStudentRequest;
import br.odravison.sogo.minicoursesdomain.presentation.student.ReadStudentResponse;
import br.odravison.sogo.minicoursesinfrasctructure.utils.MapperUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("students")
public class StudentController {

    public static final String GET_ALL_STUDENTS = "/all";

    private StudentsAPI studentsAPI;

    public StudentController(StudentsAPI studentsAPI) {
        this.studentsAPI = studentsAPI;
    }

    @GetMapping(value = StudentController.GET_ALL_STUDENTS, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReadStudentJSONResponse> getAllStudents() {
        return this.studentsAPI.readStudents()
                .getStudents()
                .stream()
                .map(readStudentResponse -> MapperUtils.mapTo(readStudentResponse, ReadStudentJSONResponse.class))
                .collect(Collectors.toList());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ReadStudentJSONResponse signUpStudent(@Valid @RequestBody CreateStudentJSONRequest request) throws MiniCourseDomainException {
        request.setPassword(CryptoUtil.hash(request.getPassword()));
        ReadStudentResponse student = this.studentsAPI.studentSignUp(
                CreateStudentRequest.createStudentRequest(
                    request.getName(),
                    request.getPassword(),
                    request.getEmail(),
                    request.getCpf(),
                    request.getBirthday()
            )
        );

        return new ReadStudentJSONResponse(student.getId(), student.getName(), student.getEmail(),
                student.getPassword(), student.getBirthday(), student.getCpf());
    }

}
