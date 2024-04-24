package com.certification.repo;

import com.certification.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
    List<Test> findAllByDepartment_Id(Long departmentId);

    List<Test> findAllByNameContainingAndDepartment_Id(String name, Long departmentId);
}
