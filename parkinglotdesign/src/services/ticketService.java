package parkinglotdesign.src.services;

import parkinglotdesign.src.repositories.ticketRepository;
import parkinglotdesign.src.domain.Ticket;
import parkinglotdesign.src.domain.Vehicle;

import java.util.UUID;
import java.util.Optional;

public class ticketService {
    private ticketRepository ticketRepo;

    public ticketService(ticketRepository ticketRepo){
        this.ticketRepo = ticketRepo;
    }

    public Ticket generateTicket(UUID slotId, Vehicle vehicle){
        System.out.println("[SERVICE] Generating ticket for vehicle: " + vehicle.getLicensePlate());

        Ticket ticket = new Ticket(slotId, vehicle.getId());
        ticketRepo.save(ticket);

        System.out.println("[SERVICE] Ticket generated successfully: " + ticket.getId());

        return ticket;
    }

    public Optional<Ticket> getTicket(UUID ticketId){
        System.out.println("[SERVICE] Retrieving ticket: " + ticketId);

        return ticketRepo.findByid(ticketId);
    }

    public void deactivate(UUID ticketId){
        System.out.println("[SERVICE] Deactivating ticket: " + ticketId);

        ticketRepo.deactivateTicket(ticketId);
    }
}
