package br.odravison.sogo.minicoursesinfrasctructure.repositories.impl;

import br.odravison.sogo.minicoursesdomain.domain.User;
import br.odravison.sogo.minicoursesdomain.infrasctructure.UserRepository;
import br.odravison.sogo.minicoursesinfrasctructure.entities.UserMapped;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate.UserMappedRepository;
import br.odravison.sogo.minicoursesinfrasctructure.utils.MapperUtils;

public class UserRepositoryImpl implements UserRepository {

    private UserMappedRepository userMappedRepository;

    public UserRepositoryImpl(UserMappedRepository userMappedRepository) {
        this.userMappedRepository = userMappedRepository;
    }

    @Override
    public User login(String email, String encryptedPassword) {
        UserMapped userMapped = this.userMappedRepository.login(email, encryptedPassword);

        if (userMapped != null) {
            return MapperUtils.mapTo(userMapped, User.class);
        }

        return null;
    }

    @Override
    public boolean existsByEmailAndDeleted(String email, boolean deleted) {
        return this.userMappedRepository.existsByEmailAndDeleted(email, deleted);
    }

    @Override
    public User findByEmailAndDeleted(String email, boolean deleted) {
        UserMapped userMapped = this.userMappedRepository.findByEmailAndDeleted(email, deleted);

        if (userMapped != null){
            return MapperUtils.mapTo(userMapped, User.class);
        }

        return null;
    }

    @Override
    public Boolean isPasswordCorrect(Long id, String password) {
        return this.userMappedRepository.isPasswordCorrect(id, password);
    }

    @Override
    public void updateLastLoginTimeByUserId(Long id, Long lastLoginTime) {
        this.userMappedRepository.updateLastLoginTimeByUserId(id, lastLoginTime);
    }
}
