function loadExampleGraph() {
    var width = $(document).width();
    var height = $(document).height();
    var g = new Graph();
    g.edgeFactory.template.style.directed = false;
    g.addEdge("CS 31","CS 32");
    g.addEdge("CS 32","CS 33");
    g.addEdge("CS 33","CS 35L");
    g.addNode("CS M51A");
    g.addEdge("CS 32", "CS 111");
    g.addEdge("CS 33", "CS 111");
    g.addEdge("CS 35L", "CS 111");
    g.addEdge("CS 32", "CS 118");
    g.addEdge("CS 33", "CS 118");
    g.addEdge("CS 35L", "CS 118");
    g.addEdge("CS 111", "CS 118");
    g.addEdge("CS 32", "CS 131");
    g.addEdge("CS 33", "CS 131");
    g.addEdge("CS 35L", "CS 131");
    var layouter = new Graph.Layout.Ordered(g, topological_sort(g));
    var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
};


/**
 * Example JSON:
 * {
   "root":"rootNode",
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
 *
 * @param graphData
 */
function drawGraph(graphData) {
    var width = $("#canvas").width();
    var height = 0.75*$(document).height();
    var g = new Graph();
    g.edgeFactory.template.style.directed = false;

    g.addNode(graphData.name);
    drawChildren(g, graphData)

    var layouter = new Graph.Layout.Spring(g);
    var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
    $("ellipse").each(function() {
        $(this).attr("onclick", "clickCallback($(this).attr('id'))")
    });
}

function drawChildren(g, node) {
    if (node.children == null) return;

    for (var i in node.children) {
        console.log("Connecting parent "+node.name+" with child: "+node.children[i].name);
        g.addEdge(node.name, node.children[i].name);
        drawChildren(g, node.children[i]);
    }
}

function clickCallback(nodeName) {
    $("#list").append("<p> Clicked node: "+nodeName+"</p>")
}