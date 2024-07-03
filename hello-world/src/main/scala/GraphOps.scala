object GraphOps {
  def dfs[V, E](graph: Graph[V, E], start: V): Set[V] = {
    def visit(vertex: V, visited: Set[V]): Set[V] = {
      if (visited.contains(vertex)) visited
      else {
        val neighbors = graph.neighbors(vertex)
        neighbors.foldLeft(visited + vertex)((acc, n) => visit(n, acc))
      }
    }
    visit(start, Set.empty)
  }
}
// pour les recherche en largeur
object GraphOps {
  def bfs[V, E](graph: Graph[V, E], start: V): Set[V] = {
    def visit(queue: List[V], visited: Set[V]): Set[V] = queue match {
      case Nil => visited
      case vertex :: tail =>
        if (visited.contains(vertex)) visit(tail, visited)
        else {
          val neighbors = graph.neighbors(vertex)
          visit(tail ++ neighbors, visited + vertex)
        }
    }
    visit(List(start), Set.empty)
  }
}
// Pour le tri topologique
object GraphOps {
  def topologicalSort[V, E](graph: Graph[V, E]): List[V] = {
    def visit(vertex: V, visited: Set[V], stack: List[V]): (Set[V], List[V]) = {
      if (visited.contains(vertex)) (visited, stack)
      else {
        val neighbors = graph.neighbors(vertex)
        val (newVisited, newStack) = neighbors.foldLeft((visited + vertex, stack)) {
          case ((vis, stk), n) => visit(n, vis, stk)
        }
        (newVisited, vertex :: newStack)
      }
    }
    val vertices = graph.vertices.toList
    vertices.foldLeft((Set.empty[V], List.empty[V])) {
      case ((visited, stack), vertex) => visit(vertex, visited, stack)
    }._2
  }
}
// Pour les détection de cycles
object GraphOps {
  def hasCycle[V, E](graph: Graph[V, E]): Boolean = {
    def visit(vertex: V, visited: Set[V], recStack: Set[V]): Boolean = {
      if (recStack.contains(vertex)) true
      else if (visited.contains(vertex)) false
      else {
        val neighbors = graph.neighbors(vertex)
        neighbors.exists(visit(_, visited + vertex, recStack + vertex))
      }
    }
    val vertices = graph.vertices
    vertices.exists(visit(_, Set.empty, Set.empty))
  }
}
