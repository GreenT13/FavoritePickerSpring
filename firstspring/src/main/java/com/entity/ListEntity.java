package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "List", schema = "dbUser")
public class ListEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Basic
    private String externalIdentifier;
    @Basic
    private Boolean hasStartedAlgorithm = false;
    @Basic
    private Integer sizeChoiceList;

    // Add listItems through this list.
    @OneToMany(mappedBy = "listEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ListItemEntity> items = new ArrayList<>();

    // Foreign key to LogonUserEntity.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logonUserId", nullable = false)
    @JsonIgnore
    private LogonUserEntity logonUserEntity;

    protected ListEntity() { }

    public ListEntity(String externalIdentifier) {
        this.externalIdentifier = externalIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListEntity that = (ListEntity) o;
        return id == that.id &&
                Objects.equals(externalIdentifier, that.externalIdentifier) &&
                Objects.equals(items, that.items);
    }

    public boolean addListItem(ListItemEntity listItemEntity) {
        listItemEntity.setListEntity(this);
        return items.add(listItemEntity);
    }

    public boolean removeListItem(ListItemEntity listItemEntity) {
        if (Boolean.TRUE.equals(hasStartedAlgorithm)) {
            // We cannot remove items if we already started.
            return false;
        }
        return items.remove(listItemEntity);
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

    public List<ListItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ListItemEntity> items) {
        this.items = items;
    }

    public LogonUserEntity getLogonUserEntity() {
        return logonUserEntity;
    }

    public void setLogonUserEntity(LogonUserEntity logonUserEntity) {
        this.logonUserEntity = logonUserEntity;
    }

    public Boolean getHasStartedAlgorithm() {
        return hasStartedAlgorithm;
    }

    public void setHasStartedAlgorithm(Boolean hasStartedAlgorithm) {
        this.hasStartedAlgorithm = hasStartedAlgorithm;
    }

    public Integer getSizeChoiceList() {
        return sizeChoiceList;
    }

    public void setSizeChoiceList(Integer sizeChoiceList) {
        this.sizeChoiceList = sizeChoiceList;
    }

}
