<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMINISTRATOR')">
		<div>
			<display:table name="ratioOfOffers" id="ratioOfOffers" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.ratio.offers" var="dash1" />
				<display:column title="${dash1}">
					<jstl:out value="${ratioOfOffers}"/>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="ratioOfRequests" id="ratioOfRequests" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.ratio.requests" var="dash2" />
				<display:column title="${dash2}">
					<jstl:out value="${ratioOfRequests}"/>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="avgAnnouncementPerCustomer" id="avgAnnouncementPerCustomer" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.avg.announcements" var="dash3" />
				<display:column title="${dash3}">
					<jstl:out value="${avgAnnouncementPerCustomer}"/>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="avgApplicationsPerAnnouncements" id="avgApplicationsPerAnnouncements" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.avg.applications" var="dash4" />
				<display:column title="${dash4}">
					<jstl:out value="${avgApplicationsPerAnnouncements}"/>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="customersMoreApplicationAccepted" id="customersMoreApplicationAccepted" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.customer.more.accepted" var="dash5" />
				<display:column title="${dash5}">
					<jstl:out value="${customersMoreApplicationAccepted.userAccount.username}"/>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="customersMoreApplicationDenied" id="customersMoreApplicationDenied" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.customer.more.denied" var="dash6" />
				<display:column title="${dash6}">
					<jstl:out value="${customersMoreApplicationDenied.userAccount.username}"/>
				</display:column>
			</display:table>
		</div>
		
		<div>
			<display:table name="minMessagesSended" id="minMessagesSended" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.min.messages.sended" var="dash7" />
				<display:column title="${dash7}">
					<jstl:out value="${minMessagesSended}"/>
				</display:column>
			</display:table>
		</div>
		
		<div>
			<display:table name="maxMessagesSended" id="maxMessagesSended" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.max.messages.sended" var="dash8" />
				<display:column title="${dash8}">
					<jstl:out value="${maxMessagesSended}"/>
				</display:column>
			</display:table>
		</div>
		
		<div>
			<display:table name="avgMessagesSended" id="avgMessagesSended" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.avg.messages.sended" var="dash9" />
				<display:column title="${dash9}">
					<jstl:out value="${avgMessagesSended}"/>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="avgPerActorOfferRequest" id="avgPerActorOfferRequest" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.avg.comentable" var="dash10" />
				<display:column title="${dash10}">
					<jstl:out value="${avgPerActorOfferRequest}"/>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="avgPerAdminsAndCustomers" id="avgPerAdminsAndCustomers" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.avg.comments.actor" var="dash11" />
				<display:column title="${dash11}">
					<jstl:out value="${avgPerAdminsAndCustomers}"/>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="actorsBetweenAvgTenPerCent" id="actorsBetweenAvgTenPerCent" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.actors.between.tenPerCent" var="dash12" />
				<display:column title="${dash12}">
					<jstl:out value="${actorsBetweenAvgTenPerCent.userAccount.username}"/>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="findActorWithMoreMessagesSent" id="findActorWithMoreMessagesSent" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.actors.more.messagesSent" var="dash13" />
				<display:column title="${dash13}">
					<jstl:out value="${findActorWithMoreMessagesSent.userAccount.username}"/>
				</display:column>
			</display:table>
		</div>
		
		<div>
		<display:table name="findActorWithMoreMessagesReceived" id="findActorWithMoreMessagesReceived" pagesize="5" requestURI="${requestURI}" class="displaytag">
				<spring:message code="dashboard.actors.more.messagesReceived" var="dash14" />
				<display:column title="${dash14}">
					<jstl:out value="${findActorWithMoreMessagesReceived.userAccount.username}"/>
				</display:column>
			</display:table>
		</div>
		
</security:authorize>