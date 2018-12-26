package pojo;

import java.sql.Date;

public class User {
	private int userid;
	private String uname;
	private String upass;
	private String usex;
	private String uimage;
	private int ugrade;
	private long uphone;
	private Date ubirthday;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUimage() {
		return uimage;
	}
	public void setUimage(String uimage) {
		this.uimage = uimage;
	}
	public int getUgrade() {
		return ugrade;
	}
	public void setUgrade(int ugrade) {
		this.ugrade = ugrade;
	}
	public long getUphone() {
		return uphone;
	}
	public void setUphone(long uphone) {
		this.uphone = uphone;
	}
	public Date getUbirthday() {
		return ubirthday;
	}
	public void setUbirthday(Date ubirthday) {
		this.ubirthday = ubirthday;
	}
	public User(int userid, String uname, String upass, String usex, String uimage, int ugrade, long uphone,
			Date ubirthday) {
		super();
		this.userid = userid;
		this.uname = uname;
		this.upass = upass;
		this.usex = usex;
		this.uimage = uimage;
		this.ugrade = ugrade;
		this.uphone = uphone;
		this.ubirthday = ubirthday;
	}
	public User(){};
}
