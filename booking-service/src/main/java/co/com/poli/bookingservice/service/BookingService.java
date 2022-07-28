package co.com.poli.bookingservice.service;

import co.com.poli.bookingservice.entity.Booking;

import java.util.List;

public interface BookingService {
    void save(Booking booking);

    void delete(Booking booking);

    List<Booking> findAll();

    Booking findById(Long id);

    Booking findByUserId(Long userId);

    Boolean listOfIds(Long movieId);

    Boolean listOfUserIds(Long userId);
}

