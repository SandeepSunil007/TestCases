package com.crud.programs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.crud.programs.entity.StudentLogin;

@Repository
@EnableJpaRepositories
public interface StudentLoginRepository extends JpaRepository<StudentLogin, Integer> {
	// Based on age we get data
	List<StudentLogin> findBystudentAge(Integer studentAge);

	public Long countBystudentAddress(String studentAddress);

	@Query(value = "SELECT e FROM StudentLogin e ORDER BY name")
	public List<StudentLogin> findAllSortedByName();
	
	//public List<StudentLogin> getMultiCondition(String studentAddress, Integer studentAge);

	@Query("select s from StudentLogin s")
	public List<StudentLogin> getUserCustomeQuery();

}
