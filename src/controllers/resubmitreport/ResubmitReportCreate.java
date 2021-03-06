package controllers.resubmitreport;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import models.validators.ReportValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ResubmitReportCreate
 */
@WebServlet("/resubmitreport/create")
public class ResubmitReportCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResubmitReportCreate() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String _token = (String) request.getParameter("_token");
		if (_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();

			Report r = new Report();

			r.setEmployee((Employee) request.getSession().getAttribute("login_employee"));

			Date report_date = new Date(System.currentTimeMillis());
			String rd_str = request.getParameter("report_date");
			if (rd_str != null && !rd_str.equals("")) {
				report_date = Date.valueOf(request.getParameter("report_date"));
			}
			r.setReport_date(report_date);

			r.setTitle(request.getParameter("title"));
			r.setContent(request.getParameter("content"));
			System.out.println("start: " + request.getParameter("start_at"));
			System.out.println("end: " + request.getParameter("end_at"));
			r.setStart_at(request.getParameter("start_at"));
			r.setEnd_at(request.getParameter("end_at"));

			Employee approval_employee = em.find(Employee.class, Integer.parseInt(request.getParameter("approval_admin_id")));

			r.setApproval_employee(approval_employee);

			Integer parent_report_id = Integer.parseInt(request.getParameter("report_id"));
			r.setParent_report_id(parent_report_id);

			r.setResubmit_flag(0);

			Report r_parrent = em.find(Report.class, parent_report_id);
			r_parrent.setResubmit_flag(0);

			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			r.setCreated_at(currentTime);
			r.setUpdated_at(currentTime);

			List<String> errors = ReportValidator.validate(r);
			if (errors.size() > 0) {
				em.close();

				request.setAttribute("_token", request.getSession().getId());
				request.setAttribute("report", r);
				request.setAttribute("errors", errors);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
				rd.forward(request, response);
			} else {
				em.getTransaction().begin();
				em.persist(r);
				em.getTransaction().commit();
				em.close();
				request.getSession().setAttribute("flush", "再提出が完了しました。");

				response.sendRedirect(request.getContextPath() + "/reports/index");
			}
		}
	}

}
