import java.util.Scanner;
public class SafeInput
{

    /**
     * Gets a non-zero length string input from the user.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString;
        do
        {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine().trim(); // trim to ignore leading/trailing whitespace
        } while (retString.length() == 0);

        return retString;
    }
    public static int getInt(Scanner pipe, String prompt)
    {
        int value = 0;
        boolean isValid = false;
        do
        {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt())
            {
                value = pipe.nextInt();
                isValid = true;
            } else
            {
                System.out.println("Invalid input. Please enter an integer.");
                pipe.nextLine(); // clear the invalid input
            }
        } while (!isValid);
        pipe.nextLine(); // clear newline from Scanner buffer
        return value;
    }
    public static double getDouble(Scanner pipe, String prompt)
    {
        double value = 0.0;
        boolean isValid = false;
        do
        {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble())
            {
                value = pipe.nextDouble();
                isValid = true;
            }
            else
            {
                System.out.println("Invalid input. Please enter a double.");
                pipe.nextLine(); // clear the invalid input
            }
        }
        while (!isValid);
        pipe.nextLine(); // clear newline from Scanner buffer
        return value;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int value = 0;
        boolean isValid = false;
        do
        {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt())
            {
                value = pipe.nextInt();
                if (value >= low && value <= high)
                {
                    isValid = true;
                }
                else
                {
                    System.out.println("Input out of range. Please enter an integer within the specified range.");
                    pipe.nextLine(); // clear the invalid input
                }
            }
            else
            {
                System.out.println("Invalid input. Please enter an integer.");
                pipe.nextLine(); // clear the invalid input
            }
        }
        while (!isValid);
        pipe.nextLine(); // clear newline from Scanner buffer
        return value;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double value = 0.0;
        boolean isValid = false;
        do
        {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble())
            {
                value = pipe.nextDouble();
                if (value >= low && value <= high)
                {
                    isValid = true;
                }
                else
                {
                    System.out.println("Input out of range. Please enter a double within the specified range.");
                    pipe.nextLine(); // clear the invalid input
                }
            }
            else
            {
                System.out.println("Invalid input. Please enter a double.");
                pipe.nextLine(); // clear the invalid input
            }
        }
        while (!isValid);
        pipe.nextLine(); // clear newline from Scanner buffer
        return value;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        boolean isValid = false;
        boolean answer = false;
        do
        {
            System.out.print("\n" + prompt + " [Y/N]: ");
            String input = pipe.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("N"))
            {
                isValid = true;
                answer = input.equals("Y");
            }
            else
            {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
        while (!isValid);
        return answer;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        String value;
        boolean isValid = false;
        do
        {
            System.out.print("\n" + prompt + ": ");
            value = pipe.nextLine().trim();
            if (value.matches(regEx))
            {
                isValid = true;
            }
            else
            {
                System.out.println("Invalid input. Please enter a string matching the pattern: " + regEx);
            }
        }
        while (!isValid);
        return value;
    }

        public static void prettyHeader(String msg)
        {
            final int HEADER_WIDTH = 60;
            int msgLength = msg.length();

            // Calculate spaces needed on each side of the message
            int spacesNeeded = (HEADER_WIDTH - msgLength - 6) / 2; // 6 for the stars and spaces around msg
            int leftSpaces = spacesNeeded;
            int rightSpaces = spacesNeeded;

            // If odd number of spaces needed, add an extra space to the right
            if ((HEADER_WIDTH - msgLength - 6) % 2 != 0)
            {
                rightSpaces++;
            }

            // Print the header
            for (int i = 0; i < HEADER_WIDTH; i++)
            {
                System.out.print("*");
            }
            System.out.println(); // Move to the next line

            // Print the centered message line
            System.out.print("***");
            for (int i = 0; i < leftSpaces; i++)
            {
                System.out.print(" ");
            }
            System.out.print(msg);
            for (int i = 0; i < rightSpaces; i++)
            {
                System.out.print(" ");
            }
            System.out.println("***");

            // Print the footer line
            for (int i = 0; i < HEADER_WIDTH; i++)
            {
                System.out.print("*");
            }
            System.out.println(); // Move to the next line
        }
        /**
        * Converts Celsius to Fahrenheit.
        *
        * @param celsius Celsius temperature to convert
        * @return Equivalent Fahrenheit temperature
        */
    public static double CtoF(double celsius)
    {
        return (celsius * 9 / 5) + 32;
    }





}



