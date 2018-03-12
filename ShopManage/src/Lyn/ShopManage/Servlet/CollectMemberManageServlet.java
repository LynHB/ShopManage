package Lyn.ShopManage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import Lyn.ShopManage.dao.CollectMemberChildDao;
import Lyn.ShopManage.dao.CollectMemberManageDao;
import Lyn.ShopManage.entity.CollectMemberManage;
import Lyn.ShopManage.util.LogPrintFormat;

public class CollectMemberManageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CollectMemberManageServlet() {
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

		doPost(request,response);
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

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();
		if(session.getAttribute("name")!=null&&session.getAttribute("authority").equals("0")){
			if(request.getParameter("event")!=null){
				String event=request.getParameter("event");
				if(event.equals("getAllMemberInfo")){
					getAllMemberInfo(request,response);
				}else if(event.equals("insertOneData")){
					//insertOneData(request,response);
				}else{
					request.getRequestDispatcher("/CMemberManage.jsp").forward(request, response);
				}
			}else{
					request.getRequestDispatcher("/CMemberManage.jsp").forward(request, response);
			}
		}else{
			LogPrintFormat.logPrint("Lyn", "IP:"+request.getRemoteAddr()+" 非法登陆库存管理");
			response.sendRedirect(request.getContextPath() + "/sessionInvalid.jsp");
		}
	}

	private void insertOneData(HttpServletRequest request,HttpServletResponse response) {
		String userId=request.getParameter("userId");
		String userName=request.getParameter("userName");
		String userAddress=request.getParameter("userAddress");
		String userIntegral=request.getParameter("userIntegral");
		String childName=request.getParameter("childName");
		String childName2=request.getParameter("childName2");
		String childBirthday=request.getParameter("childBirthday");
		String childBirthday2=request.getParameter("childBirthday2");
		int childSex=Integer.valueOf(request.getParameter("childSex"));
		int childSex2=Integer.valueOf(request.getParameter("childSex2"));
		if(!userId.equals("")&&!userName.equals("")&&!childName.equals("")&&!childBirthday.equals("")&&){
			if(!childName2.equals("")&&!childBirthday2.equals("")){
				
			}else{
				
			}
		}
		
		
		
	}

	private void getAllMemberInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		LogPrintFormat.logPrint("Lyn", "收银系统：AJAX开始返回会员列表");
		CollectMemberManageDao cmmd=new CollectMemberManageDao();
		ArrayList<CollectMemberManage> cmmList=cmmd.selectAllData();
		JSONArray jsonArray=JSONArray.fromObject(cmmList);
		PrintWriter out=response.getWriter();
		out.println(jsonArray);
		out.flush();
		out.close();
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
