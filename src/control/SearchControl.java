package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import entity.Category;
import entity.Product;

@WebServlet("/search")
public class SearchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String txtSearch = request.getParameter("txt");
		
		Dao dao = new Dao();
		List<Product> list = dao.SearchProduct(txtSearch);
		List<Category> listC = dao.getAllCategory();
		Product pLast = dao.getLastest();
		
		request.setAttribute("ListP", list);
		request.setAttribute("ListC", listC);
		request.setAttribute("pLast", pLast);
		request.setAttribute("txtS", txtSearch);
		
		RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");

		rd.forward(request, response);
	}

}
