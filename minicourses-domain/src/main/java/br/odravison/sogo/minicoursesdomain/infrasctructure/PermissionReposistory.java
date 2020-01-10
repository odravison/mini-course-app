package br.odravison.sogo.minicoursesdomain.infrasctructure;

import java.util.List;

public interface PermissionReposistory {

    List<String> findPermissionByRole(Long idRole);
}
