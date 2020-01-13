package br.odravison.sogo.minicoursesapplication.ui.rest.professor;

import br.odravison.sogo.minicoursesapplication.ui.rest.professor.requests.ProfessorCreationJSONRequest;
import br.odravison.sogo.minicoursesapplication.ui.rest.professor.responses.ReadProfessorsResponseJSON;
import br.odravison.sogo.minicoursesapplication.ui.rest.professor.responses.SimpleProfessorJSONResponse;
import br.odravison.sogo.minicoursesapplication.utils.CryptoUtil;
import br.odravison.sogo.minicoursesdomain.api.ProfessorsAPI;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import br.odravison.sogo.minicoursesdomain.presentation.professor.CreateProfessorRequest;
import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorResponse;
import br.odravison.sogo.minicoursesinfrasctructure.utils.MapperUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("professors")
public class ProfessorController {

    public static final String GET_ALL_PROFESSORS = "/all";

    private ProfessorsAPI professorsAPI;

    public ProfessorController(ProfessorsAPI professorsAPI) {
        this.professorsAPI = professorsAPI;
    }

    @GetMapping(value = ProfessorController.GET_ALL_PROFESSORS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReadProfessorsResponseJSON readProfessors() {
        return new ReadProfessorsResponseJSON(this.professorsAPI.readProfessors());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleProfessorJSONResponse createProfessor(@RequestBody ProfessorCreationJSONRequest request) throws MiniCourseDomainException {
        request.setPassword(CryptoUtil.hash(request.getPassword()));
        ReadProfessorResponse readProfessorResponse = this.professorsAPI.createProfessor(CreateProfessorRequest.createCreateProfessorRequest(
                request.getName(), request.getEmail(), request.getPassword(), request.getRegistration(),
                request.getPhones()
        ));

        return MapperUtils.mapTo(readProfessorResponse, SimpleProfessorJSONResponse.class);
    }


}
