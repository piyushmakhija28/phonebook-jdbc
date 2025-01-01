package com.techdeveloper.phonebook.annotations.sequence;

import com.techdeveloper.phonebook.annotations.groups.DbConstraintGroup;
import com.techdeveloper.phonebook.annotations.groups.EmailGroup;
import com.techdeveloper.phonebook.annotations.groups.LengthGroup;
import com.techdeveloper.phonebook.annotations.groups.NotBlankGroup;
import com.techdeveloper.phonebook.annotations.groups.NotEmptyGroup;
import com.techdeveloper.phonebook.annotations.groups.NotNullGroup;

import jakarta.validation.GroupSequence;

@GroupSequence(value = {
	NotNullGroup.class,
	NotEmptyGroup.class,
	NotBlankGroup.class,
	LengthGroup.class,
	EmailGroup.class,
	DbConstraintGroup.class
})
public interface ValidationSequence {

}
