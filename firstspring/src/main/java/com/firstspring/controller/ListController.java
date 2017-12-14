package com.firstspring.controller;

import com.entity.ListEntity;
import com.entity.ListItemEntity;
import com.entity.LogonUserEntity;
import com.firstspring.ListRepository;
import com.firstspring.LogonUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListController {

    @Autowired
    ListRepository listRepository;

    @Autowired
    LogonUserRepository logonUserRepository;

    @RequestMapping(value = "api/lists", method = RequestMethod.GET)
    public Iterable<ListEntity> getLists(@RequestParam("logonUserId") Integer logonUserId) {
        return listRepository.findByLogonUserEntity(logonUserRepository.findOne(logonUserId));
    }

    @RequestMapping(value = "api/list", method = RequestMethod.GET)
    public ListEntity getList(@RequestParam("listId") Integer listId) {
        return listRepository.findOne(listId);
    }

    @RequestMapping(value = "api/add-list", method = RequestMethod.POST)
    public String addList(@RequestBody ListEntity listEntity,
                          @RequestParam("logonUserId") Integer logonUserId) {
        listEntity.setLogonUserEntity(logonUserRepository.findOne(logonUserId));

        // Must set a value for hasStartedAlgorithm
        if (listEntity.getHasStartedAlgorithm() == null) {
            // Set to default value.
            listEntity.setHasStartedAlgorithm(false);
        }

        listRepository.save(listEntity);
        return "Saved the list";
    }


}
