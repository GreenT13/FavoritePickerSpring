package com.algorithm;

import com.entity.ListEntity;
import com.entity.ListItemEliminatedbyEntity;
import com.entity.ListItemEntity;
import com.firstspring.ListItemRepository;

import java.util.ArrayList;
import java.util.List;

public class ListEntityUtil {
    ListItemRepository listItemRepository;

    // Fixed listEntity because I call the functions a lot.
    ListEntity listEntity;

    public ListEntityUtil(ListItemRepository listItemRepository, ListEntity listEntity) {
        this.listItemRepository = listItemRepository;
        this.listEntity = listEntity;
    }

    public List<ListItemEntity> getListInput() {
        return listEntity.getItems();
    }

    public Integer getSizeChoice() {
        return listEntity.getSizeChoiceList();
    }

    public List<ListItemEntity> getFavorites() {
        List<ListItemEntity> list = new ArrayList<ListItemEntity>();
        for (ListItemEntity item : listEntity.getItems()) {
            if (item.getRank() != null) {
                list.add(item);
            }
        }

        return list;
    }

    public List<ListItemEntity> getNotEliminated() {
        // Must be ArrayList, since we use it in pickMaximalSizeList().
        List<ListItemEntity> list = new ArrayList<ListItemEntity>();
        for (ListItemEntity item : listEntity.getItems()) {
            if (item.getEliminatedbyItems().size() == 0 && item.getRank() == null) {
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Rearrange elements such that elements which have less 'eliminatedBy' elements are chosen first. After that,
     * pick at most 'sizeChoice' elements.
     * @return
     */
    public List<ListItemEntity> pickMaximalSizeList() {
        ArrayList<ListItemEntity> list = (ArrayList) getNotEliminated();

        // Sort the list based on eliminatedbyItems.size()
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).getEliminatedbyItems().size() > list.get(j + 1).getEliminatedbyItems().size()) {
                    //swap elements
                    ListItemEntity temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    flag = true;
                }
            }
        }

        return ListUtil.pickMaximalSizeList(getNotEliminated(), getSizeChoice());
    }

    public void addToFavorites(ListItemEntity item) {
        // Rank 1 is "highest", therefore determine the max + 1.
        Integer maxRank = listItemRepository.findMaxRank(listEntity);
        maxRank = maxRank == null ? Integer.valueOf(1) : maxRank + 1;

        item.setRank(maxRank);

        // Now also remove any ListItemEliminatedby which refers to this item.
        // But we need to do this weirdly so make a copy.
        for (ListItemEntity baseItem : listEntity.getItems()) {
            List<ListItemEliminatedbyEntity> copyOfEliminatedItems = new ArrayList<>();
            for (ListItemEliminatedbyEntity eliminatedbyEntity : baseItem.getEliminatedbyItems()) {
                copyOfEliminatedItems.add(eliminatedbyEntity);
            }

            for (ListItemEliminatedbyEntity eliminatedbyEntity : copyOfEliminatedItems) {
                if (eliminatedbyEntity.getEliminatedbyListItem().getId().equals(item.getId())) {
                    baseItem.removeEliminatedby(eliminatedbyEntity.getEliminatedbyListItem());
                    System.out.println("Removed the item");
                }
            }
        }
    }

    public List<ListItemEntity> getListFromChoiceRequest(List<ListItemEntity> listFromChoiceRequest) {
        List<ListItemEntity> list = new ArrayList<ListItemEntity>();
        for (ListItemEntity listItemEntity : listFromChoiceRequest) {
            // Search the item from listEntity and add it to the list.
            list.add(searchForItem(listItemEntity));
        }

        return list;
    }

    public ListItemEntity searchForItem(ListItemEntity listItemEntity) {
        for (ListItemEntity item : listEntity.getItems()) {
            if (item.getId().equals(listItemEntity.getId())) {
                return item;
            }
        }

        return null;
    }

    public ListEntity getListEntity() {
        return listEntity;
    }

    public void setListEntity(ListEntity listEntity) {
        this.listEntity = listEntity;
    }

    public ListItemRepository getListItemRepository() {
        return listItemRepository;
    }
}
