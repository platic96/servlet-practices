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
				<form id="search_form" action="" method="post">
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
								<td>${count-status.index}</td>
								<td><a
									href="${ pageContext.request.contextPath}/board?a=view&no=${vo.no}"
									style="text-align: left; padding-left: 0px">${vo.title}</a></td>
								<td>${vo.user_name }</td>
								<td>${vo.count }</td>
								<td>${vo.reg_date }</td>
								<c:if test="${authUser.no == vo.no }">
									<td><a href="${ pageContext.request.contextPath}/board?a=delete" class="del">삭제</a></td>
								</c:if>

							</tr>
						</c:forEach>
					</tr>
					<!-- pager 추가 -->
				</table>
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li class="selected"><a href="">1</a></li>
						<li><a href="/mysite02/board?p=2">2</a></li>
						<li><a href="/mysite02/board?p=3">3</a></li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<div class="bottom">
					<c:choose>
						<c:when test="${ empty authUser}">
							<a href="${pageContext.request.contextPath }/user?a=login"
								id="new-book">글쓰기</a>
						</c:when>
						<c:otherwise>
							<a href="${ pageContext.request.contextPath}/board?a=writeform&answer=false"
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