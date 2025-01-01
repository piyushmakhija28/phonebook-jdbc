package com.techdeveloper.phonebook.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.techdeveloper.phonebook.repository.ValidationRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
class ValidationRepositoryImpl implements ValidationRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public boolean validateEmail(String email) {
		return jdbcTemplate.query("select count(1)=1 as count from phonebook.phonebook where email=?", (pss) -> {
			pss.setString(1, email);
		}, (rs) -> {
			boolean valid = false;
			if (rs.next()) {
				valid = !rs.getBoolean("count");
			}
			return valid;
		});
	}

	@Override
	public boolean validateMobileNumber(String mobileNumber) {
		return jdbcTemplate.query("select count(1)=1 as count from phonebook.phonebook where mobile_number=?",
				(pss) -> {
					pss.setString(1, mobileNumber);
				}, (rs) -> {
					boolean valid = false;
					if (rs.next()) {
						valid = !rs.getBoolean("count");
					}
					return valid;
				});
	}

}
