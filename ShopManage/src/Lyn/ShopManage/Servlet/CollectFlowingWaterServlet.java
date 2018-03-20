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

import Lyn.ShopManage.dao.CollectFlowWaterDao;
import Lyn.ShopManage.dao.CollectMemberChildDao;
import Lyn.ShopManage.dao.CollectMemberManageDao;
import Lyn.ShopManage.dao.CollectShopDao;
import Lyn.ShopManage.entity.CollectFlowWater;
import Lyn.ShopManage.entity.CollectMemberManage;
import Lyn.ShopManage.entity.CollectShop;
import Lyn.ShopManage.util.InterfaceReturn;
import Lyn.ShopManage.util.LogPrintFormat;

public class CollectFlowingWaterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CollectFlowingWaterServlet() {
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
				if(event.equals("getOneMonthData")){
					getAllData(request,response);
				}else if(event.equals("cancelOneFlowWater")){
					cancelOneFlowWater(request,response);
				}else{
					request.getRequestDispatcher("/CFlowWater.jsp").forward(request, response);
				}
			}else{
				request.getRequestDispatcher("/CFlowWater.jsp").forward(request, response);
			}
		}else{
			LogPrintFormat.logPrint("Lyn", "IP:"+request.getRemoteAddr()+" 非法登陆库存管理");
			response.sendRedirect(request.getContextPath() + "/sessionInvalid.jsp");
		}
	}

	private void cancelOneFlowWater(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String flowWaterId=request.getParameter("flowWaterId");
		String shopId=request.getParameter("shopId");
		String shopNum=request.getParameter("shopNum");
		CollectFlowWater fw =new CollectFlowWater();
		fw.setFlowWaterId(flowWaterId);
		fw.setShopId(shopId);
		fw.setShopNumber(shopNum);
		fw.setFlowWaterType("1");
		CollectFlowWaterDao cfwDao=new CollectFlowWaterDao();
		CollectFlowWater cfw=cfwDao.selectOneData(fw);
		if(cfw.getFlowWaterType()==null){
			InterfaceReturn ir=new InterfaceReturn();
			ir.setErrorCode(1);
			ir.setErrorMsg("Databases doesn’t the record.");
			JSONObject resultJson=JSONObject.fromObject(ir);
			PrintWriter out =response.getWriter();
			out.println(resultJson);
			out.flush();
			out.close();
		}else{
			cfw.setFlowWaterType("1");
			int priNum=Integer.valueOf(cfw.getShopNumber());
			cfw.setFlowWaterId(cfw.getFlowWaterId().substring(0,cfw.getFlowWaterId().length()-6)+String.valueOf((int)((Math.random()*9+1)*100000)));
			float price=Float.valueOf(cfw.getShopTransaction())/Integer.valueOf(cfw.getShopNumber());
			float oneProfit=Float.valueOf(cfw.getProfit())/Integer.valueOf(cfw.getShopNumber());
			cfw.setShopNumber(shopNum);
			cfw.setShopTransaction("-"+String.valueOf(price*Integer.valueOf(shopNum)));
			cfw.setProfit(String.valueOf(-oneProfit*Integer.valueOf(shopNum)));
			CollectMemberManageDao cmmDao=new CollectMemberManageDao();
			
			CollectMemberManage cmm=cmmDao.useIdGetOneData(cfw.getUserId());
			
			
			CollectShopDao csDao=new CollectShopDao();
			cmm.setIntegral(cmm.getIntegral()-Integer.valueOf(shopNum)*Integer.valueOf(cfw.getIntegral())/priNum);
			cmm.setIntegralTotal(cmm.getIntegralTotal()-Integer.valueOf(shopNum)*Integer.valueOf(cfw.getIntegral())/priNum);
			cmmDao.upDataOneData(cmm.getUserId(), cmm);
			CollectShop cs=csDao.useCidGetData(cfw.getShopId());
			cs.setStockSell(cs.getStockSell()-Integer.valueOf(shopNum));
			cs.setStockBalance(cs.getStockBalance()+Integer.valueOf(shopNum));
			csDao.upDataBalance(cs.getCid(), cs.getStockBalance(), cs.getStockSell());
			cfwDao.insertIntoOneData(cfw);
			LogPrintFormat.logPrint("Lyn", "退单成功,id:"+cfw.getFlowWaterId());
			CollectFlowWaterDao cfDao=new CollectFlowWaterDao();
			InterfaceReturn ir=new InterfaceReturn();
			ArrayList<CollectFlowWater> cfList=cfDao.GetAllDataList();
			ir.setErrorCode(0);
			ir.setErrorMsg("");
			JSONArray cfListJson=JSONArray.fromObject(cfList);
			JSONObject resultJson=JSONObject.fromObject(ir);
			resultJson.put("Item", cfListJson);
			PrintWriter out =response.getWriter();
			out.println(resultJson);
			out.flush();
			out.close();
		}
		
		
		
	}

	private void getAllData(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectFlowWaterDao cfDao=new CollectFlowWaterDao();
		InterfaceReturn ir=new InterfaceReturn();
		ArrayList<CollectFlowWater> cfList=cfDao.GetAllDataList();
		ir.setErrorCode(0);
		ir.setErrorMsg("");
		JSONArray cfListJson=JSONArray.fromObject(cfList);
		JSONObject resultJson=JSONObject.fromObject(ir);
		resultJson.put("Item", cfListJson);
		PrintWriter out =response.getWriter();
		out.println(resultJson);
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
