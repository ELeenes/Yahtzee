// EmiLee 2020
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Yahtzee {
	public static void main(String[] args) {
		YahtzeeSpel game = new YahtzeeSpel();
		game.welcomeMessage();
		game.spelen();
	}
}
	

class YahtzeeSpel{ 

	static ArrayList<Dobbelsteen> dobbelstenen = new ArrayList<Dobbelsteen>();
	int [] blokkeerArray = {0,0,0,0,0};	
	
	YahtzeeSpel(){                                         // constructor van YahtzeeSpel
		dobbelstenen.add(new Dobbelsteen());
		dobbelstenen.add(new Dobbelsteen());
		dobbelstenen.add(new Dobbelsteen());
		dobbelstenen.add(new Dobbelsteen());
		dobbelstenen.add(new Dobbelsteen());
		
	}
	
	public void spelen() {                                // userInput
		Scanner input = new Scanner(System.in);
		while(true) {
			String userInput = input.next();
			if (userInput.equals("t") ) {
				System.out.println("Your first throw is:\n1 2 3 4 5");
				for(Dobbelsteen ds : dobbelstenen) {
					ds.val = ds.werpen();
					System.out.print(ds.val+" ");
				}
				int total = dobbelstenen.get(0).val + dobbelstenen.get(1).val + dobbelstenen.get(2).val + 
				dobbelstenen.get(3).val + dobbelstenen.get(4).val; 
				System.out.println("\nWhich dice do you want to hold? (0 for none).");
				vasthouden();
				//Worp.worpUitslag();
				System.out.println("\nEnd of this round. Your total is: " + total);
				System.out.println("-------------------------------------------------");
				System.out.println("Press t to start a new round or press q to quit.");
			} else if (userInput.equals("q")) {
				System.out.println("You pressed q, thank you for playing!");
				return;
			} else {
				System.out.println("Woops, something went wrong there. \nPlease only press t or q.");
			}
		}
		
	}
	
	public void vasthouden() {
		for(int i=0; i<blokkeerArray.length; i++) {
			blokkeerArray[i]=0;
		}
		int i = 0;
		Scanner sc = new Scanner(System.in);
		if (sc.equals("0") ) {
			return;
		} else {
			String keuze = sc.nextLine();
			for(int j = 0; j<keuze.length(); j++) {
				char c = keuze.charAt(j);
				String s = Character.toString(c);
				int y = Integer.parseInt(s)-1;
				for(int a = 0; a<blokkeerArray.length; a++) {
					if(y == a) {
					blokkeerArray[a] = 1;
					}
				}
			}
		}
		System.out.println("Your second throw is:\n1 2 3 4 5");
		for(Dobbelsteen ds : dobbelstenen) {
			if(blokkeerArray[i] == 0) {
			ds.val = ds.werpen();
			}
			System.out.print(dobbelstenen.get(i).val+" ");
			i++;
		}
		
	}
	public void welcomeMessage() { 						 // Welkom
		System.out.println("-------------------------------------------------");
		System.out.println(YELLOW + "     Welcome to EmiLee's version of Yahtzee" + RESET);
		System.out.println("-------------------------------------------------");
		System.out.println(" The game consists of a number of rounds and in ");
		System.out.println(" each round you get two rolls with five dice."); 
		System.out.println(" Your objective is to score as many points as");
		System.out.println(" possible. ");
		System.out.println("-------------------------------------------------");
		System.out.println(YELLOW +"                 How to play: " + RESET);
		System.out.println("-------------------------------------------------");
		System.out.println(" Press " + WHITE_UNDERLINED + "t" + RESET + " to start.");
		System.out.println(" You can put your dice on hold by typing its"); 
		System.out.println(" corresponding number.");
		System.out.println(" You can type "  + WHITE_UNDERLINED + "0" + RESET + " if you don’t want to put anything");
		System.out.println(" on hold.");
		System.out.println(" Press "+ WHITE_UNDERLINED +"q" + RESET+ " to end the game.");
		System.out.println("-------------------------------------------------");
		System.out.println(" Press t to start or q to quit");
	}
	
	public static final String YELLOW = "\033[0;33m";
	public static final String WHITE_UNDERLINED = "\033[4;37m";
	public static final String RESET = "\033[0m";
}

class Dobbelsteen{
	int val = this.werpen();
	public int werpen() {
		Random r = new Random();
		int worp = r.nextInt(6);
		worp++;
		return worp;
	}
}

class Worp{
		int[] werpen = new int[5];
		static Speler player1 = new Speler();
		
		 static Worp worpUitslag() {
			Worp worp = new Worp();
			for (int i = 0 ; i < 5 ; i++) {
				worp.werpen[i] = YahtzeeSpel.dobbelstenen.get(i).val;
			}
			
			player1.worpGeschiedenis.add(worp);
			player1.getHistory();
			return worp;
		}
}

class Speler{
		ArrayList<Worp> worpGeschiedenis = new ArrayList<Worp>();
		
		public void getHistory() {
			for(int i=0; i< this.worpGeschiedenis.size();i++) {
				for(int j = 0; j<5; j++) {
					System.out.print(this.worpGeschiedenis.get(i).werpen[j]+ " ");	
				}
			}
		}
}

