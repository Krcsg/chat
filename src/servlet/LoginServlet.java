package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import daoImpl.userDaoImpl;
import pojo.Chat;
import pojo.User;

public class LoginServlet extends HttpServlet {
	int time = 4;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setHeader("content-type", "text/html;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			HttpSession session = req.getSession();
			String action = req.getParameter("action");
			userDaoImpl ud = new userDaoImpl();
			if(action.equals("login")){
				String name = req.getParameter("userName");
				String psw = req.getParameter("passWord");
				User u = ud.checkUser(name, psw);
				if(u == null){
						PrintWriter out = resp.getWriter();
						out.print(time);	
						out.close();
						time--;
				}
			}else if(action.equals("login2")){
				String name = req.getParameter("userName");
				String psw = req.getParameter("passWord");
				User u = ud.checkUser(name, psw);
				List<User> us= ud.findUserById(u.getUserid());
				session.setAttribute("user", u);
				session.setAttribute("ulist", us);
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.forward(req, resp);
			}else if(action.equals("image")){
				String name = req.getParameter("userName");
			    String image = ud.findUserImage(name);
			    PrintWriter out = resp.getWriter();
			    out.print(image);	
			    out.close();
			}else if(action.equals("message")){
				String ms = req.getParameter("ms");
				int userid = Integer.parseInt(req.getParameter("userid"));
				int userids = Integer.parseInt(req.getParameter("userids"));
				ud.addChat(userid, userids, ms);
				PrintWriter out = resp.getWriter();
				out.print("success");	
				out.close();
			}else if(action.equals("answer")){
				List<Chat> listc = new ArrayList<>();
				int userid = Integer.parseInt(req.getParameter("userid"));
				int userids = Integer.parseInt(req.getParameter("userids"));
				List<Chat> list = ud.findChat();
				if(list != null){
					for(Chat c: list){
						if(c.getUserid()==userids&&c.getUserids()==userid){
							listc.add(c);
						}
					}
					JSONArray array= JSONArray.parseArray(JSON.toJSONString(listc));
					PrintWriter out = resp.getWriter();
					out.print(array);	
					out.close();
					for(Chat cs:listc){
						ud.updateChat(cs.getChatid());
					}
				}else{
					PrintWriter out = resp.getWriter();
					out.print("error");	
					out.close();
				}
			}
	}
}
