package Tileproject.service;



	import org.springframework.stereotype.Service;

import Tileproject.model.DeliveryPersonnel;
import Tileproject.repository.DeliveryPersonnelRepository;

	@Service
	public class DeliveryPersonnelService {

	    private final DeliveryPersonnelRepository deliveryPersonnelRepository;

	    public DeliveryPersonnelService(DeliveryPersonnelRepository deliveryPersonnelRepository) {
	        this.deliveryPersonnelRepository = deliveryPersonnelRepository;
	    }

	    public DeliveryPersonnel addDeliveryPerson(DeliveryPersonnel person) {
	        return deliveryPersonnelRepository.save(person);
	    }
	}


