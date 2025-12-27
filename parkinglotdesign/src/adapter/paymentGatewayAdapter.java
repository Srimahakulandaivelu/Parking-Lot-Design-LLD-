package parkinglotdesign.src.adapter;

import java.util.UUID;

public interface paymentGatewayAdapter {
    boolean pay(UUID ticketId, double amount);
}
