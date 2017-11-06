package edu.wit.comp2000.jasonfagerberg.application3;

import java.util.*;

public class Train
	{
		private int id;
		private int direction = 1;
		ArrayList<Passenger> onTrain = new ArrayList<>();
		int CAPACITY = 200;

		/**
		 * Needed for Station to know what Passengers to board
		 * 
		 * @return 1 for positive direction -1 for negative direction
		 */
		public Train(int id, Station start)
			{
				this.id = id;
			}

		public int getDirection()
			{
				return direction;
			}

		public void setDirection(int direction)
			{
				this.direction = direction;
			}

		public boolean leaveTrain(Station station, int tick)
			{
				for (int i = 0; i < onTrain.size(); i++)
					{
						if (onTrain.get(i).hasArrived(station))
							{
								Passenger passToLeave = onTrain.get(i);
								onTrain.remove(i);
								station.exit(passToLeave, tick);
								if (i > onTrain.size())
									{
										return false;
									}
								if (i == 0)
									{
										return true;
									}

							}

					}
				return false;
			}

		/**
		 * method to put passengers onto train
		 * 
		 * @param remove
		 */
		public void board(Passenger newPass)
			{
				onTrain.add(newPass);
			}

		/**
		 * @return if the train is full or not
		 */
		public boolean isFull()
			{
				return onTrain.size() >= CAPACITY;
			}

		public boolean isEmpty()
			{
				return onTrain.size() == 0;
			}

		@Override
		public String toString()
			{
				return "TRAIN " + id;
			}
	}
