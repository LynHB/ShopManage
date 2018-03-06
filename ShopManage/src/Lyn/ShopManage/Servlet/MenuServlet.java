package Lyn.ShopManage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Lyn.ShopManage.util.LogPrintFormat;

public class MenuServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MenuServlet() {
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
		HttpSession session= request.getSession();
		String type=request.getParameter("type");
		if(session.getAttribute("name")!=null&&type.equals("menu")&&session.getAttribute("authority").equals("0")){
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			request.setAttribute("sessionName",session.getAttribute("name"));
			request.setAttribute("sessionPhone",session.getAttribute("phoneId"));
			request.setAttribute("seesionBirthday",session.getAttribute("birthday"));
			request.setAttribute("sessionPhone",session.getAttribute("phoneId"));
			request.setAttribute("sessionE_mail",session.getAttribute("E_mail"));
			request.setAttribute("sessionAccountId",session.getAttribute("accountId"));
			request.setAttribute("title","我的首页");
			LogPrintFormat.logPrint("Lyn", "用户:"+session.getAttribute("name")+" 登陆个人主页");
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}else if(session.getAttribute("name")!=null&&type.equals("cancel")){
			LogPrintFormat.logPrint("Lyn", "用户:"+session.getAttribute("name")+" 注销登陆");
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/login");
		}else if(session.getAttribute("name")!=null&&type.equals("menu")&&session.getAttribute("authority").equals("1")){
			LogPrintFormat.logPrint("Lyn", "普通用户页面待完成");
		}else{
			LogPrintFormat.logPrint("Lyn", "IP:"+request.getRemoteAddr()+" 非法登陆主页菜单");
			response.sendRedirect(request.getContextPath()+"/login");
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
