package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ListItem", schema = "dbUser")
public class ListItemEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Basic
    private String externalIdentifier;
    @Basic
    private Integer rank;
    @Basic
    private Boolean chosen;

    // Foreign key to ListEntity.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "listId", nullable = false)
    @JsonIgnore
    private ListEntity listEntity;


    // Add eliminatedbyItems through this list.
    @OneToMany(mappedBy = "baseListItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ListItemEliminatedbyEntity> eliminatedbyItems = new ArrayList<>();

    protected ListItemEntity() { }

    public ListItemEntity(String externalIdentifier) {
        this.externalIdentifier = externalIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListItemEntity that = (ListItemEntity) o;
        return id == that.getId() &&
                Objects.equals(externalIdentifier, that.getExternalIdentifier()) &&
                Objects.equals(listEntity, that.getListEntity());
    }


    public boolean addEliminatedby(ListItemEntity listItemEntity) {
        if (Boolean.FALSE.equals(listEntity.getHasStartedAlgorithm())) {
            // These items can only be filled if the algorithm started.
            return false;
        }

        ListItemEliminatedbyEntity item = new ListItemEliminatedbyEntity(this, listItemEntity);
        return eliminatedbyItems.add(item);
    }

    public boolean removeEliminatedby(ListItemEntity listItemEntity) {
        if (Boolean.FALSE.equals(listEntity.getHasStartedAlgorithm())) {
            // These items can only be filled if the algorithm started.
            return false;
        }

        for (ListItemEliminatedbyEntity item : eliminatedbyItems) {
            if (item.getEliminatedbyListItem().getId().equals(listItemEntity.getId())) {
                return eliminatedbyItems.remove(item);
            }
        }

        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExternalIdentifier() {
        return externalIdentifier;
    }

    public void setExternalIdentifier(String externalIdentifier) {
        this.externalIdentifier = externalIdentifier;
    }

    public ListEntity getListEntity() {
        return listEntity;
    }

    public void setListEntity(ListEntity listEntity) {
        this.listEntity = listEntity;
    }

    public List<ListItemEliminatedbyEntity> getEliminatedbyItems() {
        return eliminatedbyItems;
    }

    public void setEliminatedbyItems(List<ListItemEliminatedbyEntity> items) {
        this.eliminatedbyItems = items;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Boolean getChosen() {
        return chosen;
    }

    public void setChosen(Boolean chosen) {
        this.chosen = chosen;
    }
}
