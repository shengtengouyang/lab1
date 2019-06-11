public class ArrayDeque<T> {
    private int size;
    private int front;
    private int next;
    T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        next = 0;
    }

    public ArrayDeque(T item) {
        items = (T[]) new Object[8];
        items[0] = item;
        size = 1;
        next = 1;
        front = 7;
    }

    public void addFirst(T item) {
        if (front == -1) {
            front = items.length - 1;
        }
        if (front == next) {
            resize(size * 2);
        }
        items[front] = item;
        front = front - 1;
        size++;
        resize(items.length);
    }

    public void addLast(T item) {
        if (next == items.length) {
            next = 0;
        }
        if (front == next) {
            resize(size * 2);
        }
        items[next] = item;
        next = next + 1;
        size++;
        resize(items.length);
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
        front = front + 1;
        if (front == items.length) {
            front = 0;
        }
        T item = items[front];
        size--;
        items[front] = null;
        if ((double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        resize(items.length);
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        next = next - 1;
        if (next == -1) {
            next = items.length - 1;
        }
        T item = items[next];
        size--;
        items[next] = null;
        if ((double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        resize(items.length);
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
        int start;
        if (front + 1 == items.length) {
            start = 0;
        } else {
            start = front + 1;
        }
        if ((start + size()) > items.length) {
            System.arraycopy(items, start, newItems, 0, items.length - start);
            System.arraycopy(items, 0, newItems, items.length - start, start + size() - items.length);
        } else {
            System.arraycopy(items, start, newItems, 0, size());
        }
        front = size - 1;
        next = size();
        items = newItems;
    }
}
