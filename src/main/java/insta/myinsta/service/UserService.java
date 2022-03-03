package insta.myinsta.service;

import insta.myinsta.domain.User;
import insta.myinsta.dto.RegisterUserDto;
import insta.myinsta.exception.DuplicateUserException;
import insta.myinsta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(RegisterUserDto registerUserDto) {
        validateUserInfo(registerUserDto);
        User user = User.setUser()
                .userId(registerUserDto.getUserId())
                .nickname(registerUserDto.getNickname())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .build();

        userRepository.save(user);
    }

    private void validateUserInfo(RegisterUserDto registerUserDto) {
        Optional<User> userInfo = userRepository.findByUserId(registerUserDto.getUserId());
        userInfo.ifPresent(user -> {
            throw new DuplicateUserException();
        });
    }


}
