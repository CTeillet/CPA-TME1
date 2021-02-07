package utils;

public class Result <T, E>{
    private T key;
    private E elem;

    public Result(T res1, E res2) {
        this.key = res1;
        this.elem = res2;
    }

    public T getKey() {
        return key;
    }

    public E getElem() {
        return elem;
    }
}
