package br.odravison.sogo.minicoursesinfrasctructure.mapper;

import br.odravison.sogo.minicoursesdomain.domain.Professor;
import br.odravison.sogo.minicoursesinfrasctructure.entities.ProfessorMapped;
import br.odravison.sogo.minicoursesinfrasctructure.utils.MapperUtils;

public class ProfessorMapper {

    public static Professor fromProfessorMapped(ProfessorMapped mapped){
        return MapperUtils.mapTo(mapped, Professor.class);
    }

    public static ProfessorMapped toProfessorMapped(Professor professor){
        return MapperUtils.mapTo(professor, ProfessorMapped.class);
    }

    public static void mapMappedFields(ProfessorMapped mapped, Professor professor) {
        mapped.setId(professor.getId());
        mapped.setPhones(professor.getPhones());
        mapped.setRegistration(professor.getRegistration());
        mapped.setEmail(professor.getEmail());
        mapped.setName(professor.getName());
        mapped.setLastLoginTime(professor.getLastLoginTime());
    }
}
