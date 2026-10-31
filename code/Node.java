//Walid Halabi 446109612
public class Node<T>
{
    public T data;
    public Node<T> next;
    public Node<T> prev;

    public Node()//O(1)
    {
        this(null);
    }

    public Node(T data)//O(1)
    {
        this.data = data;
        next = null;
        prev = null;
    }
}