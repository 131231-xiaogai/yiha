package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import dao.GoodDao;
import dao.Shoop_carDao;
import bean.GoodBean;
import bean.Shooping_carBean;
import bean.TMessage;

public class Select_shopcar_by_useridServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Select_shopcar_by_useridServlet() {
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
		
		String user_id=request.getParameter("user_id");
		String shop_car_status=request.getParameter("shop_car_status");
		
		TMessage  <List<Shooping_carBean>> tMessage=new TMessage();
		
		PrintWriter printWriter=response.getWriter();
		
		try {
			List<Shooping_carBean> shooping_carBeans = Shoop_carDao.select_shopcar_by_userid(user_id,shop_car_status);
				tMessage.setCode(200);
				tMessage.setMessage("��ѯ�ɹ�");
				tMessage.setData(shooping_carBeans);   //���Ҫ���ظ�ǰ����ʾ������
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
