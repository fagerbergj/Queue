package edu.wit.comp2000.jasonfagerberg.application3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TrainRouteTest {
	@Test
	public void testTrainRoute(){
		TrainRoute train = new TrainRoute(5);
	}
	@Test
	public void testgetStart(){
		TrainRoute train = new TrainRoute(5);
		train.getStart();
		
	}
	@Test
	public void testgetDestination(){
		TrainRoute train = new TrainRoute(5);
		Station start = train.getStart();
		train.getDestinaton(start);
	}
	@Test
	public void testgetDirection(){
		TrainRoute train = new TrainRoute(5);
		Station start = train.getStart();
		Station end = train.getDestinaton(start);
		train.getDirection(start, end);
	}
	@Test
	public void testgetDistance(){
		TrainRoute train = new TrainRoute(5);
		Station start = train.getStart();
		Station end = train.getDestinaton(start);
		train.getDistance(start, end);
		
	}
	@Test
	public void testgetValue(){
		TrainRoute train = new TrainRoute(5);
		Station start = train.getStart();
		train.getValue(start);
		Station end = train.getDestinaton(start);
		train.getValue(end);
	}
	@Test
	public void testCompare(){
		TrainRoute train = new TrainRoute(5);
		Station start = train.getStart();
		int s = train.getValue(start);
		train.compare(s);
		Station end = train.getDestinaton(start);
		int e = train.getValue(end);
		train.compare(e);		
	}

}
