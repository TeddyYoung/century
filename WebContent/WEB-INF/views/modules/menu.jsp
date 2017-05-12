<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<ul class="submenu">
	<c:forEach items="${childs }" var="child">
		<li class="">
			<a 
				<c:if test="${fn:length(child.menus) > 0 }">
					 class="dropdown-toggle" 
				</c:if>
				<c:if test="${fn:length(child.menus) == 0 }">
					href="javascript:openapp('${pageContext.request.contextPath}${child.url }','${child.menuEnname }','${child.menuName }');"
				</c:if>
			>
				<i class="menu-icon fa ${child.icon } orange"></i>
	
				${child.menuName }
				<c:if test="${fn:length(child.menus) > 0 }">
					<b class="arrow fa fa-angle-down"></b>
				</c:if>
			</a>
	
			<b class="arrow"></b>
	
			<c:if test="${fn:length(child.menus) > 0 }">
				<c:set scope="request" var="childs" value="${child.menus }"></c:set>
				<c:import url="/WEB-INF/views/modules/menu.jsp"></c:import>
			</c:if>
		</li>
	</c:forEach>
</ul>