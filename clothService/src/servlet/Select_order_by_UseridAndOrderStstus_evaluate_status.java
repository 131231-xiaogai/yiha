package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderBean;
import bean.TMessage;

import com.alibaba.fastjson.JSON;

import dao.OrderDao;

public class Select_order_by_UseridAndOrderStstus_evaluate_status extends
		HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Select_order_by_UseridAndOrderStstus_evaluate_status() {
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
		//String user_id,String order_status
		
		String user_id=request.getParameter("user_id");
		String order_status=request.getParameter("order_status");
		String evaluate_status=request.getParameter("evaluate_status");
		System.out.println("�û�"+user_id+"��ѯ����״̬Ϊ"+order_status+"�Ķ���");
		
		
		TMessage  <List<OrderBean>> tMessage=new TMessage();
		
		PrintWriter printWriter=response.getWriter();
		
		try {
			List<OrderBean> orderBeans = OrderDao.select_order_by_UseridAndOrderStstus_evaluate_status(user_id, order_status,evaluate_status);
				tMessage.setCode(200);
				tMessage.setMessage("��ѯ�ɹ�");
				tMessage.setData(orderBeans);   //���Ҫ���ظ�ǰ����ʾ������
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				tMessage.setCode(-11);
				tMessage.setMessage("��ѯʧ��");
				tMessage.setData(null);
				e.printStackTrace();
			}
			
		
		printWriter.print(JSON.toJSONString(tMessage));
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