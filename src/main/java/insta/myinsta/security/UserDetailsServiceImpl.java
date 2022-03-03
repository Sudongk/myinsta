package insta.myinsta.security;

import insta.myinsta.domain.User;
import insta.myinsta.exception.NotFoundUserException;
import insta.myinsta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUserId(username)
                .map(user -> creatUser(username, user))
                .orElseThrow(NotFoundUserException::new);
    }

    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    private org.springframework.security.core.userdetails.User creatUser(String username, User user) {
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), grantedAuthorities);
    }
}
