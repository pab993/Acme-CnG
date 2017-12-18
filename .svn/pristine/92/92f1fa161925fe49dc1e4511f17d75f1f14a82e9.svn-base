<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

		
	<!-- Listing table -->
	
	<display:table name = "offers" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >
		<jstl:choose>
			<jstl:when test="${row.ban == false }">
				<spring:message code = "offer.title" var = "titleHeader" />
				<display:column property = "title" title = "${titleHeader}" style="background:white"/>
			
				<spring:message code="offer.description" var="descriptionHeader" />
				<display:column property="description" title="${descriptionHeader}" style="background:white"/>
				
				<spring:message code = "offer.moment" var = "momentHeader" />
				<display:column property = "moment" title = "${momentHeader}" sortable = "true" style="background:white"/>
				
				<security:authorize access="hasRole('CUSTOMER')" >
				<jstl:choose>
					<jstl:when test="${own}">
						<display:column style="background:white">
								<a href="application/customer/createToOffer.do?offerId=${row.id}">
									<spring:message code="offer.newApplication" />
								</a>
						</display:column>
					</jstl:when>
				</jstl:choose>
				
				<jstl:choose>
					<jstl:when test="${owner}">
						<display:column style="background:white">
							<a href="application/customer/listByOffer.do?offerId=${row.id}">
								<spring:message code="offer.listApplication" />
							</a>
						</display:column>
					</jstl:when>
				</jstl:choose>
				</security:authorize>
				
			</jstl:when>
			<jstl:otherwise>
			
			<spring:message code = "offer.title" var = "titleHeader" />
				<display:column property = "title" title = "${titleHeader}" style="background:orange"/>
			
				<spring:message code="offer.description" var="descriptionHeader" />
				<display:column property="description" title="${descriptionHeader}" style="background:orange"/>
				
				<spring:message code = "offer.moment" var = "momentHeader" />
				<display:column property = "moment" title = "${momentHeader}" sortable = "true" style="background:orange"/>
				
				<security:authorize access="hasRole('CUSTOMER')" >
				<jstl:choose>
					<jstl:when test="${own}">
						<display:column style="background:orange">
								<a href="application/customer/createToOffer.do?offerId=${row.id}">
									<spring:message code="offer.newApplication" />
								</a>
						</display:column>
					</jstl:when>
				</jstl:choose>
				
				<jstl:choose>
					<jstl:when test="${owner}">
						<display:column style="background:orange">
							<a href="application/customer/listByOffer.do?offerId=${row.id}">
								<spring:message code="offer.listApplication" />
							</a>
						</display:column>
					</jstl:when>
				</jstl:choose>
				</security:authorize>
			
			</jstl:otherwise>
		</jstl:choose>
	</display:table>
	
	<acme:cancel code="Cancel" url ="welcome/index.do"/>