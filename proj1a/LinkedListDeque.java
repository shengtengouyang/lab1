public class LinkedListDeque<T> {
    private class LinkedNode{
        private T item;
        private LinkedNode prev;
        private LinkedNode next;
        private LinkedNode(){}
        private LinkedNode(T item,LinkedNode prev,LinkedNode next){
            this.item=item;
            this.prev=prev;
            this.next=next;
        }
    }
    private int size;
    private LinkedNode sentinel;
    public LinkedListDeque(){
        size=0;
        sentinel=new LinkedNode();
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
    }
    public LinkedListDeque(T item){
        size=1;
        sentinel=new LinkedNode();
        sentinel.next=new LinkedNode(item,sentinel,sentinel);
        sentinel.prev=sentinel.next;
    }
    public void addFirst(T items) {
        sentinel.next=new LinkedNode(items,sentinel,sentinel.next);
        size++;
    }

    public void addLast(T item) {
        sentinel.prev=new LinkedNode(item, sentinel.prev, sentinel);
        size++;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        LinkedNode current=sentinel.next;
        for(int i=0;i<size;i++){
            System.out.println(current.item+" ");
            current=current.next;
        }
    }

    public T removeFirst() {
        LinkedNode x=sentinel.next;
        sentinel.next=sentinel.next.next;
        sentinel.next.prev=sentinel;
        size--;
        return x.item;
    }

    public T removeLast() {
        LinkedNode x=sentinel.prev;
        sentinel.prev=sentinel.prev.prev;
        sentinel.prev.next=sentinel;
        size--;
        return x.item;
    }

    public T get(int index) {
        if (index>=size||index<0){
            return null;
        }
        LinkedNode current=sentinel.next;
        while(index>0){
            current=current.next;
            index--;
        }
        return current.item;
    }

    public T getRecursive(int index){
        if (index>=size||index<0){
            return null;
        }
        return getRecursiveHelper(index,sentinel.next);
    }

    public T getRecursiveHelper(int position,LinkedNode node){
        if(position==0){
            return node.item;
        }
        return getRecursiveHelper(position-1,node.next);
    }
}
