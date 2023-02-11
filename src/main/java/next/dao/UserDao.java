package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public class UserDao {
    public void insert(User user) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
//            @Override
//            public String createQuery() {
//                return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
//            }

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql);
    }

    public void update(User user) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
//            @Override
//            public String createQuery() {
//                return "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userid = ?";
//            }

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        };
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userid = ?";
        jdbcTemplate.update(sql);
    }

    public List findAll() throws SQLException {
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
            }

            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
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
        String sql = "SELECT userId, password, name, email FROM USERS";
        return jdbcTemplate.query(sql);
    }

    public User findByUserId(String userId) throws SQLException {
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }

            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
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
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return (User) jdbcTemplate.queryForObject(sql);
    }
}