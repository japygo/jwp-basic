package next.dao;

import java.util.ArrayList;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
    private UserDao() {}

    public static UserDao getInstance() {
        return UserDaoHolder.INSTANCE;
    }

    private static class UserDaoHolder {
        private static final UserDao INSTANCE = new UserDao();
    }

    public void insert(User user) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
//        PreparedStatementSetter pstmsSetter = new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement pstmt) throws Exception {
//                pstmt.setString(1, user.getUserId());
//                pstmt.setString(2, user.getPassword());
//                pstmt.setString(3, user.getName());
//                pstmt.setString(4, user.getEmail());
//            }
//        };
//        jdbcTemplate.update(sql, pstmsSetter);
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userid = ?";
//        PreparedStatementSetter pstmtSetter = new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement pstmt) throws Exception {
//                pstmt.setString(1, user.getPassword());
//                pstmt.setString(2, user.getName());
//                pstmt.setString(3, user.getEmail());
//                pstmt.setString(4, user.getUserId());
//            }
//        };
//        jdbcTemplate.update(sql, pstmtSetter);
        jdbcTemplate.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "SELECT userId, password, name, email FROM USERS";
        RowMapper<List<User>> rowMapper = rs -> {
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                userList.add(new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
            return userList;
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findByUserId(String userId) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
//        PreparedStatementSetter pstmtSetter = new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement pstmt) throws Exception {
//                pstmt.setString(1, userId);
//            }
//        };
        RowMapper<User> rowMapper = rs -> {
            User user = null;
            if (rs.next()) {
                user = new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
            return user;
        };
//        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
        return jdbcTemplate.queryForObject(sql, rowMapper, userId);
    }
}