package parkinglotdesign.src.domain;

import java.util.UUID;

public class payment {
    private UUID id;
    private UUID ticketId;
    private double amount;
    private paymentGateway  gateway;
    private paymentStatus status;

    public enum paymentGateway{
        RAZORPAY, STRIPE, GPAY
    }

    public enum paymentStatus{
        PENDING, SUCCESS, FAILED
    }

    public void payment(UUID ticketId, double amount, paymentGateway gateway){
        this.id = UUID.randomUUID();
        this.ticketId = ticketId;
        this.amount = amount;
        this.gateway = gateway;
        this.status = paymentStatus.PENDING;
    }

    public void markAsPaid(){
        this.status = paymentStatus.SUCCESS;
    }

    public void markAsUnpaid(){
        this.status = paymentStatus.FAILED;
    }

    public UUID getId(){
        return id;
    }

    public UUID getTicketId(){
        return ticketId;
    }

    public double getAmount(){
        return amount;
    }

    public paymentGateway getGateway(){
        return gateway;
    }

    public paymentStatus getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return "Payment{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", amount=" + amount +
                ", gateway=" + gateway +
                ", status=" + status +
                '}';
    }
}
