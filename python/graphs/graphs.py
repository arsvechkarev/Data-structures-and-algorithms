import random


def generate_simple_graph(nodes_num, min_weight=1, max_weight=40,
    max_edges_at_node=4):
    if nodes_num < 1 or nodes_num > 26:
        raise ValueError("nodes_num should be in range 1..26")
    graph = {}
    for code in range(65, 65 + nodes_num):
        graph_inner = _create_graph(code, min_weight, max_weight,
                                   max_edges_at_node)
        graph[chr(code)] = graph_inner
    for edge in graph:
        inner_dict = graph[edge]
        for inner_key in inner_dict:
            if graph.get(inner_key) is not None:
                graph[inner_key][edge] = inner_dict[inner_key]
    return graph


def _create_graph(letter_code, min_weight, max_weight, max_edges):
    graph_inner = {}
    edges = random.randint(1, max_edges)
    start_letter_code = letter_code - (edges // 2)
    start_range = max(start_letter_code, 65)
    end_range = min(start_range + edges, 90)
    for inner_edge_code in range(start_range, end_range + 1):
        if inner_edge_code == letter_code:
            continue
        graph_inner[chr(inner_edge_code)] = random.randint(min_weight,
                                                           max_weight)
    return graph_inner