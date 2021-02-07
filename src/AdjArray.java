import utils.Result;
import utils.Triangle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class AdjArray {
    public final static int nbIterationDiametre = 10;
    private final Map<Long, Result<List<Long>, Integer>> adjArray= new HashMap<>();
    private final List<Integer> diameter;

    AdjArray(String input){
        try ( Stream<String> stream = Files.lines(Paths.get(input)) ) {
            stream.forEach( line ->{
                if(!(line.charAt(0)=='#')){
                    String[] temp = line.split("\t");
                    if(temp.length==2){
                        Long a = Long.parseLong(temp[0]);
                        Long b = Long.parseLong(temp[1]);
                        if(adjArray.get(a)==null) adjArray.put(a, new Result<>(new ArrayList<>(), adjArray.size()));
                        adjArray.get(a).getKey().add(b);
                        if(adjArray.get(b)==null) adjArray.put(b, new Result<>(new ArrayList<>(), adjArray.size()));
                        adjArray.get(b).getKey().add(a);
                        System.out.println("Taille " + adjArray.size());
                    }
                }
            });
        }catch(IOException ignored){}
        diameter = new ArrayList<>(Collections.nCopies(adjArray.size(), 0));
    }

    public int getNodesNumber(){
        return adjArray.size();
    }

    public int getEdgesNumber(){
        int res = 0;
        for (Long l: adjArray.keySet()
             ) {
            res+=adjArray.get(l).getKey().size();
        }
        return res/2;
    }

    public Long BFS(long i){
        resetDiameter();
        Queue<Long> fifo = new ArrayDeque<>();
        Set<Long> mark = new HashSet<>();
        fifo.add(i);
        mark.add(i);
        Long u = null;
        while (!fifo.isEmpty()) {
            u = fifo.remove();
            for (Long e : adjArray.get(u).getKey()) {
                if (!mark.contains(e)) {
                    fifo.add(e);
                    mark.add(e);
                    int temp = diameter.get(adjArray.get(e).getElem());
                    temp += diameter.get(adjArray.get(u).getElem())+1;
                    diameter.set(adjArray.get(e).getElem(), temp);
                }
            }
        }
        return u;
    }

    public long getRandomNode(){
        Random rand = new Random();
        Object[] temp = adjArray.keySet().toArray();
        return (Long)temp[rand.nextInt(temp.length)];
    }

    public int getDiameter(long u){
        return diameter.get(adjArray.get(u).getElem());
    }

    public int getMaxDiameter(){
        Long res = BFS(getRandomNode());
        int temp = 0;
        for(int i=0; i<nbIterationDiametre; i++){
            temp = Integer.max(temp, getDiameter(res));
            res = BFS(res);
        }
        return temp;
    }

    private void resetDiameter() {
        for (int i=0; i<diameter.size(); i++){
            diameter.set(i,0);
        }
    }

    public List<Triangle> getTriangles(){
        List<Triangle> res = new ArrayList<>();
        sortAllLists();
        nonOriented2Oriented();
        return res;
    }

    private void nonOriented2Oriented() {
        for (Map.Entry<Long, Result<List<Long>, Integer>> a:
             adjArray.entrySet()) {
            for (Long l:
                 adjArray.get(a).getKey()) {
                adjArray.get(l).getKey().remove(a);
            }
        }
    }

    public void sortAllLists(){
        for (Map.Entry<Long, Result<List<Long>, Integer>> l:
                adjArray.entrySet()) {
            Collections.sort(l.getValue().getKey());
        }
    }
}
