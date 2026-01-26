package Tileproject.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DeliveryAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deliveryAssignmentId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private DeliveryPersonnel deliveryPerson;

    private String deliveryStatus;
    private LocalDateTime assignedDate;
    private LocalDateTime deliveredDate;

    // ===== GETTERS & SETTERS =====

    public Integer getDeliveryAssignmentId() {
        return deliveryAssignmentId;
    }

    public void setDeliveryAssignmentId(Integer deliveryAssignmentId) {
        this.deliveryAssignmentId = deliveryAssignmentId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public DeliveryPersonnel getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPersonnel deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }

    public LocalDateTime getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(LocalDateTime deliveredDate) {
        this.deliveredDate = deliveredDate;
    }
}


