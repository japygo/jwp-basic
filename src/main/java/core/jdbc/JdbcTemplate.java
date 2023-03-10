package core.jdbc;

import java.sql.*;
import java.util.List;

public class JdbcTemplate {
    private JdbcTemplate() {}

    public static JdbcTemplate getInstance() {
        return JdbcTemplateHolder.INSTANCE;
    }

    private static class JdbcTemplateHolder {
        private static final JdbcTemplate INSTANCE = new JdbcTemplate();
    }

    public long update(String sql, PreparedStatementSetter pstmtSetter) {
        try (
                Connection con = ConnectionManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
            rs.close();
            return 0;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public long update(String sql, Object... values) {
        PreparedStatementSetter pstmtSetter = createPreparedStatementSetter(values);
        return update(sql, pstmtSetter);
    }

    public <T> List<T> query(String sql, RowMapper<List<T>> rowMapper) {
        try (
                Connection con = ConnectionManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            return rowMapper.mapRow(rs);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> List<T> query(String sql, PreparedStatementSetter pstmtSetter, RowMapper<List<T>> rowMapper) {
        ResultSet rs = null;
        try (
                Connection con = ConnectionManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmtSetter.setValues(pstmt);
            rs = pstmt.executeQuery();
            return rowMapper.mapRow(rs);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> List<T> query(String sql, RowMapper<List<T>> rowMapper, Object... values) {
        PreparedStatementSetter pstmtSetter = createPreparedStatementSetter(values);
        return query(sql, pstmtSetter, rowMapper);
    }

    public <T> T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) {
        ResultSet rs = null;
        try (
            Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmtSetter.setValues(pstmt);
            rs = pstmt.executeQuery();
            return rowMapper.mapRow(rs);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... values) {
        PreparedStatementSetter pstmtSetter = createPreparedStatementSetter(values);
        return queryForObject(sql, pstmtSetter, rowMapper);
    }

    private PreparedStatementSetter createPreparedStatementSetter(Object... values) {
        return pstmt -> {
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
        };
    }

//    public abstract String createQuery();

//    public abstract void setValues(PreparedStatement pstmt) throws SQLException;

//    public abstract Object mapRow(ResultSet rs) throws SQLException;
}
