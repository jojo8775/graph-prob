package graphproblem.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import static graphproblem.util.ArgumentChecker.*;

/**
 * Tests for {@link ArgumentChecker}.
 * 
 * @author jojo
 */
public class ArgumentCheckerTest
{
	@Test
	public void testRejectIfNull_NullObject()
	{
		try
		{
			ArgumentChecker.rejectIfNull(null, "testObject");
		}
		catch (IllegalArgumentException e)
		{
			assertThat(e.getMessage(), is("testObject cannot be null."));
			return;
		}

		fail("IllegalArgumentException was expected.");
	}

	@Test
	public void testRejectIfNull_NonNullObject()
	{
		String testString = "Test text";
		assertThat(ArgumentChecker.rejectIfNull(testString, "testString"), is(testString));
	}

	@Test
	public void testRejectIfLessThanOrEqualZero_ZeroValue()
	{
		try
		{
			ArgumentChecker.rejectIfLessThanOrEqualZero(0, "testValirable");
		}
		catch (IllegalArgumentException e)
		{
			assertThat(e.getMessage(), is("testValirable cannot be less than or equal to zero."));
			return;
		}

		fail("IllegalArgumentException was expected.");
	}

	@Test
	public void testRejectIfLessThanOrEqualZero_NegativeValue()
	{
		try
		{
			ArgumentChecker.rejectIfLessThanOrEqualZero(-1, "testValirable");
		}
		catch (IllegalArgumentException e)
		{
			assertThat(e.getMessage(), is("testValirable cannot be less than or equal to zero."));
			return;
		}

		fail("IllegalArgumentException was expected.");
	}

	@Test
	public void testRejectIfLessThanOrEqualZero_GreaterThanZero()
	{
		assertThat(ArgumentChecker.rejectIfLessThanOrEqualZero(1, "testVariable"), is(1));
	}
}
