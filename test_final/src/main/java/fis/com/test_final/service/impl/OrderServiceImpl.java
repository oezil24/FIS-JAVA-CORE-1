package fis.com.test_final.service.impl;

import fis.com.test_final.dto.AddOrderItemDTO;
import fis.com.test_final.dto.CreateOrderDTO;
import fis.com.test_final.dto.OrderDTO;
import fis.com.test_final.dto.RemoveItemDTO;
import fis.com.test_final.exception.*;
import fis.com.test_final.model.*;
import fis.com.test_final.repository.OrderItemRepo;
import fis.com.test_final.repository.OrderRepo;
import fis.com.test_final.service.CustomerService;
import fis.com.test_final.service.OrderService;
import fis.com.test_final.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final CustomerService customerService;

    private final ProductService productService;
    @Autowired
    private OrderItemRepo orderItemRepo;
    public OrderServiceImpl(OrderRepo orderRepo, CustomerService customerService, ProductService productService) {
        this.orderRepo = orderRepo;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        log.info("Query all order: PageNumber: {}, PageSize: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<OrderDTO> map = orderRepo.findAll(pageable).map(OrderDTO.Mapper::mapFromOrderEntity);
        return map;
    }

    @Override
    public Order findById(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> {
            try {
                throw new OrderNotFoundException(String.format("Not found order with id %s", orderId));
            } catch (OrderNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Service: Order: {}", order);
        return order;
    }

    @Override
    public Order create(CreateOrderDTO createOrderDTO) {
        Customer customer = customerService.findById(createOrderDTO.getCustomerId());
        List<OrderItem> orderItemList = new ArrayList<>();
        createOrderDTO.getOrderItemInfo().forEach(productQuantityDTO -> {
            Product product = productService.findById(productQuantityDTO.getProductId());
            if (product.getAvaiable() < productQuantityDTO.getQuantity()) {
                try {
                    throw new ProductNotEnoughtException(String.format("Product %s Not Enought !!", product.getName()));
                } catch (ProductNotEnoughtException e) {
                    throw new RuntimeException(e);
                }
            }
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(productQuantityDTO.getQuantity())
                    .amount(product.getPrice() * productQuantityDTO.getQuantity())
                    .build();
            orderItemList.add(orderItem);
        });
        Order order = Order.builder()
                .orderDateTime(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .customer(customer)
                .orderItems(orderItemList)
                .totalAmount(orderItemList.stream().mapToDouble(OrderItem::getAmount).sum())
                .build();
        orderRepo.save(order);
        return order;
    }

    @Override
    public void delete(Long orderId) {
        Order order = findById(orderId);
        if (OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanNotDeleteCreatedStatusOrderException(
                        "Can not delete Order has status is CREATED!");
            } catch (CanNotDeleteCreatedStatusOrderException e) {
                throw new RuntimeException(e);
            }
        }
        if (OrderStatus.CANCELLED.equals(order.getStatus())) {
            try {
                throw new CanNotDeleteCancelledStatusOrderException(
                        "Can not delete Order has status is CANCELLED!");
            } catch (CanNotDeleteCancelledStatusOrderException e) {
                throw new RuntimeException(e);
            }
        }
        orderRepo.deleteById(orderId);
    }

    @Override
    public Order addOrderItem(AddOrderItemDTO addOrderItemDTO) {
        Order order = findById(addOrderItemDTO.getOrderId());
        if (null == order) {
            try {
                throw new OrderNotFoundException(String.format("Not found order with id %s", addOrderItemDTO.getOrderId()));
            } catch (OrderNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (!OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanOnlyAddOrderItemToCreatedOrderException("Can only add order item to order has status is CREATED!");
            } catch (CanOnlyAddOrderItemToCreatedOrderException e) {
                throw new RuntimeException(e);
            }
        }
        Product product = productService.findById(addOrderItemDTO.getProductId());
        if (product.getAvaiable() < addOrderItemDTO.getQuantity()) {
            try {
                throw new ProductNotEnoughtException(String.format("Product %s Not Enought !!", product.getName()));
            } catch (ProductNotEnoughtException e) {
                throw new RuntimeException(e);
            }
        }
        OrderItem newOrderItem = OrderItem.builder()
                .amount(product.getPrice() * addOrderItemDTO.getQuantity())
                .quantity(addOrderItemDTO.getQuantity())
                .order(order)
                .product(product)
                .build();
        order.setTotalAmount(order.getTotalAmount() + newOrderItem.getAmount());
        order.getOrderItems().add(newOrderItem);
        orderRepo.save(order);
        product.setAvaiable(product.getAvaiable() - addOrderItemDTO.getQuantity());
        productService.update(product);
        return findById(addOrderItemDTO.getOrderId());
    }

    @Override
    public Order removeOrderItem(RemoveItemDTO removeItemDTO) {
        Order order = findById(removeItemDTO.getOrderId());
        if (null == order) {
            try {
                throw new OrderNotFoundException(String.format("Not found order with id %s", removeItemDTO.getOrderId()));
            } catch (OrderNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (!OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanOnlyRemoveOrderItemOnCreatedOrderException(
                        "Can only remove order item on order has status is CREATED!");
            } catch (CanOnlyRemoveOrderItemOnCreatedOrderException e) {
                throw new RuntimeException(e);
            }
        }
        OrderItem orderItem = orderItemRepo.findById(removeItemDTO.getOrderItemId()).orElseThrow(() -> {
            try {
                throw new OrderItemNotFoundException(String.format("Not Found Order Item With id %s",
                        removeItemDTO.getOrderItemId()));
            } catch (OrderItemNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        order.setTotalAmount(order.getTotalAmount() - orderItem.getAmount());
        Product product = productService.findById(orderItem.getProduct().getId());
        product.setAvaiable(product.getAvaiable() + orderItem.getQuantity());
        orderItemRepo.deleteById(removeItemDTO.getOrderItemId());

        return findById(removeItemDTO.getOrderId());
    }


}
