package com.cargosmart.ita.securityjwt.repository;

import com.cargosmart.ita.securityjwt.models.ERole;
import com.cargosmart.ita.securityjwt.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
