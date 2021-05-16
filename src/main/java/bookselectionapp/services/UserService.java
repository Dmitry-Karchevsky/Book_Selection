package bookselectionapp.services;

import bookselectionapp.entities.Role;
import bookselectionapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import bookselectionapp.repositories.UserRepository;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        final List<Role> authoritiesForSpring = new ArrayList<>(user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authoritiesForSpring);
    }

    public User findUserById(Long idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User newUser) {
        if (userRepository.findByEmail(newUser.getEmail()) != null) {
            return null;
        }

        newUser.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return newUser;
    }

    public User updateUser(Long id, User user) {
        user.setId(id);
        Optional<User> userFromDb = userRepository.findById(id);
        if (userFromDb.isPresent()) {
            user.setRoles(userFromDb.get().getRoles());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public boolean isTrueUser(Long id) {
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User userFromDb = findUserById(id);

        return userDetails.getUsername().equals(userFromDb.getUsername());
    }
}
