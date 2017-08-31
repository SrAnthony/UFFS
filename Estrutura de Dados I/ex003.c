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
	int i=0, cont=0, cont2=0, in;

	P = (int *)malloc(5 * sizeof(int));
	if(P){
		while(1){
			if(cont != cont2 + 5){
				printf("Digite o valor a posição %d:\n", i);
				scanf("%d", P + i);
				in = *(P + i);
				if(!in) break;
				i++;
				cont++;
			}else{
				cont2 += 5;
				int * P2 = realloc(P, (cont + 5) * sizeof(int));
				if(P2){
					P = P2;
					printf("\n--- Realocou ---\n\n");
				}
				else{
					printf("Erro na alocação de memória\nImprimindo o que foi armazenado:\n");
					break;
				}
			}
		}
		for(i=0; i < cont; i++)
			printf("Posição %d - %d\n", i, *(P + i));
	}
	else
		printf("Erro na alocação de memória.\n");

	free(P);
	return 0;
}