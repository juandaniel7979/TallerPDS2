package co.com.poli.movieservice.userservice.services;

import co.com.poli.movieservice.userservice.client.BookingClient;
import co.com.poli.movieservice.userservice.entity.User;
import co.com.poli.movieservice.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BookingClient bookingClient;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Boolean delete(User user) {
        ModelMapper modelMapper = new ModelMapper();

        String bookingFlag = modelMapper.map(bookingClient.findAllUserIds(user.getId()).getData().toString(), String.class);

        if (!bookingFlag.equalsIgnoreCase("true")) {
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}

