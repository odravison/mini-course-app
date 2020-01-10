package br.odravison.sogo.minicoursesdomain.api;

import br.odravison.sogo.minicoursesdomain.domain.User;
import br.odravison.sogo.minicoursesdomain.infrasctructure.UserRepository;
import br.odravison.sogo.minicoursesdomain.presentation.user.LoginUserResponse;
import br.odravison.sogo.minicoursesdomain.presentation.user.UserResponse;

import javax.transaction.Transactional;

public class UserAPI {

    private UserRepository userRepository;

    public UserAPI(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public LoginUserResponse login(String email, String encryptedPassword) {
        User user = this.userRepository.login(email, encryptedPassword);
        if (user == null){
            return null;
        }
        return new LoginUserResponse(user);
    }

    @Transactional
    public boolean existsByEmailAndDeleted(String email, boolean deleted) {
        return this.userRepository.existsByEmailAndDeleted(email, deleted);
    }

    @Transactional
    public UserResponse findByEmailAndDeleted(String email, boolean deleted) {
        return new UserResponse(this.userRepository.findByEmailAndDeleted(email, deleted));
    }

    @Transactional
    public Boolean isPasswordCorrect(Long id, String password) {
        return this.userRepository.isPasswordCorrect(id, password);
    }

    @Transactional
    public void updateLastLoginTime(Long id, Long lastLoginTime) {
        this.userRepository.updateLastLoginTimeByUserId(id, lastLoginTime);
    }

}
