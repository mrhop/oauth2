package com.hopever.hope.oauth2server.repository;

/**
 * Created by Donghui Huo on 2016/3/23.
 */

import com.hopever.hope.oauth2server.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}