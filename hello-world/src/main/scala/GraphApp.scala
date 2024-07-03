import zio._
import zio.console._
import core.GraphOps._

object GraphApp extends App {
  def run(args: List[String]) = appLogic.exitCode

  val graph = DirectedGraph(Map(
    1 -> Set((2, "a"), (3, "b")),
    2 -> Set((3, "c")),
    3 -> Set((4, "d")),
    4 -> Set()
  ))

  val appLogic = for {
    _ <- putStrLn("DFS Traversal: " + dfs(graph, 1).mkString(", "))
    _ <- putStrLn("BFS Traversal: " + bfs(graph, 1).mkString(", "))
    _ <- putStrLn("Topological Sort: " + topologicalSort(graph).mkString(", "))
    _ <- putStrLn("Has Cycle: " + hasCycle(graph))
  } yield ()
}
