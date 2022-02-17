
/**
 * @author Aaron Roche and Rishi Patel
 */

import java.util.Scanner;
public class VotingMachine

{
    Scanner keyboard = new Scanner(System.in);
    Scanner keyboard1 = new Scanner(System.in);
    private ElectionData election;

    public VotingMachine()
    {
        this.election = new ElectionData();
    }

    /**
     * If a unknown candidate excpetion is thrown, this method allows the user to write in this candidate and them to the ballot.
     * If they enter a candidate that already exists a candidate exists exception is thrown
     * @param name, the name of the candidate being added to the ballot.
     */
    public void addWriteIn(String name)
    {
        try
        {
            election.addCandidate(name);
        }

        catch(CandidateExistsException e)
        {
            System.out.println("This candidate already exists");
        }

        System.out.println("You added the candidate successfully");
    }

    /**
     * This method prints out the candidates in the ballot
     */
    public void printBallot()
    {
        System.out.println("The candidates are ");
        for (String s : election.getBallot())
        {
            System.out.println(s);
        }
    }

    /**
     * This method is a user interface that allows the user to vote
     */
    public void screen()
    {
        this.printBallot();
        System.out.println("Who do you want to vote for round 1?");
        String candidate1 = keyboard.next();
        System.out.println("You voted for " + candidate1);

        System.out.println("Who do you want to vote for round 2?");
        String candidate2 = keyboard.next();
        System.out.println("You voted for " + candidate2);

        System.out.println("Who do you want to vote for round 3?");
        String candidate3 = keyboard.next();
        System.out.println("You voted for " + candidate3);

        try
        {
            election.processVote(candidate1,candidate2,candidate3);
        }

        catch(UnknownCandidateException e)
        {
            System.out.println("Would you like to add this candidate name to the ballot");
            System.out.println("Enter Y or y - add the candidate name or any other character to quit");
            String answer = keyboard1.next();
            if(answer.equals("y") || answer.equals("Y"))
            {
                System.out.println("Enter the candidate name");
                String writeIn = keyboard1.next();
                addWriteIn(writeIn);
                this.screen();
                return;
            }

            else
            {
                System.out.println("Thank You!");
            }
        }

        catch(DuplicateVotesException e)
        {
            System.out.println("You cannot vote for the same candidate twice");
            this.screen();
            return;
        }
    }
}
