package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@Table(name = "negotiations")
@NamedQueries({
//		@NamedQuery(name = "getFavoritesOfAttension", query = "SELECT f FROM Favorite AS f WHERE f.employee=:employee AND f.report=:report")

})
@Entity
public class Negotiation /* implements Serializable */ {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// Employeeモデルと多対1で結びつく
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	// Customerモデルと多対１で結びつく
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Lob
	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "report_date", nullable = false)
	private Date negotiation_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNegotiation_date() {
		return negotiation_date;
	}

	public void setNegotiation_date(Date negotiation_date) {
		this.negotiation_date = negotiation_date;
	}


}

