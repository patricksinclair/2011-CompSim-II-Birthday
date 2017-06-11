import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Group {

	private int n;

	public Group(int n){
		this.n = n;
	}

	public int getN(){
		return n;
	}
	public void setN(int n){
		this.n = n;
	}



	public double bProb(){

		//remember to use decimal points!
		double pow = (-1.0*getN()*(getN()-1.0))/(2.0*365.0);

		return 1 - Math.pow(Math.E, pow);
	}


	public void write(){

		String data = String.format("The probability of two people in this group "
				+ "having the same birthday is: %.3f", bProb());

		System.out.println(data);
	}



	public void writeFile() throws IOException{

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the name of a file for these results. "
				+ "The .txt suffix is added for you.");
		String filename = keyboard.next()+".txt";

		BufferedWriter outfile = new BufferedWriter(new FileWriter(new File(filename)));

		String data = String.format("For %d people,  the probability for sharing "
				+ "a birthday is: %.3f\n", getN(), bProb());

		outfile.write(data);

		outfile.close();
		System.out.println("Your results have been saved to " +filename);
	}





	public static Group getData(){

		int np = 0;
		Scanner keyboard = new Scanner(System.in);

		while(true){
			try{
				System.out.println("Enter the number of people in the group. \n"
						+ "Or enter 0 to see a range of predetermined values: ");
				np = keyboard.nextInt();

				if(np >= 0){
					break;
				}
				System.out.println("Group can't have a negative population");
			}catch(InputMismatchException e){
				System.out.println("Please enter an integer!");
				keyboard.nextLine();
			}
		}

		if(np == 0){
			return new GroupZero(np);
		}

		return new Group(np);	
	}



	public static int[] sortRand(int n){

		int[] rands = new int[n];
		int Min = 1;
		int Max = 365;

		for(int i = 0; i < rands.length; i++){

			int ran = Min + (int)(Math.random() * ((Max - Min) + 1));

			rands[i] = ran;	
		}
		Arrays.sort(rands);
		return rands;
	}



	public static int nInts(){

		Scanner keyboard = new Scanner(System.in);
		int nInts = 0;

		while(true){
			try{
				System.out.println("Please enter the number of integers: ");
				nInts = keyboard.nextInt();
				if(nInts > 0) break;
				System.out.println("Value must be at least 1.");
			}catch(InputMismatchException e){
				System.out.println("Please enter an integer.");
				keyboard.nextLine();
			}
		}
		return nInts;
	}



	public static double nTrials(){

		Scanner keyboard = new Scanner(System.in);
		double nTrials = 0;

		while(true){
			try{
				System.out.println("Please enter the number of trials: ");
				nTrials = keyboard.nextDouble();
				if(nTrials > 0) break;
				System.out.println("Value must be at least 1.");
			}catch(InputMismatchException e){
				System.out.println("Please enter an integer.");
				keyboard.nextLine();
			}
		}
		return nTrials;
	}



	
	public static double altProb(){

		int nDups = 0;
		int j = 0;
		int nInts = nInts();
		double nTrials = nTrials();


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




	public void altWrite(){

		double p = altProb();

		String str = String.format("Using the alternate algorithm, the probability here is: %.3f", p);
		System.out.println(str);

	}


}
