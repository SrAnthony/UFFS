/*
*  ============================
*  Anthony Nadaletti
*  https://github.com/SrAnthony
*  Bárbara Pegoraro
*  https://github.com/barbs-pm
*  ============================
*
*  Exemplo de triângulo:
*  a(25,45) | b(5,25) | c(45,5)
*
*/
#include <stdio.h>
#include <stdlib.h>
#include <stdio_ext.h>
#include <math.h>
typedef struct{
	int x, y;
}Ponto;

int area(Ponto a, Ponto b, Ponto c){
	return ((a.x * b.y) + (a.y * c.x) + (b.x * c.y)) - ((c.x * b.y) + (b.x * a.y) + (a.x * c.y));
}

int main(){
	Ponto q, a, b, c;
	int i, j, AB, AC, BC;
	system("clear");
	printf("   --- 50 x 50 ---\n");
	printf("-| Informações do primeiro ponto (x,y)\n-| ");
	scanf("%d %d",&a.x, &a.y);

	printf("-| Informações do segundo ponto (x,y)\n-| ");
    scanf("%d %d",&b.x, &b.y);

	printf("-| Informações do terceiro ponto (x,y)\n-| ");
    scanf("%d %d",&c.x, &c.y);
	
	int tam = area(a, b, c);
	system("clear");
	printf("                   \e[1mA área é: %d\e[0m\n  ", tam <= 0 ? -tam : tam);
	for(i = 0; i < 50; i++) printf("_");
	printf("\n |      a(%2d,%2d)   |   b(%2d,%2d)   |   c(%2d,%2d)      |\n |", a.x, a.y, b.x, b.y, c.x, c.y);
	for(i = 0; i < 50; i++) printf("‾");
	printf("|\n ");

	for(i = 0; i < 50; i++){
		printf("|");
		for(j = 0; j < 50; j++){

			q.x = i;
			q.y = j;

			AB = area(a, b, q);
			if(AB <= 0) AB = -AB;
		
			AC = area(a, c, q);
			if(AC <= 0) AC = -AC;
			
			BC = area(b, c, q);
			if(BC <= 0) BC = -BC;

			if(AB + AC + BC <= tam)
				printf("*");
			else
				printf(" ");
		}
		printf("|\n ");
	}
	printf(" ");
	for(i = 0; i < 50; i++)
		printf("‾");
	printf("\n");
	return 0;
}
