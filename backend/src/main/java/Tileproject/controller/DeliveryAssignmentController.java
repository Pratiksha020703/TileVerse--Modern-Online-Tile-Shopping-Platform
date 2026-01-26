package Tileproject.controller;
import org.springframework.web.bind.annotation.*;

import Tileproject.model.DeliveryAssignment;
import Tileproject.service.DeliveryAssignmentService;

	@RestController
	@RequestMapping("/api/delivery")
	public class DeliveryAssignmentController {

	    private final DeliveryAssignmentService deliveryAssignmentService;

	    public DeliveryAssignmentController(DeliveryAssignmentService deliveryAssignmentService) {
	        this.deliveryAssignmentService = deliveryAssignmentService;
	    }

	    @PostMapping("/assign")
	    public DeliveryAssignment assign(@RequestBody DeliveryAssignment assignment) {
	        return deliveryAssignmentService.assignOrder(assignment);
	    }
	}


