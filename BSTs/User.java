/**
 * This class represents a user that has a unique username and name.
 *
 * 
 * @author Daniel Ansher
 */
public class User implements Comparable<User> {
	
	private String userName;
	private String name;
	
    /**
     * Constructor for a User. Initializes username
     * and name.
     *            
     */
	public User(String userName, String name) {
		this.userName = userName;
		this.name = name;
	}
	
	@Override
	public int compareTo(User o) {
		return this.userName.toLowerCase().compareTo(o.getUserName().toLowerCase());
	}
	
    /**
     * Retrieves username for a user.
     * 
     * @return the username of a user.     
     */
	public String getUserName() {
		return userName;
	}

    /**
     * Retrieves name for a user.
     * 
     * @return the name of a user.     
     */
	public String getName() {
		return name;
	}
	
    /**
     * Creates String representation of User Object.
     * 
     * @return the string representation of a user   
     */
	public String toString() {
		return userName + "-" + name;
	}

}
