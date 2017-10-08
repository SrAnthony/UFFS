/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
import java.util.*;
public class Cliente{
	public String nome;
	public String cpf;
	public boolean fiado;
	private ArrayList<String> historico = new ArrayList<>();

	//======= Funções construtoras =======
	public Cliente(){}
	public Cliente(String nome){
		this.nome = nome;
	}
	//====================================

	//======= Getters & Setters =======
	public void addHistorico(String evento){
		this.historico.add(evento);
	}
	public ArrayList<String> getHistorico(){
		return historico;
	}
	//=================================
}