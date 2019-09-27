package dataStructures;

public class Mstack<T> {

    private Object[] objects;
    private int top;


    public Mstack() {
        this.objects = new Object[100];
        this.top = -1;
    }

    public void push(T t) {
        top++;
        if (top == objects.length) moreSpace();
        objects[top] = t;
    }

    public void moreSpace() {
        int size = 2* objects.length;
        Object[] biggerArr = new Object[size];
        for (int i = 0; i < objects.length; i++) biggerArr[i] = objects[i];
        objects = biggerArr;
    }

    public T pop() {
        if (isEmpty()) return null;
        T t = (T) objects[top];
        top--;
        return t;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top+1;
    }
}
