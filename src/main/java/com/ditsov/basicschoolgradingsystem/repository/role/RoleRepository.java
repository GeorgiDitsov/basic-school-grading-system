package com.ditsov.basicschoolgradingsystem.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ditsov.basicschoolgradingsystem.model.role.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
