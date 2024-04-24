package com.certification.repo;

import com.certification.model.AppUser;
import com.certification.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    List<AppUser> findAllByRole(Role role);

    List<AppUser> findAllByRoleAndFioContainingAndDepartment_Id(Role role, String fio, Long departmentId);

    List<AppUser> findAllByFioContaining(String fio);
}