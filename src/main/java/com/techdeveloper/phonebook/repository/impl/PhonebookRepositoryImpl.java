package com.techdeveloper.phonebook.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.techdeveloper.phonebook.dto.PhonebookDto;
import com.techdeveloper.phonebook.form.PhonebookForm;
import com.techdeveloper.phonebook.repository.PhonebookRepository;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Repository
@AllArgsConstructor
@Slf4j
class PhonebookRepositoryImpl implements PhonebookRepository {

	private final JdbcTemplate jdbcTemplate;

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	@SneakyThrows(DataAccessException.class)
	public void save(PhonebookForm phonebookForm) {
//		insertUsingSQLInjection(phonebookForm);
//		insertUsingPrparedStatementCallback(phonebookForm);
//		insertUsingUpdate(phonebookForm);
//		insertUsingSimpleJdbcInsert(phonebookForm);
		insertUsingNamedParameterJdbcTemplate(phonebookForm);
	}

	private void insertUsingNamedParameterJdbcTemplate(PhonebookForm phonebookForm) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("firstName", phonebookForm.getFirstName());
		mapSqlParameterSource.addValue("lastName", phonebookForm.getLastName());
		mapSqlParameterSource.addValue("mobileNumber", phonebookForm.getMobileNumber());
		mapSqlParameterSource.addValue("email", phonebookForm.getEmail());
		mapSqlParameterSource.addValue("dob",
				LocalDate.parse(phonebookForm.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		mapSqlParameterSource.addValue("country", phonebookForm.getCountry());
		mapSqlParameterSource.addValue("city", phonebookForm.getCity());
		mapSqlParameterSource.addValue("state", phonebookForm.getState());
		mapSqlParameterSource.addValue("district", phonebookForm.getDistrict());
		mapSqlParameterSource.addValue("address", phonebookForm.getAddress());
		mapSqlParameterSource.addValue("nickName", phonebookForm.getNickName());
		namedParameterJdbcTemplate.update(
				"""
						INSERT INTO phonebook.phonebook(id,first_name, last_name, mobile_number, email, dob, country, city, state, district, address, nick_name)
								VALUES ((select nextval('phonebook.phonebook_seq')),(:firstName), (:lastName), (:mobileNumber), (:email), (:dob), (:country), (:city), (:state), (:district), (:address), (:nickName));
							""",
				mapSqlParameterSource);
	}

	private void insertUsingSimpleJdbcInsert(PhonebookForm phonebookForm) {
		Long phonebookSequenceId = jdbcTemplate.query("select nextval('phonebook.phonebook_seq')", (rs) -> {
			Long id = null;
			if (rs.next()) {
				id = rs.getLong(1);
			}
			return id;
		});
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		Map<String, Object> map = new HashMap<>(12);
		map.put("id", phonebookSequenceId);
		map.put("first_name", phonebookForm.getFirstName());
		map.put("last_name", phonebookForm.getLastName());
		map.put("mobile_number", phonebookForm.getMobileNumber());
		map.put("email", phonebookForm.getEmail());
		map.put("dob", LocalDate.parse(phonebookForm.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		map.put("country", phonebookForm.getCountry());
		map.put("city", phonebookForm.getCity());
		map.put("state", phonebookForm.getState());
		map.put("district", phonebookForm.getDistrict());
		map.put("address", phonebookForm.getAddress());
		map.put("nick_name", phonebookForm.getNickName());
		simpleJdbcInsert.setTableName("phonebook");
		simpleJdbcInsert.setSchemaName("phonebook");
		simpleJdbcInsert.execute(map);
	}

	private void insertUsingUpdate(PhonebookForm phonebookForm) {
		jdbcTemplate.update(
				"""
						INSERT INTO phonebook.phonebook(id,first_name, last_name, mobile_number, email, dob, country, city, state, district, address, nick_name)
								VALUES ((select nextval('phonebook.phonebook_seq')),?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
						""",
				new Object[] { phonebookForm.getFirstName(), phonebookForm.getLastName(),
						phonebookForm.getMobileNumber(), phonebookForm.getEmail(),
						LocalDate.parse(phonebookForm.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						phonebookForm.getCountry(), phonebookForm.getCity(), phonebookForm.getState(),
						phonebookForm.getDistrict(), phonebookForm.getAddress(), phonebookForm.getNickName() },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR });
	}

	private void insertUsingPrparedStatementCallback(PhonebookForm phonebookForm) {
		jdbcTemplate.execute(
				"""
						INSERT INTO phonebook.phonebook(id,first_name, last_name, mobile_number, email, dob, country, city, state, district, address, nick_name)
								VALUES ((select nextval('phonebook.phonebook_seq')),?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
						""",
				new PreparedStatementCallback<Boolean>() {

					@Override
					public Boolean doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						ps.setString(1, phonebookForm.getFirstName());
						ps.setString(2, phonebookForm.getLastName());
						ps.setString(3, phonebookForm.getMobileNumber());
						ps.setString(4, phonebookForm.getEmail());
						ps.setObject(5, LocalDate.parse(phonebookForm.getDateOfBirth(),
								DateTimeFormatter.ofPattern("yyyy-MM-dd")));
						ps.setString(6, phonebookForm.getCountry());
						ps.setString(7, phonebookForm.getCity());
						ps.setString(8, phonebookForm.getState());
						ps.setString(9, phonebookForm.getDistrict());
						ps.setString(10, phonebookForm.getAddress());
						ps.setString(11, phonebookForm.getNickName());
						return ps.execute();
					}
				});
	}

	private void insertUsingSQLInjection(PhonebookForm phonebookForm) {
		jdbcTemplate.execute(String.format(
				"""
									INSERT INTO phonebook.phonebook(id,first_name, last_name, mobile_number, email, dob, country, city, state, district, address, nick_name)
						VALUES ((select nextval('phonebook.phonebook_seq')),%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s);
									""",
				"'" + phonebookForm.getFirstName() + "'", "'" + phonebookForm.getLastName() + "'",
				"'" + phonebookForm.getMobileNumber() + "'", "'" + phonebookForm.getEmail() + "'",
				("'" + phonebookForm.getDateOfBirth() + "'::date"), "'" + phonebookForm.getCountry() + "'",
				"'" + phonebookForm.getCity() + "'", "'" + phonebookForm.getState() + "'",
				"'" + phonebookForm.getDistrict() + "'", "'" + phonebookForm.getAddress() + "'",
				"'" + phonebookForm.getNickName() + "'"));
	}

	@Override
	@SneakyThrows(DataAccessException.class)
	public void update(Long id, PhonebookForm phonebookForm) {

	}

	@Override
	@SneakyThrows(DataAccessException.class)
	public PhonebookDto get(Long id) {
		return null;
	}

	@Override
	@SneakyThrows(DataAccessException.class)
	public List<PhonebookDto> get(Integer limit, Integer offset) {
		//selectUsingResultSetExtractor(limit, offset);
		return null;
	}

	private List<PhonebookDto> selectUsingResultSetExtractor(Integer limit, Integer offset) {
		return jdbcTemplate.query(String.format("select * from phonebook.phonebook limit %s offset %s", limit, offset),
				new ResultSetExtractor<List<PhonebookDto>>() {
					@Override
					public List<PhonebookDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<PhonebookDto> phonebookDtoList = new ArrayList<>(limit);
						while (rs.next()) {
							PhonebookDto phonebookDto = new PhonebookDto();
							phonebookDto.setId(rs.getLong("id"));
							phonebookDto.setFirstName(rs.getString("first_name"));
							phonebookDto.setLastName(rs.getString("last_name"));
							phonebookDto.setMobileNumber(rs.getString("mobile_number"));
							phonebookDto.setEmail(rs.getString("email"));
							phonebookDto.setDateOfBirth(
									rs.getDate("dob").toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							phonebookDto.setCountry(rs.getString("country"));
							phonebookDto.setCity(rs.getString("city"));
							phonebookDto.setState(rs.getString("state"));
							phonebookDto.setDistrict(rs.getString("district"));
							phonebookDto.setAddress(rs.getString("address"));
							phonebookDto.setNickName(rs.getString("nick_name"));
							phonebookDtoList.add(phonebookDto);
						}
						return phonebookDtoList;
					}
				});
	}

	@Override
	@SneakyThrows(DataAccessException.class)
	public void delete(Long id) {

	}

}
