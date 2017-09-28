/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdio_ext.h>
#define bs "\e[1m"//Bold
#define bf "\e[0m"//Reset
#define TAM 100
typedef struct _produto{
	int codigo;
	char nome[TAM];
	float preco;
}tp_produto;
typedef struct _nodo{
	tp_produto produto;
	struct _nodo *next;
	struct _nodo *prev;
}tp_nodo;
typedef struct _lista{
	int nItens;
	tp_nodo *first;
	tp_nodo *last;
}tp_lista;

void add(tp_lista *lista){
	tp_nodo *nodo = (tp_nodo*)malloc(sizeof(tp_nodo));

	nodo->next = NULL;
	nodo->prev = lista->last;
	
	if(lista->nItens == 0)
		lista->first = nodo;
	else
		(lista->last)->next = nodo;
	lista->last = nodo;


	printf("    Digite o nome do produto: \n    - ");
	getchar();
	fgets(nodo->produto.nome, TAM, stdin);
	nodo->produto.nome[strlen(nodo->produto.nome)-1]='\0';
	printf("    Digite o código do produto (int): \n    - ");
	int x = 0;
	do{
		scanf("%d", &nodo->produto.codigo);
		x = 0;
		for(tp_nodo *loop = lista->first; loop != lista->last; loop = loop->next)
			if(nodo->produto.codigo == loop->produto.codigo){
				printf("    Esse código já está em uso, tente outro (int): \n    - ");
				x = 1;
			}
	}while(x);
	printf("    Digite o preço do produto: \n    - ");
	scanf("%f", &nodo->produto.preco);

	(lista->nItens)++;
}
void del(tp_lista *lista){
	int cod;
	tp_nodo *temp;
	printf("    Digite o código do produto que deseja remover:\n    - ");
	scanf("%d", &cod);
	for(tp_nodo *loop = lista->first; loop != NULL; loop = loop->next){
		if(loop->produto.codigo == cod){
			if(lista->nItens == 1){
				lista->first = NULL;
				lista->last = NULL;
				(lista->nItens)--;
				break;
			}

			if(loop == lista->first){//Precisa reaponterar o first
				temp = lista->first;//Guardar o endereço de first para dar free depois
				lista->first = (lista->first)->next;
				(lista->first)->prev = NULL;
			}
			else if(loop == lista->last){//Precisa reaponterar o last
				temp = lista->last;
				lista->last = (lista->last)->prev;
				(lista->last)->next = NULL;
			}
			else{//Precisa reaponterar o nodo com prev e next
				temp = loop;
				(loop->prev)->next = loop->next;
				(loop->next)->prev = loop->prev;
			}
			free(temp);
			(lista->nItens)--;
		}
	}
}
void delAll(tp_lista *lista){
	lista->first = NULL;
	lista->last = NULL;
	lista->nItens = 0;
}
void view(tp_lista *lista){
	system("clear");
	for(tp_nodo *loop = lista->last; loop != NULL; loop = loop->prev){
		printf("\t   --------------\n\t   .| %s%s%s\n", bs, loop->produto.nome, bf);
		printf("\t   .| - Código: %d\n", loop->produto.codigo);
		printf("\t   .| - Preço: R$ %.2f\n", loop->produto.preco);
	}
	if(lista->nItens){
		printf("\t   --------------\n\t    %s1.%s Adicionar\n", bs, bf);// *** ADD
		printf("\t    %s2.%s Remover\n", bs, bf);// *** DEL
		printf("\t    %s3.%s Remover tudo (%d)\n", bs, bf, lista->nItens);// *** DELALL
	}else{
		printf("\n\t   .| - Lista vazia\n\n");
		printf("\t   --------------\n\t    %s1.%s Adicionar\n", bs, bf);// *** ADD
		printf("\t    %s2.%s \e[9mRemover%s\n", bs, bf, bf);// *** DEL
		printf("\t    %s3.%s \e[9mRemover tudo%s\n", bs, bf, bf);// *** DELALL
	}
	printf("\t    %s0.%s Sair\n\t   --------------\n\n    ", bs, bf);
	
}
int main(){
	int op;
	tp_lista *lista = (tp_lista*)calloc(1, sizeof(tp_lista));
	do{
		view(lista);
		scanf("%d", &op);
		switch(op){
			case 1:
				add(lista);
				break;
			case 2:
				if(lista->nItens) del(lista);
				break;
			case 3:
				if(lista->nItens) delAll(lista);
				break;
			default: break;
		}
	}while(op != 0);
	delAll(lista);
	return 0;
}