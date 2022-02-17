
/**
 * @author Aaron Roche and Rishi Patel
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class ElectionData
{
    private LinkedList<String> ballot = new LinkedList<String>();
    Scanner keyboard = new Scanner(System.in);

    private HashMap<String, Integer> firstPlaceVotes = new HashMap<String, Integer>();
    private HashMap<String, Integer> secondPlaceVotes = new HashMap<String, Integer>();
    private HashMap<String, Integer> thirdPlaceVotes = new HashMap<String, Integer>();

    ElectionData()
    {


    }

    /**
     * This method adds a vote to the chosen first, second, and third choice candidate
     * @param one, the name of the first choice candidate
     * @param two, the name of the second choice candidate
     * @param three, the name of the third choice candidate
     * @throws UnknownCandidateException, this exception is thrown when you enter the name of a candidate that is not in the ballot
     * @throws DuplicateVotesException, this exception is thrown when you vote for the same person more than once.
     */
    public void processVote(String one, String two, String three) throws UnknownCandidateException, DuplicateVotesException
    {
        LinkedList<String> tempNames = new LinkedList<String>();

        tempNames.add(one);
        tempNames.add(two);
        tempNames.add(three);

        for(String name:tempNames)
        {
            if(firstPlaceVotes.containsKey(name) == false)
                throw new UnknownCandidateException(name);
        }

        if(one.equals(three))
        {
            throw new DuplicateVotesException(one);
        }

        else if(one.equals(two))
        {
            throw new DuplicateVotesException(one);
        }

        else if(two.equals(three))
        {
            throw new DuplicateVotesException(two);
        }

        else
        {
            firstPlaceVotes.replace(one, (firstPlaceVotes.get(one)+1));
            secondPlaceVotes.replace(two, (secondPlaceVotes.get(two)+1));
            thirdPlaceVotes.replace(three, (thirdPlaceVotes.get(three)+1));
        }
    }

    /**
     * This method adds a candidate to the ballot
     * This method also adds the candidate to the first, second, and third place votes hash map.
     * @param candidateName, candidate name that you want to add to the ballot
     * @throws CandidateExistsException, this exception is thrown if the candidate already exists.
     */
    public void addCandidate(String candidateName) throws CandidateExistsException
    {
        if(firstPlaceVotes.containsKey(candidateName) == true)
        {
            throw new CandidateExistsException(candidateName);
        }

        else
        {
            ballot.add(candidateName);
            firstPlaceVotes.put(candidateName, 0);
            secondPlaceVotes.put(candidateName, 0);
            thirdPlaceVotes.put(candidateName, 0);
        }
    }

    /**
     * This method determines if the person with the most first place votes has more than 50 percent of all the first place votes
     * If the candidate does, than the candidate is the winner
     * @return, a String that represents the candidate that won
     */
    public String findWinnerMostFirstVotes()
    {

        double highest = 0;
        String candidate = "";
        double totalFirst = 0;
        boolean isTie = false;

        for(String name:ballot)
        {
            totalFirst += firstPlaceVotes.get(name);
            if(firstPlaceVotes.get(name) == highest)
                isTie = true;

            if (firstPlaceVotes.get(name) > highest)
            {
                highest = firstPlaceVotes.get(name);
                candidate = name;
                isTie = false;
            }
        }

        if(isTie == true)
        {
            return "Runoff Required";
        }

        if ((highest/totalFirst) > .5)
        {
            return candidate;
        }

        return "Runoff Required";
    }

    /**
     * This method finds the candidate that won by the most number of points
     * @return a String that represents the candidate that won by the most number of points
     */
    public String findWinnerMostPoints()
    {
        int mostPoints = 0;
        String candidate = "";

        for(String name:ballot)
        {
            int total = 0;
            total = ((firstPlaceVotes.get(name)*3)+(secondPlaceVotes.get(name)*2)+(thirdPlaceVotes.get(name)));
            if (total>mostPoints)
            {
                mostPoints = total;
                candidate = name;
            }
        }
        return candidate;
    }

    /**
     * This method is a getter for the ballot linked list since the field is private
     * @return a linked list of the ballot
     */
    public LinkedList<String> getBallot()
    {
        return this.ballot;
    }
}
