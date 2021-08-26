package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import entity.Account;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Signup.jsp");

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String repass = request.getParameter("repass");
		Dao dao = new Dao();
		if(!pass.equals(repass)) {
			request.setAttribute("errorMessage", "Mật khẩu nhập vào không khớp!");
			RequestDispatcher rd = request.getRequestDispatcher("Signup.jsp");

			rd.forward(request, response);
		} else {
			Account a = dao.checkIfExistUser(user);
			if(a != null) {
				request.setAttribute("errorMessage", "Tài khoản đã được sử dụng!");
				RequestDispatcher rd = request.getRequestDispatcher("Signup.jsp");

				rd.forward(request, response);
			} else {
				dao.signup(user, pass);
				HttpSession session = request.getSession();
				session.setAttribute("acc", a);
				session.setMaxInactiveInterval(300);
				response.sendRedirect("home");						
			}
		}
	}
}
