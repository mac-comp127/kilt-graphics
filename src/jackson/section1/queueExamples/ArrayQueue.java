package jackson.section1.queueExamples;

import java.util.Queue;

public class ArrayQueue<T> {

    private int front;
    private int rear;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;

    public ArrayQueue(int initialCapacity){
        data = (T[]) new Object[initialCapacity];
        front = 0;
        rear = initialCapacity-1;
        size = 0;
    }

    public ArrayQueue(){
        this(DEFAULT_CAPACITY);
    }


    public boolean offer(T item){
        if (size == data.length){
            expand();
        }
        size++;
        rear = (rear + 1) % data.length;
        data[rear] = item;
        return true;
    }


    public T poll() {
        if(size ==0){
            return null;
        }
        T item = data[front];
        front = (front+1) % data.length;
        size--;
        return item;
    }


    public T peek() {
        if(size == 0) {
            return null;
        }
        return data[front];
    }

    private void expand(){
        int newCapacity = 2 * data.length;
        T[] newData = (T[]) new Object[newCapacity];
        int j = front;
        for(int i=0; i < size; i++){
            newData[i] = data[j];
            j = (j+1) % data.length;
        }
        front = 0;
        rear = size+1;
        data = newData;
    }
}
