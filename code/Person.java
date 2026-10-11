package CCS212;

public abstract class Person implements IPerson {
	


	    private final int id;
	    private String name;
	    private String phoneNumber;
	    private LinkedList<IRide> rideHistory;

	    public Person(int id, String name, String phoneNumber) {
	        this.id = id;
	        this.name = name;
	        setPhoneNumber(phoneNumber);
	        this.rideHistory = new LinkedList<>();
	    }

	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }
	    	public void setPhoneNumber(String phoneNumber) {
	    		if(phoneNumber==null || phoneNumber.length()!=10) {
	    			throw new IllegalArgumentException("Phone number must be exactly 10 digits!");
	    		}// restricting the user to enter a phone number exactly contains 10 digits
	    		
	    		char c;
	    		
	    		
	    		for(int i=0;i<phoneNumber.length();i++) {
	    			c=phoneNumber.charAt(i);
	    			if(c<'0' ||c>'9')
	    				throw new IllegalArgumentException("Phone number must contain digits only");
	    		} // not allowing a letter to be part of the phone number
	    		
	    		this .phoneNumber=phoneNumber;
	    		
	    	}
	    	 public LinkedList<IRide> getRideHistory() {
	    	        return rideHistory;
	    	    }
	    	 public String toString() {
	    	        return "ID: " + id +
	    	               ", Name: " + name +
	    	               ", Phone: " + phoneNumber;
	    	    }

}
