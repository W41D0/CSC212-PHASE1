// Student Name : Nawaf Turki Alqusair
// Student Id :446107045
public class DriverList  implements IDriverList
{
	private Node<IDriver> head;
    private int size;
	//o(1) time complixity
    public DriverList() {
    	head=null;
    	size=0;
    }
 //worst time complixity is o(n)
public boolean add(IDriver driver) {

    if (driver == null)
        return false;
    Node<IDriver> check = head;
    
    // cheaking Whether  the same vehcile plate existed 
    // avoiding duplicates
    while (check != null) {
        if (check.data.compareTo(driver) == 0 ||
            check.data.getVehiclePlate().equals(driver.getVehiclePlate())) {
            return false;
        }
        check = check.next;
    }
     // if the list of drivers are empty it will be created at first
   
    if (head == null) {
        Node<IDriver> d = new Node<>(driver);
        head = d;
        size++;
        return true;
    }
    
    if (head.data.compareTo(driver) > 0) {
        Node<IDriver> d = new Node<>(driver);
        d.next = head;
        head = d;
        size++;
        return true;
    }

    Node<IDriver> current = head;
    Node<IDriver> prev = null;

    while (current != null) {
        if (current.data.compareTo(driver) < 0) {
            prev = current;
            current = current.next;
        }
        else {
            Node<IDriver> d = new Node<>(driver);
            d.next = current;
            prev.next = d;
            size++;
            return true;
        }
    }

    Node<IDriver> d = new Node<>(driver);
    prev.next = d;
    size++;
    return true;
}
//worst time complixity is o(n)
public IDriver findById(int driverId) {
	
		Node<IDriver>current=head;
		while(current!=null)
		{
			if(current.data.getId()==driverId)
				return current.data;
			current=current.next;
		}
	return null;
}
//worst time complixity is o(n)
public LinkedList<IDriver> findByName(String fullName) {
	LinkedList<IDriver>ListOfNames=new LinkedList<>();
	Node<IDriver>current=head;
	while(current!=null) {
		if(current.data.getName().equals(fullName))
			ListOfNames.insert(current.data);
		current=current.next;
	}
	return ListOfNames;

}
//worst time complixity is o(n)
public IDriver findByVehiclePlate(String vehiclePlate) {
	Node<IDriver>current =head;
	while(current!=null) {
		if(current.data.getVehiclePlate().equals(vehiclePlate))
			return current.data;
		current=current.next;
	}
	return null;
}	
//worst time complixity is o(n)
public LinkedList<IDriver> findByVehicleType(VehicleType vehicleType) {
	LinkedList<IDriver>LVehicles=new LinkedList<>();
	Node<IDriver>current=head;
	while(current!=null) {
		if(current.data.getVehicleType()==vehicleType)
			LVehicles.insert(current.data);
		
		current=current.next;
	}
	return LVehicles;
	
}
//worst time complixity is o(n)
public LinkedList<IDriver> getAll() {
		LinkedList<IDriver>ListOfAll=new LinkedList<>();
		Node<IDriver>current =head;
		
		while(current!=null)
		{
			ListOfAll.insert(current.data);
			current=current.next;
		}
		return ListOfAll;
}
//worst time complixity is o(n)
public boolean removeById(int driverId) {
	
	if(head==null)
		return false;
	if(head.data.getId()==driverId) {
		head=head.next;
		size--;
	return true;
	}
	Node<IDriver>current=head;
	Node<IDriver>prev=null;
	
	while(current!=null)
	{
		if(current.data.getId()==driverId)
		{
			prev.next=current.next;
			size--;
			return true;
		}
		prev=current;
		current=current.next;
	}
	return false;
	
}
public boolean removeByVehiclePlate(String vehiclePlate) {

	if(head==null)
		return false;
	if(head.data.getVehiclePlate().equals(vehiclePlate)) {
		head=head.next;
		size--;
	return true;
	}
	Node<IDriver>current=head;
	Node<IDriver>prev=null;
	
	while(current!=null)
	{
		if(current.data.getVehiclePlate().equals(vehiclePlate))
		{
			prev.next=current.next;
			size--;
			return true;
		}
		prev=current;
		current=current.next;
	}
	return false;
}//o(n) complixity
public int removeByName(String fullName) {
	
	if(head==null)
		return 0;
	
	
	Node<IDriver>current=head;
	int countRemovedNames=0;
	
	// i started from current.next to have reference point
	Node<IDriver>prev=current;
	current=current.next;
	
	while(current!=null)
	{
		
		if(current.data.getName().equals(fullName)) {
			prev.next=current.next;
			countRemovedNames++;
			size--;
		}
		else
		prev=current;
		current=current.next;
		
	}
	
	if(head.data.getName().equals(fullName)) {
		head=head.next;
		countRemovedNames++;
		size--;
	}
	return countRemovedNames;
}

// same logic is applied with method remvoeByName
//o(n) complixity
public int removeByVehicleType(VehicleType vehicleType) {
	int countRemoveVechilces=0;
	Node<IDriver>current=head;
	
	if(head==null)
		return 0;
	
	
		
	Node<IDriver>prev=current;
	current=current.next;
	
	while(current!=null)
	{
		
		if(current.data.getVehicleType()==(vehicleType)) {
			prev.next=current.next;
			countRemoveVechilces++;
			size--;
		}
		else
		prev=current;
		current=current.next;
		
	}
	
	if(head.data.getVehicleType()==(vehicleType)) {
		head=head.next;
		countRemoveVechilces++;
		size--;
	}
	return countRemoveVechilces;
	
}
//o(1) complixity
public int size() {
	return size;
}
}
