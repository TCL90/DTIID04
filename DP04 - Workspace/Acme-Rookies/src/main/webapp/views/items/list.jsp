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



<display:table name="items" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
<display:column property="name" titleKey="item.name"/>
<display:column property="description" titleKey="item.description"/>
<display:column property="link" titleKey="item.link"/>
<display:column>
	<a href="item/provider/display.do?itemId=${row.id}">
		<spring:message code="item.display"/>
	</a>
</display:column>
<display:column>
		<a href="item/provider/edit.do?itemId=${row.id}">
			<spring:message code="item.edit"/>
		</a>
</display:column>

<display:column>
		<a href="item/provider/delete.do?itemId=${row.id}">
			<spring:message code="item.delete"/>
		</a>
</display:column>
</display:table>

<security:authorize access="hasRole('PROVIDER')">

<a href="item/provider/create.do">
<spring:message code="item.create"/>
</a>
</security:authorize>








