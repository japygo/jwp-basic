package next.dao;

import core.jdbc.JdbcTemplate;
import next.model.Answer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao {
    public void insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, answer.getAnswerId(), answer.getWriter(), answer.getContents(), answer.getCreatedDate(), answer.getQuestionId());
    }

    public void update(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE ANSWERS SET writer = ?, contents = ? WHERE answerId = ?";
        jdbcTemplate.update(sql, answer.getWriter(), answer.getContents(), answer.getAnswerId());
    }

    public List<Answer> findAll(long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE questionId = ?";
        return jdbcTemplate.query(sql, rs -> {
            List<Answer> answerList = new ArrayList<>();
            while (rs.next()) {
                answerList.add(new Answer(
                        rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getObject("createdDate", LocalDateTime.class),
                        rs.getLong("questionId")
                ));
            }
            return answerList;
        }, questionId);
    }

    public Answer findByAnswerId(long answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ?";
        return jdbcTemplate.queryForObject(sql, rs -> {
            Answer answer = null;
            if (rs.next()) {
                answer = new Answer(
                        rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getObject("createdDate", LocalDateTime.class),
                        rs.getLong("questionId")
                );
            }
            return answer;
        }, answerId);
    }
}
