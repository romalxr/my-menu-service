package ru.javaops.cloudjava.mymenuservice.storage.repositories;

import ru.javaops.cloudjava.mymenuservice.dto.SortBy;
import ru.javaops.cloudjava.mymenuservice.dto.UpdateMenuRequest;
import ru.javaops.cloudjava.mymenuservice.storage.model.Category;
import ru.javaops.cloudjava.mymenuservice.storage.model.MenuItem;

import java.util.List;

public interface CustomMenuItemRepository {

    int updateMenu(Long id, UpdateMenuRequest dto);

    List<MenuItem> getMenusFor(Category category, SortBy sortBy);
}
