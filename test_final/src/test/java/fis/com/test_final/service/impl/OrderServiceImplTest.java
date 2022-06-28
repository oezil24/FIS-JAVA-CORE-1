package fis.com.test_final.service.impl;

import fis.com.test_final.dto.CreateOrderDTO;
import fis.com.test_final.dto.DetailOrderDTO;
import fis.com.test_final.dto.OrderDTO;
import fis.com.test_final.model.*;
import fis.com.test_final.repository.OrderRepo;
import fis.com.test_final.service.CustomerService;
import fis.com.test_final.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@Slf4j
class OrderServiceImplTest {
    private OrderServiceImpl orderService;
    private ProductService productService;
    private CustomerService customerService;

    private OrderRepo orderRepo;
    private Order order;
    private Product product;
    private Customer customer;
    private Pageable pageable;
    @BeforeEach
    void init() {
        pageable = mock(Pageable.class);
        orderRepo = mock(OrderRepo.class);
        customerService = mock(CustomerService.class);
        productService = mock(ProductService.class);
        orderService = new OrderServiceImpl(orderRepo,customerService, productService );
        customer = Customer.builder()
                .id(1L)
                .name("Le Viet Ha")
                .mobile("0968556308")
                .address("Ha Noi")
                .build();
        product = Product.builder()
                .id(1L)
                .name("quan ao")
                .avaiable(100L)
                .price(8000.0)
                .build();
        OrderItem orderItem = OrderItem.builder()
                .id(1L)
                .amount(16000.0)
                .quantity(2L)
                .product(product)
                .build();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        order = Order.builder()
                .id(1L)
                .customer(customer)
                .totalAmount(1000.0)
                .status(OrderStatus.CREATED)
                .orderDateTime(LocalDateTime.now())
                .orderItems(orderItems)
                .build();
    }
    @Test
    void findAll() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        Page<Order> orders = new PageImpl<>(orderList);

        when(orderRepo.findAll(pageable)).thenReturn(orders);
        log.info("Order List size: {}", orderList.size());
        Page<OrderDTO> orderDTOPage = orderService.findAll(pageable);
        orderDTOPage.forEach(orderInPage -> log.info("Order in page: {}", orderInPage));
        assertEquals(orderDTOPage.getTotalElements(), orders.getTotalElements());
        verify(orderRepo, times(1)).findAll(pageable);
    }
}