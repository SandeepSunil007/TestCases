package com.crud.programs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StudentLoginBodyResponse {
	private Boolean isError;
	private String message;
	private Object data;

}
