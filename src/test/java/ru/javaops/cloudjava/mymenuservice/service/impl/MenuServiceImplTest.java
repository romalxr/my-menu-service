package ru.javaops.cloudjava.mymenuservice.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.javaops.cloudjava.mymenuservice.BaseTest;
import ru.javaops.cloudjava.mymenuservice.dto.MenuItemDto;
import ru.javaops.cloudjava.mymenuservice.dto.SortBy;
import ru.javaops.cloudjava.mymenuservice.exception.MenuServiceException;
import ru.javaops.cloudjava.mymenuservice.service.MenuService;
import ru.javaops.cloudjava.mymenuservice.storage.model.Category;
import ru.javaops.cloudjava.mymenuservice.storage.repositories.MenuItemRepository;
import ru.javaops.cloudjava.mymenuservice.testutils.TestData;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class MenuServiceImplTest extends BaseTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void getMenusFor_DRINKS_returnsCorrectList() {
        List<MenuItemDto> drinks = menuService.getMenusFor(Category.DRINKS, SortBy.AZ);
        assertThat(drinks).hasSize(3);
        assertElementsInOrder(drinks, MenuItemDto::getName, List.of("Cappuccino", "Tea", "Wine"));
    }

    @Test
    public void createMenuItem_createsMenuItem() {
        var dto = TestData.createMenuRequest();
        var now = LocalDateTime.now().minusNanos(1000);
        MenuItemDto result = menuService.createMenuItem(dto);
        assertThat(result.getId()).isNotNull();
        assertFieldsEquality(result, dto, "name", "description", "price", "imageUrl", "timeToCook");
        assertThat(result.getCreatedAt()).isAfter(now);
        assertThat(result.getUpdatedAt()).isAfter(now);
    }

    @Test
    void createMenuItem_throwsWhenItemWithThatNameExists() {
        var dto = TestData.createMenuRequest();
        dto.setName("Cappuccino");
        assertThrows(
                MenuServiceException.class,
                () -> menuService.createMenuItem(dto)
        );
    }

    @Test
    public void getMenu_returnsMenu_whenMenuInDb() {
        Long id = getIdByName("Cappuccino");
        MenuItemDto result = menuService.getMenu(id);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    public void getMenu_throws_whenNoMenuInDb() {
        Long id = -100L;
        assertThrows(MenuServiceException.class, () -> menuService.getMenu(id));
    }

    @Test
    public void deleteMenuItem_deletesMenuItem() {
        Long id = getIdByName("Cappuccino");
        menuService.deleteMenuItem(id);
        assertThrows(MenuServiceException.class, () -> menuService.getMenu(id));
    }

    @Test
    void updateMenuItem_updatesMenuItem_whenItemPresentInDb() {
        Long id = getIdByName("Cappuccino");
        var update = TestData.updateMenuFullRequest();
        MenuItemDto result = menuService.updateMenuItem(id, update);
        assertFieldsEquality(result, update, "name", "description", "price", "imageUrl", "timeToCook");
    }

    @Test
    void updateMenuItem_throws_whenNoItemInDb() {
        Long id = 1000L;
        var update = TestData.updateMenuFullRequest();
        assertThrows(MenuServiceException.class, () -> menuService.updateMenuItem(id, update));
    }

    @Test
    void updateMenuItem_throws_whenUpdateRequestContainsNotUniqueName() {
        Long id = getIdByName("Cappuccino");
        var update = TestData.updateMenuFullRequest();
        update.setName("Wine");
        assertThrows(MenuServiceException.class, () -> menuService.updateMenuItem(id, update));
    }

}
