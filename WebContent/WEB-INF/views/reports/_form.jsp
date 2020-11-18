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
<label for="report_date">日付</label><br />
<input type="date" name="report_date" value="<fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${report.title}" />
<br /><br />

<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50">${report.content}</textarea>
<br /><br />

<!-- 出勤時間  -->
<label for="start_at">出勤時間</label><br />
<input type="time" name="start_at" value="${report.start_at}" />
<br /><br />

<!-- 退勤時間 -->
<label for="end_at">退勤時間</label><br />
<input type="time" name="end_at" value="${report.end_at}" />
<br /><br />

<label for="approval_admin_id">承認者選択</label><br />
<select name="approval_admin_id">
<option value="none">承認者を選択してください</option>
<c:forEach var="admin" items="${admins}">
	<option value="${admin.id}" <c:if test="${report.approval_employee.id == admin.id}">selected</c:if>><c:out value="${admin.name}" /></option>
</c:forEach>

</select>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<input type="hidden" name="report_id" value="${report.id}" />
<button type="submit">投稿</button>