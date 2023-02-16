package next.dao;

import core.jdbc.ConnectionManager;
import next.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionDaoTest {
    @BeforeEach
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void crud() {
        Question expected = new Question("writer", "title", "contents");
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(expected);
        Question actual = questionDao.findByQuestionId(expected.getQuestionId());
        assertThat(actual).isEqualTo(expected);

        expected = new Question("writer", "title2", "contents2");
        questionDao.update(expected);
        actual = questionDao.findByQuestionId(expected.getQuestionId());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAll() {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questionList = questionDao.findAll();
        assertThat(questionList.size()).isEqualTo(8);
    }
}
