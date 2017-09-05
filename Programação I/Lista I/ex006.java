/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
class ex006{
	public static void main(String[] args){
		int n0 = 0, n1 = 1, n2 = 0;
		System.out.printf("0, 1, ");
		while(n2 <= 100){
			n2 = n0 + n1;
			System.out.print(n2 + ", ");
			n0 = n1; n1 = n2;
		}
	}
}