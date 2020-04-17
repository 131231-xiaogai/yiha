package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import dao.ShopDao;
import dao.UsersDao;
import bean.Message;
import bean.Shooping_carBean;
import bean.ShopBean;

public class Regiest_shopServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Regiest_shopServlet() {
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
		String user_id=request.getParameter("user_id");
		String shop_name=new String(request.getParameter("shop_name").getBytes("ISO8859-1"),"UTF-8");
		String shop_dresss= new String(request.getParameter("shop_dresss").getBytes("ISO8859-1"),"UTF-8");
		String shop_phone=request.getParameter("shop_phone");
	
		System.out.println(shop_name);
		System.out.println(shop_dresss);
		Message me=new Message();
		
		ShopBean shopBean =new ShopBean();
		
		try {
			 Date date = new Date();
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              shopBean.setShop_regist_time(simpleDateFormat.format(date));
			if(ShopDao.regiest_shop(shop_name,shop_dresss,shop_phone,shopBean.getShop_regist_time(),user_id)){
				me.setCode(200);
				me.setMessage("成功注册店铺！");
				me.setData(null);
			}else{
				me.setCode(-11);//返回给前端程序代码
				me.setMessage("注册失败，请重试。");//返回给用户看
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
