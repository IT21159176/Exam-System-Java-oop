package com.exam.examinationsystem.service.implementations;

import com.exam.examinationsystem.helper.UserFoundException;
<<<<<<< HEAD
import com.exam.examinationsystem.helper.UserNotFoundException;
=======
>>>>>>> 1522421dd65efcacf18d54279702ddb9ed3b732a
import com.exam.examinationsystem.models.User;
import com.exam.examinationsystem.models.UserRole;
import com.exam.examinationsystem.repository.RoleRepository;
import com.exam.examinationsystem.repository.UserRepository;
import com.exam.examinationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static List<User> list = new ArrayList<>();

    //user create
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());
<<<<<<< HEAD
        if (local!=null) {
            System.out.println("User is Already registered");
            throw new UserFoundException();
        } else {
=======

>>>>>>> 1522421dd65efcacf18d54279702ddb9ed3b732a
            //create User
            for (UserRole ur:userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);

        return local;
    }

    //get user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    //delete user by user Id
    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
       return this.userRepository.findUserById(userId);
    }

    //update user by user ID
    public User updateUser(User user) {

        User local = this.userRepository.findUserById(user.getId());
        if (local!=null) {
            local = this.userRepository.save(user);
        }else {
            System.out.println("ID not found");
        }

        return local;
    }


}
