package graphproblem.model;

import static graphproblem.util.ArgumentChecker.rejectIfLessThanOrEqualZero;
import static graphproblem.util.ArgumentChecker.rejectIfNull;

/**
 * Represents an edge of a graph.
 * 
 * @author jojo
 */
public class Edge<T>
{
	private final Node<T> node;
	private final int distance;

	/**
	 * Creates a new {@link Edge}.
	 * 
	 * @param node
	 *            the destination {@link Node}.
	 * @param distance
	 *            the distance of the edge
	 * @throws IllegalArgumentException
	 *             if any of the condition is satisfied.
	 *             <ul>
	 *             <li>{@code node} is {@code null}</li>
	 *             <li>{@code distance} is less than or equal to zero</li>
	 *             </ul>
	 */
	public Edge(Node<T> node, int distance)
	{
		this.node = rejectIfNull(node, "node");
		this.distance = rejectIfLessThanOrEqualZero(distance, "distance");
	}

	/**
	 * @return the destination {@link Node}. The returned value cannot be
	 *         {@code null}
	 */
	public Node<T> getNode()
	{
		return node;
	}

	/**
	 * @return the distance of the edge. The returned value cannot be less than
	 *         or equal to zero.
	 */
	public int getDistance()
	{
		return distance;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + distance;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (distance != other.distance)
			return false;
		if (node == null)
		{
			if (other.node != null)
				return false;
		}
		else if (!node.equals(other.node))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Edge [node=" + node + ", distance=" + distance + "]";
	}
}
