
/**
 * This class is for the DuplicateVotesException and this is thrown if the user votes for a
 * candidate more than once in a single vote.
 * @author Aaron Roche and Rishi Patel
 * The duplicate candidate's name is stored
 */

public class DuplicateVotesException extends Exception
{
    private String name;

    public DuplicateVotesException(String name)
    {
        this.name = name;
    }

    /**
     * gets the name of the duplicate candidate
     * @return a string of the name of the duplicate candidate
     */
    public String getName()
    {
        return this.name;
    }
}