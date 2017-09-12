/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
#include <stdio.h>
#include <stdlib.h>
#include <stdio_ext.h>
#include <string.h>
#define TAM 50
#define bs "\e[1m"//Bold
#define bf "\e[0m"//Reset

typedef struct _produto{//criação de uma struct tipo produto
	int cod;
	char nome[TAM];
	float preco;
}tp_produto;
typedef struct _pilha{//criação de uma struct tipo pilha
	tp_produto *info;
	int topo;
}pilha;

int verify(pilha* duracell, int MAX){
	if(duracell->topo == MAX-1)
		return 0;//Com esse retorno não será possível adicionar novos dados
	else if(duracell->topo > -1)
		return 1;//Com esse retorno é possível adicionar e remover dados
	else
		return 2;//Com esse retorno não será possível remover dados
}

void view(pilha* duracell, int del, int MAX){
	system("clear");
	printf("\t    _____________________\n\t    | ----------------- |\n");
	for(int i=MAX-1; i >= 0; i--){
		if((duracell->info + i)->cod != 0){
			if(del == i){
				printf("\t %2d.| \e[31m%s%s%s\n", i+1, bs, (duracell->info + i)->nome, bf);
				printf("\t   .| - Código: %d\n", (duracell->info + i)->cod);
				printf("\t   .| - Preço: R$ %.2f\n", (duracell->info + i)->preco);
			}else{
				printf("\t %2d.| %s%s%s\n", i+1, bs, (duracell->info + i)->nome, bf);
				printf("\t   .| - Código: %d\n", (duracell->info + i)->cod);
				printf("\t   .| - Preço: R$ %.2f\n", (duracell->info + i)->preco);
			}
		}
	}
	printf("\t    /-------------------\\ \n\n\t   ----------------\n");
	if(verify(duracell, MAX))
		printf("\t    %s1.%s Adicionar\n", bs, bf);// *** PUSH
	else
		printf("\t    %s1.%s \e[9mAdicionar%s (Pilha cheia)\n", bs, bf, bf);// *** PUSH
	if(verify(duracell, MAX) != 2)
		printf("\t    %s2.%s Remover\n", bs, bf);// *** POP
	else
		printf("\t    %s2.%s \e[9mRemover%s (Pilha vazia)\n", bs, bf, bf);// *** POP
	printf("\t    %s0.%s Sair\n\t   --------------\n\n    ", bs, bf);
}

void push(pilha* duracell, int MAX){
	system("clear");
	view(duracell, -1, MAX);
	if(verify(duracell, MAX)){
		duracell->topo++;
		printf("Digite o nome do produto: \n    - ");
		getchar();
		fgets((duracell->info + duracell->topo)->nome, TAM, stdin);
		(duracell->info + duracell->topo)->nome[strlen((duracell->info + duracell->topo)->nome)-1]='\0';
		printf("    Digite o código do produto (int): \n    - ");
		scanf("%d", &(duracell->info + duracell->topo)->cod);
		printf("    Digite o preço do produto: \n    - ");
		scanf("%f", &(duracell->info + duracell->topo)->preco);
	}
}

void pop(pilha* duracell, int MAX){
	system("clear");
	view(duracell, -1, MAX);
	if(verify(duracell, MAX) != 2){
		view(duracell, duracell->topo, MAX);
		printf("Confirmar exclusão do produto com código %s%d%s? (\e[32mS%s/\e[31mN%s)\n    - ", bs, (duracell->info->cod+duracell->topo), bf, bf, bf);
		char sn;
		getchar(); scanf("%c", &sn);
		if(sn == 's' || sn == 'S')
			(duracell->info + (duracell->topo--))->cod = 0;
	}
}

int main(){
	int op, MAX;
	system("clear");
	printf("Digite quantos produtos deseja cadastrar\n    - ");
	scanf("%d", &MAX);
	pilha* duracell = (pilha *)malloc(sizeof(pilha));
	duracell->info = (tp_produto*)malloc(MAX * sizeof(tp_produto));
	duracell->topo = -1;
	system("clear");
	do{ 
		view(duracell, -1, MAX);		
		scanf("%d", &op);
		switch(op){
			case 1:
				push(duracell, MAX);
				break;
			case 2:
				pop(duracell, MAX);
				break;
			default:
				break;
		}

	}while(op != 0);
	free(duracell->info);
	free(duracell);
}