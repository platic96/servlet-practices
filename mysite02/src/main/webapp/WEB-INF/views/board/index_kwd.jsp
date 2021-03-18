<%@page import="com.saltlux.mysite.vo.UserVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UserVo authUser = (UserVo) session.getAttribute("authUser");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${ pageContext.request.contextPath}/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${ pageContext.request.contextPath}/board" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<tr>
						<c:set var="count" value="${fn:length(list) }"></c:set>
						<c:forEach items="${list }" var="vo" varStatus="status">
							<tr>
								<td>${(status.index*p)+1}</td>
								<c:choose>
									<c:when test="${vo.depth>1 }">
										<td style="text-align: left"><a
											href="${ pageContext.request.contextPath}/board?a=view&no=${vo.no}"
											style="text-align: left; padding-left: ${(vo.depth-1)*15}px"><img
												src="${pageContext.request.contextPath }/assets/images/reply.png" />${vo.title}</a></td>
									</c:when>
									<c:when test="${vo.depth==1 }">
										<td style="text-align: left"><a
											href="${ pageContext.request.contextPath}/board?a=view&no=${vo.no}"
											style="text-align: left; padding-left: 0px">${vo.title}</a></td>
									</c:when>
								</c:choose>
								<td>${vo.user_name }</td>
								<td>${vo.count }</td>
								<td>${vo.reg_date }</td>
								<c:if test="${authUser.name == vo.user_name }">
									<td><a
										href="${ pageContext.request.contextPath}/board?a=delete&no=${vo.no }&p=${p}"
										class="del">삭제</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tr>
					<!-- pager 추가 -->
				</table>
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<c:set var="count" value="${fn:length(list) }"></c:set>
						<c:forEach begin="${p }" end="${p+3 }" varStatus="status">
								<li class="selected"><a
									href="${ pageContext.request.contextPath}/board?a=index&p=${status.index}">${status.index}</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="bottom">
					<c:choose>
						<c:when test="${ empty authUser}">
							<a href="${pageContext.request.contextPath }/user?a=login"
								id="new-book">글쓰기</a>
						</c:when>
						<c:otherwise>
							<a
								href="${ pageContext.request.contextPath}/board?a=writeform&answer=false"
								id="new-book">글쓰기</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>