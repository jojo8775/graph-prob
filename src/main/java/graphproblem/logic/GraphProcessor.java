package graphproblem.logic;

import static graphproblem.util.ArgumentChecker.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import graphproblem.model.Edge;
import graphproblem.model.Node;

/**
 * Implementation class.
 * 
 * @author jojo
 */
public class GraphProcessor
{
	private Map<Node<Character>, List<Edge<Character>>> adjacencyList = new HashMap<Node<Character>, List<Edge<Character>>>();

	/**
	 * Adds an edge to the graph.
	 * 
	 * @param node
	 *            parent {@link Node} of {@code edge}.
	 * @param edge
	 *            an {@link Edge}.
	 * @throws IllegalArgumentException
	 *             if any of the following condition is satisfied.
	 *             <ul>
	 *             <li>{@code node} is {@code null}</li>
	 *             <li>{@code edge} is {@code null}</li>
	 *             </ul>
	 */
	public void addEdge(Node<Character> node, Edge<Character> edge)
	{
		rejectIfNull(node, "node");
		rejectIfNull(edge, "edge");

		if (!adjacencyList.containsKey(node))
		{
			adjacencyList.put(node, new ArrayList<Edge<Character>>());
		}

		adjacencyList.get(node).add(edge);
	}

	/**
	 * <p>
	 * Returns maximum number of trips possible between given {@code nodes}
	 * without extra stops.
	 * </p>
	 * 
	 * <p>
	 * If given {@code nodes} is {@code null}, or has {@code null} or less than
	 * two entries no route will be returned.
	 * </p>
	 * 
	 * @param nodes
	 *            the {@code List} of nodes.
	 * 
	 * @return maximum number of possible trips, else returns -1 if no route is
	 *         possible.
	 */
	public int findDistanceWithExactNodeStop(List<Node<Character>> nodes)
	{
		if (nodes == null || nodes.size() < 2 || doesNodesHasNullEntry(nodes))
		{
			System.out.println("NO SUCH ROUTE");
			return -1;
		}

		int count = 1;
		int distance = 0;
		Node<Character> nextNode = null;
		Node<Character> currentNode = nodes.get(0);

		while (count < nodes.size())
		{
			nextNode = nodes.get(count++);
			List<Edge<Character>> edges = adjacencyList.get(currentNode);

			if (edges == null)
			{
				System.out.println("NO SUCH ROUTE");
				return -1;
			}

			for (Edge<Character> singleEdge : edges)
			{
				if (nextNode.equals(singleEdge.getNode()))
				{
					currentNode = nextNode;
					distance += singleEdge.getDistance();
					break;
				}
			}

			if (currentNode != nextNode)
			{
				System.out.println("NO SUCH ROUTE");
				return -1;
			}
		}

		System.out.println(distance);
		return distance;
	}

