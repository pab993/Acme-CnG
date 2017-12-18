<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Display lessor -->





<h3>
	<spring:message code="actor.name" />
</h3>
<jstl:out value="${actor.getName()}" />

<h3>
	<spring:message code="actor.surname" />
</h3>
<jstl:out value="${actor.getSurname()}" />

<h3>
	<spring:message code="actor.phoneNumber" />
</h3>
<jstl:out value="${actor.getPhoneNumber()}" />

<h3>
	<spring:message code="actor.email" />
</h3>
<jstl:out value="${actor.getEmail()}" />




<security:authorize access="isAuthenticated()">
	<h3>
		<a href="comment/postComment.do?comentableId=${actor.id }"> <spring:message
				code="postComment" />
		</a>
	</h3>
</security:authorize>

<display:table name="comments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="comment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />

	<spring:message code="comment.stars" var="starsHeader" />
	<display:column property="stars" title="${starsHeader}" />

	<spring:message code="comment.userName" var="userNameHeader" />
	<display:column>
		<a href="actor/display.do?actorId=${row.actor.id}"> <spring:message
				code="display.actor" />
		</a>
	</display:column>
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column>
			<a href="comment/ban.do?commentId=${row.id}"><jstl:choose>
			<jstl:when test="${row.ban}">

				<spring:message code="unBan" />
			</jstl:when>
			<jstl:otherwise>
			<spring:message code="ban" />
			</jstl:otherwise>
		</jstl:choose>
			</a>
		</display:column>
	</security:authorize>

</display:table>




