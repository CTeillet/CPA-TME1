import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ListEdges {
    Set<Edges> edges = new LinkedHashSet<>();
    Set<Long> nodes = new LinkedHashSet<>();

    ListEdges(String input) {
        try (Stream<String> stream = Files.lines(Paths.get(input))) {
            stream.forEach( line ->{
                if(!(line.charAt(0)=='#')){
                    String[] temp = line.split("\t");
                    if(temp.length==2){
                        long a = Long.parseLong(temp[0]);
                        long b = Long.parseLong(temp[1]);
                        nodes.add(a);
                        nodes.add(b);
                        edges.add(new Edges(a, b));
                    }
                }
            });
        }catch(IOException e){

        }
    }

    public int getEdgesNumber() {
        return edges.size();
    }

    public int getNodesNumber() {
        return nodes.size();
    }
}
