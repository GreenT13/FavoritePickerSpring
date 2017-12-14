package com.firstspring.controller;

import com.entity.ListItemEntity;
import com.firstspring.ListItemRepository;
import com.firstspring.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListItemController {

    @Autowired
    ListRepository listRepository;

    @Autowired
    ListItemRepository listItemRepository;

    @RequestMapping(value = "api/list-item", method = RequestMethod.GET)
    public Iterable<ListItemEntity> getList(@RequestParam("listId") Integer listId ) {
        return listItemRepository.findByListEntity(listRepository.findOne(listId));
    }

}
