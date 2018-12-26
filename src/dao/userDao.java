package dao;

import java.util.List;

import pojo.Chat;
import pojo.User;

public interface userDao {
	//登录查找用户名，密码是否正确，返回一个user对象
	public User checkUser(String name,String psw);
	//根据用户名字符串，查找出相应用户并返回头像路径
	public String findUserImage(String name);
	//根据登录的用户id，查找出与用户有好友关系的用户
	public List<User> findUserById(int userid);
	//根据登录用户id和聊天对象id在聊天表插入聊天数据
	public void addChat(int userid,int userids,String ms);
	//根据登录用户id和聊天对象id在聊天表中查到对象回复最近的一条消息并返回
	public List<Chat> findChat();
	//根据已经加载过的聊天记录的id修改聊天记录的读取记录
	public void updateChat(int chatid);
}
