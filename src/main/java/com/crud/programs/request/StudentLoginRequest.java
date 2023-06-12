package com.crud.programs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentLoginRequest {
	private Integer studentId;

	private String studentName;

	private String studentClassname;

	private Integer studentAge;

	private String studentAddress;

}
