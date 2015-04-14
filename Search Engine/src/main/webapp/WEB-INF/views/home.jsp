<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.iosr.search.keywords.Keyword"%>
<%@ page import="java.util.List" %>
<%@ page session="false"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<title>Smart Search Engine</title>
<meta charset="utf-8" />

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="<c:url value="/resources/view/css/foundation.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/view/css/graph.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/graph/jquery-1.4.2.min.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/view/graph/raphael-min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/view/graph/dracula_graffle.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/view/graph/dracula_graph.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/view/graph/dracula_algorithms.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/view/graph.js"/>"></script>
<!-- example data -->
<script type="text/javascript">
          var exampleData = {
              "name":"rootNode",
              "children":[
                  {
                      "name":"child1",
                      "children":[
                          {
                              "name":"child11",
                              "children":[
                                  {
                                      "name":"child111"
                                  }
                              ]
                          },
                          {
                              "name":"child12"
                          },
                          {
                              "name":"child13"
                          }
                      ]
                  },
                  {
                      "name":"child2",
                      "children":[
                          {
                              "name":"child21",
                              "children":[
                                  {
                                      "name":"child211"
                                  }
                              ]
                          },
                          {
                              "name":"child22"
                          }
                      ]
                  },
                  {
                      "name":"child3",
                      "children":[
                          {
                              "name":"child31"
                          }
                      ]
                  }
              ]
          }

      </script>
<script src="/resources/view/js/vendor/modernizr.js"></script>
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
			<div>
				<button onclick=drawGraph(exampleData)>Load Example Graph</button>
			</div>
			<div id="canvas"></div>

		</div>

		<aside class="large-6 columns">

			<div class="panel">
				<form method="post">
					<div class="row small-9">
						<div class="small-9">
							<input type="text" name="search-input" placeholder="Search input here!">
							<button type="submit">"Szukaj!"</button>
						</div>
					</div>
					<div id="search-results">
						<c:forEach items="${keywords}" var="keyword">
							${keyword.baseWord}
						</c:forEach>
					</div> 
				</form>
				<h5>The time on the server is ${serverTime}.</h5>
				<h5>Search result list</h5>
				<div id="list"></div>
			</div>
		</aside>

	</div>


	<footer class="row">
		<div class="large-12 columns">
			<hr />
			<div class="row">
				<div class="large-6 columns">
					<p>© Copyright no one at all. Go to town.</p>
				</div>
				<div class="large-6 columns">
					<ul class="inline-list right">
						<li><a href="#">Link 1</a></li>
						<li><a href="#">Link 2</a></li>
						<li><a href="#">Link 3</a></li>
						<li><a href="#">Link 4!</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>


	<script src="<c:url value="/resources/view/js/vendor/jquery.js"/>"></script>
	<script src="<c:url value="/resources/view/js/foundation.min.js"/>"></script>
	<script>
      $(document).foundation();
  </script>
</body>
</html>



