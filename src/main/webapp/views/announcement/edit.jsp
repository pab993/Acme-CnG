<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="request/customer/edit.do" modelAttribute="request">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ban" />
	
	<form:hidden path="customer" />

	<fieldset >
		<legend> <b> <spring:message code="announcement.name" /></b> </legend>
		
		<acme:textbox code="announcement.title" path="title"/>
		<br />
		
		<acme:textbox code="announcement.description" path="description"/>
		<br />
		
		<acme:textbox code="announcement.moment" path="moment"/>
		<br />
		
	</fieldset>
	
	<fieldset > 
	
		<legend> <spring:message code="announcement.originPlace" /> </legend>
		
		<acme:textbox code="announcement.place" path="originPlace.place"/>
		<br />
		
		<acme:textbox code="announcement.coordinateX" path="originPlace.coordinateX"/>
		<br />
		
		<acme:textbox code="announcement.coordinateY" path="originPlace.coordinateY"/>
		<br />
	
	</fieldset>
	
	<fieldset > 
	
		<legend> <spring:message code="announcement.destinyPlace" /> </legend>
		
		<acme:textbox code="announcement.place" path="destinyPlace.place"/>
		<br />
		
		<acme:textbox code="announcement.coordinateX" path="destinyPlace.coordinateX"/>
		<br />
		
		<acme:textbox code="announcement.coordinateY" path="destinyPlace.coordinateY"/>
		<br />
	
	</fieldset>

	<br />
	
	<acme:submit name="save" code="announcement.submit"/>
	<acme:cancel url="welcome/index.do" code="cancel"/>
	
</form:form>
