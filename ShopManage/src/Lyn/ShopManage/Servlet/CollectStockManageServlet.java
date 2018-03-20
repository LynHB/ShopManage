package Lyn.ShopManage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import Lyn.ShopManage.dao.CollectShopDao;
import Lyn.ShopManage.dao.CollectStockClassificationDao;
import Lyn.ShopManage.entity.CollectShop;
import Lyn.ShopManage.entity.CollectStockClassification;
import Lyn.ShopManage.util.LogPrintFormat;

public class CollectStockManageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CollectStockManageServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");  
		HttpSession session = request.getSession();
		if(session.getAttribute("name")!=null&&session.getAttribute("authority").equals("0")){
			if(request.getParameter("event")!=null){
				String event=request.getParameter("event");
				if(event.equals("getClassification")){
					getCollectStockClassificationInterface(request,response);
				}else if(event.equals("insertOneData")){
					LogPrintFormat.logPrint("Lyn", "收银系统  执行insertIntoStockShop方法");
					insertIntoStockShop(request, response);
				}else if(event.equals("getAllShopping")){
					try{
						LogPrintFormat.logPrint("Lyn", "收银系统  执行getAllShopData方法");
						getAllShopData(request,response);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else if(event.equals("useCidGetShopInfo")){
					LogPrintFormat.logPrint("Lyn", "收银系统  执行useCidGetShopInfo方法");
					useCidGetShopInfo(request,response);
					
				}else if(event.equals("upDataOneData")){
					LogPrintFormat.logPrint("Lyn", "收银系统  执行upDataOneData方法");
					upDataOneData(request,response);
				}else{
					request.getRequestDispatcher("/CStockManage.jsp").forward(request, response);
				}
			}else{
				request.getRequestDispatcher("/CStockManage.jsp").forward(request, response);
			}
		}else{
			LogPrintFormat.logPrint("Lyn", "IP:"+request.getRemoteAddr()+" 非法登陆库存管理");
			response.sendRedirect(request.getContextPath() + "/sessionInvalid.jsp");
		}
	}


	private void upDataOneData(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String primitiveCid=request.getParameter("primitive-cid");
		String cid =request.getParameter("cid");
		String classification=request.getParameter("classification");
		String name=request.getParameter("name");
		String stockBalance=request.getParameter("stockBalance");
		String averageCost=request.getParameter("averageCost");
		String marketingPrice=request.getParameter("marketingPrice");
		String specifications=request.getParameter("specifications");
		String brand=request.getParameter("brand");
		String detail=request.getParameter("detail");
		if(!primitiveCid.equals("")&&!cid.equals("")&&!classification.equals("")&&!name.equals("")&&!stockBalance.equals("")&&!averageCost.equals("")&&!marketingPrice.equals("")&&!specifications.equals("")&&!brand.equals("")){
			CollectShop cs=new CollectShop();
			CollectShopDao csDao=new CollectShopDao();
			cs.setCid(cid);
			cs.setClassification(classification);
			cs.setName(name);
			cs.setStockBalance(Integer.valueOf(stockBalance));
			cs.setAverageCost(averageCost);
			cs.setMarketingPrice(marketingPrice);
			cs.setSpecifications(specifications);
			cs.setBrand(brand);
			cs.setDetail(detail);
			
			if(csDao.upDataOneData(cs,primitiveCid)==0){
				request.setAttribute("returnSuccessPage", "\"./CStockManage\"");
				LogPrintFormat.logPrint("Lyn", "收银系统 修改编号"+primitiveCid+"商品成功");
				request.getRequestDispatcher("/submitSuccess.jsp").forward(request, response);
			}else{
				LogPrintFormat.logPrint("Lyn", "收银系统 修改编号"+primitiveCid+"商品失败");
				request.getRequestDispatcher("/submitError.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("/submitError.jsp").forward(request, response);
		}
		
	}

	private void useCidGetShopInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String priCid=request.getParameter("cid");
		if(!priCid.equals("")){
			CollectShopDao csDao=new CollectShopDao();
			CollectShop cs=csDao.useCidGetData(priCid);
			JSONObject json=JSONObject.fromObject(cs);
			LogPrintFormat.logPrint("Lyn", "收银系统：ajax 使用CID获取商品信息");
			PrintWriter out=response.getWriter();
			out.println(json);
			out.flush();
			out.close();
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

		doGet(request,response);
	}
	
	public void getCollectStockClassificationInterface(HttpServletRequest request,HttpServletResponse response) throws IOException{
		CollectStockClassificationDao classDao=new CollectStockClassificationDao();
		ArrayList<CollectStockClassification> classificationList=classDao.selectAllData();
		JSONArray jsonArray=JSONArray.fromObject(classificationList);
		LogPrintFormat.logPrint("Lyn", "收银系统：ajax 获取分类信息");
		PrintWriter out=response.getWriter();
		out.println(jsonArray);
		out.flush();
		out.close();
	}
	
	
	public void insertIntoStockShop(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String cid =request.getParameter("cid");
		String classification=request.getParameter("classification");
		String name=request.getParameter("name");
		String stockBalance=request.getParameter("stockBalance");
		String averageCost=request.getParameter("averageCost");
		String marketingPrice=request.getParameter("marketingPrice");
		String specifications=request.getParameter("specifications");
		String brand=request.getParameter("brand");
		String detail=request.getParameter("detail");
		System.out.println(brand);
		if(!cid.equals("")&&!classification.equals("")&&!name.equals("")&&!stockBalance.equals("")&&!averageCost.equals("")&&!marketingPrice.equals("")&&!specifications.equals("")&&!brand.equals("")){
			CollectShop cs=new CollectShop();
			CollectShopDao csDao=new CollectShopDao();
			cs.setCid(cid);
			cs.setClassification(classification);
			cs.setName(name);
			cs.setStockBalance(Integer.valueOf(stockBalance));
			cs.setAverageCost(averageCost);
			cs.setMarketingPrice(marketingPrice);
			cs.setSpecifications(specifications);
			cs.setBrand(brand);
			cs.setDetail(detail);
			cs.setStockSell(0);
			if(csDao.insertOneData(cs)==0){
				LogPrintFormat.logPrint("Lyn", "收银系统 增加商品成功");
				request.setAttribute("returnSuccessPage", "\"./CStockManage\"");
				request.getRequestDispatcher("/submitSuccess.jsp").forward(request, response);
			}else{
				LogPrintFormat.logPrint("Lyn", "收银系统 增加商品失败");
				request.getRequestDispatcher("/submitError.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("/submitError.jsp").forward(request, response);
		}
			
		
	}

	
	private void getAllShopData(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try{
			CollectShopDao csDao=new CollectShopDao();
			ArrayList<CollectShop> csList=csDao.selectAllData();
			JSONArray jsonArray=JSONArray.fromObject(csList);
			LogPrintFormat.logPrint("Lyn", "收银系统：ajax 获取商品所有数据");
			PrintWriter out=response.getWriter();
			out.println(jsonArray);
			out.flush();
			out.close();
		}catch(Exception ex){
			ex.printStackTrace();
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
