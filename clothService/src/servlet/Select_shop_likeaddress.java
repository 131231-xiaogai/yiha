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
import bean.ShopBean;
import bean.TMessage;

import com.alibaba.fastjson.JSON;

import dao.OrderDao;
import dao.ShopDao;

public class Select_shop_likeaddress extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Select_shop_likeaddress() {
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
		
		String shop_dresss=new String(request.getParameter("shop_dresss").getBytes("ISO8859-1"),"UTF-8");

		//String good_name=new String(request.getParameter("good_name").getBytes("ISO8859-1"),"UTF-8");

		System.out.println("查询店铺内容"+shop_dresss);
		
		
		TMessage  <List<ShopBean>> tMessage=new TMessage();
		
		PrintWriter printWriter=response.getWriter();
		
		try {
			List<ShopBean> shopBeans = ShopDao.select_shop_likeaddress(shop_dresss);
				tMessage.setCode(200);
				tMessage.setMessage("查询成功");
				tMessage.setData(shopBeans);   //存放要返回给前端显示的数据
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				tMessage.setCode(-11);
				tMessage.setMessage("查询失败");
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