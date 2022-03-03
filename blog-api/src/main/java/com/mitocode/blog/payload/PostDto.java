package com.mitocode.blog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//https://www.baeldung.com/spring-boot-bean-validation

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class PostDto {
	private long id;
	
	@NotEmpty
	@Size(min=2,message = "el titulo debe tener 2 caracteres como minimo")
	private String title;
	
	@NotEmpty
	@Size(min=10,message = "la descripcion debe tener 10 caracteres como minimo")	
	private String description;
	
	@NotEmpty
	private String content;	
}
