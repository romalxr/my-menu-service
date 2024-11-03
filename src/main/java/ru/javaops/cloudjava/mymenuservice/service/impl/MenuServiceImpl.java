package ru.javaops.cloudjava.mymenuservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.javaops.cloudjava.mymenuservice.dto.CreateMenuRequest;
import ru.javaops.cloudjava.mymenuservice.dto.MenuItemDto;
import ru.javaops.cloudjava.mymenuservice.dto.SortBy;
import ru.javaops.cloudjava.mymenuservice.dto.UpdateMenuRequest;
import ru.javaops.cloudjava.mymenuservice.exception.MenuServiceException;
import ru.javaops.cloudjava.mymenuservice.mapper.MenuItemMapper;
import ru.javaops.cloudjava.mymenuservice.service.MenuService;
import ru.javaops.cloudjava.mymenuservice.storage.model.Category;
import ru.javaops.cloudjava.mymenuservice.storage.repositories.MenuItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuItemMapper mapper;
    private final MenuItemRepository repository;

    @Override
    public MenuItemDto createMenuItem(CreateMenuRequest dto) {
        var menu = mapper.toDomain(dto);
        try {
            return mapper.toDto(repository.save(menu));
        } catch (DataIntegrityViolationException e) {
            var msg = String.format("Failed to create MenuItem: %s. Reason: Item with name %s already exists.", dto, dto.getName());
            throw new MenuServiceException(msg, HttpStatus.CONFLICT);
        }
    }

    @Override
    public void deleteMenuItem(Long id) {
        repository.deleteById(id);
    }

    @Override
    public MenuItemDto updateMenuItem(Long id, UpdateMenuRequest update) {
        try {
            var updateCount = repository.updateMenu(id, update);
            if (updateCount == 0) {
                var msg = String.format("MenuItem with id=%d not found.", id);
                throw new MenuServiceException(msg, HttpStatus.NOT_FOUND);
            }
            return getMenu(id);
        } catch (DataIntegrityViolationException e) {
            var msg = String.format("Failed to create MenuItem: %s. Reason: Item with name %s already exists.", update, update.getName());
            throw new MenuServiceException(msg, HttpStatus.CONFLICT);
        }
    }

    @Override
    public MenuItemDto getMenu(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> {
                    var msg = String.format("Failed to find MenuItem by id %s.",id);
                    return new MenuServiceException(msg, HttpStatus.NOT_FOUND);
                });
    }

    @Override
    public List<MenuItemDto> getMenusFor(Category category, SortBy sortBy) {
        return mapper.toDtoList(repository.getMenusFor(category, sortBy));
    }
}
