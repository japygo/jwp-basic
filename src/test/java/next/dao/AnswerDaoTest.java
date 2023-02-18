package next.dao;

import core.jdbc.ConnectionManager;
import next.model.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerDaoTest {
    @BeforeEach
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void crud() {
        Answer expected = new Answer("writer", "contents", 1);
        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(expected);
        Answer actual = answerDao.findByAnswerId(expected.getAnswerId());
        assertThat(actual).isEqualTo(expected);

        expected = new Answer("writer2", "contents2", 1);
        answerDao.update(expected);
        actual = answerDao.findByAnswerId(expected.getAnswerId());
        assertThat(actual).isEqualTo(expected);

        answerDao.delete(expected.getAnswerId());
        actual = answerDao.findByAnswerId(expected.getAnswerId());
        assertThat(actual).isNull();
    }

    @Test
    public void findAll() {
        AnswerDao answerDao = new AnswerDao();
        List<Answer> answerList = answerDao.findAll(8);
        assertThat(answerList.size()).isEqualTo(3);
    }
}
