package br.odravison.sogo.minicoursesdomain.api;

import br.odravison.sogo.minicoursesdomain.infrasctructure.PermissionReposistory;

import javax.transaction.Transactional;
import java.util.List;

public class PermissionAPI {

    private PermissionReposistory permissionReposistory;

    public PermissionAPI(PermissionReposistory permissionReposistory) {
        this.permissionReposistory = permissionReposistory;
    }

    @Transactional
    public List<String> findPermissionByRole(Long idRole) {
        return this.permissionReposistory.findPermissionByRole(idRole);
    }
}
