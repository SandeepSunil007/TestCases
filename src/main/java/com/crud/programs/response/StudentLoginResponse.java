package com.crud.programs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentLoginResponse {
	
	private Integer studentId;

	private String studentName;

	private String studentClassname;

	private Integer studentAge;

	private String studentAddress;


}
