
import java.util.Scanner;

public class Minimax {
	static int result = 0;
	public static void main(String[] args){
		boolean repeat = true;
		Scanner sc = new Scanner(System.in);
		while(repeat){
			play();
			System.out.println("Do you want to play again? (Y/N)");
			String ans = sc.nextLine();
			if(ans.equalsIgnoreCase("Y"))
				repeat = true;
			else
				repeat = false;
		}
		System.out.println("Thank to play");
	}
	
	public static void play(){
		Scanner sc = new Scanner(System.in);
		int table[] = {0,0,0,0,0,0,0,0,0};
		while(!isEnd(table)){
			boolean success = false;
			int num = 0 ;
			while(!success){
				System.out.print("Enter num(0-8) : ");
				num = sc.nextInt();
				if(table[num] == 0){
					table[num] = 1;
					success = true;
				}
				else
					success = false;
			}
			if(isEnd(table)){
				printTable(table);
				break;
			}
			num = findMove(table);
			while(!success){
				System.out.print("Enter num(0-8) : ");
				num = sc.nextInt();
				if(table[num] == 0){
					table[num] = 1;
					success = true;
				}
				else
					success = false;
			}
			table[num] = 2;
			printTable(table);
		}
		if(evaluate(table) == 100){
			System.out.println("You win!");
		}else if (evaluate(table) == -100){
			System.out.println("You Lose!");
		}else{
			System.out.println("Draw!");
		}
	}
	
	public static void printTable(int[] table){
	for(int i = 0 ; i < table.length ; i++){
		System.out.print(" | ");
		if(table[i] == 0)
			System.out.print(" ");
		else if(table[i] == 1)
			System.out.print("x");
		else if(table[i] == 2)
			System.out.print("o");
		if((i+1)%3 == 0)
			System.out.println(" |");
	}
	System.out.println();
}
	
	public static int evaluate(int[] table){
		if( (table[0] == 1 && table[1] == 1 && table[2] == 1) || 
			    (table[3] == 1 && table[4] == 1 && table[5] == 1) ||
			    (table[6] == 1 && table[7] == 1 && table[8] == 1) ||
			    (table[0] == 1 && table[3] == 1 && table[6] == 1) || 
			    (table[1] == 1 && table[4] == 1 && table[7] == 1) ||
			    (table[2] == 1 && table[5] == 1 && table[8] == 1) ||
			    (table[0] == 1 && table[4] == 1 && table[8] == 1) ||
			    (table[2] == 1 && table[4] == 1 && table[6] == 1) ){
				return 100;
		}else if((table[0] == 2 && table[1] == 2 && table[2] == 2) || 
			    (table[3] == 2 && table[4] == 2 && table[5] == 2) ||
			    (table[6] == 2 && table[7] == 2 && table[8] == 2) ||
			    (table[0] == 2 && table[3] == 2 && table[6] == 2) || 
			    (table[1] == 2 && table[4] == 2 && table[7] == 2) ||
			    (table[2] == 2 && table[5] == 2 && table[8] == 2) ||
			    (table[0] == 2 && table[4] == 2 && table[8] == 2) ||
			    (table[2] == 2 && table[4] == 2 && table[6] == 2)){
				return -100;
		}
		else {
			return 0;
		}
	}
	
	public static boolean isAvailableMove(int[] table){
		for(int i = 0 ; i < table.length ; i++){
			if(table[i] == 0){
				return true;
			}
		}
		return false;
	}
	
	public static int findMove(int[] table){
		int bestMove = 1000;
		int move = -1;
		int num = -1;
		for(int i = 0 ; i < table.length ; i++){
			if(table[i] == 0){
				table[i] = 2;
				move = minimax(table,0,1);
				System.out.print(" " + move+ " ");
				table[i] = 0;
				if(move < bestMove){
					bestMove = move;
					num = i;
				}
			}
		}
		System.out.println();
		return num;
	}
	
	public static int minimax(int[] table,int depth, int turn){
		int score = evaluate(table);
		if(score == 100)
			return score-depth;
		if(score == -100)
			return score+depth;
		if(!isAvailableMove(table))
			return 0;
		
		if(turn == 1){
			int bestMax = -1000;
			for(int i = 0 ; i < table.length ; i++){
				if(table[i] == 0){
					table[i] = 1;
					bestMax = Math.max(minimax(table,depth+1,2), bestMax);
					table[i] = 0;
				}
			}
			return bestMax;
		}else {
			int bestMin = 1000;
			for(int i = 0 ; i < table.length ; i++){
				if(table[i] == 0){
					table[i] = 2;
					bestMin = Math.min(minimax(table,depth+1,1), bestMin);
					table[i] = 0;
				}
			}
			return bestMin;
		}
	}

	public static boolean isEnd(int table[]){
		if(evaluate(table) == 100 || evaluate(table) == -100 || !isAvailableMove(table))
			return true;
		else return false;
	}
}