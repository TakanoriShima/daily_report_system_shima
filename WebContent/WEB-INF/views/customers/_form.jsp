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
<label for="registert_date">日付</label><br />
<input type="date" name="register_date" value="<fmt:formatDate value='${c.register_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="company">会社名</label><br />
<input type="text" name="company" value="${c.company}" />
<br /><br />

<label for="position">役職</label><br />
<input type="text" name="position" value="${c.position}" />
<br /><br />

<label for="name">氏名</label><br />
<input type="text" name="name" value="${c.name}" />
<br /><br />

<label for="email">メールアドレス</label><br />
<input type="email" name="email" value="${c.email}" />
<br /><br />

<label for="tel">電話番号</label><br />
<input type="tel" name="tel" value="${c.tel}" />
<br /><br />

<label for="memo">メモ</label><br />
<input type="text" name="memo" value="${c.memo}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<input type="hidden" name="customer_id" value="${c.id}" />
<button type="submit">登録</button>