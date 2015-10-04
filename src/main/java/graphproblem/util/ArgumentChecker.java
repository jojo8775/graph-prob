package graphproblem.util;

/**
 * Utility class to validate arguments.
 * 
 * @author jojo
 *
 */
public class ArgumentChecker
{
	/**
	 * Verifies is given object is null.
	 * 
	 * @param object
	 *            the object to be verified.
	 * @param argumentName
	 *            the name of the object.
	 * @return {@code object} if {@code object} is not {@code null}.
	 * @throws IllegalArgumentException
	 *             if {@code object} is {@code null}.
	 */
	public static <T> T rejectIfNull(T object, String argumentName)
	{
		if (object == null)
		{
			throw new IllegalArgumentException(argumentName + " cannot be null.");
		}

		return object;
	}

	/**
	 * Verifies is given value is less than or equal to zero.
	 * 
	 * @param value
	 *            the value to be verified.
	 * @param argumentName
	 *            the name of the variable.
	 * @return {@code value} if {@code value} is greater than zero.
	 * @throws IllegalArgumentException
	 *             if {@code value} is less than or equal to zero.
	 */
	public static int rejectIfLessThanOrEqualZero(int val, String argumentName)
	{
		if (val <= 0)
		{
			throw new IllegalArgumentException(argumentName + " cannot be less than or equal to zero.");
		}

		return val;
	}
}
