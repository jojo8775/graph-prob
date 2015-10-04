package graphproblem.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import graphproblem.model.Edge;
import graphproblem.model.Node;

/**
 * Tests for {@link GraphProcessor}.
 * 
 * @author jojo
 */
public class GraphProcessorTest
{
	private static GraphProcessor graphProcessor;

	@BeforeClass
	public static void setUpBeforeClass()
	{
		graphProcessor = new GraphProcessor();
		graphProcessor.addEdge(new Node<Character>('A'), new Edge<Character>(new Node<Character>('B'), 5));
		graphProcessor.addEdge(new Node<Character>('B'), new Edge<Character>(new Node<Character>('C'), 4));
		graphProcessor.addEdge(new Node<Character>('C'), new Edge<Character>(new Node<Character>('D'), 8));
		graphProcessor.addEdge(new Node<Character>('D'), new Edge<Character>(new Node<Character>('C'), 8));
		graphProcessor.addEdge(new Node<Character>('D'), new Edge<Character>(new Node<Character>('E'), 6));
		graphProcessor.addEdge(new Node<Character>('A'), new Edge<Character>(new Node<Character>('D'), 5));
		graphProcessor.addEdge(new Node<Character>('C'), new Edge<Character>(new Node<Character>('E'), 2));
		graphProcessor.addEdge(new Node<Character>('E'), new Edge<Character>(new Node<Character>('B'), 3));
		graphProcessor.addEdge(new Node<Character>('A'), new Edge<Character>(new Node<Character>('E'), 7));
	}

	/**
	 * Testing minimum distance between A-B-C.
	 */
	@Test
	public void testFindDistanceWithExactNodeStop_distanceBetweenABC()
	{
		int actualDistance = graphProcessor.findDistanceWithExactNodeStop(
				Arrays.asList(new Node<Character>('A'), new Node<Character>('B'), new Node<Character>('C')));

		assertThat(actualDistance, is(9));
	}

	/**
	 * Testing minimum distance between A-D.
	 */
	@Test
	public void testFindDistanceWithExactNodeStop_distanceBetweenAD()
	{
		int actualDistance = graphProcessor
				.findDistanceWithExactNodeStop(Arrays.asList(new Node<Character>('A'), new Node<Character>('D')));

		assertThat(actualDistance, is(5));
	}

	/**
	 * Testing minimum distance between A-D-C.
	 */
	@Test
	public void testFindDistanceWithExactNodeStop_distanceBetweenADC()
	{
		int actualDistance = graphProcessor.findDistanceWithExactNodeStop(
				Arrays.asList(new Node<Character>('A'), new Node<Character>('D'), new Node<Character>('C')));

		assertThat(actualDistance, is(13));
	}

	/**
	 * Testing minimum distance between A-E-B-C-D.
	 */
	@Test
	public void testFindDistanceWithExactNodeStop_distanceBetweenAEBCD()
	{
		int actualDistance = graphProcessor
				.findDistanceWithExactNodeStop(Arrays.asList(new Node<Character>('A'), new Node<Character>('E'),
						new Node<Character>('B'), new Node<Character>('C'), new Node<Character>('D')));

		assertThat(actualDistance, is(22));
	}

	/**
	 * Testing minimum distance between A-E-D.
	 */
	@Test
	public void testFindDistanceWithExactNodeStop_distanceBetweenAED()
	{
		int actualDistance = graphProcessor.findDistanceWithExactNodeStop(
				Arrays.asList(new Node<Character>('A'), new Node<Character>('E'), new Node<Character>('D')));

		assertThat(actualDistance, is(-1));
	}

	/**
	 * Testing maximum number of trips starting at A and ending at C with
	 * exactly 4 stops.
	 */
	@Test
	public void testFindTripsWithExactStopCount()
	{
		int actualTrips = graphProcessor.findTripsWithExactStopCount(4, new Node<Character>('A'),
				new Node<Character>('C'));

		assertThat(actualTrips, is(3));
	}

	/**
	 * Testing maximum number of trips starting at C and ending at C with
	 * maximum 3 stops.
	 */
	@Test
	public void testFindTripsWithMaxStopCount()
	{
		int actualTrips = graphProcessor.findTripWithMaxStopCountLimit(3, new Node<Character>('C'),
				new Node<Character>('C'));

		assertThat(actualTrips, is(2));
	}

	/**
	 * Testing maximum number of trips starting at C and ending at C with
	 * maximum distance traveled is 28.
	 */
	@Test
	public void testFindTripsWithMaxDistanceLimit()
	{
		int actualTrips = graphProcessor.findTripsWithMaxDistanceLimit(28, new Node<Character>('C'),
				new Node<Character>('C'));

		assertThat(actualTrips, is(7));
	}

	/**
	 * Tests shortest route between A to C based on distance.
	 */
	@Test
	public void testFindShortestPath_DistanceFromAtoC()
	{
		int actualShortestDistance = graphProcessor.findShortestPathBasedOnDistance(new Node<Character>('A'),
				new Node<Character>('C'));
		assertThat(actualShortestDistance, is(9));
	}

	/**
	 * Tests shortest route between B to B based on distance.
	 */
	@Test
	public void testFindShortestPath_DistanceFromBtoB()
	{
		int actualShortestDistance = graphProcessor.findShortestPathBasedOnDistance(new Node<Character>('B'),
				new Node<Character>('B'));
		assertThat(actualShortestDistance, is(9));
	}
}
