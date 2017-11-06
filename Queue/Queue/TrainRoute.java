package edu.wit.comp2000.jasonfagerberg.application3;

import java.util.ArrayList;

public class TrainRoute
	{
		private Station client_destination;
		private Station client_start;
		private int train_Direction;
		private ArrayList<Station> stations = new ArrayList<>();
		private int numStations;

		public TrainRoute(int numStations)
			{
				this.numStations = numStations;
				for (int i = 0; i < numStations; i++)
					{
						stations.add(new Station(i));
					}
			}

		public Station getStart()
			{ // retrieves start
				double randStation = Math.random() * numStations;
				client_start = stations.get((int) randStation);
				return client_start;
			}

		public Station getDestinaton(Station beg)
			{ // retrieves destination
				double randStation = Math.random() * numStations;
				client_destination = stations.get((int) randStation);
				if (client_destination.equals(beg))
					{
						client_destination = getDestinaton(beg);
					}
				return client_destination;
			}

		public int getDirection(Station beg, Station end)
			{ // gets direction
				// compares
				train_Direction = getDistance(beg, end);
				train_Direction = compare(train_Direction);
				return train_Direction;
			}

		public int getDistance(Station s, Station e)
			{
				int start = getValue(s);
				int end = getValue(e);
				return end - start;
			}

		public int getValue(Station v)
			{ // gets value of the station
				int value = 0;
				for (int i = 0; i < numStations; i++)
					{
						if (v.equals(stations.get(i)))
							{
								return i;
							}
					}
				return value;
			}

		public int compare(int c)
			{ // compares the two stations value to see what direction it goes
				if (c > 0)
					{
						return 1;
					}
				else if (c < 0)
					{
						return -1;
					}
				else
					{
						return 0;
					}

			}

		public Station getZero()
			{
				return stations.get(0);
			}

		public Station getNextStation(Station current, int direction)
			{
				if (direction == 1)
					{
						for (int i = 0; i < stations.size(); i++)
							{
								if (current.equals(stations.get(i)) && i + 1 < stations.size())
									{
										return stations.get(i + 1);
									}
							}
					}
				else
					{
						for (int i = stations.size() - 1; i >= 0; i--)
							{
								if (current.equals(stations.get(i)) && i - 1 >= 0)
									{
										return stations.get(i - 1);
									}
							}
					}
				return null;
			}

		public boolean peopleWaiting()
			{
				boolean result = false;
				for (int i = 0; i < stations.size(); i++)
					{
						if(stations.get(i).peopleWaiting())
							{
								result = true;
							}
					}
				return result;
			}

		public boolean endOfTrack(Station check, int direction)
			{
				if(direction == 1)
					{
						return (check.equals(stations.get(stations.size() - 1)));
					}
				return (check.equals(stations.get(0)));
			}

		public static void main(String[] args)
			{
				TrainRoute tr = new TrainRoute(8);
				Station beg = tr.getStart();
				System.out.println("The Start Station is: STATION " + beg.getId());
				Station end = tr.getDestinaton(beg);
				System.out.println("The End station is: STATION " + end.getId());
				System.out.println("The distance between stations is " + tr.getDistance(beg, end));
				int hi = tr.getDirection(beg, end);
				System.out.println("The direction is " + hi);
				Station next = tr.getNextStation(beg, hi);
				System.out.println("The next station is " + next);
				boolean wait = tr.peopleWaiting();
				System.out.println("Are there people waiting? " + wait);
				boolean destination = tr.endOfTrack(beg, hi);
				System.out.println("Are we at the end? " + destination);

			}

	}
