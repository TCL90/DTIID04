<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="audit/auditor/edit.do"
	modelAttribute="audit">
	<form:hidden path="id" />
	<jstl:if test="${audit.id==0 }">
	<form:hidden path="moment"/>
	<form:hidden path="position" />
	</jstl:if>

	<fieldset>
		<legend align="left">
			<spring:message code="edit.audit" />
		</legend>

		<form:label path="text">
			<spring:message code="audit.text" />* :
		</form:label>
		<form:input path="text" />
		<form:errors cssClass="error" path="text" />
		<br /> <br />
		
		<form:label path="score">
			<spring:message code="audit.score" />* :
		</form:label>
		<form:input path="score" />
		<form:errors cssClass="error" path="score" />
		<br /> <br />
		
		<form:label path="finalMode">
			<spring:message code="audit.finalMode" />* :
		</form:label>
		<form:select path="finalMode" >
		<form:option value="true"><spring:message code="audit.finalMode.true"/></form:option>
		<form:option value="false"><spring:message code="audit.finalMode.false"/></form:option>
		</form:select>
		<form:errors cssClass="error" path="finalMode" />
		<br /> <br />

	</fieldset>
	<br />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="audit.save" />" />&nbsp;
		<jstl:if test="${audit.id!=0 }">
	<jstl:if test="${audit.finalMode==false}"/>
	<input type="submit" name="delete"
		value="<spring:message code="audit.delete" />"
		onclick="return confirm('<spring:message code="audit.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</jstl:if>
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('audit/auditor/list.do')"
		value="<spring:message code="audit.edit.cancel" />" />

</form:form>