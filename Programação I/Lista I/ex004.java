/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
class ex004{
	public static void main(String[] args){
		int fatorial=1;
		for(int n=1; n <= 10; n++){
			fatorial = 1;
			for(int i=n; i > 0 ; i--)
				fatorial *= i;
			System.out.println("Fatorial de " + n + ": " + fatorial);
		}
	}
}