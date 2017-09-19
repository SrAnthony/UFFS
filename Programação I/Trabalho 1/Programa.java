/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Programa{
	public static String time(){
		DateTimeFormatter form = DateTimeFormatter.ofPattern("HH:mm");
		LocalDateTime data = LocalDateTime.now();
		return data.format(form);
	}
	public static String title(){
		return "\033\143" + "\n\n\t  ─▄───────▄█▄───────▄─\n\t  ▐█▌──▄──█████──▄──▐█▌\n" +
		"\t  ─█──███▄▄███▄▄███──█─\n\t  ░█░░█▄█▄█▀▒▀█▄█▄█░░█░\n\t  ██▄▄█▄█▄█▒▒▒█▄█▄█▄▄██\n\n";
	}
	public static String lock(){
		return "\033\143" + "\n\n\t  ──▄▀▀▀▄───────────────\n\t  ──█───█───────────────\n\t  ─███████─────────▄▀▀▄─\n" +
		"\t  ░██─▀─██░░█▀█▀▀▀▀█░░█░\n\t  ░███▄███░░▀░▀░░░░░▀▀░░\n\n";
	}
	public static int newAccount(Conta[] conta, Scanner in, int num){
		System.out.println(lock() + "\t-= Criando nova conta =-\n");
		System.out.print("\t» Informe seu nome\n\t- ");
		in.nextLine();
		String aux = in.nextLine();
		System.out.print("\t» Tudo certo. Confirmar criação de nova conta? (S/N)\n\t- ");
		char op = in.nextLine().charAt(0);
		if(op == 's' || op == 'S'){
			conta[num] = new Conta();
			conta[num].dono = aux;
			conta[num].numero = num;
			conta[num].historico = "";
			System.out.println(title() + "\t» Bem vindo " + conta[num].dono + "!\n");
			return num;
		}
		return --num;
	}
	public static void mainAccount(Scanner in, int sel, Conta[] conta, int num){
		int op;
		System.out.println(title());
		do{
			System.out.print("\t1. Consultar saldo\n" +
			"\t2. Efetuar saque\n" +
			"\t3. Efetuar depósito\n" +
			"\t4. Efetuar transferência\n" +
			"\t5. Exibir dados da conta\n" +
			"\t6. Extrato de movimentações\n\n" +
			"\t0. Voltar ao menu principal\n\t-  ");
			op = in.nextInt();
			switch(op){
				case 1: conta[sel].saldo(); break;
				case 2: conta[sel].saque(in, time()); break;
				case 3: conta[sel].deposito(in, time()); break;
				case 4: conta[sel].transfer(in, num, time(), conta); break;
				case 5: conta[sel].info(); break;
				case 6: conta[sel].historico(); break;
				default: break;
			}
		}while(op != 0);
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Conta[] conta = new Conta[10];
		int op = 0, num = -1, i;
		do{
			System.out.println(title() + "\t» Selecione a conta que deseja usar\n");
			for(i=0; i <= num; i++)
				System.out.println("\t  » " + i + ". " + conta[i].dono);
			System.out.print("\n\t  » " + i + ". Criar nova conta\n\t  » " + ++i + ". Sair\n\t  - ");
			op = in.nextInt();
			if(op <= num)
				mainAccount(in, op, conta, num);
			else if(op == --i)
				num = newAccount(conta, in, ++num);

		}while(op != i+1);
	}
}