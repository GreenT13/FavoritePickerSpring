package com.firstspring;

import com.entity.ListEntity;
import com.entity.LogonUserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListRepository extends CrudRepository<ListEntity, Integer> {
    int countByLogonUserEntity(LogonUserEntity logonUserEntity);

    List<ListEntity> findByLogonUserEntity(LogonUserEntity logonUserEntity);
}
