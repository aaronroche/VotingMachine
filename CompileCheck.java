
/**
 * @author Aaron Roche and Rishi Patel
 */

import java.util.ArrayList;
import java.util.LinkedList;

class CompileCheck
{
    public static void main(String [] args)
    {
        Exception e1 = new UnknownCandidateException("gompei");
        Exception e2 = new CandidateExistsException("gompei");
        Exception e3 = new DuplicateVotesException("gompei");
        ElectionData ED = new ElectionData();

        try
        {
            ED.addCandidate("a");
            ED.addCandidate("b");
            ED.addCandidate("c");
            ED.addCandidate("e");
            ED.addCandidate("f");
        }

        catch (CandidateExistsException e)
        {

        }

        try
        {
            ED.processVote("c","a","f");
        }

        catch (UnknownCandidateException e)
        {

        }

        catch (DuplicateVotesException e)
        {

        }

        try
        {
            ED.processVote("b", "b", "f");
        }

        catch (UnknownCandidateException e)
        {

        }

        catch (DuplicateVotesException e)
        {

        }

        try
        {
            ED.processVote("b", "e", "f");
        }

        catch (UnknownCandidateException e)
        {
            System.out.println("cok");
        }

        catch (DuplicateVotesException e)
        {
            System.out.println("fok");
        }

        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();

        System.out.println ("Congratulations, your program compiled!");

        System.out.println(winner1);
        System.out.println(winner2);

        LinkedList<Integer> nums = new LinkedList<Integer>();
        LinkedList<Integer> nums1 = new LinkedList<Integer>();

        nums.add(1);
        nums.add(2);

        nums1.add(2);
        nums1.add(1);

        System.out.println("list " + (nums1 == nums));
        ArrayList<Integer> hola = new ArrayList<Integer>();
        hola.add(2);
        System.out.println(hola.get(0) + " hi");

    }
}