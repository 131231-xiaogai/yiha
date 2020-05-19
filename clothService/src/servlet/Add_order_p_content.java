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

import dao.EvaluateDao;
import dao.EventDao;

public class Add_order_p_content extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Add_order_p_content() {
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
		
		String p_content= request.getParameter("p_content");
		String order_id= request.getParameter("order_id");

		String good_id= request.getParameter("good_id");
		String user_id=request.getParameter("user_id");
		String shop_id= request.getParameter("shop_id");
		
		System.out.println("添加事件用户ID ："+user_id);
		
		Message me=new Message();
		
		try {
			if(EvaluateDao.add_order_p_content(p_content,order_id, good_id,user_id,shop_id)){
				me.setCode(200);
				me.setMessage("保存评价成功！");
				me.setData(null);
			}else{
				me.setCode(-11);//返回给前端程序代码
				me.setMessage("保存评价失败，请重试。");//返回给用户看
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
