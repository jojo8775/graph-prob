package graphproblem.model;

import static graphproblem.util.ArgumentChecker.rejectIfNull;

/**
 * Represents a node of a graph.
 * 
 * @author jojo
 */
public class Node<T>
{
	private final T value;

	/**
	 * Creates a new {@link Node}.
	 * 
	 * @param nodeValue
	 *            the node value
	 * @throws IllegalArgumentException
	 *             if {@code nodeValue} is {@code null}
	 */
	public Node(T nodeValue)
	{
		this.value = rejectIfNull(nodeValue, "nodeValue");
	}

	/**
	 * @return the node value, which cannot be {@code null}.
	 */
	public T getValue()
	{
		return value;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Node other = (Node) obj;
		if (value == null)
		{
			if (other.value != null)
				return false;
		}
		else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Node [value=" + value + "]";
	}
}
