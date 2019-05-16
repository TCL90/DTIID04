<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="${customisation.bannerUrl}" alt="${customisation.systemName}" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/administrator/create.do"><spring:message code="master.page.administrator.create" /></a></li>
					<li><a href="auditor/administrator/create.do"><spring:message code="master.page.auditor.create" /></a></li>
					<li><a href="administrator/administrator/edit.do"><spring:message code="master.page.administrator.edit" /></a></li>
					<li><a href="dashboard/administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="customisation/administrator/edit.do"><spring:message code="master.page.administrator.customisation" /></a></li>
					<li><a href="ban/administrator/list.do"><spring:message code="master.page.administrator.ban" /></a></li>
					<li><a href="score/administrator/calculate.do"><spring:message code="master.page.administrator.scoreCalculator" /></a></li>

				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUDITOR')">
			<li><a class="fNiv"><spring:message	code="master.page.auditor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="auditor/auditor/edit.do"><spring:message code="master.page.auditor.edit" /></a></li>
					<li><a href="position/auditor/list.do"><spring:message code="master.page.auditor.position.list" /></a></li>
					<li><a href="audit/auditor/list.do"><spring:message code="master.page.auditor.audits.list" /></a></li>
			</ul>
			</li>
		</security:authorize>
		
						<security:authorize access="hasRole('PROVIDER')">
			<li><a class="fNiv"><spring:message	code="master.page.provider" /></a>
			
				<ul>
					<li class="arrow"></li>
					<li><a href="provider/provider/edit.do"><spring:message code="master.page.edit.provider" /></a></li>
					<li><a href="item/provider/list.do"><spring:message code="master.page.provider.items" /></a></li>
			</ul>
			</li>
		</security:authorize>
		
		
		
		<security:authorize access="hasRole('COMPANY')">
			<li><a class="fNiv"><spring:message	code="master.page.company" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="company/company/edit.do"><spring:message code="master.page.edit.company" /></a></li>
					<li><a href="problem/company/list.do"><spring:message code="master.page.problem" /></a></li>	
					<li><a href="position/company/list.do"><spring:message code="master.page.list.position" /></a></li>
					<li><a href="application/company/list.do"><spring:message code="master.page.list.application" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ROOKIE')">
			<li><a class="fNiv"><spring:message	code="master.page.rookie" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="rookie/rookie/edit.do"><spring:message code="master.page.edit.rookie" /></a></li>
					<li><a href="finder/rookie/show.do"><spring:message code="master.page.finder" /></a></li>	
					<li><a href="curricula/rookie/list.do"><spring:message code="master.page.curricula.list"/></a></li>
					<li><a href="position/rookie/list.do"><spring:message code="master.page.rookie.list"/></a></li>
					<li><a href="application/rookie/list.do"><spring:message code="master.page.rookie.applications"/></a></li>
					
				</ul>
			</li>
		</security:authorize>

		
		<security:authorize access="isAuthenticated()">
		<li>
				<a class="fNiv"> 
					<spring:message code="master.page.socialprofile" /> 
				</a>
				<ul>
					<li class="arrow"></li>					
					<li><a href="socialprofile/list.do"><spring:message code="master.page.socialprofile.list" /> </a></li>
				</ul>
			</li>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.messages.header" /> 
				</a>
				<ul>
					<li class="arrow"></li>					
					<li><a href="boxes/list.do"><spring:message code="master.page.messages.link" /> </a></li>
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="position/list.do"><spring:message code="master.page.position" /></a></li>
			<li><a class="fNiv" href="company/list.do"><spring:message code="master.page.company" /></a></li>
			<li><a class="fNiv" href="item/list.do"><spring:message code="master.page.item" /></a></li>
			<li><a class="fNiv" href="provider/list.do"><spring:message code="master.page.provider.list" /></a></li>
			<li>
			<a class="fNiv" href="company/register.do"><spring:message code="master.page.register.company" /></a>
			</li>
			<li>
			<a class="fNiv" href="rookie/register.do"><spring:message code="master.page.register.rookie" /></a>
			</li>
			<li>
			<a class="fNiv" href="provider/register.do"><spring:message code="master.page.register.provider" /></a>
			</li>
			<li>
			<a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a>
			</li>

			</security:authorize>
		<security:authorize access="permitAll">
			<li><a class="fNiv"><spring:message	code="master.page.actions" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="search/search.do"><spring:message code="master.page.search" /></a></li>			
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>


<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

