<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.iosr.search.keywords.Keyword"%>
<%@ page import="java.util.List"%>
<%@ page session="false"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<title>Smart Search Engine</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="<c:url value="/resources/view/css/foundation.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/view/css/graph.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/view/css/search.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/view/graph/jquery-1.4.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/view/graph/raphael-min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/view/graph/dracula_graffle.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/view/graph/dracula_graph.js"/>"></script>
</head>
<body>
	<div class="row">
		<div class="large-12 columns">
			<h1>
				<small>Smart Search Engine</small>
			</h1>
			<hr />
		</div>
	</div>

	<div class="row">
		<div class="large-6 columns" role="content">
			<div id="canvas"></div>
		</div>
		<aside class="large-6 columns">
			<div class="panel">
				<div>
					<form method="get" accept-charset="UTF-8">
						<div class="small-8">
							<input type="text" name="search-input">
						</div>
						<button type="submit">Szukaj!</button>
					</form>
				</div>
				<div id="search-results">
					<c:forEach items="${results}" var="result">
						<div class="result">
							<a href="${result.url}">${result.title}</a>
							<div class="link">${result.url}</div>
							<div class="description">${result.description}</div>
						</div>
					</c:forEach>
				</div>

			</div>
		</aside>
	</div>
	<footer class="row">
		<div class="large-12 columns">
			<hr />
			<div class="row">
				<div class="large-6 columns">
					<p>AGH, IOSR 2015</p>
				</div>
			</div>
		</div>
	</footer>
	<script type="text/javascript" src="<c:url value="/resources/view/graph.js"/>"></script>
	<script src="resources/view/js/vendor/jquery.js"></script>
	<script src="resources/view/js/foundation.min.js"></script>
	<script>
		var keywords = new Array();
		<c:forEach items="${keywords}" var="keyword" varStatus="status"> 
			var k = "${keyword}";
			keywords.push(k);
	    </c:forEach> 

		$(document).ready(function() {
			makeGraphRequest(keywords);
		});
		
		$(document).foundation();
	</script>
</body>
</html>



