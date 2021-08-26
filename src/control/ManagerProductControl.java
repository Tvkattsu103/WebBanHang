package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.Dao;
import entity.Account;
import entity.Category;
import entity.Product;

@WebServlet("/managerProduct")
public class ManagerProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ManagerProductControl() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) {
			doGet_Index(request, response);
		} else {
			if (action.equalsIgnoreCase("find")) {
				doGet_Find(request, response);
			}
		}
	}
	
	protected void doGet_Find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String id = request.getParameter("id");
		Dao dao = new Dao();
		Product prd = dao.getProductByID(id);
		response.setContentType("text/html; charset=UTF-8");
		Gson gson = new Gson();
		PrintWriter writer = response.getWriter();
//		PrintWriter writer = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
		writer.print(gson.toJson(prd));
		writer.flush();
		writer.close();
	}
	
	protected void doGet_Index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao = new Dao();
		HttpSession session = request.getSession();
		Account a = (Account) session.getAttribute("acc");
		int id = a.getId();
		
		List<Product> listprd = dao.listProductBySellID(id);
		List<Category> listC = dao.getAllCategory();
		
		request.setAttribute("ListC", listC);
		request.setAttribute("listprd", listprd);
		RequestDispatcher rd = request.getRequestDispatcher("ManagerProduct.jsp");

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("create")) {
			doPost_Create(request, response);
		} else if (action.equalsIgnoreCase("delete")) {
			doPost_Delete(request, response);
		} else if (action.equalsIgnoreCase("update")) {
			doPost_Update(request, response);
		}
	}
	
	protected void doPost_Create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pname = request.getParameter("name").trim();
		String pimage = request.getParameter("image").trim();
		Double pprice = Double.parseDouble(request.getParameter("price").trim());
		String ptitle = request.getParameter("title").trim();
		String pdescription = request.getParameter("description").trim();
		String pcategory = request.getParameter("category").trim();
		HttpSession session = request.getSession();
		Account a = (Account) session.getAttribute("acc");
		int sid = a.getId();
		Dao dao = new Dao();
		dao.createProduct(pname, pimage, pprice, ptitle, pdescription, pcategory, sid);
		response.sendRedirect("managerProduct");
	}
	
	protected void doPost_Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Dao dao = new Dao();
		Product prd = dao.getProductByID(id);
		
		dao.deleteProduct(prd.getId());
		response.sendRedirect("managerProduct");
	}
	
	protected void doPost_Update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pname = request.getParameter("name").trim();
		String pimage = request.getParameter("image").trim();
		Double pprice = Double.parseDouble(request.getParameter("price").trim());
		String ptitle = request.getParameter("title").trim();
		String pdescription = request.getParameter("description").trim();
		String pcategory = request.getParameter("category").trim();
		HttpSession session = request.getSession();
		Account a = (Account) session.getAttribute("acc");
		int pid = a.getId();
		Dao dao = new Dao();
		dao.createProduct(pname, pimage, pprice, ptitle, pdescription, pcategory, pid);
		response.sendRedirect("managerProduct");
	}

}
