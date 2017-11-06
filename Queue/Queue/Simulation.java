package edu.wit.comp2000.jasonfagerberg.application3;

public class Simulation
	{
		private static int tick;

		private static int numOfStations = 5;
		private static int createPassengers = 1000;
		private static int numOfPassengers = 0;
		/**
		 * Instantiates each passenger by retrieving their start destination, end destination, and direction from TrainRoute.
		 * Then it passes each passenger to Station to be added to their respective platforms.
		 * Precondition: A TrainRoute object has been created with a set number of Stations.
		 * @param tr
		 */
		public static void createPassengers(TrainRoute tr)
			{
				Station start = tr.getStart();
				Station destination = tr.getDestinaton(start);
				int direction = tr.getDirection(start, destination);
				start.enter(new Passenger(numOfPassengers, start, destination, direction));
				numOfPassengers++;
			}
		/**
		 * Moves the train along the route by checking if the train is at the end of the track and then using the leaveTrain
		 * from the Train class and updating the Stations' log by calling trainArrived. If the train arrived at the end of the 
		 * track, it changes its direction to go in the opposite direction by multiplying the direction by -1. Updates the tick 
		 * for simulation to indicate the train has moved once. 
		 * Precondition: Each station and Train has been populated with passengers. 
		 * @param tr
		 * @param currentStation
		 * @param train
		 */
		// runs program
		public static void move(TrainRoute tr, Station currentStation, Train train)
			{
				tick++;
				if (tr.endOfTrack(currentStation, train.getDirection()))
					{
						train.setDirection(train.getDirection() * -1);
					}
				train.leaveTrain(currentStation, tick);
				currentStation.trainArrived(train, tick);
			}

		public static void main(String args[])
			{
					TrainRoute tr = new TrainRoute(numOfStations);
					Station currentStation = tr.getStart();
					Train train = new Train(1, currentStation);
			
				for (int i = 0; i < createPassengers; i++)
					{
					createPassengers(tr);
					}
				
				while (tr.peopleWaiting() || !train.isEmpty())
					{
					move(tr, currentStation, train);
					currentStation = tr.getNextStation(currentStation, train.getDirection());
					}
				System.out.print("end");
			}

	}