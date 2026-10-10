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
	    }}
