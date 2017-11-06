package edu.wit.comp2000.jasonfagerberg.application3;

import java.io.*;
import java.util.*;
/**
 * @author Jason Fagerberg
 * COMP 2000-07/08
 * Lab 3: Queue Application - Train Simulation
 * Group 22
 * Jason Fagerberg, Kristian Hosker, Justin Koutros, Sofia Lee, Dheric Seney
 */
public class Station
	{
		// Identifier
		private final int id;
		// Counter that increments every time a train arrives
		private int tick;
		// Platforms Facing different directions
		private Queue<Passenger> posPlatform = new LinkedList<>();
		private Queue<Passenger> negPlatform = new LinkedList<>();
		// to make logging easier
		private String logFile = "\\C:\\Users\\fagerbergj1\\Dropbox\\workspace\\Lab06\\src\\edu\\wit\\comp2000\\jasonfagerberg\\application3\\Station_Logs\\";
		// clear log control
		private boolean clearFile = true;

		/**
		 * Constructor creates directory to file clears or continues to write on
		 * previous log
		 * 
		 * @param id
		 *            - id for station
		 */
		public Station(int id)
			{
				this.id = id;
				logFile += "station" + id + "Log.txt";
				// Clear log
				try
					{
						if (clearFile)
							{
								clearLog(logFile);
							}
						else
							{
								log(logFile, "\n");
								log(logFile, "...........NEW RUN THROUGH PROGRAM...........");
							}
					}
				catch (Exception e)
					{
						System.out.println(e);
					}
				log(logFile, "Station " + id + " is Initlized...." + "\n");
			}

		/**
		 * Passed an array of passengers entering the station Looks at what
		 * direction they need to go and puts them on right platform
		 * 
		 * @param newPassengers
		 */
		public void enter(Passenger newPassenger)
			{
				if (newPassenger.getDirection() == 1)
					{
						log(logFile, newPassenger + " was added to platform going the positive direction \n");
						posPlatform.add(newPassenger);
					}
				else
					{
						negPlatform.add(newPassenger);
						log(logFile, newPassenger + " was added to platform going the negative direction \n");
					}
			}

		/**
		 * Adds Passengers at destination to arrived array
		 * 
		 * @param arrivedPassengers
		 */
		public void exit(Passenger arrivedPassenger, int tick)
			{
				if (!(this.tick == tick))
					{
						log(logFile, "TRAIN ARRIVED AT TICK: " + tick);
						log(logFile, "People are now exiting the train...");
					}
				this.tick = tick;
				log(logFile, "Passenger " + arrivedPassenger.toString() + " arrived at tick " + tick);
				// Some logging algorithm
			}

		/**
		 * Called by simulation gives the train that arrived at station
		 * 
		 * @param train
		 */
		public void trainArrived(Train train, int tick)
			{
				this.tick = tick;
				// Loop through and take everyone off platform and put them on
				// the train
				log(logFile, "TRAIN ARRIVED AT TICK: " + tick);
				log(logFile, "People are now entering the train...");
				if (train.getDirection() == 1)
					{
						while (!posPlatform.isEmpty() && !train.isFull())
							{
								Passenger passenger = posPlatform.remove();
								log(logFile, passenger + " was passed to train " + train + "\n");
								train.board(passenger);
								if (train.isFull())
									{
										log(logFile, "Train is now full...Moving train to next station");
									}
							}
					}
				else
					{
						while (!negPlatform.isEmpty() && !train.isFull())
							{
								Passenger passenger = negPlatform.remove();
								log(logFile, passenger + " was passed to train " + train + "\n");
								train.board(passenger);
								if (train.isFull())
									{
										log(logFile, "Train is now full...");
									}
							}
					}
				log(logFile, "Train is now leaving the station...");
			}

		public int getId()
			{
				return id;
			}

		/**
		 * Checks if one station is equal to another
		 * 
		 * @param otherStation
		 *            -station to compare to
		 * @return - if the names are equal
		 */
		@Override
		public boolean equals(Object otherStation)
			{
				if (otherStation == null)
					{
						return false;
					}
				if (this.getId() == ((Station) otherStation).getId())
					{
						return true;
					}
				return false;
			}

		public boolean peopleWaiting()
			{
				return !(posPlatform.isEmpty() && negPlatform.isEmpty());
			}

		/**
		 * Print out station ID, and number of people waiting on platform
		 */
		@Override
		public String toString()
			{
				String res = "";
				res += "STATION ID: " + id + "\n";
				res += "Number of people waiting to go North: " + posPlatform.size() + "\n";
				res += "Number of people waiting to go South: " + negPlatform.size() + "\n";
				return res;
			}

		/**
		 * Create a log for each station
		 * 
		 * @param path
		 *            - where to put/ modify log
		 * @param content
		 *            - what to write/ append
		 */
		private void log(String path, String content)
			{
				try
					{
						File file = new File(path);

						// if file doesnt exists, then create it
						if (!file.exists())
							{
								file.createNewFile();
							}

						FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
						BufferedWriter bw = new BufferedWriter(fw);
						// write in file
						bw.append(content);
						bw.newLine();
						// close connection
						bw.close();
					}
				catch (Exception e)
					{
						System.out.println(e);
					}
			}

		/**
		 * Clear an existing log
		 * 
		 * @param path
		 *            - where existing log is
		 */
		private void clearLog(String path)
			{
				File file = new File(logFile);
				FileWriter fw;
				try
					{
						fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write("");
						bw.close();
					}
				catch (IOException e)
					{
						e.printStackTrace();
					}

			}

		// unit testing
		public static void main(String args[])
			{
				// station 99 will stand in as test station
				TrainRoute tr = new TrainRoute(10);
				Station test = tr.getStart();
				Station end = tr.getDestinaton(test);
				System.out.print("TESTING STATION " + test.getId() + "\n");
				Passenger pass = new Passenger(1, test, end, tr.getDirection(test, end));
				testEnter(test, pass);
				Train testTrain = new Train(99, end);
				end.enter(new Passenger(2,tr.getZero(),end,1));
				end.enter(new Passenger(3,tr.getZero(),end,1));
				end.enter(new Passenger(4,tr.getZero(),end,1));
				testTrainArrive(testTrain,end,pass,234);
				testEquals(test,end,false);
				testEquals(test,test,true);
				testToString(test,end,false);
				testToString(test,test,true);

			}

		private static void testEnter(Station test, Passenger pass)
			{
				test.enter(pass);
				if (pass.getDirection() == 1)
					{
						for (int i = 0; i < test.posPlatform.size(); i++)
							{
								Passenger removed = test.posPlatform.remove();
								if(pass.getDirection() == 1 && pass.getid() == removed.getid())
									{
										System.out.println("Enter...Working \n Expected Passenger: " + pass + "\n Passed Passenger: " + removed);
										return;
									}
							}
						System.out.println("Enter...Broken \n Expected Passenger: " + pass + "\n Passed Passenger: " + pass);
					}
				else
					{
						for (int i = 0; i < test.negPlatform.size(); i++)
							{
								Passenger removed = test.negPlatform.remove();
								if(pass.getDirection() != 1 && pass.getid() == removed.getid())
									{
										System.out.println("Enter...Working \nExpected Passenger: " + pass + "\n Passed Passenger: " + removed);
										return;
									}
							}
						System.out.print("Enter...Broken \n Expected Passenger: " + pass + "\n Passed Passenger: " + pass);
					}
			}

		private static void testExit(Station end, Passenger pass, int tick)
			{
				end.exit(pass, tick);
				System.out.println("\n\nEXIT METHOD LOGS A PASSENGER THAT ARRIVES AT THE STATION " + end.getId() + "\n   CHECK LOG OF STATION " + end.getId()+ " \n   FOR " + pass + "\n   ARRIVING AT TICK " + tick);
			}

		private static void testTrainArrive(Train train, Station test, Passenger expected, int tick)
			{
				testExit(test,expected,234);
				System.out.println("\n\nTRAIN ARRIVE METHOD SHOULD ADD " + test.posPlatform.size() + " TO THE POSITIVE PLATFORM\n   IN " + test+"\n   CHECK THE LOG FOR THEM TO ARRIVE AT TICK " + tick);
				test.trainArrived(train, tick);
			}

		private static void testEquals(Station one, Station two, boolean expectedRes)
			{
				if(expectedRes == one.equals(two))
					{
						System.out.println("\nequals()...Working" + "\n First " + one + "\n Second " + two + "\nexpected result " + expectedRes);
						return;
					}
				System.out.println("\nequals()...Broken" + "\n First " + one + "\n Second " + two + "\nexpected result " + expectedRes);
			}

		private static void testToString(Station one, Station two, boolean expectedRes)
			{
				if(expectedRes == one.toString().equals(two.toString()))
					{
						System.out.println("\ntoString()...Working" + "\n First " + one + "\n Second " + two + "\nexpected result " + expectedRes);
						return;
					}
				System.out.println("\ntoString()...Broken" + "\n First " + one + "\n Second " + two + "\nexpected result " + expectedRes);
			}
	}
