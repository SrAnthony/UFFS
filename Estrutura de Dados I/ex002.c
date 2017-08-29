#include <stdio.h>
#include <stdlib.h>
#include <stdio_ext.h>
typedef struct{
	char nome[50];
	char endereco[100];
	int matricula;
}estudante;
int main(){
	estudante * P;
	int i, j;

	printf("Digite o tamanho do vetor:\n");
	scanf("%d", &j);

	P = (struct estudante *)malloc(j * sizeof(estudante));

	if(P){
		for(i=0; i < j; i++){
			printf("*** Posição %d ***\n", i);
			printf("Digite o valor de nome:\n");
			__fpurge(stdin);
			fgets((P+i)->nome, 50, stdin);
			printf("Digite o valor de endereço\n");
			fgets((P + i)->endereco, 100, stdin);
			printf("Digite o valor da matrícula:\n");
			scanf("%d", &(P + i)->matricula);
		}
		for(i=0; i < j; i++){
			system("clear");
			printf("\n*** Posição %d ***\n", i);
			printf("Nome: %s", (P + i) -> nome);
			printf("Endereço: %s", (P + i) -> endereco);
			printf("Matrícula: %d\n", (P + i) -> matricula);
		}
	}
	else
		printf("Erro na alocação\n");
}