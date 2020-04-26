package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import dao.UsersDao;
import bean.Message;

public class Update_user_balance extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Update_user_balance() {
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
request.setCharacterEncoding("utf-8");
response.setContentType("text/html;utf-8");
response.setCharacterEncoding("utf-8");
Message message=new Message();
PrintWriter printWriter=response.getWriter();
String uerid=request.getParameter("uerid");
String price=request.getParameter("price");
try {
	if (UsersDao.update_user_balance(uerid, price)) {
		message.setCode(200);
		message.setData(null);
		message.setMessage("支付成功");
	}else {
		message.setCode(-11);
		message.setData(null);
		message.setMessage("支付失败");
	}
} catch (SQLException e) {
	// TODO 自动生成的 catch 块
	e.printStackTrace();
	message.setCode(-11);
	message.setData(null);
	message.setMessage("支付失败");
}
printWriter.println(JSON.toJSONString(message));
		
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

		doGet(request, response);
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
