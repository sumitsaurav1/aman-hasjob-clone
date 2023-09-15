package com.aman.majorProject.hasJob.repository;

import com.aman.majorProject.hasJob.entity.Application;
import com.aman.majorProject.hasJob.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    List<Application> findByName(String loggedInUser);
}
