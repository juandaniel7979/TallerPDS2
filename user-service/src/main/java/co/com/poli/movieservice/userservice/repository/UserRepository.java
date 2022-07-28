package co.com.poli.movieservice.userservice.repository;

import co.com.poli.movieservice.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByLastName(String lastname);
}
