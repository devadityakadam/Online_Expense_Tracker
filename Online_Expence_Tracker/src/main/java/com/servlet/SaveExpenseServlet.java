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
import com.entity.Expense;
import com.entity.User;

@WebServlet("/saveExpense")
public class SaveExpenseServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String date = req.getParameter("date");
		String time = req.getParameter("time");
		String description = req.getParameter("description");
		String price = req.getParameter("price");

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("loginuser");

		Expense ex = new Expense(title, date, time, description, price, user);
		ExpanseDAO dao = new ExpanseDAO(Hibernateutil.getSessionFactory());
		boolean f = dao.saveExpense(ex);
		if (f) {
			session.setAttribute("msg", "Expense added sussesfully");

			resp.sendRedirect("user/add_expense.jsp");
		} else {
			session.setAttribute("msg", "something wrong");
			resp.sendRedirect("user/add_expense.jsp");
		}
	}

}
