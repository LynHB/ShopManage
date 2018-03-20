package Lyn.ShopManage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import Lyn.ShopManage.dao.CollectFlowWaterDao;
import Lyn.ShopManage.dao.CollectMemberManageDao;
import Lyn.ShopManage.dao.CollectShopDao;
import Lyn.ShopManage.dao.CollectStockClassificationDao;
import Lyn.ShopManage.entity.CollectFlowWater;
import Lyn.ShopManage.entity.CollectMemberManage;
import Lyn.ShopManage.entity.CollectShop;
import Lyn.ShopManage.util.InterfaceReturn;
import Lyn.ShopManage.util.LogPrintFormat;

public class CollectStartServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CollectStartServlet() {
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
//		if(session.getAttribute("name")!=null&&session.getAttribute("authority").equals("0")){
		if(true){
			if(request.getParameter("event")!=null){
				String event=request.getParameter("event");
				if(event.equals("getOneMemberInfo")){
					getOneMemberInfo(request,response);
				}else if(event.equals("getOneShopInfo")){
					getOneShopInfo(request,response);
					
				}else if(event.equals("submitShopping")){
					submitShopping(request,response);
				}else{
					request.getRequestDispatcher("/CollectStart.jsp").forward(request, response);
				}
			}else{
					request.getRequestDispatcher("/CollectStart.jsp").forward(request, response);
			}
		}else{
			LogPrintFormat.logPrint("Lyn", "IP:"+request.getRemoteAddr()+" 非法登陆库存管理");
			response.sendRedirect(request.getContextPath() + "/sessionInvalid.jsp");
		}
	
	}

	private void submitShopping(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String jsonData=request.getParameter("jsonData");
		JSONObject json=JSONObject.fromObject(jsonData);
		int flag=0;
		Iterator it =((JSONArray)(json.get("item"))).iterator();
		CollectFlowWater flowWater =new CollectFlowWater();
		flowWater.setUserId(String.valueOf(json.get("memId")));
		CollectMemberManageDao cmmDao=new CollectMemberManageDao();
		if(flowWater.getUserId().equals("")){
			flowWater.setUserName("非会员");
		}else{
			flowWater.setUserName(cmmDao.useIdGetOneData(flowWater.getUserId()).getUserName());
		}
		CollectShopDao shopDao=new CollectShopDao();
		CollectStockClassificationDao classDao=new CollectStockClassificationDao();
		
		CollectFlowWaterDao fwDao=new CollectFlowWaterDao();
		int integralTotal=0;
		int integral=0;
		int integralNow=0;
		while(it.hasNext()){
			JSONObject tmpJson=(JSONObject)it.next();
			CollectShop shop=shopDao.useCidGetData(tmpJson.getString("id"));
			flowWater.setShopId(tmpJson.getString("id"));
			flowWater.setShopName(tmpJson.getString("name"));
			flowWater.setSpecifications(shop.getSpecifications());
			flowWater.setBrand(shop.getBrand());
			flowWater.setClassification(classDao.useIdGetName(Integer.valueOf(shop.getClassification())));
			flowWater.setPrice(shop.getMarketingPrice());
			flowWater.setCost(shop.getAverageCost());
			flowWater.setTurnover(tmpJson.getString("turnover"));
			flowWater.setShopNumber(tmpJson.getString("number"));
			flowWater.setShopTransaction(tmpJson.getString("transaction"));
			flowWater.setProfit(String.valueOf(Float.valueOf(tmpJson.getString("transaction"))-Float.valueOf(shop.getAverageCost())*Integer.valueOf(tmpJson.getString("number"))));
			flowWater.setFlowWaterType("0");
			flowWater.setDiscountType(tmpJson.getString("discount"));
			Date today = new Date();
	        Calendar c=Calendar.getInstance();
	        c.setTime(today);
	        int weekday=c.get(Calendar.DAY_OF_WEEK);
	        if(weekday==3){
	        	flowWater.setIntegral(String.valueOf((int) Math.ceil(2*(Float.valueOf(classDao.useIdGetIntegral(Integer.valueOf(shop.getClassification())))*Float.valueOf(tmpJson.getString("transaction"))))));
	        }else{
	        	flowWater.setIntegral(String.valueOf((int) Math.ceil((Float.valueOf(classDao.useIdGetIntegral(Integer.valueOf(shop.getClassification())))*Float.valueOf(tmpJson.getString("transaction"))))));
	        }
			
			if(fwDao.insertIntoOneData(flowWater)==0){
				
				if(!flowWater.getUserName().equals("非会员")){
			       
			        integralNow+=Integer.valueOf(flowWater.getIntegral());
			        integralTotal=cmmDao.useIdGetOneData(flowWater.getUserId()).getIntegralTotal()+Integer.valueOf(flowWater.getIntegral());
					integral=cmmDao.useIdGetOneData(flowWater.getUserId()).getIntegral()+Integer.valueOf(flowWater.getIntegral());
			        
			        cmmDao.updateInegral(flowWater.getUserId(), integral, integralTotal);
					int balance=shop.getStockBalance()-Integer.valueOf(flowWater.getShopNumber());
					int sell=shop.getStockSell()+Integer.valueOf(flowWater.getShopNumber());
					if(shopDao.upDataBalance(shop.getCid(), balance,sell)==0){
						if(cmmDao.updateInegral(flowWater.getUserId(), integral, integralTotal)==0){
							LogPrintFormat.logPrint("Lyn","交易成功,流水单号为:"+flowWater.getFlowWaterId());
							flag=1;
						}
					}
				}else{
					int balance=shop.getStockBalance()-Integer.valueOf(flowWater.getShopNumber());
					int sell=shop.getStockSell()+Integer.valueOf(flowWater.getShopNumber());
					if(shopDao.upDataBalance(shop.getCid(), balance,sell)==0){
						LogPrintFormat.logPrint("Lyn","交易成功,流水单号为:"+flowWater.getFlowWaterId());
						flag=1;
					}
				}
			}
		}
		InterfaceReturn ir=new InterfaceReturn();
		JSONObject item=new JSONObject();
		if(flag==1){
			ir.setErrorCode(0);
			item.put("integral", integralNow);
			item.put("integralTotal", integral);
			item.put("FlowWater", flowWater.getFlowWaterId());
		}else{
			ir.setErrorCode(1);
			ir.setErrorMsg("数据库访问异常");
		}
		
		JSONObject resultJson=JSONObject.fromObject(ir);
		resultJson.put("item", item);
		PrintWriter out=response.getWriter();
		out.println(resultJson);
		out.flush();
		out.close();
	}

	private void getOneShopInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String cid=request.getParameter("shopId");
		CollectShopDao shopDao=new CollectShopDao();
		CollectShop shop =shopDao.useCidGetData(cid);
		
		PrintWriter out=response.getWriter();
		InterfaceReturn ir=new InterfaceReturn();
		JSONObject returnJson=null;
		if(shop.getCid()!=null){
			ir.setErrorCode(0);
			ir.setErrorMsg("");
			returnJson=JSONObject.fromObject(ir);
			JSONObject item=new JSONObject();
			item.put("shopId", shop.getCid());
			item.put("shopName", shop.getName());
			item.put("shopPrice", shop.getMarketingPrice());
			returnJson.put("Item", item);
			LogPrintFormat.logPrint("Lyn", "CollectStartServlet类调用 getOneShopInfo方法成功，获取到"+cid+"信息");
		}else if(shop.getCid()==null){
			ir.setErrorCode(1);
			ir.setErrorMsg("Databases doesn’t the record.");
			returnJson=JSONObject.fromObject(ir);
			LogPrintFormat.logPrint("Lyn", "CollectStartServlet类调用 getOneShopInfo方法成功，获取到"+cid+"信息");
		}else{
			ir.setErrorCode(2);
			ir.setErrorMsg("Unknow sistation.");
			returnJson=JSONObject.fromObject(ir);
			LogPrintFormat.logPrint("Lyn", "CollectStartServlet类调用 getOneShopInfo方法失败，获取到"+cid+"信息异常");
		}
		out.println(returnJson);
		out.flush();
		out.close();
	}

	private void getOneMemberInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String userId=request.getParameter("userId");
		CollectMemberManageDao cmmDao=new CollectMemberManageDao();
		CollectMemberManage cmm=cmmDao.useIdGetOneData(userId);
		JSONObject json=new JSONObject();
		PrintWriter out=response.getWriter();
		if(cmm.getUserName()==null){
			json.put("ErrorCode", "1");
			json.put("ErrorMsg", "The user phone is not exist.");
			LogPrintFormat.logPrint("Lyn", "CollectStartServlet类调用 getOneMemberInfo方法失败，获取到"+userId+"信息无记录");
		}else if(cmm.getUserName()!=null){
			json.put("ErrorCode", "0");
			json.put("ErrorMsg", "");
			json.put("UserPhoneId", cmm.getUserId());
			json.put("UserName", cmm.getUserName());
			json.put("Integral", cmm.getIntegral());
			json.put("Detail", cmm.getDetail());
			LogPrintFormat.logPrint("Lyn", "CollectStartServlet类调用 getOneMemberInfo方法成功，获取到"+userId+"信息成功");
			
		}else{
			json.put("ErrorCode", "2");
			json.put("ErrorMsg", "Unknow sistation.");
			LogPrintFormat.logPrint("Lyn", "CollectStartServlet类调用 getOneMemberInfo方法失败，获取到"+userId+"信息异常");
		}
		out.println(json);
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
