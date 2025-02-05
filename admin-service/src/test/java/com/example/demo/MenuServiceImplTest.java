package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.restaurant.admin.entity.Menu;
import com.restaurant.admin.exception.MenuNotFoundException;
import com.restaurant.admin.repository.MenuRepository;
import com.restaurant.admin.service.MenuServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MenuServiceImplTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuServiceImpl menuService;

    private Menu menu1;
    private Menu menu2;

    @BeforeEach
    public void setUp() {
        menu1 = new Menu();
        menu1.setId(1L);
        menu1.setDishName("Pasta");
        menu1.setPrice(250.0);

        menu2 = new Menu();
        menu2.setId(2L);
        menu2.setDishName("Pizza");
        menu2.setPrice(300.0);
    }

    @Test
    public void testAddDish() {
        when(menuRepository.save(menu1)).thenReturn(menu1);
        Menu result = menuService.addDish(menu1);
        assertEquals(menu1, result);
    }

    @Test
    public void testUpdateDish() {
        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu1));
        when(menuRepository.save(menu1)).thenReturn(menu1);

        menu1.setDishName("Spaghetti");
        menu1.setPrice(275.0);
        Menu result = menuService.updateDish(1L, menu1);

        assertEquals("Spaghetti", result.getDishName());
        assertEquals(275.0, result.getPrice());
    }

    @Test
    public void testUpdateDishNotFound() {
        when(menuRepository.findById(3L)).thenReturn(Optional.empty());
        assertThrows(MenuNotFoundException.class, () -> {
            menuService.updateDish(3L, menu1);
        });
    }

    @Test
    public void testDeleteDish() {
        when(menuRepository.existsById(1L)).thenReturn(true);
        doNothing().when(menuRepository).deleteById(1L);

        menuService.deleteDish(1L);

        verify(menuRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteDishNotFound() {
        when(menuRepository.existsById(3L)).thenReturn(false);
        assertThrows(MenuNotFoundException.class, () -> {
            menuService.deleteDish(3L);
        });
    }

    @Test
    public void testGetAllDishes() {
        List<Menu> menuList = Arrays.asList(menu1, menu2);
        when(menuRepository.findAll()).thenReturn(menuList);

        List<Menu> result = menuService.getAllDishes();
        assertEquals(menuList, result);
    }
}
