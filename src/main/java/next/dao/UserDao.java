package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public class UserDao {
    public void insert(User user) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        PreparedStatementSetter pstmsSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws Exception {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };
        jdbcTemplate.update(sql, pstmsSetter);
    }

    public void update(User user) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userid = ?";
        PreparedStatementSetter pstmtSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws Exception {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    public List findAll() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT userId, password, name, email FROM USERS";
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws Exception {
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
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findByUserId(String userId) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        PreparedStatementSetter pstmtSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws Exception {
                pstmt.setString(1, userId);
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws Exception {
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
            }
        };
        return (User) jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}