package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import dao.AddressDao;
import dao.GoodDao;
import dao.UsersDao;
import bean.Message;

public class Update_good_by_goodIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Update_good_by_goodIdServlet() {
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
		
		String goods_id= request.getParameter("goods_id");
		String goods_name=new String(request.getParameter("goods_name").getBytes("ISO8859-1"),"UTF-8");
		String goods_price= request.getParameter("goods_price");
		String goods_yajin=request.getParameter("goods_yajin");
		String goods_number=request.getParameter("goods_number");
		String size= request.getParameter("size");
		String clothing_length= request.getParameter("clothing_length");
		String sleeve_length= request.getParameter("sleeve_length");
		String shoulder_width= request.getParameter("shoulder_width");
		String trousers_length= request.getParameter("trousers_length");

		System.out.println(goods_name);
		
		Message me=new Message();
		try {
			if(GoodDao.update_good_by_goodId(goods_id, goods_name,goods_price,goods_yajin,goods_number,size,clothing_length,
					sleeve_length,shoulder_width,trousers_length)){
				me.setCode(200);
				me.setMessage("����ɹ���");
				me.setData(null);
			}else{
				me.setCode(-11);//���ظ�ǰ�˳������
				me.setMessage("����ʧ�ܣ������ԡ�");//���ظ��û���
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
