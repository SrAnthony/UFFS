/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
#include <stdio.h>
#include <stdlib.h>
#include <stdio_ext.h>
#define MAX 5
#define bs "\e[1m"//Bold
#define bf "\e[0m"//Reset

typedef struct _pilha{
	int info[MAX];
	int topo;
}pilha;

int verify(pilha* duracell){
	if(duracell->topo == MAX-1)
		return 0;//Com esse retorno não será possível adicionar novos dados
	else if(duracell->topo > -1)
		return 1;//Com esse retorno é possível adicionar e remover dados
	else
		return 2;//Com esse retorno não será possível remover dados
}

void view(pilha* duracell, int del){
	system("clear");
	printf("\t    ____________\n\t    | -------- |\n");
	for(int i=MAX-1; i >= 0; i--){
		if(del == i)
			printf("\t  %d.|   \e[31m%s%04d%s   |\n", i+1, bs, duracell->info[i], bf);
		else
			printf("\t  %d.|   %s%04d%s   |\n", i+1, bs, duracell->info[i], bf);
	}
	printf("\t    /----------\\ \n");
	printf("\n\t   --------------\n");
	if(verify(duracell))
		printf("\t    %s1.%s Adicionar\n", bs, bf);// *** PUSH
	else
		printf("\t    %s1.%s \e[9mAdicionar%s\n", bs, bf, bf);// *** PUSH
	if(verify(duracell) != 2)
		printf("\t    %s2.%s Remover\n", bs, bf);// *** POP
	else
		printf("\t    %s2.%s \e[9mRemover%s\n", bs, bf, bf);// *** POP
	printf("\t    %s0.%s Sair\n", bs, bf);
	printf("\t   --------------\n\n    ");
}

void push(pilha* duracell){
	system("clear");
	view(duracell, -1);
	if(verify(duracell)){
		printf("Digite o valor que deseja adicionar (int): \n    - ");
		scanf("%d", &duracell->info[++duracell->topo]);
	}
}

void pop(pilha* duracell){
	system("clear");
	view(duracell, -1);
	if(verify(duracell) != 2){
		view(duracell, duracell->topo);
		printf("Confirmar exclusão do número %s%d%s? (\e[32mS%s/\e[31mN%s)\n    - ", bs, duracell->info[duracell->topo], bf, bf, bf);
		char sn;
		getchar(); scanf("%c", &sn);
		if(sn == 's')
			duracell->info[duracell->topo--] = 0;
	}
}

int main(){
	int op;
	pilha* duracell = (pilha *)malloc(sizeof(pilha));
	duracell->topo = -1;
	system("clear");
	do{
		view(duracell, -1);		
		scanf("%d", &op);
		switch(op){
			case 1:
				push(duracell);
				break;
			case 2:
				pop(duracell);
				break;
			default:
				break;
		}

	}while(op != 0);
}