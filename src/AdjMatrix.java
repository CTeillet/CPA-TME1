import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AdjMatrix {
    private List<Long> list = new ArrayList();
    private List<List<Integer>> matrix = new ArrayList();

    AdjMatrix(String input){
        try (Stream<String> stream = Files.lines(Paths.get(input))) {
            stream.forEach( line ->{
                if(!(line.charAt(0)=='#')){
                    String[] temp = line.split("\t");
                    if(temp.length==2){
                        long a = Long.parseLong(temp[0]);
                        long b = Long.parseLong(temp[1]);
                        if(!list.contains(a))list.add(a);
                        if(!list.contains(b))list.add(b);
                    }
                }
            });
        }catch(IOException e){

        }
    }

}
