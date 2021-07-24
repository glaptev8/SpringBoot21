package edu.school21.springboot.validation;

import edu.school21.springboot.validation.annotation.PasswordMatches;
import edu.school21.springboot.validation.annotation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidation implements ConstraintValidator<ValidPassword, Object> {
  String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
  @Override
  public void initialize(ValidPassword constraintAnnotation) {

  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    String password = (String) value;
    if (password == null) return false;
    return password.matches(pattern);
  }
}
