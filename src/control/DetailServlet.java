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
import entity.*;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prdID = request.getParameter("id");
		Dao dao = new Dao();
		Product prdByID = dao.getProductByID(prdID);
		List<Category> listC = dao.getAllCategory();
		Product pLast = dao.getLastest();
		
		request.setAttribute("ListC", listC);
		request.setAttribute("pLast", pLast);
		request.setAttribute("prdByID", prdByID);
		
		RequestDispatcher rd = request.getRequestDispatcher("Detail.jsp");

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
