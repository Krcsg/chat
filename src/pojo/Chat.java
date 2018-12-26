package pojo;

import java.sql.Timestamp;

public class Chat {
	private int chatid;
	private int userid;
	private int userids;
	private String message;
	private Timestamp chatDate;
	private int flag;
	
	public int flag(){
		return flag;
	}
	public void setFlag(int flag){
		this.flag = flag;
	}
	public int getChatid(){
		return chatid;
	}
	public void setChatid(int chatid){
		this.chatid = chatid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getUserids() {
		return userids;
	}
	public void setUserids(int userids) {
		this.userids = userids;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getChatDate() {
		return chatDate;
	}
	public void setChatDate(Timestamp timestamp) {
		this.chatDate = timestamp;
	}
	public Chat(int chatid,int userid, int userids, String message, Timestamp chatDate,int flag) {
		super();
		this.chatid = chatid;
		this.userid = userid;
		this.userids = userids;
		this.message = message;
		this.chatDate = chatDate;
		this.flag = flag;
	}
	public Chat(){};
}
