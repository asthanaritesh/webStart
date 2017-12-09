<%@page import="com.webstart.algo.Graph"%>
<%Graph GraphObj = new Graph();%>
<html>
<body>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script src="../JS/springy.js"></script>
<script src="../JS/springyui.js"></script>

<!--  This is just a sample on how to create button and call Java -->
<script>
function FirstNode() {
   var name =  "<%= GraphObj.getNodesList().get(1).getName()%>";
   alert("First Node Name is:\r\n" + name);
}
</script>
<form>
<input type="button" value="First Node"
   onClick = "FirstNode()">
</form>

<!-- This is actual script -->
<script>
var graph = new Springy.Graph();
var nNodesCount = "<%= GraphObj.getNodesCount()%>";
alert("number of node is " + nNodesCount);

var nodeArray = <%= GraphObj.toJSNodeArray() %>;
for (var i = 0; i < nodeArray.length; i++) {
	document.write("<br />Current Node : " + nodeArray[i] );
	eval("var "+nodeArray[i]+" = "+"graph.newNode({label: '"+nodeArray[i]+"'});");
	//window[nodeArray[i]] = graph.newNode({label: nodeArray[i]});
}

var edgeArray = <%= GraphObj.toJSEdgeArray() %>;
for (var i = 0; i < edgeArray.length; i=i++) {
	var col = '#'+Math.floor(Math.random()*16777215).toString(16);
	graph.newEdge(window[edgeArray[i++]], window[edgeArray[i++]], {color:col, weight:edgeArray[i++]});
}

jQuery(function(){
  var springy = window.springy = jQuery('#springydemo').springy({
    graph: graph,
    nodeSelected: function(node){      
    }
  });
});
</script>

<canvas id="springydemo" width="640" height="480" />

</body>
</html>
