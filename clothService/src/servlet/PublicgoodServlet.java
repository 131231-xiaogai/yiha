package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;

import dao.GoodDao;
import bean.GoodBean;
import bean.Message;

public class PublicgoodServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PublicgoodServlet() {
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

		response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Message message = new Message();
        GoodBean goodBean = new GoodBean();
        DiskFileItemFactory factory = new DiskFileItemFactory();//工厂类
        ServletFileUpload upload = new ServletFileUpload(factory);//servlet上传文件的工具类
        List<FileItem> items = null;
        try {
            items = (List<FileItem>) upload.parseRequest(request);
        } catch (FileUploadException e1) {
            e1.printStackTrace();
        }
        Iterator<FileItem> it = items.iterator();//迭代器==》for自动循环
        if (items != null) {
            while (it.hasNext()) {
                FileItem tempitemFileItem = it.next();
                String itemNameString = tempitemFileItem.getFieldName();//项名称字符串
                if (tempitemFileItem.isFormField()) {
                	//是否是文本字段
                    String content = tempitemFileItem.getString("utf-8");
                    if ("good_name".equals(itemNameString)) {
                    	goodBean.setGoods_name(content);
                    }else if ("goods_price".equals(itemNameString)) {
						goodBean.setGoods_price(content);
					}else if ("goods_yajin".equals(itemNameString)) {
						goodBean.setGoods_yajin(content);	
					}else if ("goods_size_id".equals(itemNameString)) {
						goodBean.setSize(content);	
					} else if ("shop_id".equals(itemNameString)) {
						goodBean.setShop_id(content);	
					}else if ("shop_name".equals(itemNameString)) {
						goodBean.setShop_name(content);
					}
					else if ("type_id".equals(itemNameString)) {
						goodBean.setType_id(content);
					}else if ("type_activity_id".equals(itemNameString)) {
						goodBean.setType_activity_id(content);
					}else if ("add_number".equals(itemNameString)) {
						goodBean.setGoods_number(content);
					}
					else if ("Clothing_length".equals(itemNameString)) {
						goodBean.setClothing_length(content);
					}else if ("Sleeve_length".equals(itemNameString)) {
						goodBean.setSleeve_length(content);
					}else if ("Shoulder_width".equals(itemNameString)) {
						goodBean.setShoulder_width(content);
					}else if ("trousers_length".equals(itemNameString)) {
						goodBean.setTrousers_length(content);
					}
                    
                } else {
                    File tempFile = new File(request.getSession().getServletContext().getRealPath("/") + "images" + File.separator
                            + new File(System.currentTimeMillis() + "_" + tempitemFileItem.getName()).getName());
                    System.out.println(tempFile);
                    if (!tempFile.getParentFile().exists()) tempFile.getParentFile().mkdirs();
                    try {
                        tempitemFileItem.write(tempFile);//保存文件/图片

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    goodBean.setGood_img(tempFile.getName());
                }

            }
        }
        try {
            GoodDao goodDao = new GoodDao();
            boolean addFlag = GoodDao.addgood(
            		goodBean.getGood_img(),//1
            		goodBean.getGoods_name(),//2
            		goodBean.getGoods_price(),//3
            		goodBean.getGoods_yajin(),//4
            		goodBean.getSize(),//5
            		goodBean.getShop_id(),//6
            		goodBean.getShop_name(),//7
                    goodBean.getType_id(),//8
                    goodBean.getType_activity_id(),//9
                    goodBean.getGoods_number()//10
                    );
	
            if (addFlag) {
            	message.setCode(200);
                message.setData(null);
                message.setMessage("添加成功");
                out.print(JSON.toJSONString(message));
            } else {
                message.setCode(-11);
                message.setData("null");
                message.setMessage("添加失败");
                out.print(JSON.toJSONString(message));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

	
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
