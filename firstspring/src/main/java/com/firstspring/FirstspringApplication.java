package com.firstspring;

import com.entity.ListEntity;
import com.entity.ListItemEntity;
import com.entity.LogonUserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
@EntityScan("com.entity")
public class FirstspringApplication {

	@Autowired
	ListRepository listRepository;

	@Autowired
    ListItemRepository listItemRepository;

	@Autowired
    LogonUserRepository logonUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(FirstspringApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
            LogonUserEntity logonUserEntity = new LogonUserEntity("Rico Apon");
            // Add password to the logonUser.
            logonUserEntity.setHashedPassword(BCrypt.hashpw("password", BCrypt.gensalt()));

            ListEntity list = new ListEntity("My first list");
            list.setSizeChoiceList(Integer.valueOf(2));
            list.setHasStartedAlgorithm(Boolean.TRUE);
            logonUserEntity.addList(list);

            for (int i = 1; i < 4; i++) {
                list.addListItem(new ListItemEntity(String.valueOf(i)));
            }

            logonUserRepository.save(logonUserEntity);
        };
	}
}
