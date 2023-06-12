package com.crud.programs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crud.programs.entity.StudentLogin;
import com.crud.programs.repository.StudentLoginRepository;
import com.crud.programs.request.StudentLoginRequest;
import com.crud.programs.response.StudentLoginResponse;
import com.crud.programs.service.StudentLoginServiceImpl;

public class Test_Service {

	@Mock
	private StudentLoginRepository loginRepository;

	@InjectMocks
	private StudentLoginServiceImpl loginService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	private static final String STUDENT_DATA_UPDATED_SUCCESFULLY = "student data updated succesfully";
	public static final String STUDENT_RECORD_DELETED_FROM_DATA_BASE_SUCCESSFULLY = "Student Record Deleted from DataBase Successfully.....";

	@Test
	public void testMyServiceMethod() {
		StudentLogin studentLogin = new StudentLogin();
		studentLogin.setStudentId(1);
		studentLogin.setStudentName("abc");
		studentLogin.setStudentAge(12);
		studentLogin.setStudentClassname("7th class");
		studentLogin.setStudentAddress("Bangalore");

		when(loginRepository.save(any(StudentLogin.class))).thenReturn(studentLogin);
		StudentLogin result = loginService.addStudentDetails1(studentLogin);
		// Assert
		verify(loginRepository, times(1)).save(any(StudentLogin.class));
//		assertNotNull(result); ---> The result is not a null value
	
		assertEquals(studentLogin.getStudentId(), result.getStudentId());
		assertEquals(studentLogin.getStudentName(), result.getStudentName());
		assertEquals(studentLogin.getStudentAge(), result.getStudentAge());
		assertEquals(studentLogin.getStudentClassname(), result.getStudentClassname());
		assertEquals(studentLogin.getStudentAddress(), result.getStudentAddress());

	}

	@Test
	public void testGetStudentIdSuccess() {
		int studentId = 1;

		// Create a dummy student object
		StudentLogin student = new StudentLogin(studentId, "John Doe", "7th class", 12, "Bangalore");

		// Mock the behavior of the loginRepository.findById() method
		when(loginRepository.findById(studentId)).thenReturn(Optional.of(student));

		// Call the method in the loginService
		StudentLogin studentRecordById = loginService.getStudentRecordById(studentId);

		// Verify that the loginRepository.findById() method was called once with the
		// correct studentId
		verify(loginRepository, times(1)).findById(studentId);

		// Verify that the returned student object is the same as the mocked one
		assertEquals(student, studentRecordById);
	}

	@Test
	public void testUpdateApi() {
		// Create a dummy student update request object
		int studentId = 1;
		StudentLoginRequest updateRequest = new StudentLoginRequest();
		updateRequest.setStudentId(studentId);
		updateRequest.setStudentName("Jane Doe");
		updateRequest.setStudentClassname("8th class");
		updateRequest.setStudentAge(13);
		updateRequest.setStudentAddress("Mumbai");

		// Create a dummy existing student object
		StudentLogin existingStudent = new StudentLogin(studentId, "John Doe", "7th class", 12, "Bangalore");

		// Mock the behavior of the loginRepository.findById() method
		when(loginRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));

		// Call the method in the loginService to update the student details
		String result = loginService.updateStudentDetails(updateRequest);
		System.err.println("result  : " + result);

		// Verify that the loginRepository.findById() method was called once with the
		// correct studentId
		verify(loginRepository, times(1)).findById(studentId);

		// Verify that the loginRepository.save() method was called once with the
		// updated student object
		verify(loginRepository, times(1)).save(existingStudent);

		// Verify the result message returned by the update method
		assertEquals(STUDENT_DATA_UPDATED_SUCCESFULLY, result);

		// Verify that the existing student object is updated with the new values
		assertEquals(updateRequest.getStudentName(), existingStudent.getStudentName());
		assertEquals(updateRequest.getStudentClassname(), existingStudent.getStudentClassname());
		assertEquals(updateRequest.getStudentAge(), existingStudent.getStudentAge());
		assertEquals(updateRequest.getStudentAddress(), existingStudent.getStudentAddress());
	}

	@Test
	public void testGetAllApi() {
		// Create a list of dummy student objects
		java.util.List<StudentLogin> studentList = new ArrayList<>();
		studentList.add(new StudentLogin(1, "John Doe", "7th class", 12, "Bangalore"));
		studentList.add(new StudentLogin(2, "Jane Smith", "8th class", 13, "Mumbai"));

		// Mock the behavior of the loginRepository.findAll() method
		when(loginRepository.findAll()).thenReturn(studentList);

		// Call the method in the loginService to get all student records
		java.util.List<StudentLoginResponse> result = loginService.getAllRecordsFromDataBase();

		// Verify that the loginRepository.findAll() method was called once
		verify(loginRepository, times(1)).findAll();

		// Verify the size of the returned list matches the size of the dummy student
		// list
		assertEquals(studentList.size(), result.size());

		// Verify that the returned student records have the correct values
		for (int i = 0; i < studentList.size(); i++) {
			StudentLogin student = studentList.get(i);
			StudentLoginResponse response = result.get(i);

			assertEquals(student.getStudentId(), response.getStudentId());
			assertEquals(student.getStudentName(), response.getStudentName());
			assertEquals(student.getStudentClassname(), response.getStudentClassname());
			assertEquals(student.getStudentAge(), response.getStudentAge());
			assertEquals(student.getStudentAddress(), response.getStudentAddress());
		}
	}

	@Test
	public void testDeleteStudentRecordById() {
		int deleteRecordId = 1;
		String deleteData = "Student Record Deleted from DataBase Successfully.....";

		// Mock the behavior of the loginRepository.findById() method
		StudentLogin student = new StudentLogin(deleteRecordId, "John Doe", "7th class", 12, "Bangalore");
		when(loginRepository.findById(deleteRecordId)).thenReturn(Optional.of(student));

		// Call the method in the loginService to delete the student record
		String response = loginService.deleteStudentRecord(deleteRecordId);

		// Verify that the loginRepository.findById() method was called once with the
		// correct deleteRecordId
		verify(loginRepository, times(1)).findById(deleteRecordId);

		// Verify the response is equal to the expected deleteData
		assertEquals(deleteData, response);
	}
}