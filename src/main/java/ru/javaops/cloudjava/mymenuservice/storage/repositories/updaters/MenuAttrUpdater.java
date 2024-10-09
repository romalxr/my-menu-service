package ru.javaops.cloudjava.mymenuservice.storage.repositories.updaters;

import jakarta.persistence.criteria.CriteriaUpdate;
import lombok.AllArgsConstructor;
import ru.javaops.cloudjava.mymenuservice.dto.UpdateMenuRequest;
import ru.javaops.cloudjava.mymenuservice.storage.model.MenuItem;

@AllArgsConstructor
public class MenuAttrUpdater<V> {
    public void updateAttr(CriteriaUpdate<MenuItem> criteria, UpdateMenuRequest dto) {
        //  TODO  criteria.set(attr, dtoValue);
    }
}
