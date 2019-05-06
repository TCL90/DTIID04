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



<display:table name="providers" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">

<display:column property="name" titleKey="provider.name"/>
<display:column property="photo" titleKey="provider.photo"/>
<display:column property="email" titleKey="provider.email"/>
<display:column property="phoneNumber" titleKey="provider.phoneNumber"/>
<display:column property="address" titleKey="provider.address"/>

<display:column>
	<a href="provider/show.do?providerId=${row.id}">
		<spring:message code="provider.show"/>
	</a>
</display:column>

<display:column>
	<a href="provider/item/list.do?providerId=${row.id}">
		<spring:message code="provider.items"/>
	</a>
</display:column>

</display:table>







