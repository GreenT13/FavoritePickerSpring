package com.firstspring.controller;

import com.algorithm.Algorithm;
import com.algorithm.AlgorithmException;
import com.algorithm.ChoiceResponse;
import com.algorithm.ListEntityUtil;
import com.entity.ListEntity;
import com.entity.ListItemEntity;
import com.firstspring.ListItemRepository;
import com.firstspring.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlgorithmController {
    @Autowired
    ListRepository listRepository;
    @Autowired
    ListItemRepository listItemRepository;

    @RequestMapping(value = "api/algorithm", method = RequestMethod.GET)
    public ChoiceResponse getChoice(@RequestParam("listId") Integer listId) {
        // Execute the algorithm.
        try {
            Algorithm algorithm = new Algorithm(listItemRepository, listRepository.findOne(listId));
            return algorithm.getNextChoice();
        } catch (AlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "api/algorithm", method = RequestMethod.POST)
    public ChoiceResponse makeChoice(@RequestParam("listId") Integer listId,
                                     @RequestBody ChoiceResponse choiceResponse) {
        // Make sure that the list is filled on each request.
        ListEntity listEntity = listRepository.findOne(listId);

        // Retrieve all items, seems to be needed (without it gives errors).
        ListEntityUtil util = new ListEntityUtil(listItemRepository, listEntity);
        util.getNotEliminated();

        try {
            Algorithm algorithm = new Algorithm(listItemRepository, listEntity);
            algorithm.makeChoice(choiceResponse);

            System.out.println("Starting to save all items");
            for (ListItemEntity item : listEntity.getItems()) {
                listItemRepository.save(item);
            }
            System.out.println("Saving done.");

            return algorithm.getNextChoice();
        } catch (AlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
