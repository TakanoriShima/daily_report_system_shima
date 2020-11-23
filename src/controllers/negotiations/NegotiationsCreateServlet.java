package controllers.negotiations;

import java.io.IOException;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Customer;
import models.Employee;
import models.Negotiation;
import utils.DBUtil;

/**
 * Servlet implementation class NegotiationsCreateServlet
 */
@WebServlet("/negotiations/create")
public class NegotiationsCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NegotiationsCreateServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String _token = (String) request.getParameter("_token");
		if (_token != null && _token.equals(request.getSession().getId())) {

			EntityManager em = DBUtil.createEntityManager();

			// Report r = new Report();
			Negotiation n = new Negotiation();

			Date report_date = new Date(System.currentTimeMillis());
			String rd_str = request.getParameter("report_date");
			if (rd_str != null && !rd_str.equals("")) {
				report_date = Date.valueOf(request.getParameter("report_date"));
			}

			n.setReport_date(report_date);

			n.setEmployee((Employee) request.getSession().getAttribute("login_employee"));
			Customer c = em.find(Customer.class, Integer.parseInt(request.getParameter("customer_id")));
			n.setCustomer(c);
			n.setContent(request.getParameter("content"));

			em.getTransaction().begin();

			em.persist(n);
			em.getTransaction().commit();
			em.close();
			request.getSession().setAttribute("flush", "商談の登録が完了しました。");

			response.sendRedirect(request.getContextPath() + "/reports/new");

		}

	}
}
