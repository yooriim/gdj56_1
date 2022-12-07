package com.jsp.model.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@Data
@Builder
public class Animal {
	private String name;
	private @Getter int age;
	private @Setter double height;
	private double weight;
	private char gender;
}
