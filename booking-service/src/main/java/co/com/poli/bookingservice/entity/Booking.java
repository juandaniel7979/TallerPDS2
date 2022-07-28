package co.com.poli.bookingservice.entity;

import co.com.poli.bookingservice.model.Showtime;
import co.com.poli.bookingservice.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "showtimeId", nullable = false)
    private Long showtimeId;

    @Transient
    private User user;

    @Transient
    private Showtime showtime;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<BookingMovies> items;
}
