package controllers.favorites;

import java.io.IOException;

import javax.persistence.EntityManager;
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
 * Servlet implementation class FavoritesDestroy
 */
@WebServlet("/favorites/destroy")
public class FavoritesDestroy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FavoritesDestroy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String _token = (String) request.getParameter("_token");
		if (_token != null && _token.equals(request.getSession().getId())) {

			EntityManager em = DBUtil.createEntityManager();

			Employee e = (Employee) request.getSession().getAttribute("login_employee");

			Integer report_id = Integer.parseInt(request.getParameter("report_id"));
			Report r = em.find(Report.class, report_id);

			Favorite f = em.createNamedQuery("getFavoritesOfAttension", Favorite.class).setParameter("employee", e)
					.setParameter("report", r).getSingleResult();

			em.getTransaction().begin();
			em.remove(f); // データ削除
			em.getTransaction().commit();
			em.close();

			request.getSession().setAttribute("flush", "いいねを削除しました。");

			response.sendRedirect(request.getContextPath() + "/reports/show?id=" + report_id);
		}
	}

}
