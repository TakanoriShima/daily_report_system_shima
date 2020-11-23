<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<c:if test="${flush != null}">
			<div id="flush_success">
				<c:out value="${flush}"></c:out>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${negotiationsOfAttension != null}">
				<h2>顧客 詳細ページ</h2>

				<table>
					<tbody>
						<tr>
							<th>顧客番号</th>
							<td><c:out value="${c.id}" /></td>
						</tr>
						<tr>
							<th>会社名</th>
							<td><c:out value="${c.company}" /></td>
						</tr>
						<tr>
							<th>役職</th>
							<td><c:out value="${c.position}" /></td>
						</tr>
						<tr>
							<th>名前</th>
							<td><c:out value="${c.name}" /></td>
						</tr>
						<tr>
							<th>メールアドレス</th>
							<td><c:out value="${c.email}" /></td>
						</tr>
						<tr>
							<th>電話番号</th>
							<td><c:out value="${c.tel}" /></td>
						</tr>
						<tr>
							<th>メモ</th>
							<td><pre>
									<c:out value="${c.memo}" />
								</pre></td>
						</tr>
						<tr>
							<th>顧客登録日</th>
							<td><fmt:formatDate value="${c.register_date}"
									pattern="yyyy-MM-dd" /></td>
						</tr>

					</tbody>
				</table>
				<br />
				<br />

				<c:forEach var="negotiation" items="${negotiationsOfAttension}">
					<table>
						<tbody>
							<tr>
								<th>商談担当</th>
								<th>商談内容</th>
								<th>商談日時</th>
							</tr>
							<tr>

								<td><c:out value="${negotiation.employee.name}" /></td>
								<td><c:out value="${negotiation.content}" /></td>
								<td><fmt:formatDate value="${negotiation.report_date}"
										pattern="yyyy-MM-dd" /></td>
							</tr>
						</tbody>
					</table>

				</c:forEach>
			</c:when>
			<c:otherwise>
				<h2>お探しのデータは見つかりませんでした。</h2>
			</c:otherwise>
		</c:choose>

		<p>
			<a href="<c:url value="/customers/index" />">顧客一覧に戻る</a>
		</p>
	</c:param>
</c:import>