<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<h1>${owner}'s Book Store</h1>
	<ol class="order-list">
		<table>
		<c:forEach var="order" items="${all_orders}">
			<s:url value="/bookstore/{customerName}" var="order_url">
				<s:param name="customerName" value="${order.customer.name}" />
			</s:url>
			<tr><td>
			<a href="${order_url}">
				<c:out value="${order.customer.name}" />
			</a>
			</td>
			<td>
			<c:forEach var="book" items="${order.books}">
				<c:out value="${book.name}" />&nbsp;
			</c:forEach>
			</td>
			<td>
			<small><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd hh:mm:ss" /></small>
			</td></tr>
		</c:forEach>
		</table>
	</ol>
</div>