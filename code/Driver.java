// Student Name: Nawaf turki alqusair
// Student ID: 446107045
public class Driver extends Person implements IDriver {
	
	private String vehiclePlate;
    private VehicleType vehicleType;

	 public Driver(int id, String name, String phoneNumber,
				   String vehiclePlate, VehicleType vehicleType) {
	 		super(id, name, phoneNumber);

  		setVehiclePlate(vehiclePlate);
	  	setVehicleType(vehicleType);
}
	public String getVehiclePlate()
	{
        return vehiclePlate;
    }
	  public void setVehiclePlate(String vehiclePlate){

		   // Check the length
        if (vehiclePlate == null || vehiclePlate.length() != 7)
	  {
            throw new IllegalArgumentException("Vehicle plate must contain exactly 7 characters"); 
        }		
			for(int i=0;i<3;i++) //for cheaking the first 3 letters 
				{
					if(!(vehiclePlate.charAt(i)>=65 && vehiclePlate.charAt(i)<=90)) //asci code upper letters from 65 to 90
        			throw new IllegalArgumentException("First 3 characters must be uppercase letters" );
		  		}
			for(int i=3 ;i<7;i++)
				{
					char cDigit=vehiclePlate.charAt(i);
					if(!(cDigit<='9' && cDigit>='0'))
  throw new IllegalArgumentException("Last 4 characters must be digits");				}

			        this.vehiclePlate = vehiclePlate;
				
	}
 
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    
    public void setVehicleType(VehicleType vehicleType) {

        if (vehicleType == null) {
            throw new IllegalArgumentException(
                "Vehicle type cannot be null");
        }

        this.vehicleType = vehicleType;
    }

    

    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IDriver)) {
            return false;
        }

        IDriver other = (IDriver) obj;

        return this.getId() == other.getId();
    }

   
    public int compareTo(IDriver other) {

    if (this.getId() > other.getId()) {
        return 1;
    }
    else if (this.getId() < other.getId()) {
        return -1;
    }
    else {
        return 0;
    }
}
public int hashCode() {
    return getId();
}
    
    public String toString() {
        return super.toString()
                + ", Vehicle Plate: " + vehiclePlate
                + ", Vehicle Type: " + vehicleType;
    }
}

