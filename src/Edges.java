import java.util.Objects;

public class Edges {
    private long a;
    private long b;

    public Edges(long a, long b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edges edges = (Edges) o;
        return a == edges.a && b == edges.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
