package com.mysite.sbb.user.repository;

import com.mysite.sbb.user.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SiteUser,Long> {


}
