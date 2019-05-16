<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="audits" requestURI="${requestURI}" id="row">

	<display:column property="moment" titleKey="audit.moment"  />
	<display:column property="text" titleKey="audit.text"  />
	<display:column property="score" titleKey="audit.score"  />	
	
	<display:column titleKey="audit.finalMode" >
		<jstl:if test="${row.finalMode == 'true' }">
			<spring:message code="audit.yes"/>
		</jstl:if>
		<jstl:if test="${row.finalMode == 'false' }">
			<spring:message code="audit.no"/>
		</jstl:if>
	</display:column>
	
	<display:column property="position.title" titleKey="audit.position.title"  />
	
	<display:column>
			<a href="audit/auditor/show.do?auditId=${row.id}">
			<spring:message code="audit.show" />
			</a>
	</display:column>
	
	<display:column>

	<jstl:if test="${!row.finalMode }">

	<a href="audit/auditor/edit.do?auditId=${row.id}">
			<spring:message code="audit.edit" />
			</a>
	</jstl:if>
	</display:column>
		
</display:table>