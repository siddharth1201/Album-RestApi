package org.studyeasy.SpringRest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.studyeasy.SpringRest.model.Account;
import org.studyeasy.SpringRest.service.AccountService;
import org.studyeasy.SpringRest.util.constants.Authority;

@Component
public class SeedData implements CommandLineRunner{

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
       Account account01 = new Account();
       Account account02 = new Account();
    
       account01.setEmail("user@user.com");
       account01.setPassword("pass987");
       account01.setAuthorities(Authority.USER.toString());
       accountService.save(account01);

       account02.setEmail("user2@user.com");
       account02.setPassword("pass987");
       account02.setAuthorities(Authority.ADMIN.toString()+" "+Authority.USER.toString());
       accountService.save(account02);

    }
    
}
