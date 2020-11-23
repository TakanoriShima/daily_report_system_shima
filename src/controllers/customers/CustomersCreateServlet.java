package controllers.customers;

import java.io.IOException;
import java.sql.Date;
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
import models.validators.CustomerValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CustomersCreateServlet
 */
@WebServlet("/customers/create")
public class CustomersCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomersCreateServlet() {
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
		String _token = (String) request.getParameter("_token");
		if (_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();

			Customer c = new Customer();

			c.setEmployee((Employee) request.getSession().getAttribute("login_employee"));

			Date register_date = new Date(System.currentTimeMillis());
			String rd_str = request.getParameter("register_date");
			if (rd_str != null && !rd_str.equals("")) {
				register_date = Date.valueOf(request.getParameter("register_date"));
			}

			c.setRegister_date(register_date);

			c.setCompany(request.getParameter("company"));
			c.setPosition(request.getParameter("position"));
			c.setName(request.getParameter("name"));
			c.setEmail(request.getParameter("email"));
			c.setTel(request.getParameter("tel"));
			c.setMemo(request.getParameter("memo"));

			List<String> errors = CustomerValidator.validate(c);

			if (errors.size() > 0) {
				em.close();

				request.setAttribute("_token", request.getSession().getId());
				request.setAttribute("c", c);
				request.setAttribute("errors", errors);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/customers/new.jsp");
				rd.forward(request, response);

			} else {
				em.getTransaction().begin();
				em.persist(c);
				em.getTransaction().commit();
				em.close();
				request.getSession().setAttribute("flush", "顧客登録が完了しました。");

				response.sendRedirect(request.getContextPath() + "/customers/index");
			}
		}
	}
}
