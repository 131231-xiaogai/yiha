package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AddressBean;
import bean.EvaluateBean;
import bean.TMessage;

import com.alibaba.fastjson.JSON;

import dao.AddressDao;
import dao.EvaluateDao;

public class Select_evaluate_by_orderid extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Select_evaluate_by_orderid() {
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
		
		String order_id=request.getParameter("order_id");
		System.out.println("查询订单评分的订单编号"+order_id);
		
		TMessage  tMessage=new TMessage(); 
		
		PrintWriter printWriter=response.getWriter();
		
		try {
			EvaluateBean evaluateBean=EvaluateDao.select_evaluate_by_orderid(order_id);
			
				tMessage.setCode(200);
				tMessage.setMessage("查询订单评分成功");
				tMessage.setData(evaluateBean);   //存放要返回给前端显示的数据
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				tMessage.setCode(-11);
				tMessage.setMessage("查询订单评分失败");
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