	private boolean doesNodesHasNullEntry(List<Node<Character>> nodes)
	{
		for (Node<Character> singleNode : nodes)
		{
			if (singleNode == null)
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns maximum number of trips possible between {@code node1} and
	 * {@code node2} with number of stops equal to {@code stopCount}.
	 * 
	 * @param stopCount
	 *            exact number of stop counts in each trip.
	 * @param node1
	 *            the starting node.
	 * @param node2
	 *            the ending node.
	 * @return maximum number of possible trips.
	 * @throws IllegalArgumentException
	 *             if any of the following condition is satisfied.
	 *             <ul>
	 *             <li>{@code node1} is {@code null}</li>
	 *             <li>{@code node2} is {@code null}</li>
	 *             <li>{@code stopCount} is less than or equals to zero</li>
	 *             </ul>
	 */
	public int findTripsWithExactStopCount(int stopCount, Node<Character> node1, Node<Character> node2)
	{
		int tripCount = findTripCountHelper(rejectIfNull(node1, "node1"), rejectIfNull(node2, "node2"), 0,
				rejectIfLessThanOrEqualZero(stopCount, "stopCount"), TripCalculationMode.EXACT_STOPS);

		System.out.println(tripCount);
		return tripCount;
	}

	/**
	 * Returns maximum number of trips possible between {@code node1} and
	 * {@code node2} which has number of stops less than or equal to
	 * {@code stopCount}.
	 * 
	 * @param stopCount
	 *            maximum number of stop counts in each trip.
	 * @param node1
	 *            the starting node.
	 * @param node2
	 *            the ending node.
	 * @return maximum number of possible trips.
	 * @throws IllegalArgumentException
	 *             if any of the following condition is satisfied.
	 *             <ul>
	 *             <li>{@code node1} is {@code null}</li>
	 *             <li>{@code node2} is {@code null}</li>
	 *             <li>{@code stopCount} is less than or equals to zero</li>
	 *             </ul>
	 */
	public int findTripWithMaxStopCountLimit(int stopCount, Node<Character> node1, Node<Character> node2)
	{
		int tripCount = findTripCountHelper(rejectIfNull(node1, "node1"), rejectIfNull(node2, "node2"), 0,
				rejectIfLessThanOrEqualZero(stopCount, "stopCount"), TripCalculationMode.MAXIMUM_STOPS);

		System.out.println(tripCount);
		return tripCount;
	}

	private int findTripCountHelper(Node<Character> node1, Node<Character> node2, int count, int refCount,
			TripCalculationMode mode)
	{
		if (count > refCount)
		{
			return 0;
		}

		int trip = 0;
		if (count > 0 && isCountValid(count, refCount, mode) && node1.equals(node2))
		{
			if (mode == TripCalculationMode.MAXIMUM_STOPS)
			{
				trip += 1;
			}
			else
			{
				return 1;
			}
		}

		List<Edge<Character>> edges = adjacencyList.get(node1);
		if (edges == null)
		{
			return trip;
		}

		for (Edge<Character> singleEdge : edges)
		{
			trip += findTripCountHelper(singleEdge.getNode(), node2, count + 1, refCount, mode);
		}

		return trip;
	}

	private boolean isCountValid(int count, int refCount, TripCalculationMode mode)
	{
		return (mode == TripCalculationMode.EXACT_STOPS) ? count == refCount : count <= refCount;
	}

	/**
	 * Returns maximum number of trips which can be possible with the given
	 * maximum distance limit.
	 * 
	 * @param maxDistance
	 *            the given maximum distance limit.
	 * @param node1
	 *            the starting node.
	 * @param node2
	 *            the destination node.
	 * @return the maximum number of trips.
	 * @throws IllegalArgumentException
	 *             if any of the following condition is satisfied.
	 *             <ul>
	 *             <li>{@code node1} is {@code null}</li>
	 *             <li>{@code node2} is {@code null}</li>
	 *             <li>{@code maxDistance} is less than or equals to zero</li>
	 *             </ul>
	 */
	public int findTripsWithMaxDistanceLimit(int maxDistance, Node<Character> node1, Node<Character> node2)
	{
		int trips = findTripsWithDistanceLimitHelper(rejectIfNull(node1, "node1"), rejectIfNull(node2, "node2"), 0,
				rejectIfLessThanOrEqualZero(maxDistance, "maxDistance"));
		System.out.println(trips);
		return trips;
	}

	private int findTripsWithDistanceLimitHelper(Node<Character> node1, Node<Character> node2, int distance,
			int refDistance)
	{
		if (distance > refDistance)
		{
			return 0;
		}

		int trips = 0;
		if (distance > 0 && distance < refDistance && node1.equals(node2))
		{
			trips += 1;
		}

		List<Edge<Character>> edges = adjacencyList.get(node1);
		if (edges == null)
		{
			return trips;
		}

		for (Edge<Character> singleEdge : edges)
		{
			trips += findTripsWithDistanceLimitHelper(singleEdge.getNode(), node2, distance + singleEdge.getDistance(),
					refDistance);
		}

		return trips;
	}

	/**
	 * uses Dijkstra's algorithm to return the shortest distance between
	 * {@code node1} and {@code node2}.
	 * 
	 * @param node1
	 *            the starting node.
	 * @param node2
	 *            the ending node.
	 * @return the shorted distance between {@code node1} and {@code node2} if
	 *         they are in a connected graph, else returns -1.
	 * @throws IllegalArgumentException
	 *             if any of the following condition is satisfied.
	 *             <ul>
	 *             <li>{@code node1} is {@code null}</li>
	 *             <li>{@code node2} is {@code null}</li>
	 *             </ul>
	 */
	public int findShortestPathBasedOnDistance(Node<Character> node1, Node<Character> node2)
	{
		rejectIfNull(node1, "node1");
		rejectIfNull(node2, "node2");

		Queue<Node<Character>> queue = new LinkedList<Node<Character>>();
		queue.add(node1);

		Set<Node<Character>> visitedNodes = new HashSet<Node<Character>>();

		Node<Character> parentNode = null;
		Map<Node<Character>, Integer> shortestDistanceByNode = new HashMap<Node<Character>, Integer>();

		while (!queue.isEmpty())
		{
			parentNode = queue.remove();

			List<Edge<Character>> edges = adjacencyList.get(parentNode);

			if (edges == null)
			{
				continue;
			}

			for (Edge<Character> singleEdge : edges)
			{
				Node<Character> currentNode = singleEdge.getNode();
				if (visitedNodes.add(currentNode))
				{
					queue.add(currentNode);
				}

				int currentCumulativeWeight = (shortestDistanceByNode.get(parentNode) == null ? 0
						: shortestDistanceByNode.get(parentNode)) + singleEdge.getDistance();
				if (!shortestDistanceByNode.containsKey(currentNode))
				{
					shortestDistanceByNode.put(currentNode, currentCumulativeWeight);
				}
				else
				{
					int prevCumulativeWeight = shortestDistanceByNode.get(currentNode);
					shortestDistanceByNode.put(currentNode, Math.min(currentCumulativeWeight, prevCumulativeWeight));
				}
			}
		}

		if (shortestDistanceByNode.get(node2) == null)
		{
			System.out.println("NODES ARE DISCONNECTED");
			return -1;
		}

		System.out.println(shortestDistanceByNode.get(node2));
		return shortestDistanceByNode.get(node2);
	}

	private enum TripCalculationMode
	{
		EXACT_STOPS, MAXIMUM_STOPS;
	}
}
