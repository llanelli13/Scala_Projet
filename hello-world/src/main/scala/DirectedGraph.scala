case class DirectedGraph[V, E](adjacencyList: Map[V, Set[(V, E)]]) extends Graph[V, E] {
  def vertices: Set[V] = adjacencyList.keySet
  def edges: Set[E] = adjacencyList.values.flatten.map(_._2).toSet
  def neighbors(vertex: V): Set[V] = adjacencyList.getOrElse(vertex, Set.empty).map(_._1)

  def addEdge(edge: (V, V, E)): DirectedGraph[V, E] = {
    val (from, to, e) = edge
    val updatedList = adjacencyList.updated(from, adjacencyList.getOrElse(from, Set.empty) + ((to, e)))
    copy(adjacencyList = updatedList)
  }

  def removeEdge(edge: (V, V, E)): DirectedGraph[V, E] = {
    val (from, to, e) = edge
    val updatedList = adjacencyList.updated(from, adjacencyList.getOrElse(from, Set.empty) - ((to, e)))
    copy(adjacencyList = updatedList)
  }
}
