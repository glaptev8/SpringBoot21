package edu.school21.springboot.validation.annotation;

import edu.school21.springboot.validation.PasswordMatchesValidator;
import edu.school21.springboot.validation.PasswordValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidation.class)
public @interface ValidPassword {
  String message() default "Password incorrect";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
