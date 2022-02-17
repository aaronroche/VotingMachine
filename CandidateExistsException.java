
/**
 * This class is for the CandidateExistsException and it is thrown if the user tries to add a candidate that
 * already exists in the ballot
 * @author Aaron Roche and Rishi Patel
 * The existing candidate's name is stored
 */

public class CandidateExistsException extends Exception
{
    private String name;
    public CandidateExistsException(String name)
    {
        this.name = name;
    }

    public void print()
    {

    }
}
