package core.ref;

import next.model.Question;
import next.model.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        logger.debug(clazz.getName());
        logger.debug(Arrays.toString(clazz.getFields()));
        logger.debug(Arrays.toString(clazz.getConstructors()));
        logger.debug(Arrays.toString(clazz.getMethods()));
        logger.debug(Arrays.toString(clazz.getDeclaredFields()));
        logger.debug(Arrays.toString(clazz.getDeclaredConstructors()));
        logger.debug(Arrays.toString(clazz.getDeclaredMethods()));
    }

    @Test
    public void newInstanceWithConstructorArgs() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());
        String userId = "test";
        String password = "password";
        String name = "name";
        String email = "test@email.com";
        try {
            User user = clazz.getDeclaredConstructor(String.class, String.class, String.class, String.class).newInstance(userId, password, name, email);
            assertThat(user).isEqualTo(new User(userId, password, name, email));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void privateFieldAccess() {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
    }
}
