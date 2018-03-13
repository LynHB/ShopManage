package Lyn.ShopManage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import Lyn.ShopManage.dao.CollectMemberChildDao;
import Lyn.ShopManage.dao.CollectMemberManageDao;
import Lyn.ShopManage.entity.CollectMemberChild;
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
					insertOneData(request,response); 
				}else if(event.equals("useUserIdGetInfo")){
					useUserIdGetInfo(request,response);
				}else if(event.equals("upDataOneData")){
					upDataOneData(request,response);
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

	private void upDataOneData(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String primitiveUserId=request.getParameter("primitive-userId");
		String userId=request.getParameter("userId");
		String userName=request.getParameter("userName");
		String userAddress=request.getParameter("userAddress");
		String userIntegral=request.getParameter("userIntegral");
		String childName=request.getParameter("childName");
		String childName2=request.getParameter("childName2");
		String childBirthday=request.getParameter("childBirthday");
		String childBirthday2=request.getParameter("childBirthday2");
		String detail=request.getParameter("detail");
		int childSex=Integer.valueOf(request.getParameter("childSex"));
		int childSex2=Integer.valueOf(request.getParameter("childSex2"));
		
		if(!primitiveUserId.equals("")&&!userId.equals("")&&!userName.equals("")&&!childName.equals("")&&!childBirthday.equals("")&&!userIntegral.equals("")){	
			request.setAttribute("returnSuccessPage", "\"./CMemberManage\"");
			if(!childName2.equals("")&&!childBirthday2.equals("")){
				//用户现在是两个宝宝
				CollectMemberManageDao cmmDao=new CollectMemberManageDao();
				CollectMemberChildDao childDao=new CollectMemberChildDao();
				CollectMemberManage priCmm=cmmDao.useIdGetOneData(primitiveUserId);
				CollectMemberManage nowCmm=new CollectMemberManage();
				CollectMemberChild priChild1=priCmm.getChilden().get(0);
				CollectMemberChild nowChild1=new CollectMemberChild();
				CollectMemberChild nowChild2=new CollectMemberChild();
				String time2=String.valueOf(Calendar.getInstance().getTimeInMillis());
				if(priCmm.getChilden().size()==1){
					//增加一个宝宝、修改宝宝数据、用户自身数据
					String childId2=String.valueOf(Calendar.getInstance().getTimeInMillis())+String.valueOf((int)((Math.random()*9+1)*100000));
					nowChild1.setBirthday(childBirthday);
					nowChild2.setBirthday(childBirthday2);
					nowChild1.setChilderId(priChild1.getChilderId());
					nowChild2.setChilderId(childId2);
					nowChild1.setChildName(childName);
					nowChild2.setChildName(childName2);
					nowChild1.setUpdateTime(time2);
					nowChild2.setUpdateTime(time2);
					nowChild1.setSex(Integer.valueOf(childSex));
					nowChild2.setSex(Integer.valueOf(childSex2));
					nowChild1.setUserId(userId);
					nowChild2.setUserId(userId);
					nowChild2.setCreateTime(time2);
					nowCmm.setUserId(userId);
					nowCmm.setUserName(userName);
					nowCmm.setAddress(userAddress);
					nowCmm.setIntegral(Integer.valueOf(userIntegral));
					nowCmm.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
					nowCmm.setIntegralTotal(priCmm.getIntegralTotal()-priCmm.getIntegral()+Integer.valueOf(userIntegral));
					nowCmm.setDetail(detail);
					nowCmm.setUserChildId(nowChild1.getChilderId()+" "+nowChild2.getChilderId());
					if(cmmDao.upDataOneData(primitiveUserId, nowCmm)==0&&childDao.updateChildenDatat(nowChild1)==0&&childDao.insertChildenData(nowChild2)==0){
						LogPrintFormat.logPrint("Lyn", "收银系统：会员信息修改成功，ID："+userId);
						request.getRequestDispatcher("/submitSuccess.jsp").forward(request, response);
					}else{
						LogPrintFormat.logPrint("Lyn", "收银系统：会员信息修改失败，ID："+userId);
						request.getRequestDispatcher("/submitError.jsp").forward(request, response);
					}
				}else if(priCmm.getChilden().size()==2){
					//修改两个宝宝数据、用户自身数据
					CollectMemberChild priChild2=priCmm.getChilden().get(1);
					nowChild1.setBirthday(childBirthday);
					nowChild2.setBirthday(childBirthday2);
					nowChild1.setChilderId(priChild1.getChilderId());
					nowChild2.setChilderId(priChild2.getChilderId());
					nowChild1.setChildName(childName);
					nowChild2.setChildName(childName2);
					nowChild1.setUpdateTime(time2);
					nowChild2.setUpdateTime(time2);
					nowChild1.setSex(Integer.valueOf(childSex));
					nowChild2.setSex(Integer.valueOf(childSex2));
					nowChild1.setUserId(userId);
					nowChild2.setUserId(userId);
					nowCmm.setUserId(userId);
					nowCmm.setUserName(userName);
					nowCmm.setAddress(userAddress);
					nowCmm.setIntegral(Integer.valueOf(userIntegral));
					nowCmm.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
					nowCmm.setIntegralTotal(priCmm.getIntegralTotal()-priCmm.getIntegral()+Integer.valueOf(userIntegral));
					nowCmm.setDetail(detail);
					nowCmm.setUserChildId(nowChild1.getChilderId()+" "+nowChild2.getChilderId());
					
					if(cmmDao.upDataOneData(primitiveUserId, nowCmm)==0&&childDao.updateChildenDatat(nowChild1)==0&&childDao.updateChildenDatat(nowChild2)==0){
						LogPrintFormat.logPrint("Lyn", "收银系统：会员信息修改成功，ID："+userId);
						request.getRequestDispatcher("/submitSuccess.jsp").forward(request, response);
					}else{
						LogPrintFormat.logPrint("Lyn", "收银系统：会员信息修改失败，ID："+userId);
						request.getRequestDispatcher("/submitError.jsp").forward(request, response);
					}	
				}else{
					LogPrintFormat.logPrint("Lyn", "收银系统：会员信息修改失败，ID："+userId);
					request.getRequestDispatcher("/submitError.jsp").forward(request, response);
				}
			}else if(!childName2.equals("")||!childBirthday2.equals("")){
				request.getRequestDispatcher("/submitError.jsp").forward(request, response);
			}else if(childName2.equals("")&&childBirthday2.equals("")){
				//用户现在只有一个宝宝
				CollectMemberManageDao cmmDao=new CollectMemberManageDao();
				CollectMemberChildDao childDao=new CollectMemberChildDao();
				CollectMemberManage priCmm=cmmDao.useIdGetOneData(primitiveUserId);
				CollectMemberManage nowCmm=new CollectMemberManage();
				CollectMemberChild priChild1=priCmm.getChilden().get(0);
				CollectMemberChild nowChild1=new CollectMemberChild();
				String time2=String.valueOf(Calendar.getInstance().getTimeInMillis());
				if(priCmm.getChilden().size()==2){
					//减少了一个宝宝、修改自身数据
					CollectMemberChild priChild2=priCmm.getChilden().get(1);
					nowChild1.setBirthday(childBirthday);
					nowChild1.setChilderId(priChild1.getChilderId());
					nowChild1.setChildName(childName);
					nowChild1.setUpdateTime(time2);
					nowChild1.setSex(Integer.valueOf(childSex));
					nowChild1.setUserId(userId);
					nowCmm.setUserId(userId);
					nowCmm.setUserName(userName);
					nowCmm.setAddress(userAddress);
					nowCmm.setIntegral(Integer.valueOf(userIntegral));
					nowCmm.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
					nowCmm.setIntegralTotal(priCmm.getIntegralTotal()-priCmm.getIntegral()+Integer.valueOf(userIntegral));
					nowCmm.setDetail(detail);
					nowCmm.setUserChildId(nowChild1.getChilderId());
					if(cmmDao.upDataOneData(primitiveUserId, nowCmm)==0&&childDao.updateChildenDatat(nowChild1)==0&&childDao.useIdDeleteData(priChild2.getChilderId())==0){
						LogPrintFormat.logPrint("Lyn", "收银系统：会员信息修改成功，ID："+userId);
						request.getRequestDispatcher("/submitSuccess.jsp").forward(request, response);
					}else{
						LogPrintFormat.logPrint("Lyn", "收银系统：会员信息修改失败，ID："+userId);
						request.getRequestDispatcher("/submitError.jsp").forward(request, response);
					}
					
					
				}else if(priCmm.getChilden().size()==1){
					//宝宝数量不变，修改自身数据
					nowChild1.setBirthday(childBirthday);
					nowChild1.setChilderId(priChild1.getChilderId());
					nowChild1.setChildName(childName);
					nowChild1.setUpdateTime(time2);
					nowChild1.setSex(Integer.valueOf(childSex));
					nowChild1.setUserId(userId);
					nowCmm.setUserId(userId);
					nowCmm.setUserName(userName);
					nowCmm.setAddress(userAddress);
					nowCmm.setIntegral(Integer.valueOf(userIntegral));
					nowCmm.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
					nowCmm.setIntegralTotal(priCmm.getIntegralTotal()-priCmm.getIntegral()+Integer.valueOf(userIntegral));
					nowCmm.setDetail(detail);
					nowCmm.setUserChildId(nowChild1.getChilderId());
					if(cmmDao.upDataOneData(primitiveUserId, nowCmm)==0&&childDao.updateChildenDatat(nowChild1)==0){
						LogPrintFormat.logPrint("Lyn", "收银系统：会员信息修改成功，ID："+userId);
						request.getRequestDispatcher("/submitSuccess.jsp").forward(request, response);
					}else{
						LogPrintFormat.logPrint("Lyn", "收银系统：会员信息修改失败，ID："+userId);
						request.getRequestDispatcher("/submitError.jsp").forward(request, response);
					}
					
					
				}else{
					LogPrintFormat.logPrint("Lyn", "收银系统：会员信息修改失败，ID："+userId);
					request.getRequestDispatcher("/submitError.jsp").forward(request, response);
				}
				
				
			}else{
				request.getRequestDispatcher("/submitError.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("/submitError.jsp").forward(request, response);
		}
		
		
	}

	private void useUserIdGetInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String userId= request.getParameter("userId");
		CollectMemberManageDao cmmDao=new CollectMemberManageDao();
		CollectMemberManage cmm=cmmDao.useIdGetOneData(userId);
		JSONObject json = JSONObject.fromObject(cmm);
		PrintWriter out=response.getWriter();
		out.println(json);
		out.flush();
		out.close();
		
	}

	private void insertOneData(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String userName=request.getParameter("userName");
		String userAddress=request.getParameter("userAddress");
		String userIntegral=request.getParameter("userIntegral");
		String childName=request.getParameter("childName");
		String childName2=request.getParameter("childName2");
		String childBirthday=request.getParameter("childBirthday");
		String childBirthday2=request.getParameter("childBirthday2");
		String detail=request.getParameter("detail");
		int childSex=Integer.valueOf(request.getParameter("childSex"));
		int childSex2=Integer.valueOf(request.getParameter("childSex2"));
		if(!userId.equals("")&&!userName.equals("")&&!childName.equals("")&&!childBirthday.equals("")){
			request.setAttribute("returnSuccessPage", "\"./CMemberManage\"");
			if(!childName2.equals("")&&!childBirthday2.equals("")){
				//用户有两个宝宝
				CollectMemberManage cmm=new CollectMemberManage();
				CollectMemberChild child=new CollectMemberChild();
				CollectMemberChild child2=new CollectMemberChild();
				ArrayList<CollectMemberChild> childList=new ArrayList<CollectMemberChild>();
				CollectMemberManageDao cmmDao=new CollectMemberManageDao();
				CollectMemberChildDao childDao=new CollectMemberChildDao();
				
				cmm.setUserId(userId);
				cmm.setUserName(userName);
				cmm.setAddress(userAddress);
				cmm.setIntegral(Integer.valueOf(userIntegral.equals("")?"0":userIntegral));
				cmm.setIntegralTotal(cmm.getIntegral());
				cmm.setDetail(detail);
				cmm.setCreateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				cmm.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				cmm.setUserChildName(childName+" "+childName2);
				String childId=String.valueOf(Calendar.getInstance().getTimeInMillis())+String.valueOf((int)((Math.random()*9+1)*100000));
				String childId2=String.valueOf(Calendar.getInstance().getTimeInMillis())+String.valueOf((int)((Math.random()*9+1)*100000));
				cmm.setUserChildId(childId+" "+childId2);
				
				//第一个孩子
				child.setChilderId(childId);
				child.setChildName(childName);
				child.setCreateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				child.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				child.setBirthday(childBirthday);
				child.setSex(childSex);
				child.setUserId(cmm.getUserId());
				
				//第二个孩子
				child2.setChilderId(childId2);
				child2.setChildName(childName2);
				child2.setCreateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				child2.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				child2.setBirthday(childBirthday2);
				child2.setSex(childSex2);
				child2.setUserId(cmm.getUserId());
				childList.add(child2);
				
				cmm.setChilden(childList);
				if(cmmDao.insertOneData(cmm)==0&&childDao.insertChildenData(child)==0&&childDao.insertChildenData(child2)==0){
					LogPrintFormat.logPrint("Lyn", "收银系统：会员信息增加成功，ID："+cmm.getUserId());
					request.getRequestDispatcher("/submitSuccess.jsp").forward(request, response);
				}else{
					LogPrintFormat.logPrint("Lyn", "收银系统：会员信息增加失败，ID："+cmm.getUserId());
					request.getRequestDispatcher("/submitError.jsp").forward(request, response);
				}
			}else if(!childName2.equals("")||!childBirthday2.equals("")){
				LogPrintFormat.logPrint("Lyn", "收银系统：会员信息增加失败，ID："+userId);
				request.getRequestDispatcher("/submitError.jsp").forward(request, response);
			}else if(childName2.equals("")&&childBirthday2.equals("")){
				//用户只有一个宝宝
				CollectMemberManage cmm=new CollectMemberManage();
				CollectMemberChild child=new CollectMemberChild();
				ArrayList<CollectMemberChild> childList=new ArrayList<CollectMemberChild>();
				CollectMemberManageDao cmmDao=new CollectMemberManageDao();
				CollectMemberChildDao childDao=new CollectMemberChildDao();
				
				cmm.setUserId(userId);
				cmm.setUserName(userName);
				cmm.setAddress(userAddress);
				cmm.setIntegral(Integer.valueOf(userIntegral.equals("")?"0":userIntegral));
				cmm.setIntegralTotal(cmm.getIntegral());
				cmm.setDetail(detail);
				cmm.setCreateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				cmm.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				cmm.setUserChildName(childName);
				String childId=String.valueOf(Calendar.getInstance().getTimeInMillis())+String.valueOf((int)((Math.random()*9+1)*100000));
				cmm.setUserChildId(childId);
				
				child.setChilderId(childId);
				child.setChildName(childName);
				child.setCreateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				child.setUpdateTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				child.setBirthday(childBirthday);
				child.setSex(childSex);
				child.setUserId(cmm.getUserId());
				childList.add(child);
				cmm.setChilden(childList);
				if(cmmDao.insertOneData(cmm)==0&&childDao.insertChildenData(child)==0){
					LogPrintFormat.logPrint("Lyn", "收银系统：会员信息增加成功，ID："+userId);
					request.getRequestDispatcher("/submitSuccess.jsp").forward(request, response);
				}else{
					LogPrintFormat.logPrint("Lyn", "收银系统：会员信息增加失败，ID："+userId);
					request.getRequestDispatcher("/submitError.jsp").forward(request, response);
				}
			}else{
				LogPrintFormat.logPrint("Lyn", "收银系统：会员信息增加失败，ID："+userId);
				request.getRequestDispatcher("/submitError.jsp").forward(request, response);
			}
		}else{
			LogPrintFormat.logPrint("Lyn", "收银系统：会员信息增加失败，ID："+userId);
			request.getRequestDispatcher("/submitError.jsp").forward(request, response);
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
