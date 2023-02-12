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

    public List query(String sql, RowMapper rowMapper) throws Exception {
        try (
                Connection con = ConnectionManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            return (List) rowMapper.mapRow(rs);
        }
    }

    public Object queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper rowMapper) throws Exception {
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

//    public abstract String createQuery();

//    public abstract void setValues(PreparedStatement pstmt) throws SQLException;

//    public abstract Object mapRow(ResultSet rs) throws SQLException;
}
