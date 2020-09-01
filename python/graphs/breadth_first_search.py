import graphs


def breadth_first_search(graph, node, on_node_visited):
    queue = []
    visited = []
    queue.append(node)
    visited.append(node)

    while queue:
        node = queue.pop(0)
        on_node_visited(node, graph[node])

        for neighbour in graph[node]:
            if neighbour not in visited:
                queue.append(neighbour)
                visited.append(neighbour)


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

breadth_first_search(
    graph=example_graph,
    node='A',
    on_node_visited=
    lambda node, neighbours: print(str(node) + " -> " + str(neighbours.keys()))
)
