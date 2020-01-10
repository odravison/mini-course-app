package br.odravison.sogo.minicoursesinfrasctructure.repositories.impl;

import br.odravison.sogo.minicoursesdomain.infrasctructure.PermissionReposistory;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate.PermissionMappedRepository;

import java.util.List;

public class PermissionRepositoryImpl implements PermissionReposistory {

    private PermissionMappedRepository permissionMappedRepository;

    public PermissionRepositoryImpl(PermissionMappedRepository permissionRepository) {
        this.permissionMappedRepository = permissionRepository;
    }

    @Override
    public List<String> findPermissionByRole(Long idRole) {
        return this.permissionMappedRepository.findPermissionByRole(idRole);
    }
}
