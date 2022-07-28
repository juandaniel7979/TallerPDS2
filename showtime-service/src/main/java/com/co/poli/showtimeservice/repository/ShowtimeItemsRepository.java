package co.com.poli.showtimeservice.repository;

import co.com.poli.showtimeservice.entity.ShowtimeItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeItemsRepository extends JpaRepository<ShowtimeItems, Long> {
}
