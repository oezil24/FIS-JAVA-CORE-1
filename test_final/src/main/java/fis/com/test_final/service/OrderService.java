package fis.com.test_final.service;

import fis.com.test_final.dto.AddOrderItemDTO;
import fis.com.test_final.dto.CreateOrderDTO;
import fis.com.test_final.dto.OrderDTO;
import fis.com.test_final.dto.RemoveItemDTO;
import fis.com.test_final.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface OrderService {
    Page<OrderDTO> findAll(Pageable pageable);
    Order findById(Long orderId);
    Order create(CreateOrderDTO createOrderDTO);
    void delete(Long orderId);
    Order addOrderItem(AddOrderItemDTO addOrderItemDTO);

    Order removeOrderItem(RemoveItemDTO removeItemDTO);
}
