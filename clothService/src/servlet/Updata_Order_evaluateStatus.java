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

import dao.OrderDao;

public class Updata_Order_evaluateStatus extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Updata_Order_evaluateStatus() {
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
		
		String order_id= request.getParameter("order_id");
		//String contact_name=new String(request.getParameter("contact_name").getBytes("ISO8859-1"),"UTF-8");
		String evaluate_status= request.getParameter("evaluate_status");

		System.out.println("�����Ķ������Ϊ"+order_id+"��������״̬Ϊ"+evaluate_status);
		
		Message me=new Message();
		
		try {
			if(OrderDao.updata_Order_evaluateStatus(order_id, evaluate_status)){
				me.setCode(200);
				me.setMessage("�޸Ķ�������״̬�ɹ���");
				me.setData(null);
			}else{
				me.setCode(-11);//���ظ�ǰ�˳������
				me.setMessage("�޸Ķ�������״̬ʧ�ܣ������ԡ�");//���ظ��û���
				me.setData(null);
				
			}
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
