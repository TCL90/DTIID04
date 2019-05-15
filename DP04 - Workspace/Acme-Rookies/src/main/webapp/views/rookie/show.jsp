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
		<spring:message code="rookie.edit.label.name" />:
	</h4>
	<jstl:out value="${rookie.name}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.edit.label.surname" />:
	</h4>
	<jstl:out value="${rookie.surname}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.edit.label.address" />:
	</h4>
	<jstl:out value="${rookie.address}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.edit.label.vat" />:
	</h4>
	<jstl:out value="${rookie.vat}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.edit.label.email" />:
	</h4>
	<jstl:out value="${rookie.email}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.edit.label.phoneNumber" />:
	</h4>
	<jstl:out value="${rookie.phoneNumber}"></jstl:out>

	<h4>
		<spring:message code="rookie.edit.label.username" />:
	</h4>
	<jstl:out value="${rookie.userAccount.username}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.holderName" />:
	</h4>
	<jstl:out value="${rookie.holderName}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.makeName" />:
	</h4>
	<jstl:out value="${rookie.makeName}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.number" />:
	</h4>
	<jstl:out value="${rookie.number}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.expirationYear" />:
	</h4>
	<jstl:out value="${rookie.expirationYear}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.expirationMonth" />:
	</h4>
	<jstl:out value="${rookie.expirationMonth}"></jstl:out>
	
	<h4>
		<spring:message code="rookie.cvv" />:
	</h4>
	<jstl:out value="${rookie.cvv}"></jstl:out>
	

<br/><br/>

	<spring:message code="rookie.export.explanation" var="exportExplanation"/>
	<jstl:out value="${exportExplanation}"/>
	
	<a href="rookie/rookie/edit.do"> Link </a>
	
	
	<br/>
