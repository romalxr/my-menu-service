package ru.javaops.cloudjava.mymenuservice.service;

import ru.javaops.cloudjava.mymenuservice.dto.CreateMenuRequest;
import ru.javaops.cloudjava.mymenuservice.dto.MenuItemDto;
import ru.javaops.cloudjava.mymenuservice.dto.SortBy;
import ru.javaops.cloudjava.mymenuservice.dto.UpdateMenuRequest;
import ru.javaops.cloudjava.mymenuservice.storage.model.Category;

import java.util.List;

public interface MenuService {

    MenuItemDto createMenuItem(CreateMenuRequest dto);

    void deleteMenuItem(Long id);

    MenuItemDto updateMenuItem(Long id, UpdateMenuRequest update);

    MenuItemDto getMenu(Long id);

    List<MenuItemDto> getMenusFor(Category category, SortBy sortBy);

}
