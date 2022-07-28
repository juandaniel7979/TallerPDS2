package com.co.poli.showtimeservice.repository;

import com.co.poli.showtimeservice.entity.ShowtimeItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeItemsRepository extends JpaRepository<ShowtimeItems, Long> {
}
