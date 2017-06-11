import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class GroupZero extends Group{


	
	public GroupZero(int n){
		super(n);
	}
	
	
	
	public ArrayList<Double> probRange(){
		
		ArrayList<Double> list = new ArrayList<Double>();
		
		for(int i = 0; i < 50; i++){
			
			int np = i+1;
			Group people = new Group(np);
			double bp = people.bProb();
			list.add(bp);
	
		}		
		return list;
	}
	
	
	
	
	@Override
	public void write(){
		
		String data = null;
		for(int i = 0; i < probRange().size(); i++){
			
			int np = i+1;
			double bp = probRange().get(i);
			
			if(np == 1) data = String.format("For %d person, the probability of sharing"
					+ " a birthday is: %.3f", np, bp);
			else
				data = String.format("For %d people, the probability of sharing"
					+ " a birthday is: %.3f", np, bp);
			
			System.out.println(data);
		}

	}
	
	
	@Override
	public void writeFile()throws IOException{
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the name of a file to save the results to.");
		String filename = keyboard.next()+".txt";
		String data = null;
		
		BufferedWriter outfile = new BufferedWriter(new FileWriter(new File(filename)));
		
		for(int i = 0; i < probRange().size(); i++){
			
			int np = i+1;
			double bp = probRange().get(i);
			
			if(np == 1)	data = String.format("For %d person, the probability is: %.3f\n", np, bp);
			else data = String.format("For %d people, the probability is: %.3f\n", np, bp);
			
			outfile.write(data);
		}
		
		outfile.close();
		System.out.println("Your results have been saved to " +filename);
	}
	
	
	
	public static double altProb(double nTrials){
		
		int nInts = 20;
		int nDups = 0;
		int j = 0;
		
		for(int t = 0; t < nTrials; t++){

			int[] nums = sortRand(nInts);

			innerloop:
				for(int i = 0; i < nums.length; i++){
					if(i == nums.length - 1) j = 0;
					else j = i + 1;

					if(nums[i]==nums[j]){
						nDups++;
						break innerloop;
					}	
				}
		}

		return nDups/nTrials;
	}
	
	
	public static double altProb(double nTrials, int nInts){

		int nDups = 0;
		int j = 0;
		
		for(int t = 0; t < nTrials; t++){

			int[] nums = sortRand(nInts);

			innerloop:
				for(int i = 0; i < nums.length; i++){
					if(i == nums.length - 1) j = 0;
					else j = i + 1;

					if(nums[i]==nums[j]){
						nDups++;
						break innerloop;
					}	
				}
		}

		return nDups/nTrials;		
	}
	
	
	@Override
	public void altWrite(){
		
		System.out.println("The algorithm now uses 20 integers and varying number of trials");
		double p10 = altProb(10);
		double p100 = altProb(100);
		double p1000  = altProb(1000);
		
		String str10 = String.format("The probability at 10 trials is: %.3f", p10);
		String str100 = String.format("The probability at 100 trials is: %.3f", p100);
		String str1000 = String.format("The probability at 1000 trials is: %.3f", p1000);
		String comparison = String.format("Compared to the precise value of: %.3f", probRange().get(19));
		
		System.out.println(str10);
		System.out.println(str100);
		System.out.println(str1000);
		System.out.println(comparison);
		
	}
}
