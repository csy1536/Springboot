package com.springboot.jpa.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelephoneValidator.class)
public @interface Telephone {
	String message() default "전화번호 형식이 일치하지 않습니다.";  //유효성 검사가 실패할 경우 반환되는 메세지

	Class<?>[] groups() default { }; // 유효성 검사를 사용하는 그룹으로 설정

	Class<? extends Payload>[] payload() default { }; // 사용자가 추가 정보를 위해 전달하는 값

}
