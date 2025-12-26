package parkinglotdesign.src.repositories;

import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import parkinglotdesign.src.domain.payment;


public class paymentRepository {
    private Map<UUID, payment> payments = new ConcurrentHashMap<>();
    private Map<UUID, List<UUID>> ticketToPayments = new ConcurrentHashMap<>();

    public payment save(payment payment){
        payments.put(payment.getId(), payment);

        ticketToPayments.computeIfAbsent(payment.getTicketId(), k -> new ArrayList<>()).add(payment.getId());

        return payment;
    }

    public Optional<payment> findById(UUID paymentId){
        return Optional.ofNullable(payments.get(paymentId));
    }

    public List<payment> findByTicketId(UUID ticketId){
        List<payment> result = new ArrayList<>();
        List<UUID> ids = ticketToPayments.get(ticketId);

        if(ids != null){
            for(UUID id : ids){
                payment p = payments.get(id);
                if(p != null){
                    result.add(p);
                }
            }
        }

        return result;
    }

    private List<payment> findAll(){
        return new ArrayList<>(payments.values());
    }

    private void update(payment payment){
        if(payments.containsKey(payment.getId())){
            payments.put(payment.getId(), payment);
        }
    }

    private void delete(UUID paymentId){
        payment payment = payments.remove(paymentId);

        if(payment != null){
            List<UUID> ticketPayments = ticketToPayments.get(payment.getTicketId());
            if(ticketPayments != null){
                ticketPayments.remove(paymentId);
            }
        }
    }

    public void clear(){
        payments.clear();
        ticketToPayments.clear();
    }
}
