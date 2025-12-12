package com.group3.petcareorganizer.service;


import com.group3.petcareorganizer.model.Event;
import com.group3.petcareorganizer.repository.EventRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final EmailService emailService;

    public EventService(EventRepository eventRepository, EmailService emailService) {
        this.eventRepository = eventRepository;
        this.emailService = emailService;
    }

    @Scheduled (fixedRate = 60 * 1000) //runs every min
    public void sendReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime soon = now.plusMinutes(30);

        List<Event> upcomingEvents = eventRepository.findAllByEventStartTimeBetween(now, soon);

        for(Event event : upcomingEvents) {

            emailService.sendEmail(
                    event.getPet().getOwner().getEmail(),
                    "Upcoming Event: " + event.getEventName(),
                    "Hello! " + event.getPet().getOwner().getUsername() + ",\n\n" +
                            "Reminder: Your pet " + event.getPet().getPetName() +
                            " has an upcoming event: " + event.getEventName() + " from " + event.getEventStartTime() +
                            " to " + event.getEventEndTime() + "."
            );
        }
    }
}
