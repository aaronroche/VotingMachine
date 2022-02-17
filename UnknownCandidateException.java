/**
 * This class is for the UnknownCandidateException and this is thrown if the user votes
 * for a candidate that is not in the ballot
 * @author Aaron Roche and Rishi Patel
 * The unknown candidate's name is stored
 */

public class UnknownCandidateException extends Exception
{
    private String name;

    public UnknownCandidateException(String name)
    {
        this.name = name;
    }

    /**
     * gets the name of the unknown candidate
     * @return a string of the name of the unknown candidate
     */
    public String getName()
    {
        return this.name;
    }
}
