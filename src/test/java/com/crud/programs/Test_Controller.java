package com.crud.programs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crud.programs.Controller.StudentLoginController;
import com.crud.programs.entity.StudentLogin;
import com.crud.programs.request.StudentLoginRequest;
import com.crud.programs.response.StudentLoginBodyResponse;
import com.crud.programs.response.StudentLoginResponse;
import com.crud.programs.service.StudentLoginService;

public class Test_Controller {
	@Mock
	private StudentLoginService loginService;

	@InjectMocks
	private StudentLoginController controller;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	public static final String STUDENT_DATA_UPDATED_SUCCESFULLY = "student data updated succesfully";
	public static final String STUDENT_RECORD_DELETED_FROM_DATA_BASE_SUCCESSFULLY = "Student Record Deleted from DataBase Successfully.....";

	@Test
	public void testSaveStudentData() {
		StudentLogin loginRequest = new StudentLogin();
		// Set up the properties of the loginRequest object

		// Mock the behavior of the loginService.addStudentDetails1() method
		when(loginService.addStudentDetails1(loginRequest)).thenReturn(loginRequest);

		// Call the method in the loginController to save the student data
		ResponseEntity<StudentLoginBodyResponse> responseEntity = controller.saveStudentData(loginRequest);

		// Verify that the loginService.addStudentDetails1() method was called once with
		// the correct loginRequest
		verify(loginService, times(1)).addStudentDetails1(loginRequest);

		// Verify the response status is HttpStatus.OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Verify the response body contains the correct data
		StudentLoginBodyResponse responseBody = responseEntity.getBody();
		assertNotNull(responseBody);
		assertFalse(responseBody.getIsError());
		assertEquals(loginRequest, responseBody.getData());
	}

	@Test
	public void testGetStudentRecord() {
		int getRecordId = 1;
		StudentLogin studentLogin = new StudentLogin();
		// Set up the properties of the studentLogin object

		// Mock the behavior of the loginService.getStudentRecordById() method
		when(loginService.getStudentRecordById(getRecordId)).thenReturn(studentLogin);

		// Call the method in the loginController to get the student record
		ResponseEntity<StudentLoginBodyResponse> responseEntity = controller.getStudentRecord(getRecordId);

		// Verify that the loginService.getStudentRecordById() method was called once
		// with the correct getRecordId
		verify(loginService, times(1)).getStudentRecordById(getRecordId);

		// Verify the response status is HttpStatus.OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Verify the response body contains the correct data
		StudentLoginBodyResponse responseBody = responseEntity.getBody();
		assertNotNull(responseBody);
		assertFalse(responseBody.getIsError());
		assertEquals(studentLogin, responseBody.getData());
	}

	@Test
	public void testGetAllRecordsFromDatabase() {
		List<StudentLoginResponse> allRecords = new ArrayList<>();
		// Add student records to the allRecords list

		// Mock the behavior of the loginService.getAllRecordsFromDatabase() method
		when(loginService.getAllRecordsFromDataBase()).thenReturn(allRecords);

		// Call the method in the loginController to get all student records
		ResponseEntity<StudentLoginBodyResponse> responseEntity = controller.getAllRecordsFromDatabase();

		// Verify that the loginService.getAllRecordsFromDatabase() method was called
		// once
		verify(loginService, times(1)).getAllRecordsFromDataBase();

		// Verify the response status is HttpStatus.OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Verify the response body contains the correct data
		StudentLoginBodyResponse responseBody = responseEntity.getBody();
		assertNotNull(responseBody);
		assertFalse(responseBody.getIsError());
		assertEquals(allRecords, responseBody.getData());
	}

	@Test
	public void testUpdateStudentRecord() {
		StudentLoginRequest updateRequest = new StudentLoginRequest();
		// Set up the properties of the updateRequest object

		// Call the method in the loginController to update the student record
		ResponseEntity<StudentLoginBodyResponse> responseEntity = controller.updateStudentRecord(updateRequest);

		// Verify that the loginService.updateStudentDetails() method was called once
		// with the correct updateRequest
		verify(loginService, times(1)).updateStudentDetails(updateRequest);

		// Verify the response status is HttpStatus.OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Verify the response body contains the correct message
		StudentLoginBodyResponse responseBody = responseEntity.getBody();
		assertNotNull(responseBody);
		assertFalse(responseBody.getIsError());
		assertEquals(STUDENT_DATA_UPDATED_SUCCESFULLY, responseBody.getMessage());
	}

	@Test
	public void testDeleteStudentRecordById() {
		int deleteRecordId = 1;
		String deleteData = "Student record deleted";

		// Mock the behavior of the loginService.deleteStudentRecord() method
		when(loginService.deleteStudentRecord(deleteRecordId)).thenReturn(deleteData);

		// Call the method in the loginController to delete the student record
		ResponseEntity<StudentLoginBodyResponse> responseEntity = controller.deleteStudentRecordById(deleteRecordId);

		// Verify that the loginService.deleteStudentRecord() method was called once
		// with the correct deleteRecordId
		verify(loginService, times(1)).deleteStudentRecord(deleteRecordId);

		// Verify the response status is HttpStatus.OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Verify the response body contains the correct data
		StudentLoginBodyResponse responseBody = responseEntity.getBody();
		assertNotNull(responseBody);
		assertFalse(responseBody.getIsError());
		assertEquals(STUDENT_RECORD_DELETED_FROM_DATA_BASE_SUCCESSFULLY, responseBody.getMessage());
		assertEquals(deleteData, responseBody.getData());
	}
}
