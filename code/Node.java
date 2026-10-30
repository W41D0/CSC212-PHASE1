//Walid Halabi 446109612
public class Node<T>
{
    public T data;
    public Node<T> next;
    public Node<T> prev;

    public Node()
    {
        this(null);
    }

    public Node(T data)
    {
        this.data = data;
        next = null;
        prev = null;
    }
}