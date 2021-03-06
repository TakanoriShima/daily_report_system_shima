package models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "reports")
@NamedQueries({ @NamedQuery(name = "getAllReports", query = "SELECT r FROM Report AS r ORDER BY r.id DESC"),
		@NamedQuery(name = "getReportsCount", query = "SELECT COUNT(r) FROM Report AS r"),
		@NamedQuery(name = "getApprovalReports", query = "SELECT r FROM Report AS r WHERE r.approval_employee=:approval_employee"),
		@NamedQuery(name = "getMyAllReports", query = "SELECT r FROM Report AS r WHERE r.employee = :employee ORDER BY r.id DESC"),
		@NamedQuery(name = "getMyReportsCount", query = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :employee"),

})
@Entity
public class Report /* implements Serializable */ {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 多対1
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	@Column(name = "report_date", nullable = false)
	private Date report_date;

	@Column(name = "title", length = 255, nullable = false)
	private String title;

	@Lob
	@Column(name = "content", nullable = false)
	private String content;

	@OneToOne
	@JoinColumn(name = "appraval_employee_id", nullable = false)
	private Employee approval_employee;

	// 多対多
	@ManyToMany(mappedBy = "favotiteReports", fetch = FetchType.EAGER)
	List<Employee> favoritedEmployees;

	// 双方向1対1
	@OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
	private Approval approval;

	// 再提出の際、再提出もとになったレポート番号
	@Column(name = "parent_report_id", length = 255)
	private Integer parent_report_id;

	// 再提出可能か
	@Column(name = "resubmit_flag", length = 255)
	private Integer resubmit_flag;

	public Integer getResubmit_flag() {
		return resubmit_flag;
	}

	public void setResubmit_flag(Integer resubmit_flag) {
		this.resubmit_flag = resubmit_flag;
	}

	public Integer getParent_report_id() {
		return parent_report_id;
	}

	public void setParent_report_id(Integer parent_report_id) {
		this.parent_report_id = parent_report_id;
	}

	public Approval getApproval() {
		return approval;
	}

	public void setApproval(Approval approval) {
		this.approval = approval;
	}

	public String getStart_at() {
		return start_at;
	}

	public void setStart_at(String start_at) {
		this.start_at = start_at;
	}

	public String getEnd_at() {
		return end_at;
	}

	public void setEnd_at(String end_at) {
		this.end_at = end_at;
	}

	// 出勤時間
	@Column(name = "start_at")
	private String start_at;

	// 退勤時間
	@Column(name = "end_at")
	private String end_at;

	@Column(name = "created_at", nullable = false)
	private Timestamp created_at;

	@Column(name = "updated_at", nullable = false)
	private Timestamp updated_at;

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

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public Employee getApproval_employee() {
		return approval_employee;
	}

	public void setApproval_employee(Employee approval_employee) {
		this.approval_employee = approval_employee;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public List<Employee> getFavoritedEmployees() {
		return favoritedEmployees;
	}

	public void setFavoritedEmployees(List<Employee> favoritedEmployees) {
		this.favoritedEmployees = favoritedEmployees;
	}

}