package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LogonUser", schema = "dbUser")
public class LogonUserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Basic
    private String name;
    @Basic
    private int maxNrOfLists;
    @Basic
    private String hashedPassword;

    // Add listItems through this list.
    @OneToMany(mappedBy = "logonUserEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ListEntity> lists = new ArrayList<>();

    protected LogonUserEntity() { }

    public LogonUserEntity(String name) {
        this.name = name;
        maxNrOfLists = 1;
    }

    public boolean addList(ListEntity listEntity) {
        listEntity.setLogonUserEntity(this);
        return lists.add(listEntity);
    }

    public boolean removeList(ListEntity listEntity) {
        return lists.remove(listEntity);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxNrOfLists() {
        return maxNrOfLists;
    }

    public void setMaxNrOfLists(int maxNrOfLists) {
        this.maxNrOfLists = maxNrOfLists;
    }

    public List<ListEntity> getLists() {
        return lists;
    }

    public void setLists(List<ListEntity> lists) {
        this.lists = lists;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
