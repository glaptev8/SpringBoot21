package edu.school21.springboot.validation;

import edu.school21.springboot.entity.User;
import edu.school21.springboot.validation.annotation.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {

  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    User user = (User) value;
    return user.getPassword().equals(user.getMatchingPassword());
  }
}
