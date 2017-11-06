package edu.wit.comp2000.jasonfagerberg.application3;

/**
 * @author Sofia Lee
 * COMP 2000-07/08
 * Lab 3: Queue Application - Train Simulation
 * Group 22
 * Jason Fagerberg, Kristian Hosker, Justin Koutros, Sofia Lee, Dheric Seney
 */
public class Passenger {
	private int id;
	private Station start;
	private Station destination;
	private int direction = 0;
	private boolean arrived = false;
	
	/**
	 * Sets the id, start, destination, and direction of the passenger.
	 * Precondition: Start and Destination will be different. Therefore, direction != 0.
	 * @param id of the passenger, start, destination, and direction
	 */
	public Passenger(int id, Station start, Station destination, int direction){
		this.id = id;
		this.start = start;
		this.destination = destination;
		this.direction = direction;
	}
	
	// Not sure if this method is handled by Passenger.
	/**
	 * Returns a boolean that tells if the passenger has arrived at destination.
	 * @param Current station of the train
	 * @return Passenger arrival status
	 */
	public boolean hasArrived(Station current){
		if(current.equals(destination)){
			arrived = true;
		}
		return arrived;
	}
	
	public int getid(){
		return id;
	}
	
	public Station getStart(){
		return start;
	}
	
	public Station getDest(){
		return destination;
	}
	
	public int getDirection(){
		return direction;
	}
	
	@Override
	public String toString(){
		String result = "";
		result += "Passenger " + id + "\t";
		result += "Start: STATION " + start.getId() + "\t";
		result += "Destination: STATION " + destination.getId();
		return result;
	}
	
	public static void main(String[] args){
		TrainRoute tr = new  TrainRoute(5);
		int numOfPassenger = 3;
		Passenger[] passArray = new Passenger[numOfPassenger];
		for(int i = 0; i < numOfPassenger; i++){
			Station start = tr.getStart();
			Station destination = tr.getDestinaton(start);
			int direction = tr.getDirection(start, destination);
			int passId = i;
			passArray[i] = new Passenger(passId, start, destination, direction);
			System.out.println(passArray[i]);
		}
	}
}
