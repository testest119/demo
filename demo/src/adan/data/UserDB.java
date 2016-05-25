package adan.data;

import java.sql.*;
import org.apache.commons.codec.digest.DigestUtils;

public class UserDB {

	public static String login(String acc, String pass) {
		// TODO Auto-generated method stub
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String valid = "N";
        String query = "SELECT hash FROM login "
                + "WHERE acc = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, acc);
            rs = ps.executeQuery();
            if (rs.next()) {
            	if(rs.getString(1).equals(DigestUtils.sha1Hex(pass)))
            		valid = "Y";
            }
            return valid;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
}
