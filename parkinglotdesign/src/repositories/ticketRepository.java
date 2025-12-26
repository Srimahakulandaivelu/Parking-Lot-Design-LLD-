package parkinglotdesign.src.repositories;

import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import parkinglotdesign.src.domain.Ticket;

public class ticketRepository {
    private Map<UUID, Ticket> tickets = new ConcurrentHashMap<>();

    public Ticket save(Ticket ticket){
        tickets.put(ticket.getId(), ticket);

        return ticket;
    }

    public Optional<Ticket> findByid(UUID ticketId){
        return Optional.ofNullable(tickets.get(ticketId));
    }

    public List<Ticket> findActiveTicket(){
        List<Ticket> activeTickets = new ArrayList<>();

        for(Ticket ticket : tickets.values()){
            if(ticket.isActive() == true){
                activeTickets.add(ticket);
            }
        }

        return activeTickets;
    }

    public void deactivateTicket(UUID ticketId){
        Ticket ticket = tickets.get(ticketId);

        if(ticket != null){
            ticket.deactivate();
        }
    }

    public void clear(){
        tickets.clear();
    }
}
