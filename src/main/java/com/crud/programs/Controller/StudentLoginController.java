package com.crud.programs.Controller;

import static com.crud.programs.constants.StudentConstants.*;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.programs.entity.StudentLogin;
import com.crud.programs.repository.StudentLoginRepository;
import com.crud.programs.request.StudentLoginRequest;
import com.crud.programs.response.StudentLoginBodyResponse;
import com.crud.programs.response.StudentLoginResponse;
import com.crud.programs.service.StudentLoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/a1")
public class StudentLoginController {

	private final StudentLoginService loginService;
	private final StudentLoginRepository loginRepository;

//	@PostMapping("/studentLogin")
//	public ResponseEntity<StudentLoginBodyResponse> saveStudentData(@RequestBody StudentLoginRequest loginRequest) {
//		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.FALSE)
//				.message(loginService.addStudentDetails(loginRequest)).build());
//
//	}

	@PostMapping("/studentLogin")
	public ResponseEntity<StudentLoginBodyResponse> saveStudentData(@RequestBody StudentLogin loginRequest) {
		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.FALSE)
				.data(loginService.addStudentDetails1(loginRequest)).build());

	}

	@DeleteMapping("/studentDelete/{deleteRecord}")
	public ResponseEntity<StudentLoginBodyResponse> deleteStudentRecordById(@PathVariable Integer deleteRecord) {
		String deleteData = loginService.deleteStudentRecord(deleteRecord);
		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.FALSE)
				.message(STUDENT_RECORD_DELETED_FROM_DATA_BASE_SUCCESSFULLY).data(deleteData).build());

	}

	@GetMapping("/studentget/{getRecord}")
	public ResponseEntity<StudentLoginBodyResponse> getStudentRecord(@PathVariable Integer getRecord) {
		StudentLogin studentLogin = loginService.getStudentRecordById(getRecord);
		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.FALSE)
				.message(DATA_IS_FETCHED_SUCCESSFULLY).data(studentLogin).build());

	}

	@GetMapping("/student-getAll")
	public ResponseEntity<StudentLoginBodyResponse> getAllRecordsFromDatabase() {
		List<StudentLoginResponse> allRecordsFromDataBase = loginService.getAllRecordsFromDataBase();
		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.FALSE)
				.message(FECHING_THE_ALL_RECORDS_FROM_DATABASE).data(allRecordsFromDataBase).build());
	}

	@PutMapping("/student-update")
	public ResponseEntity<StudentLoginBodyResponse> updateStudentRecord(
			@RequestBody StudentLoginRequest updateRequest) {
		loginService.updateStudentDetails(updateRequest);
		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.FALSE)
				.message(STUDENT_DATA_UPDATED_SUCCESFULLY).build());

	}

	@GetMapping("/age/{studentAge}")
	public ResponseEntity<StudentLoginBodyResponse> getStudentAge(@PathVariable Integer studentAge) {
		List<StudentLogin> findBystudentAge = loginRepository.findBystudentAge(studentAge);
		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.FALSE).message("get data from age")
				.data(findBystudentAge).build());
	}

	@GetMapping("/address/{studentAddress}")
	public ResponseEntity<StudentLoginBodyResponse> countBasedOnAddressNameMath(@PathVariable String studentAddress) {
		Long countBystudentAddress = loginRepository.countBystudentAddress(studentAddress);
		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.FALSE).message("get data from age")
				.data(countBystudentAddress).build());
	}

//	@GetMapping("/twoConditions/{studentAddress}/{studentAge}")
//	public ResponseEntity<StudentLoginBodyResponse> usingTwoConditions(@PathVariable  String studentAddress, Integer studentAge){
//		List<StudentLogin> findBystudentAddressandstudentAge = loginRepository.getMultiCondition(studentAddress, studentAge);
//		return ResponseEntity.ok(StudentLoginBodyResponse.builder().isError(Boolean.FALSE).message("get data from age").data(findBystudentAddressandstudentAge).build());
//	}

}
