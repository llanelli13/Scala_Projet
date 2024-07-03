case class UndirectedGraph[V, E](adjacencyList: Map[V, Set[(V, E)]]) extends Graph[V, E] {
  def vertices: Set[V] = adjacencyList.keySet
  def edges: Set[E] = adjacencyList.values.flatten.map(_._2).toSet
  def neighbors(vertex: V): Set[V] = adjacencyList.getOrElse(vertex, Set.empty).map(_._1)

  def addEdge(edge: (V, V, E)): UndirectedGraph[V, E] = {
    val (v1, v2, e) = edge
    val updatedList1 = adjacencyList.updated(v1, adjacencyList.getOrElse(v1, Set.empty) + ((v2, e)))
    val updatedList2 = updatedList1.updated(v2, updatedList1.getOrElse(v2, Set.empty) + ((v1, e)))
    copy(adjacencyList = updatedList2)
  }

  def removeEdge(edge: (V, V, E)): UndirectedGraph[V, E] = {
    val (v1, v2, e) = edge
    val updatedList1 = adjacencyList.updated(v1, adjacencyList.getOrElse(v1, Set.empty) - ((v2, e)))
    val updatedList2 = updatedList1.updated(v2, updatedList1.getOrElse(v2, Set.empty) - ((v1, e)))
    copy(adjacencyList = updatedList2)
  }
}
