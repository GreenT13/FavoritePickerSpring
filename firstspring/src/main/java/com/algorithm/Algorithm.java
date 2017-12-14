package com.algorithm;

import com.entity.ListEntity;
import com.entity.ListItemEntity;
import com.firstspring.ListItemRepository;

import java.util.List;

public class Algorithm {
    // Contains the following variables for the algorithm:
    // 1. listInput
    // 2. sizeChoiceList
    // 3. favorites (because the listInput contains rank element).
    ListEntityUtil listEntityUtil;

    public Algorithm(ListItemRepository listItemRepository, ListEntity listEntity) throws AlgorithmException {
        if (listEntity == null) {
            throw new AlgorithmException("Cannot use null listEntity");
        }

        listEntityUtil = new ListEntityUtil(listItemRepository, listEntity);

        if (listEntityUtil.getSizeChoice() < Integer.valueOf(2)) {
            throw new AlgorithmException("Size of the choice list must be at least 2.");
        }
    }

    /**
     * Determine whether the algorithm is finished.
     * @return {@code true} if more choices are needed, {@code false} if the algorithm has finished.
     */
    public Boolean hasNextChoice() {
        return Boolean.valueOf(listEntityUtil.getFavorites().size() != listEntityUtil.getListInput().size());
    }

    /**
     * Give a list of elements for which the user has to pick the favorites.
     * @return ChoiceResponse
     */
    public ChoiceResponse getNextChoice() {
        // Pick sizeChoiceList number of elements from the not-eliminated elements.
        return new ChoiceResponse(listEntityUtil.pickMaximalSizeList());
    }

    /**
     * Return the elements chosen from the list derived by getNextChoice().
     * @param choiceResponse
     * @return Result object indicating whether progress has been made.
     * @throws AlgorithmException if the choiceResponse is not valid.
     */
    public void makeChoice(ChoiceResponse choiceResponse) throws AlgorithmException {
        if (choiceResponse == null || !choiceResponse.isValidRequest(listEntityUtil.getListEntity())) {
            throw new AlgorithmException("Invalid request.");
        }

        // For each item in 'choice' that is not in 'chosen', fill eliminatedBy with each element of 'chosen'.
        // We cannot edit elements of choiceResponse, since these are not from the database.
        // Therefore we create a list from the elements from listEntity.
        List<ListItemEntity> choice = listEntityUtil.getListFromChoiceRequest(choiceResponse.getListItems());
        List<ListItemEntity> chosen = listEntityUtil.getListFromChoiceRequest(choiceResponse.getChosen());

        for (ListItemEntity item : choice) {
            if (!chosen.contains(item)) {
                // Add each item from chosen to the eliminated-list from item.
                for (ListItemEntity eliminatedByListItem : chosen) {
                    item.addEliminatedby(eliminatedByListItem);
                }
            }
        }

        // Test whether favorite is determined. Make it a while loop, so if only one choice is left we pick it.
        while (listEntityUtil.getNotEliminated().size() == 1) {
            // Add element to favorites.
            listEntityUtil.addToFavorites(listEntityUtil.getNotEliminated().get(0));
        }
    }
}
