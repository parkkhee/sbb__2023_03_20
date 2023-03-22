package com.mysite.sbb.user.service;

import com.mysite.sbb.base.exception.DataNotFoundException;
import com.mysite.sbb.user.entity.SiteUser;
import com.mysite.sbb.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String password, String email) {

        SiteUser siteUser = new SiteUser();

        siteUser.setUsername(username);
        siteUser.setEmail(email);
        siteUser.setPassword(passwordEncoder.encode(password));

        userRepository.save(siteUser);

        return siteUser;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteuser = userRepository.findByUsername(username);

        if (siteuser.isPresent()) {

            return siteuser.get();

        }else{
            throw new DataNotFoundException("siteuser not found");
        }
    }


}
