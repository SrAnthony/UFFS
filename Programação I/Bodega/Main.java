/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
import java.util.*;
public class Main{
	public static void inicio(Empresa empresa){
		empresa.contratar(new Funcionario("Marcelo", "830.966.775-22", 27, 2450));
		empresa.contratar(new Funcionario("Thiago", "262.759.217-30", 34, 2450));
		empresa.contratar(new Funcionario("Rodrigo", "767.385.050-86", 25, 2450));
		empresa.addCliente(new Cliente("Anthony Nadaletti"));
		empresa.addCliente(new Cliente("Barbara Pegoraro"));
		empresa.addCliente(new Cliente("Augusto Araujo"));
		empresa.addBebida(new Bebida("Guaraná", 10, 10));
		empresa.addBebida(new Bebida("Whisky", 35, 0));
		empresa.addBebida(new Bebida("Cerveja", 5, 50));
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int op;
		Empresa joaosBar = new Empresa("João's Bar", "19.254.363/0001-81");
		inicio(joaosBar);
		Carregamento.disclaimer(Menus.menu);
		in.nextLine();
		Carregamento.logo1(Menus.menu);
		do{
			Menus.jAdmin();
			op = in.nextInt();
			switch(op){
				case 1:
					Menus.jBebidas(joaosBar);
					break;
				case 2:
					Menus.jBodegueiros(joaosBar);
					break;
				case 3:
					Menus.jClientes(joaosBar);
					break;
				case 4:
					Menus.jVenda(joaosBar);
					break;
				case 5:
					Carregamento.disclaimer(Menus.menu);
					in.nextLine();
					in.nextLine();
					break;
				case 6:
					Menus.jEmpresa(joaosBar);
					break;
				default: break;
			}
		}while(op != 0);
	}
}