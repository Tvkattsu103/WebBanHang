package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import entity.Product;

@WebServlet("/load")
public class LoadMoreControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoadMoreControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int amount = Integer.parseInt(request.getParameter("exist"));
		Dao dao = new Dao();
		List<Product> list = dao.getNext3Product(amount);
		PrintWriter out = response.getWriter();
		
		for(Product product : list) {
			out.println("<div class=\"product col-12 col-md-6 col-lg-4 mb-3\">\r\n"
					+ "                                <div class=\"card\">\r\n"
					+ "                                    <img class=\"card-img-top\" src=\""+product.getImage()+"\" alt=\"Card image cap\">\r\n"
					+ "                                    <div class=\"card-body\">\r\n"
					+ "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?id="+product.getId()+"\" title=\"View Product\">"+product.getName()+"</a></h4>\r\n"
					+ "                                        <p class=\"card-text show_txt\">"+product.getTitle()+"\r\n"
					+ "                                        </p>\r\n"
					+ "                                        <div class=\"row\">\r\n"
					+ "                                            <div class=\"col\">\r\n"
					+ "                                                <p class=\"btn btn-danger btn-block\">"+product.getPrice()+" $</p>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"col\">\r\n"
					+ "                                                <a href=\"#\" class=\"btn btn-success btn-block\">Add to cart</a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
