/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
import java.util.*;
public class Carregamento{

	public static void disclaimer(Janela menu){
		//menu.limpa();
		menu.topo(false);
		menu.texto("- DISCLAIMER -", Color.bold);
		menu.novaLinha(1);
		menu.texto("Regras decididas por Joãozin:", Color.bold);
		menu.novaLinha(1);
		menu.texto(Color.green+Color.bold + " ✓" + Color.reset + 
			" Classe Funcionário, com informações dos funcionários que trabalham na bodega", Color.white, -13);
		menu.texto("   - Método de exibição");
		menu.texto(Color.green+Color.bold + " ✓" + Color.reset + 
			" Classe Empresa com Nome e CNPJ da Bodega e:", Color.white, -13);
		menu.texto("   - Método contrata");
		menu.texto("   - Lista de funcionáros");
		menu.texto("   - Lista de produtos");
		menu.texto("   - Lista de clientes");
		menu.texto(Color.green+Color.bold + " ✓" + Color.reset + 
			" Classe Bebida com nome, teor alcoólico, qntd (ml), preço e estoque", Color.white, -13);
		menu.texto("   - Método comprar");
		menu.texto("   - Método vender");
		menu.texto("   - Método confereEstoque");
		menu.texto("   - Método de exibição");
		menu.texto(Color.green+Color.bold + " ✓" + Color.reset + 
			" Classe Cliente com nome, cpf e boolean 'fiado'", Color.white, -13);
		menu.texto(Color.green+Color.bold + " ✓" + Color.reset + 
			" Todas as classe com pelo menos um construtor", Color.white, -13);
		menu.novaLinha(1);
		menu.texto("Extras", Color.bold);
		menu.novaLinha(1);
		menu.texto(" As classes extras são:");
		menu.texto(Color.green+Color.bold + " ✓" + Color.reset + 
			" Classe Carregamento: Mostra essa tela e a de carregamento", Color.white, -13);
		menu.texto(Color.green+Color.bold + " ✓" + Color.reset + 
			" Classe Janela: Possui todos os métodos para criar as janelas", Color.white, -13);
		menu.texto(Color.green+Color.bold + " ✓" + Color.reset + 
			" Classe Menus: Usa a classe Janela criar e armazenar os menus", Color.white, -13);
		menu.texto(Color.green+Color.bold + " ✓" + Color.reset + 
			" Classe Color: Possui strings staticas para colorir os textos", Color.white, -13);
		menu.novaLinha(1);
		System.out.print(menu);
	}

	public static void logo1(Janela menu){
		String porcentagem = "█";
		float count = 0;
		int point = 0;

		for(int i=0; i<menu.largura-10; i++){
			menu.limpa();
			menu.topo(false);
			menu.novaLinha(1);
			menu.texto("      ▄████████████████████████████████████████████████████████████▄▐█▄▄▄▄█▌");
			menu.texto("      ███████ ▄▄█ ▄▄ █ ▄▄ █ ▄▄ █ ▄▄█ ▄▄▄█ ▄▄ █ ▄▄▀█ ▄▄▀█ ▄▄ ████████▌▀▀██▀▀");
			menu.texto("      ███████ ███ ▀▀ █ ▀▀▄█ ▀▀▄█ ▄▄█ █▄ █ ▀▀ █ ██ █ ██ █ ██ █████████▄▄█▌");
			menu.texto("      ████▄██▄▄▄█▄██▄█▄█▄▄█▄█▄▄█▄▄▄█▄▄▄▄█▄██▄█▄██▄█▄▄▄██▄▄▄▄█"
				+ ((point <= 3) ? "█" : "▄") + "█" + ((point <= 6) ? "█" : "▄") + "█" + ((point <= 9) ? "█" : "▄") +"█████▀");
			menu.texto("      ▄▄▄▄▄██████████████████████████████████████████████████████████▀");
			menu.novaLinha(2);
			count += 1.23;
			menu.texto("                                     " + (int)count + "%");
			menu.botaoSimples(porcentagem, Color.white, menu.largura-10);
			porcentagem += "█";
			esperar(50);
			menu.novaLinha(2);
			System.out.print(menu);
			point++;
			if(i%15 == 1) point = 0;
		}
	}
	public static void esperar(int tempo){
		try{
		    Thread.sleep(tempo);
		}catch(InterruptedException ex){
		    Thread.currentThread().interrupt();
		}
	}
}