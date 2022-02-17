
/**
 * @author Aaron Roche and Rishi Patel
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class Examples
{
    public Examples()
    {

    }

    ElectionData election()
    {
        ElectionData ED = new ElectionData();
        try
        {
            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");
        }

        catch (CandidateExistsException e)
        {

        }

        try
        {
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("husky", "ziggy", "gompei");
            ED.processVote("husky", "gompei", "ziggy");

        }

        catch(UnknownCandidateException e)
        {

        }

        catch (DuplicateVotesException e)
        {

        }

        return(ED);
    }

    ElectionData electionTie ()
    {
        ElectionData ED = new ElectionData();

        try
        {
            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");
        }

        catch (CandidateExistsException e)
        {

        }

        try
        {
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("husky", "ziggy", "gompei");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
        }

        catch(UnknownCandidateException e)
        {

        }

        catch (DuplicateVotesException e)
        {

        }

        return(ED);
    }

    ElectionData electionUnknown() throws UnknownCandidateException
    {
        ElectionData ED = new ElectionData();

        try
        {
            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");
        }

        catch(CandidateExistsException e)
        {

        }

        try
        {
            ED.processVote("gompei", "jason", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("husky", "gompei", "ziggy");
        }
        catch (UnknownCandidateException e)
        {
            throw e;
        }

        catch(DuplicateVotesException e)
        {

        }

        return(ED);
    }

    ElectionData duplicateUnknown () throws DuplicateVotesException
    {
        ElectionData ED = new ElectionData();

        try
        {
            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");
        }

        catch(CandidateExistsException e)
        {

        }

        try
        {
            ED.processVote("gompei", "ziggy", "ziggy");
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("husky", "gompei", "ziggy");
        }
        catch (UnknownCandidateException e)
        {


        }

        catch(DuplicateVotesException e)
        {
            throw e;
        }

        return(ED);
    }

    ElectionData CandidateExistsElection() throws CandidateExistsException
    {
        ElectionData ED = new ElectionData();

        try
        {
            ED.addCandidate("gompei");
            ED.addCandidate("ziggy");
            ED.addCandidate("ziggy");
        }

        catch(CandidateExistsException e)
        {
            e.print();
            throw e;
        }

        try
        {
            ED.processVote("gompei", "ziggy", "ziggy");
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("husky", "gompei", "ziggy");
        }

        catch (UnknownCandidateException e)
        {


        }

        catch(DuplicateVotesException e)
        {

        }

        return(ED);
    }

    ElectionData electionBothExceptionEdgeCase () throws UnknownCandidateException
    {
        ElectionData ED = new ElectionData();

        try
        {
            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");
        }

        catch (CandidateExistsException e)
        {

        }

        try
        {
            ED.processVote("gompei", "husk", "gompei");
        }

        catch(UnknownCandidateException e)
        {
            throw e;
        }

        catch (DuplicateVotesException e)
        {

        }

        return(ED);
    }

    ElectionData electionEdge()
    {
        ElectionData ED = new ElectionData();

        try
        {
            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");
        }

        catch (CandidateExistsException e)
        {

        }

        try
        {
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("husky", "ziggy", "gompei");
            ED.processVote("ziggy", "gompei", "husky");
        }

        catch(UnknownCandidateException e)
        {

        }

        catch (DuplicateVotesException e)
        {

        }

        return(ED);
    }

    // Test for a clear winner for winner with most first votes
    @Test
    public void testMostFirstWinner()
    {
        assertEquals ("husky", election().findWinnerMostFirstVotes());
    }

    // Test for a clear winner for winner with most points
    @Test
    public void testWinnerByPoints()
    {
        assertEquals ("husky", election().findWinnerMostPoints());
    }

    // Tests when there is a tie for the most first place votes
    @Test
    public void testMostFirstWinnerTie()
    {
        assertEquals ("Runoff Required", electionTie().findWinnerMostFirstVotes());
    }

    // Gompei won the most first-place votes, however the problem says that you must receive more than 50%
    // of the votes, and Gompei received exactly 50, so it needs a runoff.
    @Test
    public void testMostFirstWinnerEdgeCase()
    {
        assertEquals ("Runoff Required", electionEdge().findWinnerMostFirstVotes());
    }

    // Tests when there is a tie for the most points
    @Test
    public void testWinnerByPointsTie()
    {
        assertEquals ("gompei", electionTie().findWinnerMostPoints());
    }

    // Tests when a unknown candidate exception should be thrown
    @Test(expected=UnknownCandidateException.class)
    public void testUnknownCandidateException() throws UnknownCandidateException
    {
        electionUnknown().findWinnerMostPoints();
    }

    // Tests when a duplicate votes exception should be thrown
    @Test(expected=DuplicateVotesException.class)
    public void testDuplicateVotesException() throws DuplicateVotesException
    {
        duplicateUnknown().findWinnerMostPoints();
    }

    // Tests when a candidate votes exception should be thrown
    @Test(expected=CandidateExistsException.class)
    public void testCandidateExistsException() throws CandidateExistsException
    {
        CandidateExistsElection().findWinnerMostPoints();
    }

    // Tests when a duplicate candidate and unknown candidate exists, but we
    // were told to throw the unknown exception before the duplicate votes exception
    @Test(expected=UnknownCandidateException.class)
    public void testCandidateExistsException1() throws UnknownCandidateException
    {
        electionBothExceptionEdgeCase().findWinnerMostPoints();
    }
}