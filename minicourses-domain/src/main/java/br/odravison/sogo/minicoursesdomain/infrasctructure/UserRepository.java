package br.odravison.sogo.minicoursesdomain.infrasctructure;

import br.odravison.sogo.minicoursesdomain.domain.User;

public interface UserRepository {

    User login(String email, String encryptedPassword);

    boolean existsByEmailAndDeleted(String email, boolean deleted);

    User findByEmailAndDeleted(String email, boolean deleted);

    Boolean isPasswordCorrect(Long id, String password);

    void updateLastLoginTimeByUserId(Long id, Long lastLoginTime);

    boolean existsByEmail(String email);
}
