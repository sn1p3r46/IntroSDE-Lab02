import java.util.HashMap;
import java.util.Map;

import pojos.HealthProfile;
import pojos.Person;

public class HealthProfileReader {

	/* Solution to Exercise #02-2b */
	public static Map<Long, Person> database = new HashMap<Long, Person>(); //

	static {
		Person pallino = new Person();
		Person pallo = new Person(new Long(1), "Pinco", "Pallo", "1984-06-21");
		HealthProfile hp = new HealthProfile(68.0, 1.72);
		Person john = new Person(new Long(2), "John", "Doe", "1985-03-20", hp);

		database.put(pallino.getPersonId(), pallino);
		database.put(pallo.getPersonId(), pallo);
		database.put(john.getPersonId(), john);
	}

	/**
	 * The health profile reader gets information from the command line about
	 * weight and height and calculates the BMI of the person based on this
	 * parameters
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/* Solution to Exercise #01-2e */
		int argCount = args.length;
		if (argCount == 0) {
			System.out
					.println("I cannot create people out of thing air. Give me at least a name and lastname.");
		} else if (argCount < 2) {
			System.out
					.println("Are you sure you gave me ALL the information I need?");
		} else {
			String method = args[0];
			if (method.equals("createNewPerson")) {
				Long personId = Long.parseLong(args[1]);
				String firstname = args[2];
				String lastname = args[3];
				String birthdate = args[4];
				createPerson(personId, firstname, lastname, birthdate);
			} else if (method.equals("displayHealthProfile")) {
				Long personId = Long.parseLong(args[1]);
				displayHealthProfile(personId);				
			}  else if (method.equals("updateHealthProfile")) {
				Long personId = Long.parseLong(args[1]);
				Double height = Double.parseDouble(args[2]);
				Double weight = Double.parseDouble(args[3]);
				updateHealthProfile(personId, height, weight);
			} else if (method.equals("getBMI")) {
				getBMI(args[1]);

			} else {
				System.out.println("The system did not find the method '"+method+"'");
			}
		}
	}
	
	public static void getBMI(String personId){
		Long pID = Long.parseLong(personId);
		Person p = database.get(pID);
		if(p!=null){
			HealthProfile hp = p.gethProfile();

			if (hp!=null) {
				System.out.println(" BMI: " + hp.getBMI());
			}
		}
	}

	/* Solution to Exercise #01-2b */
	public static void createPerson(Long personId, String firstname,
			String lastname, String birthdate) {
		Person p = new Person(personId, firstname, lastname, birthdate);
		database.put(p.getPersonId(), p);
		System.out.println("A new person record (" + p.getPersonId()
				+ ") has been created for " + p.getLastname() + ", "
				+ p.getFirstname() + " born on " + p.getBirthdate());
	}

	/* Solution to Exercise #01-2c */
	public static void displayHealthProfile(Long personId) {
		Person p = database.get(personId);
		HealthProfile hp = p.gethProfile();
		System.out.println(p.getFirstname() + " has a weight of "
				+ hp.getWeight() + " Kg. and a height of " + hp.getHeight());
	}

	/* Solution to Exercise #01-2d */
	public static void updateHealthProfile(Long personId, Double height,
			Double weight) {
		Person p = database.get(personId);
		HealthProfile hp = p.gethProfile();
		hp.setHeight(height);
		hp.setWeight(weight);
		System.out.println(p.getFirstname() + " has updated weight to "
				+ hp.getWeight() + " Kg. and updated height to "
				+ hp.getHeight());
	}
}