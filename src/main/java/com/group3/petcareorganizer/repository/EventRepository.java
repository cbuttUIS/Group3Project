package com.group3.petcareorganizer.repository;

import com.group3.petcareorganizer.model.Event;
import com.group3.petcareorganizer.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

/*Extending JpaRepository gives the CRUD operations to our interface,give access to event data
 */
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByPetId(Long id);


    List<Event> findByOwnerOrderByEventStartTimeAsc(Owner owner);

    List<Event> findAllByEventStartTimeBetween(LocalDateTime now, LocalDateTime soon);
}
