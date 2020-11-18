package controllers.approvals;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Approval;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ApprovalsCreateServlet
 */
@WebServlet("/approvals/create")
public class ApprovalsCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String _token = (String) request.getParameter("_token");
//		if (_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();

			Approval a = new Approval();

			Report r = em.find(Report.class, Integer.parseInt(request.getParameter("report_id")));
			a.setReport(r);
			a.setComment(request.getParameter("comment"));
			a.setResult(request.getParameter("approval_result"));
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			a.setCreated_at(currentTime);
			a.setUpdated_at(currentTime);

			em.getTransaction().begin();

			em.persist(a);
			r.setResubmit_flag(1);

			em.getTransaction().commit();

			em.close();

			em = DBUtil.createEntityManager();

			request.getSession().setAttribute("flush", "承認可否を決定しました。");

			response.sendRedirect(request.getContextPath() + "/approvals/index");

//		}

	}

}
