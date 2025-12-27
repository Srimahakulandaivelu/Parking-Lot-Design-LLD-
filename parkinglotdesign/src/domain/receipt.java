package parkinglotdesign.src.domain;

import java.util.UUID;
import java.time.LocalDateTime;

public class receipt {
    private UUID id;
    private UUID ticketId;
    private LocalDateTime exitTime;
    private double totalFee;
    private paymentStatus status;

    public enum paymentStatus{
        SUCCESS, FAILED, PENDING
    }

    public receipt(UUID ticketId, double totalFee){
        this.id = UUID.randomUUID();
        this.ticketId = ticketId;
        this.exitTime = LocalDateTime.now();
        this.totalFee = totalFee;
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

    public LocalDateTime getExitTime(){
        return exitTime;
    }

    public double getTotalFee(){
        return totalFee;
    }

    public paymentStatus getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return "Receipt{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", exitTime=" + exitTime +
                ", totalFee=" + totalFee +
                ", paymentStatus=" + status +
                '}';
    }

}
