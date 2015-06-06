function makeGraphRequest(keywords) {

	if (keywords == undefined || keywords == "") {
		return;
	}

	$.ajax({
		// developujac lokanie ten url trzeba zmienic na localhost
		// 172.17.84.84:8181/search-1.0.0-BUILD-SNAPSHOT/
		url : "http://localhost:8080/search/rest/graph/" + keywords,
		headers : {
			Accept : "application/json; charset=utf-8",
		},
		type : 'GET',
		accepts : "application/json",
		dataType : 'json',
		success : function(data) {
			drawGraph(data);
		}
	});
}

function drawGraph(graphData) {
	var width = $("#canvas").width();
	var height = 0.75 * $(document).height();
	var g = new Graph();
	g.edgeFactory.template.style.directed = false;

	g.addNode(graphData.name);
	drawChildren(g, graphData)

	var layouter = new Graph.Layout.Spring(g);
	var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);

	$("ellipse").each(function() {
		var flag = 0;
		this.addEventListener("mousedown", function() {
			flag = 0;
		}, false);
		this.addEventListener("mousemove", function() {
			flag = 1;
		}, false);
		this.addEventListener("mouseup", function() {
			if (flag === 0) {
				clickCallback(g, $(this).attr('id'))
			}
		}, false);
	});
}

function drawChildren(g, node) {
	if (node.children == null)
		return;

	for ( var i in node.children) {
		console.log("Connecting parent " + node.name + " with child: "
				+ node.children[i].name);
		g.addEdge(node.name, node.children[i].name);
		drawChildren(g, node.children[i]);
	}
}

function clickCallback(graph, nodeName) {
	console.log("Clicked node: " + nodeName);
	window.location.replace(window.location.href + "+" + nodeName);
}
