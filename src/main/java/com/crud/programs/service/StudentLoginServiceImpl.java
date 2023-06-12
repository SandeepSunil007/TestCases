package com.crud.programs.service;

import static com.crud.programs.constants.StudentConstants.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.crud.programs.entity.StudentLogin;
import com.crud.programs.exception.StudentGetNotFountException;
import com.crud.programs.exception.StudentNotFountException;
import com.crud.programs.exception.StudentUpdateNotFoundException;
import com.crud.programs.repository.StudentLoginRepository;
import com.crud.programs.request.StudentLoginRequest;
import com.crud.programs.response.StudentLoginResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentLoginServiceImpl implements StudentLoginService {

	private final StudentLoginRepository loginRepository;

//	public @Override String addStudentDetails(StudentLoginRequest loginRequest) {
//		StudentLogin login = StudentLogin.builder().studentName(loginRequest.getStudentName())
//				.studentClassname(loginRequest.getStudentClassname()).studentAge(loginRequest.getStudentAge())
//				.studentAddress(loginRequest.getStudentAddress()).build();
//		loginRepository.save(login);
//		return STUDENT_DATA_SUCCESSFULLY_ADDED_IN_THE_DATA_BASE;
//	}

	public @Override String deleteStudentRecord(Integer deleteRecord) {
		StudentLogin studentDelete = loginRepository.findById(deleteRecord)
				.orElseThrow(() -> new StudentNotFountException(deleteRecord + NOT_PRESENT_IN_THE_DATABASE));
		loginRepository.delete(studentDelete);
		return STUDENT_RECORD_DELETED_FROM_DATA_BASE_SUCCESSFULLY;
	}

	public @Override StudentLogin getStudentRecordById(Integer getRecord) {

		return loginRepository.findById(getRecord)
				.orElseThrow(() -> new StudentGetNotFountException(getRecord + NOT_PRESENT_IN_THE_DATABASE));
	}

	@Override
	public List<StudentLoginResponse> getAllRecordsFromDataBase() {

		return loginRepository.findAll().stream().map(student -> {
			StudentLoginResponse loginResponse = StudentLoginResponse.builder().build();
			BeanUtils.copyProperties(student, loginResponse);
			return loginResponse;

		}).collect(Collectors.toList());
	}

	@Override
	public String updateStudentDetails(StudentLoginRequest updateRequest) {
		StudentLogin studentUpdate = loginRepository.findById(updateRequest.getStudentId())
				.orElseThrow(() -> new StudentUpdateNotFoundException(STUDENT_DATA_NOT_FOUNT));
		BeanUtils.copyProperties(updateRequest, studentUpdate);
		loginRepository.save(studentUpdate);
		return STUDENT_DATA_UPDATED_SUCCESFULLY;
	}

//	public List<StudentLogin> getStudenAge(Integer studentAge){
//		return loginRepository.findBystudentAge(studentAge);
//		
//	}
//	
//	
//	public Long getCountBasedOnAddressMatch(@PathVariable String studentAddress) {
//		return loginRepository.countBystudentAddress(studentAddress);
//		
//	}
//	
//	public List<StudentLogin> usingMultiConditionGetTheRecords(String studentAddress, Integer studentAge){
//		return loginRepository.getMultiCondition(studentAddress, studentAge);
//		
//	}

	// custom query

	public List<StudentLogin> getUserCustomeQuery() {
		return loginRepository.getUserCustomeQuery();
	}

	@Override
	public StudentLogin addStudentDetails1(StudentLogin studentLogin) {
		StudentLogin build = StudentLogin.builder().studentName(studentLogin.getStudentName())
				.studentClassname(studentLogin.getStudentClassname()).studentAge(studentLogin.getStudentAge())
				.studentAddress(studentLogin.getStudentAddress()).build();
		StudentLogin save = loginRepository.save(build);
		return save;
	}

}
