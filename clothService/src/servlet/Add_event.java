package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;
import bean.Shooping_carBean;

import com.alibaba.fastjson.JSON;

import dao.EventDao;
import dao.Shoop_carDao;

public class Add_event extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Add_event() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;utf-8");
		
		PrintWriter out = response.getWriter();
		
		String user_id =request.getParameter("event_date");
		
		String evevt_title =new String(request.getParameter("evevt_title").getBytes("ISO8859-1"),"utf-8");
		String event_content=new String( request.getParameter("event_content").getBytes("ISO8859-1"),"utf-8");
		
		String event_date= request.getParameter("event_date");
		String event_start_time= request.getParameter("event_start_time");
		String event_finish_time= request.getParameter("event_finish_time");
		
		System.out.println("添 加 事 件 的 用 户 ID ："+user_id);
		
		Message me=new Message();
		Shooping_carBean shooping_carBean =new Shooping_carBean();
		
		try {
//			 Date date = new Date();
//             SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//              shooping_carBean.setAdd_time(simpleDateFormat.format(date));
			if(EventDao.addevent(user_id,evevt_title,event_content,event_date,event_start_time,event_finish_time)){
				me.setCode(200);
				me.setMessage("保存事件成功！");
				me.setData(null);
			}else{
				me.setCode(-11);//返回给前端程序代码
				me.setMessage("保存失败，请重试。");//返回给用户看
				me.setData(null);	
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		out.println(JSON.toJSONString(me));
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
