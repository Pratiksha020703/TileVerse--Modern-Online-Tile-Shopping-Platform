package Tileproject.service;



	import org.springframework.stereotype.Service;

import Tileproject.model.DeliveryAssignment;
import Tileproject.repository.DeliveryAssignmentRepository;

import java.time.LocalDateTime;

	@Service
	public class DeliveryAssignmentService {

	    private final DeliveryAssignmentRepository deliveryAssignmentRepository;

	    public DeliveryAssignmentService(DeliveryAssignmentRepository deliveryAssignmentRepository) {
	        this.deliveryAssignmentRepository = deliveryAssignmentRepository;
	    }

	    public DeliveryAssignment assignOrder(DeliveryAssignment assignment) {
	        assignment.setAssignedDate(LocalDateTime.now());
	        assignment.setDeliveryStatus("ASSIGNED");
	        return deliveryAssignmentRepository.save(assignment);
	    }
	}


