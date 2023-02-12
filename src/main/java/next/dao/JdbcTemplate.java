package next.dao;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class JdbcTemplate {
    public void update(String sql, PreparedStatementSetter pstmtSetter) throws Exception {
        try (
                Connection con = ConnectionManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();
        }
    }

    public void update(String sql, Object... values) throws Exception {
        try (
                Connection con = ConnectionManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            PreparedStatementSetter pstmtSetter = createPreparedStatementSetter(values);
            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();
        }
    }

    public <T> List<T> query(String sql, RowMapper<List<T>> rowMapper) throws Exception {
        try (
                Connection con = ConnectionManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            return rowMapper.mapRow(rs);
        }
    }

    public <T> T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws Exception {
        ResultSet rs = null;
        try (
            Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmtSetter.setValues(pstmt);
            rs = pstmt.executeQuery();
            return rowMapper.mapRow(rs);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... values) throws Exception {
        ResultSet rs = null;
        try (
                Connection con = ConnectionManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            PreparedStatementSetter pstmtSetter = createPreparedStatementSetter(values);
            pstmtSetter.setValues(pstmt);
            rs = pstmt.executeQuery();
            return rowMapper.mapRow(rs);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    private PreparedStatementSetter createPreparedStatementSetter(Object... values) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws Exception {
                for (int i = 0; i < values.length; i++) {
                    pstmt.setObject(i + 1, values[i]);
                }
            }
        };
    }

//    public abstract String createQuery();

//    public abstract void setValues(PreparedStatement pstmt) throws SQLException;

//    public abstract Object mapRow(ResultSet rs) throws SQLException;
}
