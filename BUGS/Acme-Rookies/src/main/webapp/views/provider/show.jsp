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
		<spring:message code="provider.edit.label.name" />:
	</h4>
	<jstl:out value="${provider.name}"></jstl:out>
	
	<h4>
		<spring:message code="provider.edit.label.surname" />:
	</h4>
	<jstl:out value="${provider.surname}"></jstl:out>
	
	<h4>
		<spring:message code="provider.edit.label.address" />:
	</h4>
	<jstl:out value="${provider.address}"></jstl:out>
	
	<h4>
		<spring:message code="provider.edit.label.vat" />:
	</h4>
	<jstl:out value="${provider.vat}"></jstl:out>
	
	<h4>
		<spring:message code="provider.edit.label.email" />:
	</h4>
	<jstl:out value="${provider.email}"></jstl:out>
	
	<h4>
		<spring:message code="provider.edit.label.phoneNumber" />:
	</h4>
	<jstl:out value="${provider.phoneNumber}"></jstl:out>

	<h4>
		<spring:message code="provider.edit.label.username" />:
	</h4>
	<jstl:out value="${provider.userAccount.username}"></jstl:out>


	<h4>
		<spring:message code="provider.holderName" />:
	</h4>
	<jstl:out value="${provider.holderName}"></jstl:out>
	
	<h4>
		<spring:message code="provider.makeName" />:
	</h4>
	<jstl:out value="${provider.makeName}"></jstl:out>
	
	<h4>
		<spring:message code="provider.number" />:
	</h4>
	<jstl:out value="${provider.number}"></jstl:out>
	
	<h4>
		<spring:message code="provider.expirationYear" />:
	</h4>
	<jstl:out value="${provider.expirationYear}"></jstl:out>
	
	<h4>
		<spring:message code="provider.expirationMonth" />:
	</h4>
	<jstl:out value="${provider.expirationMonth}"></jstl:out>
	
	<h4>
		<spring:message code="provider.cvv" />:
	</h4>
	<jstl:out value="${provider.cvv}"></jstl:out>
	
	<h4>
		<spring:message code="provider.socialprofile" />:
	</h4>
	<jstl:forEach items="${provider.socialProfiles}" var="sp">
		<jstl:out value="${sp.nick}"></jstl:out>
	</jstl:forEach>
	
	


<br/><br/>

	<spring:message code="provider.export.explanation" var="exportExplanation"/>
	<jstl:out value="${exportExplanation}"/>
	
	<a href="provider/provider/edit.do"> Link </a>
	
	
	<br/>
