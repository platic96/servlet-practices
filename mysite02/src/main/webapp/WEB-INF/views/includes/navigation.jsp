<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="navigation">
			<ul>
				<li><a href="${ pageContext.request.contextPath}">정민용</a></li>
				<li><a href="${ pageContext.request.contextPath}/guestbook?a=index">방명록</a></li>
				<li><a href="${ pageContext.request.contextPath}/board?a=index&p=1">게시판</a></li>
			</ul>
		</div>