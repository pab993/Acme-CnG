<%--
 * search.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="offer/customer/search.do">

	<h4>
		<spring:message code="offer.searchText" />
	</h4>

	<input type="text" name="keyword" />
	<input type="submit" name="search"
		value="<spring:message code = "offer.search" />" />

</form:form>

<jstl:if test="${!firstTime}">
		
		<display:table name = "offers" id = "row" requestURI="offer/customer/searchForm.do" pagesize = "10" class = "displaytag" >
	
		<jstl:choose>
			<jstl:when test="${row.ban == false}">
				<spring:message code = "offer.title" var = "titleHeader" />
				<display:column property = "title" title = "${titleHeader}" sortable = "true" style="background:white"/>	
				
				<spring:message code = "offer.description" var = "descriptionHeader" />
				<display:column property = "description" title = "${descriptionHeader}" sortable = "true" style="background:white"/>	
				
				<spring:message code = "offer.moment" var = "momentHeader" />
				<display:column property = "moment" title = "${momentHeader}" sortable = "true" style="background:white"/>	
				
				<spring:message code = "offer.originPlace" var = "originPlaceHeader" />
				<display:column property = "originPlace.place" title = "${originPlaceHeader}" sortable = "true" style="background:white"/>	
				
				<spring:message code = "offer.originXPlace" var = "originXPlaceHeader" />
				<display:column property = "originPlace.coordinateX" title = "${originXPlaceHeader}" sortable = "true" style="background:white"/>	
				
				<spring:message code = "offer.originYPlace" var = "originYPlaceHeader" />
				<display:column property = "originPlace.coordinateY" title = "${originYPlaceHeader}" sortable = "true" style="background:white"/>	
				
				<spring:message code = "offer.destinyPlace" var = "destinyPlaceHeader" />
				<display:column property = "destinyPlace.place" title = "${destinyPlaceHeader}" sortable = "true" style="background:white"/>	
				
				<spring:message code = "offer.destinyXPlace" var = "destinyXPlaceHeader" />
				<display:column property = "destinyPlace.coordinateX" title = "${destinyXPlaceHeader}" sortable = "true" style="background:white"/>	
				
				<spring:message code = "offer.destinyYPlace" var = "destinyYPlaceHeader" />
				<display:column property = "destinyPlace.coordinateY" title = "${destinyYPlaceHeader}" sortable = "true" style="background:white"/>
				<display:column style="background:white">
						<a href="actor/display.do?actorId=${row.customer.id}">
							<spring:message code="offer.customer.display" />
						</a>
					</display:column>
				<display:column style="background:white">
						<a href="announcement/display.do?announcementId=${row.id}">
							<spring:message code="offer.display" />
						</a>
				</display:column>
			</jstl:when>
			<jstl:otherwise>
				
				<spring:message code = "offer.title" var = "titleHeader" />
				<display:column property = "title" title = "${titleHeader}" sortable = "true" style="background:orange"/>	
				
				<spring:message code = "offer.description" var = "descriptionHeader" />
				<display:column property = "description" title = "${descriptionHeader}" sortable = "true" style="background:orange"/>	
				
				<spring:message code = "offer.moment" var = "momentHeader" />
				<display:column property = "moment" title = "${momentHeader}" sortable = "true" style="background:orange"/>	
				
				<spring:message code = "offer.originPlace" var = "originPlaceHeader" />
				<display:column property = "originPlace.place" title = "${originPlaceHeader}" sortable = "true" style="background:orange"/>	
				
				<spring:message code = "offer.originXPlace" var = "originXPlaceHeader" />
				<display:column property = "originPlace.coordinateX" title = "${originXPlaceHeader}" sortable = "true" style="background:orange"/>	
				
				<spring:message code = "offer.originYPlace" var = "originYPlaceHeader" />
				<display:column property = "originPlace.coordinateY" title = "${originYPlaceHeader}" sortable = "true" style="background:orange"/>	
				
				<spring:message code = "offer.destinyPlace" var = "destinyPlaceHeader" />
				<display:column property = "destinyPlace.place" title = "${destinyPlaceHeader}" sortable = "true" style="background:orange"/>	
				
				<spring:message code = "offer.destinyXPlace" var = "destinyXPlaceHeader" />
				<display:column property = "destinyPlace.coordinateX" title = "${destinyXPlaceHeader}" sortable = "true" style="background:orange"/>	
				
				<spring:message code = "offer.destinyYPlace" var = "destinyYPlaceHeader" />
				<display:column property = "destinyPlace.coordinateY" title = "${destinyYPlaceHeader}" sortable = "true" style="background:orange"/>
				<display:column style="background:orange">
						<a href="actor/display.do?actorId=${row.customer.id}">
							<spring:message code="offer.customer.display" />
						</a>
					</display:column>
				<display:column style="background:orange">
						<a href="announcement/display.do?announcementId=${row.id}">
							<spring:message code="offer.display" />
						</a>
				</display:column>
				
			</jstl:otherwise>
		</jstl:choose>
		
	</display:table>
</jstl:if>