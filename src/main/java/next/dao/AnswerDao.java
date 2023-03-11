package next.dao;

import core.jdbc.JdbcTemplate;
import next.model.Answer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao {
    private AnswerDao() {}

    public static AnswerDao getInstance() {
        return AnswerDaoHolder.INSTANCE;
    }

    private static class AnswerDaoHolder {
        private static final AnswerDao INSTANCE = new AnswerDao();
    }


    public Answer insert(Answer answer) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        long id = jdbcTemplate.update(sql, answer.getWriter(), answer.getContents(), answer.getCreatedDate(), answer.getQuestionId());
        return findByAnswerId(id);
    }

    public void update(Answer answer) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "UPDATE ANSWERS SET writer = ?, contents = ? WHERE answerId = ?";
        jdbcTemplate.update(sql, answer.getWriter(), answer.getContents(), answer.getAnswerId());
    }

    public List<Answer> findAll(long questionId) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
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
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
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

    public void delete(long answerId) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "DELETE FROM ANSWERS WHERE answerId = ?";
        jdbcTemplate.update(sql, answerId);
    }
}
