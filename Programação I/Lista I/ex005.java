/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*
*  Ele começa a mostrar resultados errados a partir de certo ponto
*  Devido ao tamanho máximo de um número que um dado do tipo int
*  Suporta.
*/
class ex005{
	public static void main(String[] args){
		int fatorial = 1;
		for(int n=1; n <= 40; n++){
			fatorial = 1;
			for(int i=n; i > 0 ; i--)
				fatorial *= i;
			System.out.println("Fatorial de " + n + ": " + fatorial);
		}
	}
}