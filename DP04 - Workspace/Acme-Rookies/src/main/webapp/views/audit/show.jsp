<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('AUDITOR')">

	<h3 style="color:blue;">
		<spring:message code="audit.moment" />:
	</h3>
	<jstl:out value="${audit.moment}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="audit.text" />:
	</h3>
	<jstl:out value="${audit.text}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="audit.score" />:
	</h3>
	<jstl:out value="${audit.score}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="audit.finalMode" />:
	</h3>
	<jstl:out value="${audit.finalMode}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="audit.position.title" />:
	</h3>
	<jstl:out value="${audit.position.title}"></jstl:out>
	
	<br/>
	<br/>
	
	<form:form action="audit/auditor/edit.do" modelAttribute="audit">
	<form:hidden path="id"/>
	<jstl:if test="${!audit.finalMode }">
	<input type="submit" name="delete"
			value="<spring:message code="audit.delete" />"
			onclick="return confirm('<spring:message code="audit.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</form:form>
	
	
	<input type="button" name="back" onclick="javascript: window.location.replace('audit/auditor/list.do')"
		value="<spring:message code="audit.back" />" />
	</security:authorize>