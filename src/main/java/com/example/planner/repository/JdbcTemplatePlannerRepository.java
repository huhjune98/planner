package com.example.planner.repository;

import com.example.planner.dto.PlannerResponseDto;
import com.example.planner.entity.Planner;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplatePlannerRepository implements PlannerRepository {

    private final JdbcTemplate jdbcTemplate;
    public JdbcTemplatePlannerRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public PlannerResponseDto createPlanner(Planner planner) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("planner").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userName", planner.getUserName());
        parameters.put("password", planner.getPassword());
        parameters.put("title", planner.getTitle());
        parameters.put("content", planner.getContent());
        parameters.put("createDate", planner.getCreateDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));


        return new PlannerResponseDto(key.longValue(), planner.getTitle(), planner.getContent(), planner.getCreateDate(), planner.getUpdateDate());
    }

    @Override
    public List<PlannerResponseDto> getAllPlanners() {
        return jdbcTemplate.query("select * from planner where update_date = ? or username = ?", plannerRowMapper());
    }

//    SELECT*FROM Event
//    WHERE update_date = 'YYYY-MM-DD'
//    AND username = '허준'

    @Override
    public Planner findPlannerByIdOrElseThrow(Long id) {

        List<Planner> result = jdbcTemplate.query("select * from planner where id=?", plannerRowMapperV2() , id);
        return result.stream()
                .findFirst().
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    @Override
    public int updatePlanner(Long id, String userName, String content, String password) {

        return jdbcTemplate.update("update planner set userName = ?, content = ?, updateDate = NOW() where id = ?, password = ?", userName, content, id, password);
    }

    @Override
    public int deletePlanner(Long id, String password) {

        return jdbcTemplate.update("delete from planner where id=?, password = ?", id, password);
    }

    private RowMapper<PlannerResponseDto> plannerRowMapper() {

        return new RowMapper<PlannerResponseDto>() {

            @Override
            public PlannerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlannerResponseDto(
                        rs.getLong("eventId"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("createDate").toLocalDateTime(),
                        rs.getDate("updateDate").toLocalDate()
                );
            }
        };
    }

    private RowMapper<Planner> plannerRowMapperV2() {
        return new RowMapper<Planner>() {

            @Override
            public Planner mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Planner(
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("content")
                );
            }
        };
    }
}
