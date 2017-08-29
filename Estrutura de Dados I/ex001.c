/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
#include <stdio.h>
#include <stdlib.h>
int main(){
	int * P;
	int i, j;
	printf("Digite o tamanho do vetor:\n");
	scanf("%d", &j);

	P = (int *)calloc(j, sizeof(int));
	if(P){

		for(i=0; i < j; i++){
			printf("Digite o valor a posição %d:\n", i);
			scanf("%d", P + i);
		}
		for(i=0; i < j; i++)
			printf("Posição %d - %d\n", i, *(P + i));
	}
	else
		printf("Erro na alocação de memória\n");

	
}