import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private final char SIGN_X = 'x';
	private final char SIGN_O = 'o';
	private final char SIGN_EMPTY = '_';
	private int countTableCells = -1;
	private char[][] table;
	private Random random;
	private Scanner scanner;
	
	public static void main(String[] args) {
		new TicTacToe().game();
	}
	
	public TicTacToe() {
		random = new Random();
		scanner = new Scanner(System.in);
	}
	
	public void game() {
		do {
			setCountTableCells();
			initTable();
			while (true) {
				turnHuman();
				System.out.println("Human:");
				printTable();
				if (checkWin(SIGN_X)) {
					System.out.println("YOU WIN!");
					break;
				}
				if (isTableFull()) {
					System.out.println("Sorry, DRAW!");
					break;
				}
				turnAI();
				System.out.println("AI:");
				printTable();
				if (checkWin(SIGN_O)) {
					System.out.println("AI WIN!");
					break;
				}
				if (isTableFull()) {
					System.out.println("Sorry, DRAW!");
					break;
				}
			}
			System.out.println("Repeat game: Yes-1, No-0");
		} while (scanner.nextInt() == 1);
	}
	
	private void initTable() {
		for (int row = 0; row < countTableCells; row++)
			for (int col = 0; col < countTableCells; col++)
				table[row][col] = SIGN_EMPTY;
	}
	
	private void printTable() {
		for (int row = 0; row < countTableCells; row++) {
			for (int col = 0; col < countTableCells; col++)
				System.out.print(table[row][col] + " ");
			System.out.println();
		}
	}
	
	private boolean isCellValid(int x, int y) {
		if (x < 0 || y < 0 || x >= countTableCells || y >= countTableCells)
			return false;
		return table[x][y] == SIGN_EMPTY;
	}
	
	private void setCountTableCells() {
		do {
			System.out.println("Enter count table cells (3-5):");
			if (scanner.hasNextInt()) {
				countTableCells = scanner.nextInt();
			} else {
				scanner.next();
			}
		} while (!(countTableCells >= 3 && countTableCells <= 5));
		table = new char[countTableCells][countTableCells];
	}
	
	private void turnHuman() {
		int x = -1;
		int y = -1;
		do {
			System.out.println("Enter X and Y (1.." + countTableCells + "):");
			if (scanner.hasNextInt()) {
				x = scanner.nextInt() - 1;
			} else {
				scanner.next();
				continue;
			}
			if (scanner.hasNextInt()) {
				y = scanner.nextInt() - 1;
			} else {
				scanner.next();
				continue;
			}
		} while (!isCellValid(x, y));
		
		System.out.println("x:" + x);
		System.out.println("y:" + y);
		table[x][y] = SIGN_X;
	}
	
	private void turnAI() {
		int x, y;
		do {
			x = random.nextInt(countTableCells);
			y = random.nextInt(countTableCells);
		} while (!isCellValid(x, y));
		table[x][y] = SIGN_O;
	}
	
	private boolean checkWin(char dot) {
		int countCellDot = 0;
		for (int row = 0; row < countTableCells; row++) { 
			countCellDot = 0;
			for (int col = 0; col < countTableCells; col++) {
				if (table[row][col] == dot) {
					countCellDot++;
					if (countCellDot == countTableCells) return true;
				}
			}
		}
		
		countCellDot = 0;
		for (int col = 0; col < countTableCells; col++) { 
			countCellDot = 0;
			for (int row = 0; row < countTableCells; row++) {
				if (table[row][col] == dot) {
					countCellDot++;
					if (countCellDot == countTableCells) return true;
				}
			}
		}
		
		countCellDot = 0;
		for (int col = 0; col < countTableCells; col++) { 
			if (table[col][col] == dot) {
				countCellDot++;
				if (countCellDot == countTableCells) return true;
			}
		}
		
		countCellDot = 0;
		for (int col = 0; col < countTableCells; col++) { 
			if (table[col][countTableCells - col - 1] == dot) {
				countCellDot++;
				if (countCellDot == countTableCells) return true;
			}
		}
			 
		return false;
	}
	
	private boolean isTableFull() {
		for (int row = 0; row < countTableCells; row++)
			for (int col = 0; col < countTableCells; col++)
				if (table[row][col] == SIGN_EMPTY)
					return false;
		return true;
	}
	
}