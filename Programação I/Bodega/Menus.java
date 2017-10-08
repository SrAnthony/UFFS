import java.util.*;
public class Menus{
	public static Janela menu = new Janela("BodegaSys - Sistema de gestão para bodegas", 92);
	private static Scanner in = new Scanner(System.in);
	private static ArrayList temp;

	public static void jHeader(String str, String color){
		menu.topo(true);
		menu.texto(str, color);
		if(!str.equals("")) menu.texto("   » Usuário logado: Joãozinho (Gerente)");
		menu.novaLinha(1);
	}
	//======= Telas principais =======
	public static void jAdmin(){
		jHeader("- ♚ Painel de administração", Color.bold+Color.red);
		menu.botao("1", "Bebidas", Color.bold, "2", "Bodegueiros", Color.bold, "3", "Clientes", Color.bold, 11, 20);
		menu.botao("4", "Tela de venda", Color.bold, "5", "Disclaimer", Color.bold, "6", "Empresa", Color.bold, 11, 20);
		menu.novaLinha(1);
		System.out.print(menu);
	}
	public static void jBodegueiros(Empresa empresa){
		temp = empresa.getBodegueiros();
		int op;
		do{
			jHeader("- ♚ Painel de bodegueiros", Color.bold+Color.green);
			jListarBodegueiros();
			menu.novaLinha(1, '-', '#');
			menu.texto("- Opções de bodegueiros", Color.bold);
			menu.botao("1", "Cadastrar", Color.bold, "2", "Visualizar", Color.bold, "3", "Deletar", Color.bold, 5, 24);
			System.out.print(menu);
			op = in.nextInt();
			switch(op){
				case 1:
					jCadastrarFuncionario(empresa);
					break;
				case 2:
					jVisualizarFuncionario(empresa);
					break;
				case 3:
					jDeletarFuncionario(empresa);
					break;
				default:
					break;
			}
			menu.limpa();
		}while(op != 0);
	}
	public static void jClientes(Empresa empresa){
		temp = empresa.getClientes();
		int op;
		do{
			jHeader("- ♚ Painel de clientes", Color.bold+Color.blue);
			jListarClientes();
			menu.novaLinha(1, '-', '#');
			menu.texto("- Opções de clientes", Color.bold);
			menu.botao("1", "Cadastrar", Color.bold, "2", "Deletar", Color.bold, "3", "Histórico", Color.bold, 11, 20);
			System.out.print(menu);
			op = in.nextInt();
			switch(op){
				case 1:
					jCadastrarCliente(empresa);
					break;
				case 2:
					jDeletarCliente(empresa);
					break;
				case 3:
					jHistoricoCliente(empresa);
					break;
				default:
					break;
			}
			menu.limpa();
		}while(op != 0);
	}
	public static void jBebidas(Empresa empresa){
		temp = empresa.getBebidas();
		int op;
		do{
			jHeader("- ♚ Painel de bebidas", Color.bold+Color.yellow);
			jListarBebidas();
			menu.novaLinha(1, '-', '#');
			menu.texto("- Opções de produto", Color.bold);
			menu.botao("1", "Cadastrar", Color.bold, "2", "Visualizar", Color.bold, "3", "Deletar", Color.bold, 5, 24);
			System.out.print(menu);
			op = in.nextInt();
			switch(op){
				case 1:
					jCadastrarBebida(empresa);
					break;
				case 2:
					jVisualizarBebida(empresa);
					break;
				case 3:
					jDeletarBebida(empresa);
					break;
				default:
					break;
			}
			menu.limpa();
		}while(op != 0);
	}
	public static void jVenda(Empresa empresa){
		int op;
		do{
			jHeader("- ♚ Painel de venda", Color.bold+Color.green);
			jListarVendas(empresa);
			menu.novaLinha(1, '-', '#');
			menu.texto("- Opções de venda", Color.bold);
			menu.botao("1", "Nova venda", Color.bold, "2", "Clientes", Color.bold, 11, 20);
			menu.novaLinha(1);
			System.out.print(menu);
			op = in.nextInt();
			switch(op){
				case 1:
					jCadastrarVenda(empresa);
					break;
				case 2:
					jClientes(empresa);
					break;
				default:
					break;
			}
		}while(op != 0);
	}
	public static void jEmpresa(Empresa empresa){
		jHeader("- ♚ Infomações sobre a empresa", Color.bold+Color.green);
		menu.botao("", empresa.nome, Color.bold, 35);
		menu.texto("  - CNPJ: " + empresa.cnpj);
		menu.texto("  - Aberta em: 05/10/2017");
		menu.botao("", "Funcionários", Color.bold, 35);
		menu.texto(" - Joãozinho (Gerente)");
		for(Funcionario fn: empresa.getBodegueiros())
			menu.texto("   - "+fn.nome+" - R$ "+fn.salario);
		menu.novaLinha(1);
		System.out.print(menu);
		in.nextLine();
	}
	//================================

