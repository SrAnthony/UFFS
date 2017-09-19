/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
import java.util.Scanner;
public class Conta{
	int numero;
	String dono;
	double saldo;
	String historico;

	public String title(){// ** O código \033\143 serve para limpar o terminal **
		return "\033\143" + "\n\n\t  ─▄───────▄█▄───────▄─\n\t  ▐█▌──▄──█████──▄──▐█▌\n" +
		"\t  ─█──███▄▄███▄▄███──█─\n\t  ░█░░█▄█▄█▀▒▀█▄█▄█░░█░\n\t  ██▄▄█▄█▄█▒▒▒█▄█▄█▄▄██\n\n";
	}
	public void historico(){
		System.out.println(this.title() + "\t» Histórico de movimentações\n" + historico + "\n\t  » Saldo atual: " + this.saldo + "\n");
	}
	public void saldo(){
		System.out.println(this.title() + "\t» O saldo atual da sua conta é de R$ " + this.saldo + "\n");
	}
	public void saque(Scanner in, String now){
		System.out.print(this.title() + "\t» Digite o valor que deseja sacar da sua conta\n\t- ");
		double valor = in.nextDouble();
		if(this.saldo - valor < 0)
			System.out.println(this.title() + "\t» Saldo insuficiente (R$ " + this.saldo + ") ✕\n");
		else{
			this.saldo -= valor;
			System.out.println(this.title() + "\t» Saque de R$ " + valor + " realizado com sucesso! ✓\n");
			this.historico += "\t  » [" + now + "] - R$ " + valor + " (Saque)\n";
		}
	}
	public void deposito(Scanner in, String now){
		System.out.print(this.title() + "\t» Digite o valor que deseja depositar na sua conta\n\t- ");
		double valor = in.nextDouble();
		this.saldo += valor;
		System.out.println(this.title() + "\t» Depósito de R$ " + valor + " realizado com sucesso! ✓\n");
		this.historico += "\t  » [" + now + "] + R$ " + valor + " (Depósito)\n";
	}
	public void transfer(Scanner in, int max, String now, Conta[] conta){
		int dest;
		System.out.print(this.title() + "\t» Digite o número da conta de destino\n\t- ");
		dest = in.nextInt();
		while(dest > max){
			System.out.print(this.title() + "\t» Essa conta não existe, tente novamente\n\t- ");
			dest = in.nextInt();
		}
		System.out.println(this.title() + "\t» Conta selecionada: " + conta[dest].dono);
		System.out.print("\n\t» Digite o valor da transferência\n\t- ");
		double valor = in.nextDouble();
		if(this.saldo - valor < 0)
			System.out.println(this.title() + "\t» Saldo insuficiente (R$ " + this.saldo + ")\n");	
		else{
			this.saldo -= valor;
			conta[dest].saldo += valor;
			System.out.println(this.title() + "\t» O valor R$ " + valor + " foi transferido com sucesso! ✓\n");
			this.historico += "\t  » [" + now + "] - R$ " + valor + " (Transferência para " + conta[dest].dono + ")\n";
			conta[dest].historico += "\t  » [" + now + "] + R$ " + valor + " (Transferência de " + this.dono + ")\n";
		}
	}
	public void info(){
		System.out.println(this.title() + "\t» Dono: " + this.dono);
		System.out.println("\t» Saldo: " + this.saldo);
		System.out.println("\t» Código: " + this.numero + "\n");
	}
}