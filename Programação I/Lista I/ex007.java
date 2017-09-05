/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
import java.util.Scanner;
class ex007{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		do{
			x = x % 2 == 0 ? x/2 : 3 * x + 1;
			System.out.print(x + " -> ");
		}while(x != 1);
		System.out.println("");
	}
}