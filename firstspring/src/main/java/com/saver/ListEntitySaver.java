package com.saver;

import com.entity.ListEntity;
import com.firstspring.ListRepository;
import com.firstspring.Result;

public class ListEntitySaver {
    ListRepository listRepository;

    public ListEntitySaver(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public Result save(ListEntity listEntity) {
        // Check if you don't overwrite a list.
        if (listEntity.getId() != null && listRepository.findOne(listEntity.getId()) != null) {
            return new Result(Result.ID_EXISTS);
        }

        // Check constraints on externalIdentifier.
        if (listEntity.getExternalIdentifier() == null) {
            return new Result(Result.ID_EXISTS);
        }

        return null;
    }
}
