package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.db.Hibernateutil;
import com.entity.User;

@WebServlet("/userRegister")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String about = req.getParameter("about");

		User u = new User(fullname, email, password, about);

		// System.out.print(u);
		UserDao dao = new UserDao(Hibernateutil.getSessionFactory());
		boolean f = dao.saveuser(u);

		HttpSession session = req.getSession();

		if (f) {
			session.setAttribute("msg", "register sussesfully");
			// System.out.println("register sussesfully");
			resp.sendRedirect("register.jsp");
		} else {
			session.setAttribute("msg", "something wrong");
			resp.sendRedirect("register.jsp");
			// System.out.println("something wrong");
		}
	}

}
