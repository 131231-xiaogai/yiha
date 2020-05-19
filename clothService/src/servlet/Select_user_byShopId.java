package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ShopBean;
import bean.TMessage;
import bean.UsersBean;

import com.alibaba.fastjson.JSON;

import dao.ShopDao;
import dao.UsersDao;

public class Select_user_byShopId extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Select_user_byShopId() {
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
		
		String shop_id= request.getParameter("shop_id");
		
		System.out.println("��ѯ�û���ŵ��տ��̼���"+shop_id);

		TMessage  tMessage=new TMessage(); 
		
		PrintWriter printWriter=response.getWriter();
		try {
			ShopBean shopBean=ShopDao.select_user_by_shopID(shop_id);
			tMessage.setCode(200);
			tMessage.setMessage("��ѯ�ɹ�");
			tMessage.setData(shopBean);   //���Ҫ���ظ�ǰ����ʾ������
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