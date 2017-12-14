package com.firstspring;

import com.entity.ListEntity;
import com.entity.ListItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListItemRepository extends CrudRepository<ListItemEntity, Integer> {
    int countByListEntity(int id);

    List<ListItemEntity> findByListEntity(ListEntity listEntity);

    @Query("select max(t.rank) from ListItemEntity t where t.listEntity = :listEntity")
    Integer findMaxRank(@Param("listEntity") ListEntity listEntity);
}
