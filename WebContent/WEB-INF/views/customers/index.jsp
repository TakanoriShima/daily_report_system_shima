<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>顧客　一覧</h2>
        <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_name">会社名</th>
                    <th class="report_date">役職</th>
                    <th class="report_title">お名前</th>
                    <th class="report_date">メール</th>
                    <th class="report_title">電話番号</th>
                    <th class="report_action">メモ</th>
                    <th class="report_action">登録日時</th>
                    <th class="report_action">詳細</th>

                </tr>
                <c:forEach var="customer" items="${my_customers}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${customer.company }" /></td>
                        <td class="report_name"><c:out value="${customer.position }" /></td>
                        <td class="report_name"><c:out value="${customer.name }" /></td>
                        <td class="report_name"><c:out value="${customer.email }" /></td>
                        <td class="report_name"><c:out value="${customer.tel }" /></td>
                        <td class="report_name"><c:out value="${customer.memo}" /></td>
                        <td class="report_name"><c:out value="${customer.register_date}" /></td>
                        <td class="report_action"><a href="<c:url value='/customers/show?id=${cunstomer.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <p><a href="<c:url value='/customers/new' />">新規顧客の登録</a></p>

    </c:param>
</c:import>