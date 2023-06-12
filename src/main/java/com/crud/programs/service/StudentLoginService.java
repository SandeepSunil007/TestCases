package com.crud.programs.service;

import java.util.List;

import com.crud.programs.entity.StudentLogin;
import com.crud.programs.request.StudentLoginRequest;
import com.crud.programs.response.StudentLoginResponse;

public interface StudentLoginService {
//	String addStudentDetails(StudentLoginRequest loginRequest);

	String deleteStudentRecord(Integer deleteRecord);

	StudentLogin getStudentRecordById(Integer getRecord);
	
	List<StudentLoginResponse> getAllRecordsFromDataBase(); 
	
	String updateStudentDetails(StudentLoginRequest updateRequest);
	
	StudentLogin addStudentDetails1(StudentLogin studentLogin);
	

}
