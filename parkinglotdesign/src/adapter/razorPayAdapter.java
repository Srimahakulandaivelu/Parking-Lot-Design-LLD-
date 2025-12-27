package parkinglotdesign.src.adapter;

import java.util.UUID;
import java.util.Random;

public class razorPayAdapter implements paymentGatewayAdapter {

    @Override
    public boolean pay(UUID ticketId, double amount){
        System.out.println("[ADAPTER] RazorpayAdapter processing payment for ticket: " + ticketId + " amount: " + amount);

        Random random = new Random();
        boolean success = random.nextDouble() < 0.9;

        System.out.println("[ADAPTER] RazorpayAdapter payment result: " + (success ? "SUCCESS" : "FAILED"));

        return success;
    }
}
