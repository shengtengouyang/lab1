public class ArrayDeque<T> {
    private int size;
    private int front;
    private int next;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        next = 0;
    }

    public void addFirst(T item) {
        if (size==items.length) {
            resize(size * 2);
        }
        if(isEmpty()){
            next=correct(next+1);
        }
        items[front] = item;
        front = correct(front - 1);
        size++;
    }

    public void addLast(T item) {
        if (size==items.length) {
            resize(size * 2);
        }
        if(isEmpty()){
            front=correct(front-1);
        }
        items[next] = item;
        next = correct(next + 1);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int start = front + 1;
        for (int i = start; i < start + size; i++) {
            int ptr = i % items.length;
            System.out.print(get(ptr) + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        front = correct(front + 1);
        if(next==correct(front+1)){
            next=front;
        }
        T item = items[front];
        size--;
        items[front] = null;
        if ((double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        next = correct(next - 1);
        if(front==correct(next-1)){
            front=next;
        }
        T item = items[next];
        size--;
        items[next] = null;
        if ((double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index > items.length) {
            return null;
        } else {
            return items[index];
        }
    }

    private void resize(int size) {
        if (size == 0) {
            size = 8;
        }
        T[] newItems = (T[]) new Object[size];
        int start=correct(front+1);
        if ((start + size()) > items.length) {
            System.arraycopy(items, start, newItems, 0, items.length - start);
            System.arraycopy(items, 0, newItems, items.length - start, start + size() - items.length);
        } else {
            System.arraycopy(items, start, newItems, 0, size());
        }
        if(front==next){
            front=0;
            next=0;
        }
        else{
            front = size - 1;
            next = size();
        }
        items = newItems;
    }
    private int correct(int index){
        if(index==-1){
            index=items.length-1;
        }
        else if(index==items.length){
            index=0;
        }
        return index;
    }
}
