public class LinkedList<T> implements List<T>
{
    protected int size = 0;
    protected Node<T> head;
    protected Node<T> current;
    protected Node<T> tail;

    public LinkedList () 
    {
        head = current = tail = null;
    }

    public boolean empty () 
    {
        return head == null;
    }

    public boolean last () 
    {
        return current == tail;
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
        if (current != null) current = current.next; 
    }    

    public void findPrevious () 
    {
        if (current != null) current = current.prev;
    }
    
    public void findLast () 
    {
        current = tail;
    }

    public void findAt(int index)
    {
        if (empty()) return;
        
        if (index <= 0)
        {
            current = head;
            return;
        }
        
        if (index >= size - 1)
        {
            current = tail;
            return;
        }
        
        current = head;
        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }
    }

    public T retrieve () 
    {
        return current != null ? current.data : null;
    }

    public void update (T data) 
    {
        if (current != null) current.data = data;
    }

    public void insertHead(T data)
    {
        Node<T> tmp = new Node<>(data);
        if (empty())
        {
            head = tail = current = tmp;
        }
        else
        {
            tmp.next = head;
            head.prev = tmp;
            head = tmp;
        }
        size++;
    }

    public void insertTail(T data)
    {
        Node<T> tmp = new Node<>(data);
        if (empty())
        {
            head = tail = current = tmp;
        }
        else
        {
            tail.next = tmp;
            tmp.prev = tail;
            tail = tmp;
        }
        size++;
    }

    public void insert(T data)
    {
        if (empty())
        {
            insertHead(data);
            return;
        }
        Node<T> tmp = new Node<>(data);

        tmp.next = current.next;
        tmp.prev = current;

        if (last())
            tail = tmp;
        else 
            current.next.prev = tmp;

        current.next = tmp;

        findNext();
        size++;
    }

    public void insertAt(T data, int index)
    {
        if (empty() || index <= 0)
        {
            insertHead(data);
            return;
        }
        
        if (index >= size)
        {
            insertTail(data);
            return;
        }
        
        Node<T> tmp = head;
        for (int i = 0; i < index - 1; i++)
        {
            tmp = tmp.next;
        }
        
        Node<T> newNode = new Node<>(data);
        
        newNode.next = tmp.next;
        newNode.prev = tmp;
        
        tmp.next.prev = newNode;
        tmp.next = newNode;
        
        size++;
    }

    public void removeHead()
    {
        if (empty()) return;
        if (head == tail)
        {
            head = tail = current = null;
        }
        else
        {
            if (current == head) current = head.next;
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    public void removeTail()
    {
        if (empty()) return;
        if (head == tail)
        {
            head = tail = current = null;
        }
        else
        {
            if (current == tail) current = tail.prev;
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public void removeInfront()
    {
        if (empty() || current == head) return;
        
        if (current.prev == head)
        {
            removeHead();
            return;
        }
        
        Node<T> toRemove = current.prev;
        toRemove.prev.next = current;
        current.prev = toRemove.prev;
        size--;
    }

    public void removeBehind()
    {
        if (empty() || current == tail) return;
        
        if (current.next == tail)
        {
            removeTail();
            return;
        }
        
        Node<T> toRemove = current.next;
        current.next = toRemove.next;
        toRemove.next.prev = current;
        size--;
    }

    public void remove () 
    {
        if (empty()) return;
        
        if (current == head)
        {
            removeHead();
            return;
        }
        if (current == tail)
        {
            removeTail();
            return;
        }
        
        current.prev.next = current.next;
        current.next.prev = current.prev;
        current = current.next;
        size--;
    }

    public void removeAt(int index)
    {
        if (empty()) return;
        
        if (index <= 0)
        {
            removeHead();
            return;
        }
        
        if (index >= size - 1)
        {
            removeTail();
            return;
        }
        
        Node<T> tmp = head;
        for (int i = 0; i < index; i++)
        {
            tmp = tmp.next;
        }
        
        if (current == tmp) 
        {
            current = tmp.next;
        }

        tmp.prev.next = tmp.next;
        tmp.next.prev = tmp.prev;
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

    public Node<T> getTail()
    {
        return tail;
    }

    public void setHead(Node<T> head)
    {
        this.head = head;
    }

    public void setCurrent(Node<T> current)
    {
        this.current = current;
    }

    public void setTail(Node<T> tail)
    {
        this.tail = tail;
    }

    public int size()
    {
        return size;
    }
}