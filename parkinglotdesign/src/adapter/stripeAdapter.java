package parkinglotdesign.src.adapter;

import java.util.Random;
import java.util.UUID;

public class stripeAdapter implements paymentGatewayAdapter{

    @Override
    public boolean pay(UUID ticketId, double amount){
        System.out.println("[ADAPTER] StripeAdapter processing payment for ticket: " + ticketId + " amount: " + amount);

        Random random = new Random();
        boolean success = random.nextDouble() < 0.9;

        System.out.println("[ADAPTER] StripeAdapter payment result: " + (success ? "SUCCESS" : "FAILED"));

        return success;
    }
}
