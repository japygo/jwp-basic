package next.dao;

import core.jdbc.JdbcTemplate;
import next.model.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    private QuestionDao() {}

    public static QuestionDao getInstance() {
        return QuestionDaoHolder.INSTANCE;
    }

    private static class QuestionDaoHolder {
        private static final QuestionDao INSTANCE = new QuestionDao();
    }

    public void insert(Question question) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(), question.getCreatedDate(), question.getCountOfAnswer());
    }

    public void update(Question question) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "UPDATE QUESTIONS SET writer = ?, title = ?, contents = ?, countOfAnswer = ? WHERE questionId = ?";
        jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(), question.getCountOfAnswer(), question.getQuestionId());
    }

    public void addCountOfAnswer(long questionId) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        Question question = findByQuestionId(questionId);
        String sql = "UPDATE QUESTIONS SET countOfAnswer = ? WHERE questionId = ?";
        jdbcTemplate.update(sql, question.getCountOfAnswer() + 1, questionId);
    }

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS";
        return jdbcTemplate.query(sql, rs -> {
            List<Question> questionList = new ArrayList<>();
            while (rs.next()) {
                questionList.add(new Question(
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getObject("createdDate", LocalDateTime.class),
                        rs.getInt("countOfAnswer")
                ));
            }
            return questionList;
        });
    }

    public Question findByQuestionId(long questionId) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId = ?";
        return jdbcTemplate.queryForObject(sql, rs -> {
            Question question = null;
            if (rs.next()) {
                question = new Question(
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getObject("createdDate", LocalDateTime.class),
                        rs.getInt("countOfAnswer")
                );
            }
            return question;
        }, questionId);
    }

    public void delete(long questionId) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";
        jdbcTemplate.update(sql, questionId);
    }
}
