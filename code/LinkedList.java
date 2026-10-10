public class LinkedList<T> implements List<T>
{
    protected int size = 0;
    protected Node<T> head;
    protected Node<T> current;

    public LinkedList () 
    {
        head = current = null;
    }

    public boolean empty () 
    {
        return head == null;
    }
    public boolean last () 
    {
        return current.next == null;
    }
    public boolean full () 
    {
        return false;
    }
    public void findFirst () 
    {
        current = head;
    }
    public void findNext () 
    {
        current = current.next; 
    }    
    public T retrieve () 
    {
        return current.data;
    }
    public void update (T data) 
    {
        current.data = data;
    }

    public void insert (T data) 
    {
        Node<T> tmp;
        if (empty()) 
        {
            current = head = new Node<T> (data);
        }
        else   
        {
            tmp = current.next; 
            current.next = new Node<T> (data);
            current = current.next;
            current.next = tmp;
        }

        size++;
    }

    public void insertAt(T data, int index)
    {
        if (empty())
        {
            insert(data);
            return;
        }

        findFirst();
        for (int i = 0; i <= index; i++)
        {
            if(!last())
                findNext();
            else
                break;
        }

        insert(data);
    }

    public void remove () 
    {
        if (current == head) 
        {
            head = head.next;
        }
        else 
        {
            Node<T> tmp = head;
            while (tmp.next != current)
            tmp = tmp.next;
            tmp.next = current.next;
        }
    
        if (current.next == null)
            current = head;
        else
            current = current.next;

        size--;
    }

    public Node<T> getHead()
    {
        return head;
    }

    public Node<T> getCurrent()
    {
        return current;
    }

    public void setHead(Node<T> head)
    {
        this.head = head;
    }

    public void setCurrent(Node<T> current)
    {
        this.current = current;
    }
}