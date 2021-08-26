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

@WebServlet({"/home","/index"})
public class HomeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomeControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao = new Dao();
		List<Product> list = dao.getTop3();
		List<Category> listC = dao.getAllCategory();
		Product pLast = dao.getLastest();
		
		request.setAttribute("ListP", list);
		request.setAttribute("ListC", listC);
		request.setAttribute("pLast", pLast);
		
		RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
		rd.forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
