package ru.javaops.cloudjava.mymenuservice.storage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaops.cloudjava.mymenuservice.storage.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>, CustomMenuItemRepository {
}
