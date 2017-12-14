package com.algorithm;

import com.entity.ListEntity;
import com.entity.ListItemEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that will be send to the front-end.
 */
public class ChoiceResponse {
    List<ListItemEntity> listItems;

    @JsonCreator
    public ChoiceResponse(@JsonProperty("listItems") List<ListItemEntity> listItems) {
        this.listItems = listItems;
    }

    /**
     * Verifies the choice/chosen on the following conditions:
     * 1. listItems contains at least 1 element.
     * 2. Every element in choice is not contained in the favorites.
     * @param listEntity
     * @return
     */
    public boolean isValidRequest(ListEntity listEntity) {
        // 1. listItems contains at least 1 element.
        if (listItems == null || listItems.size() < 1) {
            return false;
        }

        // 2. Every element in choice is not contained in the favorites.
        ListEntityUtil listEntityUtil = new ListEntityUtil(null, listEntity);
        for (ListItemEntity item : getChosen()) {
            if (listEntityUtil.getFavorites().contains(item)) {
                return false;
            }
        }

        return true;
    }

    @JsonIgnore
    public List<ListItemEntity> getChosen() {
        List<ListItemEntity> chosen = new ArrayList<ListItemEntity>();
        for (ListItemEntity item : listItems) {
            if (item.getChosen() != null && item.getChosen().equals(Boolean.TRUE)) {
                chosen.add(item);
            }
        }

        return chosen;
    }


    public List<ListItemEntity> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItemEntity> listItems) {
        this.listItems = listItems;
    }
}
