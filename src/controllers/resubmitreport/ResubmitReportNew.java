package controllers.resubmitreport;

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
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ResubmitReportCreate
 */
@WebServlet("/resubmitreport/new")
public class ResubmitReportNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResubmitReportNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManager em = DBUtil.createEntityManager();
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        List<Employee> admins_except_me = em.createNamedQuery("getAllAdminsExceptMe", Employee.class).setParameter("id", login_employee.getId()).getResultList();

        em.close();

        if(r != null && login_employee.getId() == r.getEmployee().getId()) {
            request.setAttribute("report", r);
            request.setAttribute("admins", admins_except_me);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("report_id", r.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/resubmit.jsp");
        rd.forward(request, response);
	}


}
