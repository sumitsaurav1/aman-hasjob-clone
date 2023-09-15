package com.aman.majorProject.hasJob.repository;

import com.aman.majorProject.hasJob.entity.Organizations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organizations,Integer> {
    Organizations findByOrganizationName(String organizationName);
}
