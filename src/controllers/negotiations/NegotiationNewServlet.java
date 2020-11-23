package controllers.negotiations;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Customer;
import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class NegotiationNewSerlet
 */
@WebServlet("/negotiations/new")
public class NegotiationNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NegotiationNewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("_token", request.getSession().getId());

		EntityManager em = DBUtil.createEntityManager();
		Employee login_employee = (Employee) request.getSession().getAttribute("login_employee");

		List<Customer> myCustomers = em.createNamedQuery("getMyCustomers", Customer.class)
				.setParameter("employee", login_employee).getResultList();

		em.close();

		request.setAttribute("myCustomers", myCustomers);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/negotiations/new.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
