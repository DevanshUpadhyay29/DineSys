package com.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.kitchen.entity.Order;
import com.restaurant.kitchen.exception.ResourceNotFoundException;
import com.restaurant.kitchen.feign.UserServiceClient;
import com.restaurant.kitchen.service.KitchenServiceImpl;

@ExtendWith(MockitoExtension.class)
public class KitchenServiceImplTest {

    @Mock
    private UserServiceClient userServiceClient;

    @InjectMocks
    private KitchenServiceImpl kitchenService;

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
    public void testAddOrder() {
        when(userServiceClient.placeOrder(order1)).thenReturn(order1);
        Order result = kitchenService.addOrder(order1);
        assertEquals(order1, result);
    }

    @Test
    public void testUpdateOrderStatus() {
        when(userServiceClient.getOrderById(1L)).thenReturn(order1);
        when(userServiceClient.updateOrderStatus(1L, "Completed")).thenReturn(order1);

        order1.setStatus("Completed");
        Order result = kitchenService.updateOrderStatus(1L, "Completed");

        assertEquals("Completed", result.getStatus());
    }

    @Test
    public void testUpdateOrderStatusNotFound() {
        when(userServiceClient.getOrderById(3L)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> {
            kitchenService.updateOrderStatus(3L, "Completed");
        });
    }

    @Test
    public void testGetPendingOrders() {
        List<Order> orderList = Arrays.asList(order1, order2);
        when(userServiceClient.getPendingOrders()).thenReturn(orderList);

        List<Order> result = kitchenService.getPendingOrders();
        assertEquals(orderList, result);
    }

    @Test
    public void testGetPendingOrdersNotFound() {
        when(userServiceClient.getPendingOrders()).thenReturn(Arrays.asList());
        assertThrows(ResourceNotFoundException.class, () -> {
            kitchenService.getPendingOrders();
        });
    }
}
