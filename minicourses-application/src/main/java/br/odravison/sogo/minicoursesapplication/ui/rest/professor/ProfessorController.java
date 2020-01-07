package br.odravison.sogo.minicoursesapplication.ui.rest.professor;

import br.odravison.sogo.minicoursesapplication.ui.rest.professor.responses.ReadProfessorsResponseJSON;
import br.odravison.sogo.minicoursesdomain.api.ProfessorsAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("professors")
public class ProfessorController {

    public static final String READ_ALL_PROFESSORS = "/all";

    private ProfessorsAPI professorsAPI;

    public ProfessorController(ProfessorsAPI professorsAPI) {
        this.professorsAPI = professorsAPI;
    }

    @GetMapping(value = ProfessorController.READ_ALL_PROFESSORS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReadProfessorsResponseJSON readProfessors() {
        return new ReadProfessorsResponseJSON(this.professorsAPI.readProfessors());
    }


}
