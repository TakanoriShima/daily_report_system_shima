package controllers.reports;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Favorite;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportsShowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("OKOK");
		EntityManager em = DBUtil.createEntityManager();

		Employee e = (Employee) request.getSession().getAttribute("login_employee");
		Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
		List<Favorite> favoritesOfAttension = em.createNamedQuery("getFavoritesOfAttension", Favorite.class)
				.setParameter("employee", e).setParameter("report", r).getResultList();

		em.close();

		List<Employee> favoritedEmployeeList = r.getFavoritedEmployees();

		// System.out.println("いいねしてくれている従業員リスト");
		// for(Employee emp : list){
		// System.out.println("従業員名" + emp.getName());
		// }

		request.setAttribute("report", r);
		request.setAttribute("favoritesOfAttensionCount", favoritesOfAttension.size());
		request.setAttribute("_token", request.getSession().getId());
		request.setAttribute("favoritedEmployeeList", favoritedEmployeeList);


		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
		rd.forward(request, response);
	}

}