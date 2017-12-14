package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ListItemEliminatedby", schema = "dbUser", uniqueConstraints={
        @UniqueConstraint(columnNames = {"baseListItemId", "eliminatedbyListItemId"})
})
public class ListItemEliminatedbyEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    // Foreign key to ListItem for the 'base' element.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "baseListItemId", nullable = false)
    @JsonIgnore
    private ListItemEntity baseListItem;

    // Foreign key to ListItem which eliminated the 'base' element.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eliminatedbyListItemId", nullable = false)
    @JsonIgnore
    private ListItemEntity eliminatedbyListItem;

    protected ListItemEliminatedbyEntity() { }

    public ListItemEliminatedbyEntity(ListItemEntity baseListItem, ListItemEntity eliminatedbyListItemEntity) {
        this.baseListItem = baseListItem;
        this.eliminatedbyListItem = eliminatedbyListItemEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ListItemEntity getBaseListItem() {
        return baseListItem;
    }

    public void setBaseListItem(ListItemEntity baseListItem) {
        this.baseListItem = baseListItem;
    }

    public ListItemEntity getEliminatedbyListItem() {
        return eliminatedbyListItem;
    }

    public void setEliminatedbyListItem(ListItemEntity eliminatedbyListItem) {
        this.eliminatedbyListItem = eliminatedbyListItem;
    }

}
