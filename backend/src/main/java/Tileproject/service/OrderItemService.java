package Tileproject.service;
import org.springframework.stereotype.Service;
import Tileproject.model.OrderItem;
import Tileproject.repository.OrderItemRepository;

import java.util.List;

	@Service
	public class OrderItemService {

	    private final OrderItemRepository orderItemRepository;

	    public OrderItemService(OrderItemRepository orderItemRepository) {
	        this.orderItemRepository = orderItemRepository;
	    }

	    public List<OrderItem> getItemsByOrder(Integer orderId) {
	        return orderItemRepository.findByOrder_OrderId(orderId);
	    }
	}
