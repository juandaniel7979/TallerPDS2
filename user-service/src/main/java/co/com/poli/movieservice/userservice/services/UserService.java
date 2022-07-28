package co.com.poli.movieservice.userservice.services;

import co.com.poli.movieservice.userservice.entity.User;

import java.util.List;

public interface UserService {
    void save(User user);

    Boolean delete(User user);

    List<User> findAll();

    User findById(Long id);

}
