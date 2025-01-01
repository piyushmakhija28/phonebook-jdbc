package com.techdeveloper.phonebook.form;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techdeveloper.phonebook.annotations.ValidateEmail;
import com.techdeveloper.phonebook.annotations.ValidateMobileNumber;
import com.techdeveloper.phonebook.annotations.groups.DbConstraintGroup;
import com.techdeveloper.phonebook.annotations.groups.EmailGroup;
import com.techdeveloper.phonebook.annotations.groups.LengthGroup;
import com.techdeveloper.phonebook.annotations.groups.NotBlankGroup;
import com.techdeveloper.phonebook.annotations.groups.NotEmptyGroup;
import com.techdeveloper.phonebook.annotations.groups.NotNullGroup;
import com.techdeveloper.phonebook.constants.ValidationMessageConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PhonebookForm extends ValidationMessageConstants {

	@NotNull(message = FIRST_NAME_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@NotEmpty(message = FIRST_NAME_CANNOT_BE_EMPTY, groups = NotEmptyGroup.class)
	@NotBlank(message = FIRST_NAME_CANNOT_BE_BLANK, groups = NotBlankGroup.class)
	private String firstName;

	@NotNull(message = LAST_NAME_CANNOT_BE_NULL,groups = NotNullGroup.class)
	@NotEmpty(message = LAST_NAME_CANNOT_BE_EMPTY,groups = NotEmptyGroup.class)
	@NotBlank(message = LAST_NAME_CANNOT_BE_BLANK,groups = NotBlankGroup.class)
	private String lastName;

	@NotNull(message = MOBILE_NUMBER_CANNOT_BE_NULL,groups = NotNullGroup.class)
	@NotEmpty(message = MOBILE_NUMBER_CANNOT_BE_EMPTY,groups = NotEmptyGroup.class)
	@NotBlank(message = MOBILE_NUMBER_CANNOT_BE_BLANK,groups = NotBlankGroup.class)
	@Length(min = 10,max = 10,message = MOBILE_NUMBER_CAN_ONLY_CONTAIN_MINIMUM_AND_MAXIMUM_OF_10_DIGITS,groups = LengthGroup.class)
	@ValidateMobileNumber(message = MOBILE_NUMBER_ALREADY_EXISTS,groups = DbConstraintGroup.class)
	private String mobileNumber;

	@NotNull(message = EMAIL_CANNOT_BE_NULL,groups = NotNullGroup.class)
	@NotEmpty(message = EMAIL_CANNOT_BE_EMPTY,groups = NotEmptyGroup.class)
	@NotBlank(message = EMAIL_CANNOT_BE_BLANK,groups = NotBlankGroup.class)
	@Email(regexp = EMAIL_REGEX_PATTERN,message = INVALID_EMAIL_PATTERN,groups = EmailGroup.class)
	@ValidateEmail(message = EMAIL_ALREADY_EXISTS,groups = DbConstraintGroup.class)
	private String email;

	@NotNull(message = DATE_OF_BIRTH_CANNOT_BE_NULL,groups = NotNullGroup.class)
	@NotEmpty(message = DATE_OF_BIRTH_CANNOT_BE_EMPTY,groups = NotEmptyGroup.class)
	@NotBlank(message = DATE_OF_BIRTH_CANNOT_BE_BLANK,groups = NotBlankGroup.class)
	private String dateOfBirth;

	@NotNull(message = COUNTRY_CANNOT_BE_NULL,groups = NotNullGroup.class)
	@NotEmpty(message = COUNTRY_CANNOT_BE_EMPTY,groups = NotEmptyGroup.class)
	@NotBlank(message = COUNTRY_CANNOT_BE_BLANK,groups = NotBlankGroup.class)
	private String country;

	@NotNull(message = CITY_CANNOT_BE_NULL,groups = NotNullGroup.class)
	@NotEmpty(message = CITY_CANNOT_BE_EMPTY,groups = NotEmptyGroup.class)
	@NotBlank(message = CITY_CANNOT_BE_BLANK,groups = NotBlankGroup.class)
	private String city;

	@NotNull(message = STATE_CANNOT_BE_NULL,groups = NotNullGroup.class)
	@NotEmpty(message = STATE_CANNOT_BE_EMPTY,groups = NotEmptyGroup.class)
	@NotBlank(message = STATE_CANNOT_BE_BLANK,groups = NotBlankGroup.class)
	private String state;

	@NotNull(message = DISTRICT_CANNOT_BE_NULL,groups = NotNullGroup.class)
	@NotEmpty(message = DISTRICT_CANNOT_BE_EMPTY,groups = NotEmptyGroup.class)
	@NotBlank(message = DISTRICT_CANNOT_BE_BLANK,groups = NotBlankGroup.class)
	private String district;

	@NotNull(message = ADDRESS_CANNOT_BE_NULL,groups = NotNullGroup.class)
	@NotEmpty(message = ADDRESS_CANNOT_BE_EMPTY,groups = NotEmptyGroup.class)
	@NotBlank(message = ADDRESS_CANNOT_BE_BLANK,groups = NotBlankGroup.class)
	private String address;

	@NotNull(message = NICK_NAME_CANNOT_BE_NULL,groups = NotNullGroup.class)
	@NotEmpty(message = NICK_NAME_CANNOT_BE_EMPTY,groups = NotEmptyGroup.class)
	@NotBlank(message = NICK_NAME_CANNOT_BE_BLANK,groups = NotBlankGroup.class)
	private String nickName;

}
