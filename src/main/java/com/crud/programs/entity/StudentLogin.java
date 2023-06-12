package com.crud.programs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class StudentLogin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
	
	
	private String studentName;
	
	private String studentClassname;
	
	private Integer studentAge;
	
	private String studentAddress;
	
	
}
