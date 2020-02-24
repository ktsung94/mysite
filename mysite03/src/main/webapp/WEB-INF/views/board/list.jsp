<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	pageContext.setAttribute("newLine", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board?a=list&page=1" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>삭제</th>
					</tr>	
					<c:set var='size' value='${fn:length(list) }'></c:set>
					<c:forEach items='${list }' var='vo' varStatus='status' begin="${(page-1)*5 }" end="${(5*page)-1 }">			
					<tr>
						<td>${size - status.index }</td>
						<c:choose>
							<c:when test="${vo.depth eq 0 }">
								<td>
									<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no }">
										${vo.title }
									</a>
								</td>							
							</c:when>
							<c:otherwise>
								<td style="text-align:left; padding-left:${25*vo.depth }px">
									<img src='/mysite02/assets/images/reply.png'>
										<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no }">
											${vo.title }
										</a>								
								</td>	
							</c:otherwise>
						</c:choose>		
				
						<td>${vo.name }</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						<c:choose>
							<c:when test="${authUser.no eq vo.userNo }">
								<td><a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no }" class="del"><img src='/mysite02/assets/images/recycle.png'></a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${page >= 3 }">
								<c:choose>
									<c:when test="${page ne 1 }">
										<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page-1 }">◀</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=1">◀</a></li>
									</c:otherwise>
								</c:choose>
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page-2 }">${page-2 }</a></li>
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page-1 }">${page-1 }</li>
								<li class="selected"><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page }">${page }</a></li>
								<c:choose>
									<c:when test="${page <= (size/5) }">
										<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page+1 }">${page+1 }</li>
										<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page+2 }">${page+2 }</li>
										<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page+1 }">▶</a></li>
									</c:when>
									<c:otherwise>
										<li>${page+1 }</li>
										<li>${page+2 }</li>
										<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page}">▶</a></li>
									</c:otherwise>
								</c:choose>								
							</c:when>							
							<c:otherwise>
								<c:choose>
									<c:when test="${page ne 1 }">
										<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page-1 }">◀</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=1">◀</a></li>
									</c:otherwise>
								</c:choose>
									
									<c:choose>
										<c:when test="${page eq 1 }"><li class="selected"><a href="${pageContext.servletContext.contextPath }/board?a=list&page=1">1</a></li></c:when>
										<c:otherwise><li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=1">1</a></li></c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${page eq 2 }"><li class="selected"><a href="${pageContext.servletContext.contextPath }/board?a=list&page=2">2</li></c:when>
										<c:otherwise><li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=2">2</li></c:otherwise>
									</c:choose>
									<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=3">3</a></li>
									<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=4">4</li>
									<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=5">5</li>
									<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page+1 }">▶</a></li>
							</c:otherwise>						
						</c:choose>
						
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
				<c:choose>
					<c:when test="${!empty authUser }">
						<a href="${pageContext.servletContext.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath }/user?a=loginform" id="new-book">글쓰기</a>
					</c:otherwise>
				</c:choose>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>