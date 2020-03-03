package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.Message;
import dao.UserDao;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;utf-8");


		PrintWriter out = response.getWriter();

		Message message=new Message();

		String phone = request.getParameter("phone");

		String password = request.getParameter("password");

		try {

			if (UserDao.userLogin(phone, password)) {

					System.out.println("success in Server ");

					message.setCode(200);

					message.setData("token");

					message.setMessage("登陆成功");

					out.print(JSON.toJSONString(message));

				} else {

					message.setCode(-11);

					message.setData("null");

					message.setMessage("登录失败");

					out.print(JSON.toJSONString(message));

				}



		

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
