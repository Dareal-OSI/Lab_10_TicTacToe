import java.util.Scanner;

/**
 * SafeInput - a library of static methods for bulletproof console input.
 * Every input method keeps looping until the user gives a valid value.
 * The Scanner pipe is always the first parameter and the prompt is always second.
 */
public class SafeInput
{
    /**
     * Gets a String that is not zero length.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;
    }

    /**
     * Gets any int value from the user.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return an int value
     */
    public static int getInt(Scanner pipe, String prompt)
    {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt())
            {
                retVal = pipe.nextInt();
                pipe.nextLine(); // clear the newline from the buffer
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a whole number, not: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Gets any double value from the user.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a double value
     */
    public static double getDouble(Scanner pipe, String prompt)
    {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble())
            {
                retVal = pipe.nextDouble();
                pipe.nextLine(); // clear the newline from the buffer
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a number, not: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Gets an int value within an inclusive range [low - high].
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user (the range is added to it)
     * @param low    the lowest valid value
     * @param high   the highest valid value
     * @return an int value from low to high inclusive
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt())
            {
                retVal = pipe.nextInt();
                pipe.nextLine(); // clear the newline from the buffer
                if (retVal >= low && retVal <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You entered " + retVal + ". It must be from " + low + " to " + high + ".");
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a whole number, not: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Gets a double value within an inclusive range [low - high].
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user (the range is added to it)
     * @param low    the lowest valid value
     * @param high   the highest valid value
     * @return a double value from low to high inclusive
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble())
            {
                retVal = pipe.nextDouble();
                pipe.nextLine(); // clear the newline from the buffer
                if (retVal >= low && retVal <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You entered " + retVal + ". It must be from " + low + " to " + high + ".");
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a number, not: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Gets a Yes or No answer. Accepts y, Y, n, or N.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return true for yes, false for no
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        String response = "";
        boolean retVal = false;
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y"))
            {
                retVal = true;
                done = true;
            }
            else if (response.equalsIgnoreCase("N"))
            {
                retVal = false;
                done = true;
            }
            else
            {
                System.out.println("You must answer Y or N, not: " + response);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Gets a String that matches a regular expression pattern.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx  the regEx pattern in java String format to match
     * @return a String that matches the pattern
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        String response = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regEx))
            {
                done = true;
            }
            else
            {
                System.out.println("\"" + response + "\" is not in the right format. Try again.");
            }
        } while (!done);

        return response;
    }

    /**
     * Prints a 60 character wide header with the message centered on the second line
     * and 3 stars at each end of that line.
     *
     * @param msg the message to center in the header
     */
    public static void prettyHeader(String msg)
    {
        final int WIDTH = 60;
        int space = WIDTH - 6 - msg.length(); // room left between the two sets of 3 stars
        int leftPad = space / 2;
        int rightPad = space - leftPad;     // the right side gets the extra space when it is odd

        // Line 1: a full row of stars
        for (int i = 0; i < WIDTH; i++)
        {
            System.out.print("*");
        }
        System.out.println();

        // Line 2: 3 stars, the centered message, 3 stars
        for (int i = 0; i < 3; i++)
        {
            System.out.print("*");
        }
        for (int i = 0; i < leftPad; i++)
        {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < rightPad; i++)
        {
            System.out.print(" ");
        }
        for (int i = 0; i < 3; i++)
        {
            System.out.print("*");
        }
        System.out.println();

        // Line 3: a full row of stars
        for (int i = 0; i < WIDTH; i++)
        {
            System.out.print("*");
        }
        System.out.println();
    }
}
