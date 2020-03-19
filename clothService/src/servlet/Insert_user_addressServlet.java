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

import dao.AddressDao;

public class Insert_user_addressServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Insert_user_addressServlet() {
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
		
		String userid= request.getParameter("userid");
		String contact_name=new String(request.getParameter("contact_name").getBytes("ISO8859-1"),"UTF-8");
		String contact_phone= request.getParameter("contact_phone");
		String address_total=new String(request.getParameter("address_total").getBytes("ISO8859-1"),"UTF-8");
		String address_detail=new String(request.getParameter("address_detail").getBytes("ISO8859-1"),"UTF-8");
		String creator_phone= request.getParameter("creator_phone");
		System.out.println(creator_phone);
		
		Message me=new Message();
		
		try {
			if(AddressDao.insert_user_address(contact_name, contact_phone,address_total,address_detail,userid,creator_phone)){
				me.setCode(200);
				me.setMessage("保存成功！");
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
