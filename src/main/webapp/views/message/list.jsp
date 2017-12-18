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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<!-- Listing table -->

<display:table name="messages" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="message.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="message.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />

	<spring:message code="message.attachment" var="attachmentHeader" />
	<display:column property="attachment" title="${attachmentHeader}" />

	<spring:message code="message.sender" var="senderHeader" />
	<display:column property="actorSender.name" title="${senderHeader}" />

	<spring:message code="message.recipient" var="recipientHeader" />
	<display:column property="actorRecipient.name"
		title="${recipientHeader}" />

	<display:column>
		<a href="message/editForward.do?messageId=${row.id}"> <spring:message
				code="message.forward" />
		</a>
	</display:column>

	<jstl:if test="${principalId != row.actorSender.id}">
		<display:column>
			<a href="message/editReply.do?messageId=${row.id}"> <spring:message
					code="message.reply" />
			</a>
		</display:column>
	</jstl:if>

	<jstl:if test="${principalId == row.actorSender.id}">
		<display:column>
			<a href="message/delete.do?messageId=${row.id}"> <spring:message
					code="message.delete" />
			</a>
		</display:column>
	</jstl:if>

</display:table>

<acme:cancel code="cancel" url="welcome/index.do" />