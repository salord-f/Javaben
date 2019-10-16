package javaben.mapping;

public abstract class Network {
        private int verticesCount;
        private int edgesCount;

        public abstract void readFile(String filepath);

    public int getVerticesCount() {
        return verticesCount;
    }

    public void setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;
    }

    public int getEdgesCount() {
        return edgesCount;
    }

    public void setEdgesCount(int edgesCount) {
        this.edgesCount = edgesCount;
    }
}
