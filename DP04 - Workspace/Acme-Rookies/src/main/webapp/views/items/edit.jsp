<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>





<form:form modelAttribute="item" action="item/provider/edit.do">
		<form:hidden path="id"/>
		<form:hidden path="version" />
		<form:hidden path="provider" />

		<form:label path="name">
			<spring:message code="item.name" />:*
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		<br />	
		
		<form:label path="description">
			<spring:message code="item.description" />:*
		</form:label>
		<form:input path="description" />
		<form:errors cssClass="error" path="description" />
		<br />	

		<form:label path="link">
			<spring:message code="item.link" />:*
		</form:label>
		<form:input path="link" />
		<form:errors cssClass="error" path="link" />
		
		<br />
		
		<form:label path="photos">
			<spring:message code="item.photos" />:
		</form:label>
		<form:input path="photos" placeholder="obj1,obj2, ..." />
		<form:errors cssClass="error" path="photos" />
		<br />
		
	<input type ="submit" name="save" value="<spring:message code="item.save"/>" />

	<input type="button" name="cancel" value="<spring:message code="item.cancel" />" onclick="javascript:relativeRedir('item/provider/list.do');" />


</form:form>





