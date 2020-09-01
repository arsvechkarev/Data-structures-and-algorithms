def depth_first_search(graph, start_node, on_node_visited):
    """
    Performs a depth-first search on a given graph

    :param graph: Graph as an adjacency list to perform search on
    :param start_node: Node to start with
    :param on_node_visited: Lambda that is called for every visited node
    """

    visited = []
    _dfs_recursive(graph, visited, start_node, on_node_visited)


def _dfs_recursive(graph, visited, node_name, on_node_visited):
    if node_name in visited:
        return
    visited.append(node_name)
    on_node_visited(node_name, graph[node_name])
    neighbours = graph[node_name]
    for n in neighbours:
        _dfs_recursive(graph, visited, n, on_node_visited)


example_graph = {
    'A': {'B': 4, 'C': 3},
    'B': {'A': 4, 'G': 2},
    'C': {'A': 3, 'D': 5, 'E': 8, 'F': 7},
    'D': {'C': 5, 'E': 1},
    'E': {'C': 8, 'D': 1, 'H': 6, 'I': 5},
    'F': {'C': 7},
    'G': {'B': 2, 'H': 1},
    'H': {'G': 1, 'I': 4},
    'I': {'E': 5, 'H': 4, 'J': 3, 'K': 9},
    'J': {'I': 3, 'K': 2},
    'K': {'I': 9, 'J': 2}
}

depth_first_search(
    graph=example_graph,
    start_node='A',
    on_node_visited=
    lambda node, neighbours: print(str(node) + " -> " + str(neighbours.keys()))
)
