<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	<h4>
		<spring:message code="auditor.edit.label.name" />:
	</h4>
	<jstl:out value="${auditor.name}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.edit.label.surname" />:
	</h4>
	<jstl:out value="${auditor.surname}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.edit.label.address" />:
	</h4>
	<jstl:out value="${auditor.address}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.edit.label.vat" />:
	</h4>
	<jstl:out value="${auditor.vat}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.edit.label.email" />:
	</h4>
	<jstl:out value="${auditor.email}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.edit.label.phoneNumber" />:
	</h4>
	<jstl:out value="${auditor.phoneNumber}"></jstl:out>

	<h4>
		<spring:message code="auditor.edit.label.username" />:
	</h4>
	<jstl:out value="${auditor.userAccount.username}"></jstl:out>
	

	<h4>
		<spring:message code="auditor.holderName" />:
	</h4>
	<jstl:out value="${auditor.holderName}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.makeName" />:
	</h4>
	<jstl:out value="${auditor.makeName}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.number" />:
	</h4>
	<jstl:out value="${auditor.number}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.expirationYear" />:
	</h4>
	<jstl:out value="${auditor.expirationYear}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.expirationMonth" />:
	</h4>
	<jstl:out value="${auditor.expirationMonth}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.cvv" />:
	</h4>
	<jstl:out value="${auditor.cvv}"></jstl:out>
	
	<h4>
		<spring:message code="auditor.socialprofile" />:
	</h4>
	<jstl:forEach items="${auditor.socialProfiles}" var="sp">
		<jstl:out value="${sp.nick}"></jstl:out>
	</jstl:forEach>

<br/><br/>

	<spring:message code="auditor.export.explanation" var="exportExplanation"/>
	<jstl:out value="${exportExplanation}"/>
	
	<a href="auditor/auditor/edit.do"> Link </a>
	
	
	<br/>

