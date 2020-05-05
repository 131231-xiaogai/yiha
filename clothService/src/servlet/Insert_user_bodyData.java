package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;

import com.alibaba.fastjson.JSON;

import dao.ConsumerDao;
import dao.EventDao;

public class Insert_user_bodyData extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Insert_user_bodyData() {
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
		
		String user_id= request.getParameter("user_id");
		String weight= request.getParameter("weight");
		String height= request.getParameter("height");
		String bust=request.getParameter("bust");
		String the_waist= request.getParameter("the_waist");
		String hipline= request.getParameter("hipline");
		String shoulder_width= request.getParameter("shoulder_width");
		String clothing_length=request.getParameter("clothing_length");
		String trousers_length= request.getParameter("trousers_length");
		
		
		System.out.println("添加用户参数的用户ID ："+user_id);
		
		Message me=new Message();
		
		try {
			if(ConsumerDao.insert_user_bodyData( 
					 user_id,
					 weight,
					 height,
					 bust,
					 the_waist,
					 hipline,
					 shoulder_width,
					 clothing_length,
					 trousers_length)){
				me.setCode(200);
				me.setMessage("保存用户参数成功！");
				me.setData(null);
			}else{
				me.setCode(-11);//返回给前端程序代码
				me.setMessage("保存用户参数失败，请重试。");//返回给用户看
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

