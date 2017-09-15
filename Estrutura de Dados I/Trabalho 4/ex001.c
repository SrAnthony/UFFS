#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdio_ext.h>
#define bs "\e[1m"//Bold
#define bf "\e[0m"//Reset
#define TAM 100
typedef struct produto{
	int codigo;
	char nome[TAM];
	float preco;
}tp_produto;
typedef struct nodo{
	tp_produto produto;
	struct nodo *next;
}tp_nodo;

tp_nodo * add(tp_nodo *nodo){
	tp_nodo * check;
	tp_nodo * temp = (tp_nodo *)malloc(sizeof(tp_nodo));
	temp->next = nodo;

	printf("    Digite o nome do produto: \n    - ");
	getchar();
	fgets(temp->produto.nome, TAM, stdin);
	temp->produto.nome[strlen(temp->produto.nome)-1]='\0';
	printf("    Digite o código do produto (int): \n    - ");
	int x = 0;
	do{
		scanf("%d", &temp->produto.codigo);
		check = nodo;
		x = 0;
		for( ; check != NULL; check=check->next)
			if(temp->produto.codigo == check->produto.codigo){
				printf("    Esse código já está em uso, tente outro (int): \n    - ");
				x = 1;
			}
	}while(x);
	printf("    Digite o preço do produto: \n    - ");
	scanf("%f", &temp->produto.preco);

	return temp;
}

tp_nodo * rem(tp_nodo *nodo){
	tp_nodo * first = nodo;
	tp_nodo * prev = NULL;
	int cod;
	printf("    Digite o código do produto que deseja remover:\n    - ");
	scanf("%d", &cod);

	for( ; nodo != NULL; nodo=nodo->next){
		if(cod == nodo->produto.codigo){
			if(nodo->next == NULL){// *** O primeiro número digitado
				if(prev != NULL){
					nodo = prev;
					nodo->next = NULL;
				}else
					first = NULL;
				break;
			}else if(prev == NULL){// *** O ultimo número digitado
				first = nodo->next;
				break;
			}else{// *** Números no meio
				prev->next = nodo->next;
				break;
			}
		}
		prev = nodo;
	}
	return first;
}

void view(tp_nodo *nodo){
	tp_nodo * temp = nodo;

	system("clear");
	for( ; temp != NULL; temp=temp->next){
		printf("\t   --------------\n\t   .| %s%s%s\n", bs, temp->produto.nome, bf);
		printf("\t   .| - Código: %d\n", temp->produto.codigo);
		printf("\t   .| - Preço: R$ %.2f\n", temp->produto.preco);
	}
	printf("\t   --------------\n\t    %s1.%s Adicionar\n", bs, bf);// *** PUSH
	printf("\t    %s2.%s Remover\n", bs, bf);// *** POP
	printf("\t    %s0.%s Sair\n\t   --------------\n\n    ", bs, bf);
}

int main(){
	int op;
	tp_nodo * nodo = NULL;
	do{
		view(nodo);
		scanf("%d", &op);
		switch(op){
			case 1: 
				nodo = add(nodo);
				break;
			case 2: 
				nodo = rem(nodo);
				break;
			default: break;
		}
	}while(op != 0);
}