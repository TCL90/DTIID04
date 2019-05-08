<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>



<b><spring:message code="item.name"/></b>
<jstl:out value="${item.name}"/>
<br/>

<b><spring:message code="item.description"/></b>
<jstl:out value="${item.description}"/>
<br/>

<b><spring:message code="item.link"/></b>
<jstl:out value="${item.link}"/>
<br/>

<b><spring:message code="item.photos"/></b>
<jstl:out value="${item.photos}"/>
<br/>
