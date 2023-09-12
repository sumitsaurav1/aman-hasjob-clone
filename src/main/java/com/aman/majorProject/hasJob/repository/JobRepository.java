package com.aman.majorProject.hasJob.repository;

import com.aman.majorProject.hasJob.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Integer> {

    @Query("SELECT DISTINCT j.location FROM Job j")
    List<String> findAllLocations();

    @Query("SELECT DISTINCT j.jobType FROM Job j")
    List<String> findAllJobType();

    @Query("SELECT DISTINCT j.jobCategory FROM Job j")
    List<String> findAllJobCategory();

    @Query("SELECT j FROM Job j")
    List<Job> findAllJobs();

    @Query("SELECT DISTINCT j.payingType FROM Job j")
    List<String> findAllPay();

//    @Query("SELECT j FROM Job j WHERE j.location LIKE %:search% OR j.jobType LIKE %:search% OR j.jobCategory " +
//            "LIKE %:search% OR j.jobDescription LIKE %:search% OR j.jobRole LIKE %:search%")
//    List<Job> findJobsBySearch(@Param("search") String search);
//
//    @Query("SELECT j FROM Job j " +
//            "WHERE j.location IN :checkedLocations " +
//            "OR j.jobType IN :checkedJobType " +
//            "OR j.jobCategory IN :checkedJobCategory " +
//            "OR j.payingType IN :checkedPayingType")
//    List<Job> findJobsByFilters(
//            @Param("checkedLocations") List<String> checkedLocations,
//            @Param("checkedJobType") List<String> checkedJobType,
//            @Param("checkedJobCategory") List<String> checkedJobCategory,
//            @Param("checkedPayingType") List<String> checkedPayingType
//    );

    @Query("SELECT j FROM Job j " +
            "WHERE " +
            "(:searchTerm IS NULL OR " +
            "LOWER(j.location) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(j.jobType) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(j.jobCategory) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(j.payingType) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND " +
            "(:emptyLocations = true OR j.location IN :checkedLocations) AND " +
            "(:emptyJobTypes = true OR j.jobType IN :checkedJobType) AND " +
            "(:emptyJobCategories = true OR j.jobCategory IN :checkedJobCategory) AND " +
            "(:emptyPayingTypes = true OR j.payingType IN :checkedPayingType)")
    List<Job> findJobsByFiltersAndSearch(
            @Param("checkedLocations") List<String> checkedLocations,
            @Param("checkedJobType") List<String> checkedJobType,
            @Param("checkedJobCategory") List<String> checkedJobCategory,
            @Param("checkedPayingType") List<String> checkedPayingType,
            @Param("emptyLocations") boolean emptyLocations,
            @Param("emptyJobTypes") boolean emptyJobTypes,
            @Param("emptyJobCategories") boolean emptyJobCategories,
            @Param("emptyPayingTypes") boolean emptyPayingTypes,
            @Param("searchTerm") String searchTerm
    );

    @Query("SELECT j FROM Job j " +
            "WHERE " +
            "(:emptyLocations = true OR j.location IN :checkedLocations) AND " +
            "(:emptyJobTypes = true OR j.jobType IN :checkedJobType) AND " +
            "(:emptyJobCategories = true OR j.jobCategory IN :checkedJobCategory) AND " +
            "(:emptyPayingTypes = true OR j.payingType IN :checkedPayingType)")
    List<Job> findJobsByFilters(
            @Param("checkedLocations") List<String> checkedLocations,
            @Param("checkedJobType") List<String> checkedJobType,
            @Param("checkedJobCategory") List<String> checkedJobCategory,
            @Param("checkedPayingType") List<String> checkedPayingType,
            @Param("emptyLocations") boolean emptyLocations,
            @Param("emptyJobTypes") boolean emptyJobTypes,
            @Param("emptyJobCategories") boolean emptyJobCategories,
            @Param("emptyPayingTypes") boolean emptyPayingTypes
    );


//    @Query("SELECT j FROM Job j WHERE j.location = :selectedValue")
//    List<Job> findJobsBySelectedValue(String selectedValue);
}
