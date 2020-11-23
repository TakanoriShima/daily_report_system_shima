<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="cunstomer_id">商談顧客選択</label><br />
<select name="customer_id">
<option value="0">顧客を選択してください</option>
<c:forEach var="c" items="${myCustomers}">
	<option value="<c:out value="${c.id}"/>"><c:out value="${c.name}"/></option>
</c:forEach>
</select>
<br /><br />

<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50"></textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<input type="hidden" name="report_id" value="${report.id}" />
<button type="submit">投稿</button>
