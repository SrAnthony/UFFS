/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
import java.util.*;
public class Empresa{
	public String nome;
	public String cnpj;
	private float salario;
	private ArrayList<Funcionario> bodegueiros = new ArrayList<>();
	private ArrayList<Bebida> bebidas = new ArrayList<>();
	private ArrayList<Cliente> clientes = new ArrayList<>();
	private ArrayList<String> historico = new ArrayList<>();

	//======= Funções construtoras =======
	public Empresa(String nome, String cnpj){
		this.nome = nome;
		this.cnpj = cnpj;
	}
	//====================================

	//======= Getters & Setters =======
	public ArrayList<Funcionario> getBodegueiros(){
		return this.bodegueiros;
	}
	public ArrayList<Bebida> getBebidas(){
		return this.bebidas;
	}
	public ArrayList<Cliente> getClientes(){
		return this.clientes;
	}
	public void addBebida(Bebida bebida){
		this.bebidas.add(bebida);
	}
	public void delBebida(Bebida bebida){
		this.bebidas.remove(bebida);
	}
	public void addCliente(Cliente cliente){
		this.clientes.add(cliente);
	}
	public void delCliente(Cliente cliente){
		this.clientes.remove(cliente);
	}
	public void addHistorico(String historico){
		this.historico.add(historico);
	}
	public ArrayList<String> getHistorico(){
		return this.historico;
	}
	//=================================

	//======= Funções públicas =======
	public void contratar(Funcionario fn){
		this.bodegueiros.add(fn);
	}
	public void demitir(Funcionario fn){
		this.bodegueiros.remove(fn);
	}
	//================================
	
}