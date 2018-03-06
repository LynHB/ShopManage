package Lyn.ShopManage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Lyn.ShopManage.entity.Account;
import Lyn.ShopManage.util.LogPrintFormat;

public class ConfirmServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ConfirmServlet() {
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
		//权限服务后期独立开发
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		Account at =  new Account(userName,passWord);
		try {
			String confirmStr=at.AccountConfirm();
			if(confirmStr.equals("0")){
				HttpSession session =request.getSession();
				String sessionInfo=at.getInfo();
				if(sessionInfo.equals("0")){
					session.setAttribute("accountId", at.getAccountId());
					session.setAttribute("name", at.getUserName());
					session.setAttribute("E_mail",at.getE_mail());
					session.setAttribute("phoneId",at.getPhoneId());
					session.setAttribute("birthday",at.getBirthday());
					session.setAttribute("sex",at.getSex());
					session.setAttribute("authority",at.getAuthority());
					response.sendRedirect(request.getContextPath()+"/menu?type=menu");
				}else{
					LogPrintFormat.logPrint("Lyn", "美贝ID:"+userName+" 数据库异常，请检查数据库");
					request.setAttribute("title","我的登陆页");
					request.setAttribute("errorInfo", "<b style=\"color:red;\">"+confirmStr+"</b>");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("title","我的登陆页");
				request.setAttribute("errorInfo", "<b style=\"color:red;\">"+confirmStr+"</b>");
				LogPrintFormat.logPrint("Lyn", "美贝ID:"+userName+" "+confirmStr);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
