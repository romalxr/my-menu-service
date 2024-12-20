package ru.javaops.cloudjava.mymenuservice.storage.repositories.updaters;

import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.AllArgsConstructor;
import ru.javaops.cloudjava.mymenuservice.dto.UpdateMenuRequest;
import ru.javaops.cloudjava.mymenuservice.storage.model.MenuItem;

import java.util.function.Function;

@AllArgsConstructor
public class MenuAttrUpdater<V> {
    SingularAttribute<MenuItem, V> attr;
    Function<UpdateMenuRequest, V> dtoValueExtractor;

    public void updateAttr(CriteriaUpdate<MenuItem> criteria, UpdateMenuRequest dto) {
        V dtoValue = dtoValueExtractor.apply(dto);
        if (dtoValue != null) {
            criteria.set(attr, dtoValue);
        }
    }
}
