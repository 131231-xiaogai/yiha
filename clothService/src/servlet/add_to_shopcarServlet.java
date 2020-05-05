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

import dao.Shoop_carDao;
import dao.UsersDao;
import bean.Message;
import bean.Shooping_carBean;

public class add_to_shopcarServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public add_to_shopcarServlet() {
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
		
		String user_id= request.getParameter("user_id");
		String goods_id =request.getParameter("goods_id");
		String good_number =request.getParameter("good_number");
		String shop_id= request.getParameter("shop_id");
		
		String good_name=new String( request.getParameter("good_name").getBytes("ISO8859-1"),"utf-8");
		String good_price= request.getParameter("good_price");
		String good_img= request.getParameter("good_img");
		
		String shop_name=new String( request.getParameter("shop_name").getBytes("ISO8859-1"),"utf-8");
		String cancle_time= request.getParameter("cancle_time");
		String shop_car_status= request.getParameter("shop_car_status");
		String goods_yajin = request.getParameter("goods_yajin");
		
		System.out.println(user_id);
		
		Message me=new Message();
		Shooping_carBean shooping_carBean =new Shooping_carBean();
		
		try {
			 Date date = new Date();
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              shooping_carBean.setAdd_time(simpleDateFormat.format(date));
			if(Shoop_carDao.add_to_shopcar(user_id,good_number,goods_id,shop_id,good_name,
					good_price,good_img,shop_name,shooping_carBean.getAdd_time(),cancle_time,shop_car_status,goods_yajin)){
				me.setCode(200);
				me.setMessage("保存成功！");
				me.setData(null);
			}else{
				me.setCode(-11);//返回给前端程序代码
				me.setMessage("保存失败，请重试。");//返回给用户看
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
