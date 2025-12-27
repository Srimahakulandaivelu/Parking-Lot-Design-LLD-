package parkinglotdesign.src.services;

import parkinglotdesign.src.adapter.razorPayAdapter;
import parkinglotdesign.src.adapter.stripeAdapter;
import parkinglotdesign.src.repositories.paymentRepository;
import parkinglotdesign.src.adapter.paymentGatewayAdapter;
import parkinglotdesign.src.domain.payment;
import parkinglotdesign.src.domain.payment.paymentGateway;

import java.util.UUID;

public class paymentService {
    private paymentRepository paymentRepo;
    private paymentGatewayAdapter defaultgateway;

    public paymentService(paymentRepository paymentRepo){
        this.paymentRepo = paymentRepo;
        this.defaultgateway = new razorPayAdapter();

        System.out.println("[SERVICE] PaymentService initialized with default gateway: Razorpay");
    }

    public boolean processPayment(UUID ticketId, double amount){
        System.out.println("[SERVICE] Processing payment for ticket: " + ticketId + " amount: " + amount);

        payment payment = new payment(ticketId, amount, paymentGateway.RAZORPAY);
        paymentRepo.save(payment);

        boolean success = defaultgateway.pay(ticketId, amount);

        if(success){
            payment.markAsPaid();
        } else {
            payment.markAsUnpaid();
        }

        paymentRepo.update(payment);

        System.out.println("[SERVICE] Payment processed with status: " + (success ? "SUCCESS" : "FAILED"));

        return success;
    }

    public boolean processPaymentWithRetries(UUID ticketId, double amount, int maxRetries){
        System.out.println("[SERVICE] Processing payment with retry for ticket: " + ticketId);

        for(int attempt = 1; attempt <= maxRetries; attempt++){
            System.out.println("[SERVICE] Payment attempt " + attempt + " of " + maxRetries);

            boolean success = processPayment(ticketId, amount);

            if(success){
                System.out.println("[SERVICE] Payment successful on attempt " + attempt);
                return true;
            }

            if(attempt > 1){
                defaultgateway = new stripeAdapter();
                System.out.println("[SERVICE] Switching to Stripe gateway for retry");
            }
        }

        System.out.println("[SERVICE] Payment failed after " + maxRetries + " attempts");

        return false;

    }

    public void setDefaultGateway(paymentGatewayAdapter gateway) {
        this.defaultgateway = gateway;
    }


}