	//======= Telas secundárias =======

	/*= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
	 *                         BODEGUEIROS
	 *= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
	private static void jListarBodegueiros(){
		ArrayList<Funcionario> bod = temp;//Tive que criar outro auxiliar pra colocar o arraylist como funcionario
		int qnt = bod.size();
		int num=0, for3=0;
		if(bod.size() > 0) menu.texto("- Todos os bodegueiros", Color.bold);
		for(int i=0; i < qnt; ){
			if(qnt >= 3){
				menu.botao(num+"", (bod.get(num++)).nome, Color.bold, num+"", bod.get(num++).nome, Color.bold, num+"", bod.get(num++).nome, Color.bold, 5, 24);
				qnt -= 3;
			}else if(qnt >= 2){
				menu.botao(num+"", (bod.get(num++)).nome, Color.bold, num+"", bod.get(num++).nome, Color.bold, 4, 39);
				qnt -= 2;
			}else if(qnt == 1){
				menu.botao(num+"", (bod.get(num++)).nome, Color.bold, 39);
				qnt--;
			}
		}
	}
	private static void jCadastrarFuncionario(Empresa empresa){
		Funcionario funcionario = new Funcionario();
		jHeader("- ♚ Cadastrar funcionário", Color.bold+Color.green);
		menu.novaLinha(1);
		menu.botao("1", "Insira o nome", Color.bold, 25);
		System.out.print(menu);
		in.nextLine();
		funcionario.nome = in.nextLine();
		menu.limpa();

		jHeader("- ♚ Cadastrar funcionário", Color.bold+Color.green);
		menu.botao("Nome", funcionario.nome, Color.bold, 39);
		menu.botao("2", "Insira o CPF", Color.bold, 25);
		System.out.print(menu);
		funcionario.cpf = in.nextLine();
		menu.limpa();

		jHeader("- ♚ Cadastrar funcionário", Color.bold+Color.green);
		menu.botao("Nome", funcionario.nome, Color.bold, "CPF", funcionario.cpf, Color.bold, 4, 39);
		menu.botao("3", "Insira a idade", Color.bold, 25);
		System.out.print(menu);
		funcionario.idade = in.nextInt();
		menu.limpa();

		jHeader("- ♚ Cadastrar funcionário", Color.bold+Color.green);
		menu.botao("Nome", funcionario.nome, Color.bold, "CPF", funcionario.cpf, Color.bold, "Idade", funcionario.idade+"", Color.bold, 5, 24);
		menu.botao("4", "Insira o saláro", Color.bold, 25);
		System.out.print(menu);
		funcionario.salario = in.nextFloat();
		menu.limpa();

		jHeader("- ♚ Cadastrar funcionário", Color.bold+Color.green);
		menu.botao("Nome", funcionario.nome, Color.bold, "CPF", funcionario.cpf, Color.bold, "Idade", funcionario.idade+"", Color.bold, 5, 24);
		menu.botao("Salário", funcionario.salario+"", Color.bold, 24);
		menu.novaLinha(1);
		menu.texto("Funcionário cadastrado com sucesso!", Color.bold+Color.green);
		menu.novaLinha(1);
		System.out.print(menu);
		in.nextLine();
		in.nextLine();

		empresa.contratar(funcionario);
	}
	private static void jVisualizarFuncionario(Empresa empresa){
		jHeader("- ♚ Visualizar funcionário", Color.bold+Color.green);
		jListarBodegueiros();
		menu.novaLinha(1, '-', '#');
		menu.botao("", "Informe qual o profissional que deseja visualizar", Color.white, 60);
		System.out.print(menu);
		if(temp.size() > 0){
			int op = in.nextInt();
			if(temp.size() >= op){
				menu.limpa();
				jHeader("- ♚ Visualizar funcionário", Color.bold+Color.green);
				Funcionario func = empresa.getBodegueiros().get(op);
				menu.botao("Nome ", func.nome, Color.bold, 39);
				menu.texto("- CPF: "+func.cpf);
				menu.texto("- Idade: "+func.idade);
				menu.texto("- Salário: "+func.salario);
				menu.novaLinha(1);
				System.out.print(menu);
				in.nextLine();
				in.nextLine();
			}
		}
	}
	private static void jDeletarFuncionario(Empresa empresa){
		jHeader("- ♚ Deletar funcionário", Color.bold+Color.green);
		jListarBodegueiros();
		menu.novaLinha(1, '-', '#');
		menu.botao("", "Informe qual o profissional que deseja deletar", Color.white, 50);
		System.out.print(menu);
		if(temp.size() > 0){
			int op = in.nextInt();
			if(temp.size() >= op) empresa.demitir((Funcionario)temp.get(op));
		}
	}


	/*= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
	 *                         CLIENTES
	 *= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
	private static void jListarClientes(){
		ArrayList<Cliente> cli = temp;
		int qnt = cli.size();
		int num=0, for3=0, fiado = 0;
		for(Cliente cl: cli)
			if(cl.fiado == true) fiado++;
		menu.botao("Total de clientes: ", qnt+"", Color.bold, "Que podem pagar fiado:", fiado+"", Color.bold, 4, 39);
		if(cli.size() > 0) menu.texto("- Todos os clientes", Color.bold);
		for(int i=0; i < qnt; ){
			if(qnt >= 3){
				menu.botao(num+"", (cli.get(num)).nome + " - " + (cli.get(num++).fiado == true ? "S" : "N"), Color.bold, 
					num+"", cli.get(num).nome + " - " + (cli.get(num++).fiado == true ? "S" : "N"), Color.bold, num+"", 
					cli.get(num).nome + " - " + (cli.get(num++).fiado == true ? "S" : "N"), Color.bold, 5, 24);
				qnt -= 3;
			}else if(qnt >= 2){
				menu.botao(num+"", (cli.get(num)).nome + " - " + (cli.get(num++).fiado == true ? "S" : "N"), Color.bold, 
					num+"", cli.get(num).nome + " - " + (cli.get(num++).fiado == true ? "S" : "N"), Color.bold, 4, 39);
				qnt -= 2;
			}else if(qnt == 1){
				menu.botao(num+"", (cli.get(num)).nome + " - " + (cli.get(num++).fiado == true ? "S" : "N"), Color.bold, 39);
				qnt--;
			}
		}
		System.out.print(menu);
	}
	private static void jCadastrarCliente(Empresa empresa){
		Cliente cliente = new Cliente();
		jHeader("- ♚ Cadastrar cliente", Color.bold+Color.blue);
		menu.novaLinha(1);
		menu.botao("1", "Insira o nome", Color.bold, 25);
		System.out.print(menu);
		in.nextLine();
		cliente.nome = in.nextLine();
		menu.limpa();

		jHeader("- ♚ Cadastrar cliente", Color.bold+Color.blue);
		menu.botao("Nome", cliente.nome, Color.bold, 39);
		menu.botao("2", "Insira o CPF", Color.bold, 25);
		System.out.print(menu);
		cliente.cpf = in.nextLine();
		menu.limpa();

		jHeader("- ♚ Cadastrar cliente", Color.bold+Color.blue);
		menu.botao("Nome", cliente.nome, Color.bold, "CPF", cliente.cpf, Color.bold, 4, 39);
		menu.botao("3", "Pode comprar fiado? (1 ou 0)", Color.bold, 39);
		System.out.print(menu);
		int n = in.nextInt();
		cliente.fiado = n > 0 ? true : false;
		menu.limpa();

		jHeader("- ♚ Cadastrar cliente", Color.bold+Color.blue);
		menu.botao("Nome", cliente.nome, Color.bold, "CPF", cliente.cpf, Color.bold, "Fiado", cliente.fiado+"", Color.bold + (cliente.fiado ? Color.green : Color.red), 5, 24);
		menu.novaLinha(1);
		menu.texto("Cliente cadastrado com sucesso!", Color.bold+Color.green);
		System.out.print(menu);
		in.nextLine();
		in.nextLine();

		empresa.addCliente(cliente);
	}
	private static void jDeletarCliente(Empresa empresa){
		jHeader("- ♚ Deletar cliente", Color.bold+Color.blue);
		jListarClientes();
		menu.novaLinha(1, '-', '#');
		menu.botao("", "Informe qual o cliente que deseja deletar", Color.white, 50);
		System.out.print(menu);
		if(temp.size() > 0){
			int op = in.nextInt();
			if(temp.size() >= op) empresa.delCliente((Cliente)temp.get(op));
		}
	}
	public static void jHistoricoCliente(Empresa empresa){
		jHeader("- ♚ Histórico do cliente", Color.bold+Color.blue);
		jListarClientes();
		menu.novaLinha(1, '-', '#');
		menu.botao("", "Informe qual o cliente que deseja fuçar", Color.white, 50);
		System.out.print(menu);
		if(temp.size() > 0){
			int op = in.nextInt();
			if(temp.size() >= op){
				Cliente cli = empresa.getClientes().get(op);
				menu.limpa();
				jHeader("- ♚ Histórico do cliente", Color.bold+Color.blue);
				menu.botao("Nome", cli.nome+"", Color.bold, "CPF", cli.cpf+"", Color.bold, 4, 39);
				menu.texto("Histórico de compras", Color.bold);
				for(String his: cli.getHistorico())
					menu.texto("- " + his);
				menu.novaLinha(1);
				System.out.print(menu);
				in.nextLine();
				in.nextLine();
			}
		}
	}


	/*= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
	 *                          BEBIDAS
	 *= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
	private static void jListarBebidas(){
		ArrayList<Bebida> beb = temp;
		int qnt = beb.size();
		int num=0, for3=0;
		if(beb.size() > 0) menu.texto("- Todos as bebidas", Color.bold);
		for(int i=0; i < qnt; ){
			if(qnt >= 3){
				menu.botao(num+"", (beb.get(num).getQnt())+"x "+(beb.get(num++)).nome, Color.bold, 
					num+"", (beb.get(num).getQnt())+"x "+beb.get(num++).nome, Color.bold, 
					num+"", (beb.get(num).getQnt())+"x "+beb.get(num++).nome, Color.bold, 5, 24);
				qnt -= 3;
			}else if(qnt >= 2){
				menu.botao(num+"", (beb.get(num).getQnt())+"x "+(beb.get(num++)).nome, Color.bold, 
					num+"", (beb.get(num).getQnt())+"x "+beb.get(num++).nome, Color.bold, 4, 39);
				qnt -= 2;
			}else if(qnt == 1){
				menu.botao(num+"", (beb.get(num).getQnt())+"x "+(beb.get(num++)).nome, Color.bold, 39);
				qnt--;
			}
		}
	}
	private static void jCadastrarBebida(Empresa empresa){
		Bebida bebida = new Bebida();
		jHeader("- ♚ Cadastrar bebida", Color.bold+Color.yellow);
		menu.novaLinha(1);
		menu.botao("1", "Insira o nome", Color.bold, 25);
		System.out.print(menu);
		in.nextLine();
		bebida.nome = in.nextLine();
		menu.limpa();

		jHeader("- ♚ Cadastrar bebida", Color.bold+Color.yellow);
		menu.botao("Nome", bebida.nome, Color.bold, 39);
		menu.botao("2", "Insira o teor alcoolico", Color.bold, 25);
		System.out.print(menu);
		bebida.teorAlcoolico = in.nextFloat();
		menu.limpa();

		jHeader("- ♚ Cadastrar bebida", Color.bold+Color.yellow);
		menu.botao("Nome", bebida.nome, Color.bold, "Teor", bebida.teorAlcoolico+"", Color.bold, 4, 39);
		menu.botao("3", "Insira o preço", Color.bold, 25);
		System.out.print(menu);
		bebida.preco = in.nextFloat();
		menu.limpa();

		jHeader("- ♚ Cadastrar bebida", Color.bold+Color.yellow);
		menu.botao("Nome", bebida.nome, Color.bold, "Teor", bebida.teorAlcoolico+"", Color.bold, "Preço", bebida.preco+"", Color.bold, 5, 24);
		menu.botao("3", "Insira o volume", Color.bold, 25);
		System.out.print(menu);
		bebida.qntMl = in.nextInt();
		menu.limpa();

		jHeader("- ♚ Cadastrar bebida", Color.bold+Color.yellow);
		menu.botao("Nome", bebida.nome, Color.bold, "Teor", bebida.teorAlcoolico+"", Color.bold, "Preço", bebida.preco+"", Color.bold, 5, 24);
		menu.botao("Volume", bebida.qntMl+" ml", Color.bold, 39);
		menu.botao("3", "Insira o estoque atual", Color.bold, 27);
		System.out.print(menu);
		bebida.addQnt(in.nextInt());
		menu.limpa();

		jHeader("- ♚ Cadastrar bebida", Color.bold+Color.yellow);
		menu.botao("Nome", bebida.nome, Color.bold, "Teor", bebida.teorAlcoolico+"", Color.bold, "Preço", bebida.preco+"", Color.bold, 5, 24);
		menu.botao("Volume", bebida.qntMl+" ml", Color.bold, "Estoque", bebida.getQnt()+"", Color.bold, 4, 39);
		menu.novaLinha(1);
		menu.texto("Bebida cadastrada com sucesso!", Color.bold+Color.green);
		System.out.print(menu);
		in.nextLine();
		in.nextLine();

		empresa.addBebida(bebida);
	}
	private static void jVisualizarBebida(Empresa empresa){
		menu.limpa();
		jHeader("- ♚ Visualizar bebida", Color.bold+Color.yellow);
		temp = empresa.getBebidas();
		int op;
		jListarBebidas();
		menu.novaLinha(1, '-', '#');
		menu.botao("", "Informe a bebida", Color.bold, 25);
		System.out.print(menu);
		menu.limpa();
		jHeader("- ♚ Visualizar bebida", Color.bold+Color.yellow);
		int beb = in.nextInt();
		Bebida bebida = empresa.getBebidas().get(beb);
		menu.botao("Nome ", bebida.nome+"", Color.bold, 25);
		menu.texto("- Teor Alcoolico: "+bebida.teorAlcoolico);
		menu.texto("- Volume: "+bebida.qntMl);
		menu.texto("- Estoque: "+bebida.getQnt());
		menu.texto("- Preço: "+bebida.preco);
		menu.novaLinha(1);
		System.out.print(menu);
		in.nextLine();
		in.nextLine();
	}
	private static void jDeletarBebida(Empresa empresa){
		jHeader("- ♚ Deletar bebida", Color.bold+Color.yellow);
		jListarBebidas();
		menu.novaLinha(1, '-', '#');
		menu.botao("", "Informe qual a bebida que deseja deletar", Color.white, 50);
		System.out.print(menu);
		if(temp.size() > 0){
			int op = in.nextInt();
			if(temp.size() >= op) empresa.delBebida((Bebida)temp.get(op));
		}
	}


	/*= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
	 *                          BEBIDAS
	 *= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
	private static void jListarVendas(Empresa empresa){
		ArrayList<String> his = empresa.getHistorico();
		int qnt = his.size();
		int num=0, for3=0;
		if(his.size() > 0) menu.texto("- Últimas vendas", Color.bold);
		if(qnt >= 3)
			menu.botao("", his.get(qnt--), Color.bold, "", his.get(qnt--), Color.bold, "", his.get(qnt--), Color.bold, 5, 24);
		else if(qnt >= 2)
			menu.botao("", his.get(qnt--), Color.bold, "", his.get(qnt--), Color.bold, 4, 39);
		else if(qnt == 1)
			menu.botao("", his.get(qnt--), Color.bold, 39);
	}
	private static void jCadastrarVenda(Empresa empresa){
		jHeader("- ♚ Nova venda", Color.bold+Color.green);
		temp = empresa.getClientes();//Preciso setar o temp para clientes para printá-los
		jListarClientes();
		menu.botao("1", "Informe o cliente", Color.bold, 24);
		System.out.print(menu);
		int cli = in.nextInt();
		menu.limpa();

		jHeader("- ♚ Nova venda", Color.bold+Color.green);
		temp = empresa.getBebidas();
		jListarBebidas();
		menu.novaLinha(1, '-', '#');
		menu.botao("Cliente", empresa.getClientes().get(cli).nome, Color.bold, 39);
		menu.botao("2", "Informe a bebida", Color.bold, 24);
		System.out.print(menu);
		int beb = in.nextInt();
		menu.limpa();

		jHeader("- ♚ Nova venda", Color.bold+Color.green);
		menu.botao("Cliente", empresa.getClientes().get(cli).nome, Color.bold, "Produto", empresa.getBebidas().get(beb).nome, Color.bold, 4, 39);
		menu.botao("3", "Informe a quantidade", Color.bold, 26);
		System.out.print(menu);
		int qnt = in.nextInt();
		menu.limpa();

		jHeader("- ♚ Nova venda", Color.bold+Color.green);
		menu.botao("Cliente", empresa.getClientes().get(cli).nome, Color.bold, "Produto", qnt+"x "+empresa.getBebidas().get(beb).nome, Color.bold, 4, 39);
		if(empresa.getBebidas().get(beb).comprar(empresa.getClientes().get(cli), qnt)){
			menu.texto("Venda realizada com sucesso!", Color.bold+Color.green);
			menu.novaLinha(1);
		}
		else{
			menu.texto("Estoque do produto insuficiente para realizar a venda", Color.bold+Color.red);
			menu.novaLinha(1);
		}
		System.out.print(menu);
		in.nextLine();
		in.nextLine();

	}
	//=================================
}