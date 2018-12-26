package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.userDao;
import pojo.Chat;
import pojo.User;
import util.JDBC;

public class userDaoImpl implements userDao{

	@Override
	public User checkUser(String name, String psw) {
		Connection conn = null;
		PreparedStatement stm = null;
		conn = JDBC.getConnection();
		ResultSet set;
		String sql = "select * from kuser where uname = ? and upass = ?";
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			stm.setString(2,psw);
			set = stm.executeQuery();
			User u = new User();
			if(set.next()){
				u.setUbirthday(set.getDate("ubirthday"));
				u.setUgrade(set.getInt("ugarde"));
				u.setUimage(set.getString("uimage"));
				u.setUname(set.getString("uname"));
				u.setUpass(set.getString("upass"));
				u.setUphone(set.getLong("uphone"));
				u.setUserid(set.getInt("userid"));
				u.setUsex(set.getString("usex"));
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String findUserImage(String name) {
		Connection conn = null;
		PreparedStatement stm = null;
		conn = JDBC.getConnection();
		ResultSet set;
		String headImage="";
		String sql = "select * from kuser where uname like '%"+name+"%'";
		try {
			stm = conn.prepareStatement(sql);
			set = stm.executeQuery();
			if(set.next()){
				headImage = set.getString("uimage");
			}
			return headImage;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findUserById(int userid) {
		Connection conn = null;
		PreparedStatement stm = null;
		conn = JDBC.getConnection();
		ResultSet set;
		List<User> us = new ArrayList<>();
		String sql = "select * from kuser where userid in (select userids from rela where relaid>=1 and userid=?)";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, userid);
			set = stm.executeQuery();
			while(set.next()){
				User u = new User();
				u.setUbirthday(set.getDate("ubirthday"));
				u.setUgrade(set.getInt("ugarde"));
				u.setUimage(set.getString("uimage"));
				u.setUname(set.getString("uname"));
				u.setUpass(set.getString("upass"));
				u.setUphone(set.getLong("uphone"));
				u.setUserid(set.getInt("userid"));
				u.setUsex(set.getString("usex"));
				us.add(u);
			}
			return us;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addChat(int userid, int userids, String ms) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stm = null;
		conn = JDBC.getConnection();
		String sql = "insert into chats values(seq_chats.NEXTVAL,?,?,?,sysdate,0)";
		try{
			stm = conn.prepareStatement(sql);
			stm.setInt(1, userid);
			stm.setInt(2, userids);
			stm.setString(3, ms);
			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Chat> findChat() {
		Connection conn = null;
		PreparedStatement stm = null;
		conn = JDBC.getConnection();
		ResultSet set;
		List<Chat> listc = new ArrayList<>();
		String sql = "select * from chats where flag=0 order by chatdate asc";
		try {
			stm = conn.prepareStatement(sql);
			set = stm.executeQuery();
			while(set.next()){
				Chat c = new Chat();
				c.setChatid(set.getInt("chatid"));
				c.setUserid(set.getInt("userid"));
				c.setUserids(set.getInt("userids"));
				c.setMessage(set.getString("message"));
				c.setChatDate(set.getTimestamp("chatDate"));
				c.setFlag(set.getInt("flag"));
				listc.add(c);
			}
			return listc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void updateChat(int chatid) {
		Connection conn = null;
		PreparedStatement stm = null;
		conn = JDBC.getConnection();
		String sql = "update chats set flag = 1 where chatid=?";
		try{
			stm = conn.prepareStatement(sql);
			stm.setInt(1, chatid);
			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
