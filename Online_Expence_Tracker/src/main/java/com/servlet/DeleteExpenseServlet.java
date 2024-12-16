package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ExpanseDAO;
import com.db.Hibernateutil;

@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		ExpanseDAO dao = new ExpanseDAO(Hibernateutil.getSessionFactory());
		boolean f = dao.deleteExpense(id);

		HttpSession session = req.getSession();

		if (f) {
			session.setAttribute("msg", "Delete Successfully");
			resp.sendRedirect("user/view_expense.jsp");
		} else {
			session.setAttribute("msg", "Something Wrong on Server");
			resp.sendRedirect("user/view_expense.jsp");
		}
	}

}
