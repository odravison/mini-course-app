package br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate;

import br.odravison.sogo.minicoursesinfrasctructure.entities.UserMapped;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMappedRepository extends CrudRepository<UserMapped, Long> {

    /**
     * Login the user.
     */
    @Query(value = "select * from user_account " +
            "where upper(email) = upper(?1) " +
            "and password = ?2 " +
            "and deleted = false;",
            nativeQuery = true)
    UserMapped login(String email, String encryptedPassword);

    /**
     * Checks if exists an user with the same email.
     */
    boolean existsByEmailAndDeleted(String email, boolean deleted);

    /**
     * Finds the user by email.
     */
    UserMapped findByEmailAndDeleted(String email, boolean deleted);

    /**
     * Checks if the password of the user is correct.
     */
    @Query(value = "SELECT EXISTS(" +
            "SELECT * FROM user_account " +
            "WHERE id = ?1 " +
            "AND password = ?2 " +
            "AND deleted = false" +
            ");", nativeQuery = true)
    Boolean isPasswordCorrect(Long id, String password);

    @Modifying
    @Query(value = "UPDATE user_account " +
            "SET last_login_time = ?2 " +
            "WHERE id = ?1", nativeQuery = true)
    void updateLastLoginTimeByUserId(Long id, Long lastLoginTime);
}
