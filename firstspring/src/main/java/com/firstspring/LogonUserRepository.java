package com.firstspring;

import com.entity.LogonUserEntity;
        import org.springframework.data.repository.CrudRepository;

public interface LogonUserRepository extends CrudRepository<LogonUserEntity, Integer> {

}
