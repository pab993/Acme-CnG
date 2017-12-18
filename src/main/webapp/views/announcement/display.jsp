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
	<spring:message code="announcement.title" />
</h3>
<jstl:out value="${announcement.getTitle()}" />

<h3>
	<spring:message code="announcement.description" />
</h3>
<jstl:out value="${announcement.getDescription()}" />

<h3>
	<spring:message code="announcement.moment" />
</h3>
<jstl:out value="${announcement.getMoment()}" />

<h3>
	<spring:message code="announcement.originPlaceX" />
</h3>
<jstl:out value="${announcement.getOriginPlace().getCoordinateX()}" />

<h3>
	<spring:message code="announcement.originPlaceY" />
</h3>
<jstl:out value="${announcement.getOriginPlace().getCoordinateY()}" />

<h3>
	<spring:message code="announcement.destinyPlaceX" />
</h3>
<jstl:out value="${announcement.getDestinyPlace().getCoordinateX()}" />
<h3>
	<spring:message code="announcement.destinyPlaceY" />
</h3>
<jstl:out value="${announcement.getDestinyPlace().getCoordinateY()}" />

<security:authorize access="hasRole('ADMINISTRATOR')">
	<h3>
		<spring:message code="announcement.ban" />
	</h3>
	<jstl:out value="${announcement.getBan()}" />

	<a
		href="announcement/banAnnouncement.do?announcementId=${announcement.id }">
		<jstl:choose>
			<jstl:when test="${announcement.getBan()}">

				<spring:message code="unBan" />
			</jstl:when>
			<jstl:otherwise>
			<spring:message code="ban" />
			</jstl:otherwise>
		</jstl:choose>
	</a>

</security:authorize>

<h3>
	<a href="comment/postComment.do?comentableId=${announcement.id }">
		<spring:message code="postComment" />
	</a>
</h3>


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




