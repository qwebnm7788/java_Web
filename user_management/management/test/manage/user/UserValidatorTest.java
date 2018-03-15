package manage.user;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserValidatorTest {
	private static Validator validator;
	
	//���� Ŭ���� ����� �ѹ��� �����
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void userIdIsNull() {
		User user = new User(null, "password", "", "name");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		
		System.out.println(constraintViolations.size());
		assertEquals(1, constraintViolations.size());
		System.out.println(constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void userIdLength() throws Exception {
		User user = new User("us", "password", "", "name");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		
		assertEquals(1, constraintViolations.size());
		System.out.println(constraintViolations.iterator().next().getMessage());
		
		user = new User("userIduserId2", "password", "", "name");
		constraintViolations = validator.validate(user);
		
		assertEquals(1, constraintViolations.size());
		System.out.println(constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void email() throws Exception {
		User user = new User("user", "password", "email", "name");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		
		assertEquals(1, constraintViolations.size());
		System.out.println(constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void invalidUser() throws Exception {
		User user = new User("us", "password", "email", "name");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		
		assertEquals(2, constraintViolations.size());
		Iterator<ConstraintViolation<User>> violations = constraintViolations.iterator();
		while(violations.hasNext()) {
			ConstraintViolation<User> each = violations.next();
			System.out.println(each.getPropertyPath() + " : " + each.getMessage());
		}
	}
}
