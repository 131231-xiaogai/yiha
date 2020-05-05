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

import dao.GoodDao;

public class Update_goodNumber_add extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Update_goodNumber_add() {
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
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;utf-8");

		PrintWriter out = response.getWriter();
		String good_id = request.getParameter("good_id");
		String good_number = request.getParameter("ruturn_goodNumber");
		System.out.println("��Ʒ���"+good_id+"�������˻��·�����Ϊ"+good_number);
		Message message = new Message();
		try {
			if (GoodDao.update_goodNumber_add(good_id, good_number)) {
				message.setCode(200);
				message.setData(null);
				message.setMessage("�޸ĳɹ�");
			} else {
				message.setCode(-11);
				message.setData(null);
				message.setMessage("�޸�ʧ��");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			message.setCode(-11);
//			message.setData(null);
//			message.setMessage("�޸�ʧ��");
		}
		out.println(JSON.toJSONString(message));
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

