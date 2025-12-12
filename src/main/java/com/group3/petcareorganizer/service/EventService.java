package com.group3.petcareorganizer.service;


import com.group3.petcareorganizer.model.Event;
import com.group3.petcareorganizer.repository.EventRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/* @Service means this class is a Spring service, it handles event operations, and sends event notifications with email
 */
@Service
public class EventService {

    // access event data
    private final EventRepository eventRepository;

    //access email service
    private final EmailService emailService;

    /* event constructor
     */
    public EventService(EventRepository eventRepository, EmailService emailService) {
        this.eventRepository = eventRepository;
        this.emailService = emailService;
    }

    /* sets when email notifs are sent and hor they are formatted
     */
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
