public class Bebida{
	public String nome;
	public float teorAlcoolico;
	public float preco;
	public int qntMl;
	private int qnt;

	//======= Funções construtoras =======
	public Bebida(){}
	public Bebida(String nome, float preco, int qnt){
		this.nome = nome;
		this.preco = preco;
		this.qnt = qnt;
	}
	public Bebida(String nome, float teor, float preco, int qntMl, int qnt){
		this.nome = nome;
		this.teorAlcoolico = teor;
		this.preco = preco;
		this.qntMl = qntMl;
		this.qnt = qnt;
	}
	//====================================

	//======= Getters & Setters =======
	public void addQnt(int qnt){
		this.qnt = qnt > 0 ? (this.qnt + qnt) : (this.qnt - qnt);
	}
	public int getQnt(){
		return this.qnt;
	}
	//=================================

	//======= Funções públicas =======
	public boolean confereEstoque(int qnt){
		return this.qnt - qnt >= 0 ? true : false;
	}
	public boolean comprar(Cliente cliente, int qnt){
		boolean estoque = confereEstoque(qnt);
		if(estoque){
			this.qnt -= qnt;
			cliente.addHistorico("Compra de " + qnt + "x " + this.nome + " por R$ " + (this.preco*qnt));
		}
		return estoque;
	}
	//================================
}