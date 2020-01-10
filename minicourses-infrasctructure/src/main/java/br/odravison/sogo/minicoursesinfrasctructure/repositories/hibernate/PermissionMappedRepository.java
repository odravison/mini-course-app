package br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate;

import br.odravison.sogo.minicoursesinfrasctructure.entities.PermissionMapped;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissionMappedRepository extends CrudRepository<PermissionMapped, Long> {

    @Cacheable("db-role-permissions")
    @Query(value = "select p.name from role_permission rp " +
            "inner join permission p on p.id = rp.id_permission " +
            "where rp.id_role = ?1",
            nativeQuery = true)
    List<String> findPermissionByRole(Long idRole);
}
