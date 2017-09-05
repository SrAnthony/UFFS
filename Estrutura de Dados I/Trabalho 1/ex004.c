/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  ============================
*/
#include <stdio.h>
#include <stdlib.h>
int * uniao(int *v1, int n1, int *v2, int n2){
	int * v3 = (int*)malloc((n1 + n2) * sizeof(int));
	for(int i=0; i < n1; i++){
		*(v3+i) = *(v1+i);
	}
	for(int i=n1, j=0; i < n1+n2; i++, j++){
		*(v3+i) = *(v2+j);
	}

	return v3;
}
int main(){
	int n1, n2;
	printf("Digite a quantidade de números do v1:\n");
	scanf("%d", &n1);
	printf("Digite a quantidade de números do v2:\n");
	scanf("%d", &n2);

	int * v1 = (int*)malloc(n1 * sizeof(int));
	int * v2 = (int*)malloc(n2 * sizeof(int));

	for(int i=0; i < n1; i++){
		printf("-- v1[%d]: ", i);
		scanf("%d", v1+i);
		puts("");
	}
	for(int i=0; i < n2; i++){
		printf("-- v2[%d]: ", i);
		scanf("%d", v2+i);
		puts("");
	}

	int * v3;

	v3 = uniao(v1, n1, v2, n2);
	for(int i=0; i < n1+n2; i++)
		printf("v3[%d] = %d\n", i, *(v3+i));
	free(v1);
	free(v2);
	free(v3);
	return 0;
}