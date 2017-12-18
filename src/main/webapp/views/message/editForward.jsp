<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="message/editForward.do" modelAttribute="messageForward">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<fieldset>
		<legend>
			<b> <spring:message code="message.recipient" /></b>
		</legend>

		<acme:select items="${actorRecipients}" itemLabel="name"
			code="choose" path="actorRecipient" />

	</fieldset>

	<br />

	<acme:submit name="save" code="message.submit" />
	<acme:cancel url="welcome/index.do" code="cancel" />

</form:form>
