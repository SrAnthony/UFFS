import java.util.*;
public class Janela{

	public int largura = 57;
	public String titulo = "Titulo de janela";
	private String conteudo = "";

	//======= Funções construtoras =======
	public Janela(){}//Padrão 57
	public Janela(int largura){
		this.setLargura(largura);
	}
	public Janela(String titulo){
		this.setTitulo(titulo);
	}
	public Janela(String titulo, int largura){
		this.setTitulo(titulo);
		this.setLargura(largura);
	}
	public String toString(){
		return this.conteudo + this.rodape();
	}
	//====================================

	//======= Getters & Setters =======
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	public void setLargura(int largura){
		this.largura = largura;
	}
	public void limpa(){//Apenas zera o conteudo, não é possível alterar seu valor
		this.conteudo = "";
	}
	//=================================

	//======= Funções públicas =======
	public void topo(boolean logo){
		this.conteudo += "\033\143\n   " + Color.white;
		for(int i=0; i<this.largura; i++)
			this.conteudo += "_";
		this.conteudo += "\n   | " + this.titulo;
		for(int i=0; i<this.largura-(this.titulo.length()+13); i++)
			this.conteudo += " ";
		this.conteudo += "[_][☐][✕] |\n   |";
		for(int i=0; i<this.largura-2; i++)
			this.conteudo += "‾";
		this.conteudo += "|\n";
		if(this.largura > 61 && logo == true){
			this.texto(" █▄▀▄▀▄█", Color.white);
			this.texto(" █░▀░▀░█▄       █ █▀▀█ █▀▀█ █▀▀█ █ █▀▀ 　 █▀▀▄ █▀▀█ █▀▀█", Color.white, 1);
			this.texto(" █░▀░░░█ █   ▄  █ █  █ █▄▄█ █  █   ▀▀█ 　 █▀▀▄ █▄▄█ █▄▄▀", Color.white, 1);
			this.texto(" █░░░▀░█▄▀   █▄▄█ ▀▀▀▀ ▀  ▀ ▀▀▀▀   ▀▀▀ 　 ▀▀▀  ▀  ▀ ▀ ▀▀", Color.white, 1);
			this.texto(" ▀▀▀▀▀▀▀", Color.white);
			this.novaLinha(1);
		}
	}
	public String rodape(){
		String rodape = "   ";
		for(int i=0; i<this.largura; i++)
			rodape += "‾";

		return rodape+"\n    - ";
	}
	public void botaoSimples(String str, String tipo, int tam){
		/* - - - - - - - - - - - - - - - - - - - - - -
		 *  Uso: Janela.botaoSimples("Conteúdo", Color.tipo, tamanho);
		 *  - - - - - - - - - - - - - - - - - - - - - -*/

		//=== Parte de cima do botão
		this.conteudo += "   |    ";
		for(int i=0; i<tam; i++)
			this.conteudo += "_";//Parte de cima do botão
		for(int i=tam+4; i<this.largura-2; i++)
			this.conteudo += " ";
		this.conteudo += "|\n   |   ";

		//=== Parte do meio do botão
		this.conteudo += "|" + Color.white + tipo + str + Color.boldOff + Color.white;
		for(int i=str.length(); i<tam; i++)
			this.conteudo += " ";
		this.conteudo += "|";
		for(int i=tam+4; i<this.largura-3; i++)
			this.conteudo += " ";

		//=== Parte de baixo do botão
		this.conteudo += "|\n   |    ";
		for(int i=0; i<tam; i++)
			this.conteudo += "‾";
		for(int i=tam+4; i<this.largura-2; i++)
			this.conteudo += " ";
		this.conteudo += "|\n";
	}
	public void botao(String op, String str, String tipo, int tam){
		/* - - - - - - - - - - - - - - - - - - - - - -
		 *  Uso: Janela.botao("Prefixo", "Conteúdo", Color.tipo, tamanho);
		 *  - - - - - - - - - - - - - - - - - - - - - -*/
		//=== Corta as strings se forem menores que os botões
		if(str.length()+op.length() > tam-4)
			str = str.substring(0, tam-op.length()-4);

		//=== Parte de cima do botão
		this.conteudo += "   |    ";
		for(int i=0; i<tam; i++)
			this.conteudo += "_";//Parte de cima do botão
		for(int i=tam+4; i<this.largura-2; i++)
			this.conteudo += " ";
		this.conteudo += "|\n   |   ";

		//=== Parte do meio do botão
		this.botaoTexto(op, str, tipo, tam);//Botão 1
		for(int i=tam+4; i<this.largura-3; i++)
			this.conteudo += " ";

		//=== Parte de baixo do botão
		this.conteudo += "|\n   |    ";
		for(int i=0; i<tam; i++)
			this.conteudo += "‾";
		for(int i=tam+4; i<this.largura-2; i++)
			this.conteudo += " ";
		this.conteudo += "|\n";
	}
	public void botao(String op, String str, String tipo, String op2, String str2, String tipo2, int esp, int tam){
		/* - - - - - - - - - - - - - - - - - - - - - -
		 *  Uso: Janela.botao("Prefixo", "Conteúdo", Color.tipo, "Prefixo2", "Conteúdo2", Color.tipo, espaçamento, tamanho);
		 *  - - - - - - - - - - - - - - - - - - - - - -*/
		if(esp < 2) esp = 2;//Espaçamento não pode ser menor que 2

		//=== Corta as strings se forem menores que os botões
		if(str.length()+op.length() > tam-4)
			str = str.substring(0, tam-op.length()-4);
		if(str2.length()+op2.length() > tam-4)
			str2 = str2.substring(0, tam-op2.length()-4);

		//=== Parte de cima dos botões
		this.conteudo += "   |    ";
		for(int i=0; i<tam; i++)
			this.conteudo += "_";//Botão 1
		this.botaoEspacamento(esp, 0);
		for(int i=0; i<tam; i++)
			this.conteudo += "_";//Botão 2
		for(int i=tam+4; i<this.largura-tam-esp-2; i++)
			this.conteudo += " ";
		this.conteudo += "|\n   |   ";

		//=== Parte do meio dos botões
		this.botaoTexto(op, str, tipo, tam);//Botão 1
		this.botaoEspacamento(esp, 2);
		this.botaoTexto(op2, str2, tipo2, tam);//Botão 2
		for(int i=tam+4; i<this.largura-tam-esp-3; i++)
			this.conteudo += " ";
		this.conteudo += "|\n   |    ";

		//=== Parte de baixo dos botões
		for(int i=0; i<tam; i++)//Botão 1
			this.conteudo += "‾";
		this.botaoEspacamento(esp, 0);
		for(int i=0; i<tam; i++)//Botão 2
			this.conteudo += "‾";
		for(int i=tam+4; i<this.largura-tam-esp-2; i++)
			this.conteudo += " ";
		this.conteudo += "|\n";
	}
	public void botao(String op, String str, String tipo, String op2, String str2, String tipo2, String op3, 
					String str3, String tipo3, int esp, int tam){
		/* - - - - - - - - - - - - - - - - - - - - - -
		 *  Uso: Você já entendeu como funciona
		 *  - - - - - - - - - - - - - - - - - - - - - -*/
		if(esp < 2) esp = 2;//Espaçamento não pode ser menor que 2

		//=== Corta as strings se forem menores que os botões
		if(str.length()+op.length() > tam-4)
			str = str.substring(0, tam-op.length()-4);
		if(str2.length()+op2.length() > tam-4)
			str2 = str2.substring(0, tam-op2.length()-4);
		if(str3.length()+op3.length() > tam-4)
			str3 = str3.substring(0, tam-op3.length()-4);

		//=== Parte de cima dos botões
		this.conteudo += "   |    ";
		for(int i=0; i<2; i++)
			this.botaoUpper(tam, esp, '_');//Botões 1 e 2
		for(int i=0; i<tam; i++)
			this.conteudo += "_";//Botão 3
		for(int i=tam+6; i<this.largura-tam*2-esp*2; i++)
			this.conteudo += " ";
		this.conteudo += "|\n   |   ";

		//=== Parte do meio dos botões
		this.botaoTexto(op, str, tipo, tam);//Botão 1
		this.botaoEspacamento(esp, 2);
		this.botaoTexto(op2, str2, tipo2, tam);//Botão 2
		this.botaoEspacamento(esp, 2);
		this.botaoTexto(op3, str3, tipo3, tam);//Botão 3
		for(int i=tam+4; i<this.largura-3-tam*2-esp*2; i++)
			this.conteudo += " ";
		this.conteudo += "|\n   |    ";

		//=== Parte de baixo dos botões
		for(int i=0; i<2; i++)
			this.botaoUpper(tam, esp, '‾');//Botões 1 e 2
		for(int i=0; i<tam; i++)//Botão 3
			this.conteudo += "‾";
		for(int i=tam+4; i<this.largura-2-tam*2-esp*2; i++)
			this.conteudo += " ";
		this.conteudo += "|\n";
	}
	public void texto(String str){
		/* - - - - - - - - - - - - - - - - - - - - - -
		 *  Uso: Janela.texto("Conteúdo");
		 *  - - - - - - - - - - - - - - - - - - - - - -*/
		this.conteudo += "   |    " + Color.white + str;
		for(int i=0; i<this.largura-str.length()-6; i++)
			this.conteudo += " ";
		this.conteudo += "|\n";
	}
	public void texto(String str, String tipo){
		/* - - - - - - - - - - - - - - - - - - - - - -
		 *  Uso: Janela.texto("Conteúdo", Color.tipo);
		 *  - - - - - - - - - - - - - - - - - - - - - -*/
		this.conteudo += "   |    " + tipo + str + Color.boldOff + Color.white;
		for(int i=0; i<this.largura-str.length()-6; i++)
			this.conteudo += " ";
		this.conteudo += "|\n";
	}
	public void texto(String str, String tipo, int n){
		/* - - - - - - - - - - - - - - - - - - - - - -
		 *  Uso: Janela.texto("Conteúdo", Color.tipo, Inteiro);
		 *  - - - - - - - - - - - - - - - - - - - - - -*/
		this.conteudo += "   |    " + tipo + str;
		for(int i=0; i<this.largura-str.length()-6-n; i++)
			this.conteudo += " ";
		this.conteudo += "|\n";
	}
	public void novaLinha(int n){
		/* - - - - - - - - - - - - - - - - - - - - - -
		 *  Uso: Janela.novaLinha(Quantidade);
		 *  - - - - - - - - - - - - - - - - - - - - - -*/
		for(int j=0; j<n; j++){
			this.conteudo += "   |";
			for(int i=0; i<this.largura-2; i++)
				this.conteudo += " ";
			this.conteudo += "|\n";
		}
	}
	public void novaLinha(int n, char c, char m){
		/* - - - - - - - - - - - - - - - - - - - - - -
		 *  Uso: Janela.novaLinha(Quantidade, Caracter para linha, Caracter para meio);
		 *  - - - - - - - - - - - - - - - - - - - - - -*/
		for(int j=0; j<n; j++){
			this.conteudo += "   |";
			for(int i=0; i<this.largura-2; i++)
				if(!(i == this.largura/2))
					this.conteudo += c;
				else
					this.conteudo += m;
			this.conteudo += "|\n";
		}
	}
	//================================
	//======= Funções privadas =======
	private void botaoUpper(int tam, int esp, char c){
		for(int i=0; i<tam; i++)
			this.conteudo += c;//Botão N
		for(int i=0; i<esp; i++)//Espaçamento
			this.conteudo += " ";
	}
	private void botaoEspacamento(int esp, int n){
		for(int i=0; i<esp-n; i++)//Espaçamento
			this.conteudo += " ";
	}
	private void botaoTexto(String op, String str, String tipo, int tam){
		this.conteudo += "| " + Color.white + op + "| " + tipo + str + Color.boldOff + Color.white;
		for(int i=str.length()+op.length()+3; i<tam; i++)
			this.conteudo += " ";
		this.conteudo += "|";
	}
	//================================
}