public class Edge {

    private int weight;
    private boolean isIncluded = false;

}

public class Vertex {

    private String label = null;
    private Map<Vertex, Edge> edges = new HashMap<>();
    private boolean isVisited = false;

}

public class Prim {

    private List<Vertex> graph;

    public void run() {
        if (graph.size() > 0) {
            graph.get(0).setVisited(true);
        }
        while (isDisconnected()) {
            Edge nextMinimum = new Edge(Integer.MAX_VALUE);
            Vertex nextVertex = graph.get(0);
            for (Vertex vertex : graph) {
                if (vertex.isVisited()) {
                    Pair<Vertex, Edge> candidate = vertex.nextMinimum();
                    if (candidate.getValue().getWeight() < nextMinimum.getWeight()) {
                        nextMinimum = candidate.getValue();
                        nextVertex = candidate.getKey();
                    }
                }
            }
            nextMinimum.setIncluded(true);
            nextVertex.setVisited(true);
        }
    }

    private boolean isDisconnected() {
        for (Vertex vertex : graph) {
            if (!vertex.isVisited()) {
                return true;
            }
        }
        return false;
    }

    public Pair<Vertex, Edge> nextMinimum() {
        Edge nextMinimum = new Edge(Integer.MAX_VALUE);
        Vertex nextVertex = this;
        Iterator<Map.Entry<Vertex,Edge>> it = edges.entrySet()
            .iterator();
        while (it.hasNext()) {
            Map.Entry<Vertex,Edge> pair = it.next();
            if (!pair.getKey().isVisited()) {
                if (!pair.getValue().isIncluded()) {
                    if (pair.getValue().getWeight() < nextMinimum.getWeight()) {
                        nextMinimum = pair.getValue();
                        nextVertex = pair.getKey();
                    }
                }
            }
        }
        return new Pair<>(nextVertex, nextMinimum);
    }

    

}


