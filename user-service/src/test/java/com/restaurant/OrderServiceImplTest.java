package com.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.user.entity.MenuItem;
import com.restaurant.user.entity.Order;
import com.restaurant.user.exception.InvalidOrderStatusException;
import com.restaurant.user.exception.OrderNotFoundException;
import com.restaurant.user.feign.AdminServiceClient;
import com.restaurant.user.repository.OrderRepository;
import com.restaurant.user.service.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private AdminServiceClient adminServiceClient;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order1;
    private Order order2;

    @BeforeEach
    public void setUp() {
        order1 = new Order();
        order1.setId(1L);
        order1.setDishName("Pasta");
        order1.setStatus("Pending");

        order2 = new Order();
        order2.setId(2L);
        order2.setDishName("Pizza");
        order2.setStatus("Pending");
    }

    @Test
    public void testViewMenu() {
        List<MenuItem> menuList = Arrays.asList(new MenuItem(), new MenuItem());
        when(adminServiceClient.getMenu()).thenReturn(menuList);

        List<MenuItem> result = orderService.viewMenu();
        assertEquals(menuList, result);
    }

    @Test
    public void testGetOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
        Order result = orderService.getOrderById(1L);
        assertEquals(order1, result);
    }

    @Test
    public void testGetOrderByIdNotFound() {
        when(orderRepository.findById(3L)).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> {
            orderService.getOrderById(3L);
        });
    }

    @Test
    public void testSaveOrder() {
        when(orderRepository.save(order1)).thenReturn(order1);
        Order result = orderService.saveOrder(order1);
        assertEquals(order1, result);
    }

    @Test
    public void testUpdateOrderStatus() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
        when(orderRepository.save(order1)).thenReturn(order1);

        order1.setStatus("Accepted");
        Order result = orderService.updateOrderStatus(1L, "Accepted");

        assertEquals("Accepted", result.getStatus());
    }

    @Test
    public void testUpdateOrderStatusInvalid() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
        assertThrows(InvalidOrderStatusException.class, () -> {
            orderService.updateOrderStatus(1L, "InvalidStatus");
        });
    }

    @Test
    public void testGetOrdersByStatus() {
        List<Order> orderList = Arrays.asList(order1, order2);
        when(orderRepository.findByStatus("Pending")).thenReturn(orderList);

        List<Order> result = orderService.getOrdersByStatus("Pending");
        assertEquals(orderList, result);
    }
}
