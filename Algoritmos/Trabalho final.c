#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdio_ext.h>
#define start "\e[97m\n   ________________________________________________________ \n   | \e[1mUFFS - Portal do professor\e[0m\e[97m                 [_][☐][✕] |\n   |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|\n"
#define bC "\e[97m   |    ________________________________                  |\n   |   "
#define bF "\e[97m  |                 |\n   |    ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾                  |\n"
#define end "\e[97m   ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ \n"
#define white "\e[97m"
#define bold "\e[1m"
#define reset "\e[0m\e[97m"
#define M_Al 50 //Máximo de alunos
#define M_Tr 10 //Máximo de turmas
//start = Cabeçalho | bC = Parte de cima do botão | bF = Parte de baixo do botão | end = Rodapé
typedef struct{
	char seg[40], ter[40], qua[40], qui[40], sex[40];
}hr_turma;
typedef struct{
	int codigo;
	char comp_curr[40];
	int fase;
	hr_turma horario;
}turma_cd;
typedef struct{
	double NP1, NP2;
}notas_cd;
typedef struct{
	int turma, matricula;
	char nome[40], cpf[12];
	notas_cd notas;
	double media;
}aluno_cd;
//-- Variáveis globais --
	int cont_turmas=0, cont_alunos=0, cod=20;//cod é puramente visual
	char aux[40];
	turma_cd turma[M_Tr];//Vetor de turmas com tamanho "M_Tr"
	aluno_cd aluno[M_Al];//Vetor de alunos com tamanho "M_Al"
//-----------------------
// -- Começo dos titulos --
void novaLinha(){
	printf("   |                                                      |\n");
}
void title_visu_alunos(){
	system("clear");
	printf("%s%s   | %s\e[34mVisualização   %s                                      |\n", start, white, bold, reset);
}
void title_cad_turm(){
	system("clear");
	printf("%s%s   | %s\e[34mCadastrar turma%s                                      |\n", start, white, bold, reset);
}
void title_cad_aluno(){
	system("clear");
	printf("%s%s   | %s\e[34mCadastrar aluno%s                                      |\n", start, white, bold, reset);
}
void title_inserir_nota(int np){
	void botaoCustom2();
	system("clear");
	printf("%s%s   | %s\e[34mCadastrar nota %s                                      |\n", start, white, bold, reset);
	novaLinha();
	printf("%s", bold);
	if(np == 1) botaoCustom2("", "Cadastrar notas da NP1", "normal");
	else botaoCustom2("", "Cadastrar notas da NP2", "normal");
	printf("%s", reset);
}
void title_exibir_nota(){
	system("clear");
	printf("%s%s   | %s\e[34mExibir nota    %s                                      |\n", start, white, bold, reset);
	novaLinha();
}
void title_media_final(){
	system("clear");
	printf("%s%s   | %s\e[34mMédia final    %s                                      |\n", start, white, bold, reset);
	novaLinha();
}
void title_encerrar_diario(){
	system("clear");
	printf("%s%s   | %s\e[34mEncerrar diário%s                                      |\n", start, white, bold, reset);
	novaLinha();
}
// -- Fim dos titulos --
void botaoCustom(char str[], int cod){//Cria um botão com o tamanho correto da string
	int j, k=0;
	if(strlen(str) > 20){
		printf("   |    ________________________________");
		for(j=strlen(str)-1; j > 20; j--){
			printf("_");
			k++;}
		for(j=18-k; j>0; j--) printf(" ");
		printf("|\n   |   | TR %s%d%s |  %s |%s", bold, cod, reset, str, reset);
		for(j=17-k; j>0; j--) printf(" ");
		printf("|\n   |    ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
		for(j=strlen(str)-1; j > 20; j--) printf("‾");
		for(j=18-k; j>0; j--) printf(" ");
		printf("|\n");
	}else{
		for(j=strlen(str); j<=20; j++) str[j]=' ';
		str[20]='\0';
		printf("%s| TR %s%d%s |  %s%s", bC, bold, cod, reset, str, bF);}
}
void botaoCustom2(char prefix[], char str[], char tipo[]){//Somente imprime a string dentro do bloco
	if(strcmp(tipo, "bold") == 0)
		printf("   |    %s%s%s%s", bold, prefix, str, reset);
	else
		printf("   |    %s%s", prefix, str);
	for(int i=strlen(str)+strlen(prefix); i<50; i++)
		printf(" ");
	printf("|\n");
}
void botaoCustom_int(char prefix[], int num, double num2, char tipo[]){//Somente imprime um inteiro/double dentro do bloco
	char str[40];
	if(num2 != 0.001) sprintf(str, "%.2lf", num2);
	else sprintf(str, "%d", num);
	if(strcmp(tipo, "bold") == 0)
		printf("   |    %s%s%s%s", bold, prefix, str, reset);
	else
		printf("   |    %s%s", prefix, str);
	for(int i=strlen(str)+strlen(prefix); i<50; i++)
		printf(" ");
	printf("|\n");
}
void removeLinha(char str[]){//Remove o '\n' das strings lidas por fgets
	str[strlen(str)-1]='\0';
}
void validadorCPF(char cpf[], int *v){
	int icpf[12];
	int i, somador=0, digito1, result1, result2, digito2, valor;
	for(i=0; i<11; i++)
		icpf[i] = cpf[i] - 48;
	for(i=0; i<9; i++)
		somador += icpf[i] * (10-i);
	result1=somador%11;
	if(result1 == 0 || result1 == 1)
		digito1=0;
	else
		digito1 = 11 - result1;
	somador = 0;
	for(i=0; i<10; i++)
		somador += icpf[i] * (11-i);
	valor = (somador / 11) * 11;
	result2 = somador-valor;
	if(result2 == 0 || result2 == 1)
		digito2 = 0;
	else
		digito2 = 11 - result2;
	if((digito1 == icpf[9]) && (digito2 == icpf[10]))
		*v=1;
	else *v=0;
}
void listaTurmas(){//Lista todas as turmas somente com codigo e comp_curr para escolha do usuario
	if(cont_turmas > 0){
		novaLinha();
		botaoCustom2("", "Turmas disponiveis:", "normal");
		for(int i=0; i <= cont_turmas; i++)
			if(turma[i].codigo == 20+i){
				novaLinha();
				botaoCustom2(" - ", turma[i].comp_curr, "bold");
				botaoCustom_int("    > ID: ", turma[i].codigo, 0.001, "normal");
			}
	}
}
void listaAlunos(){
	if(cont_alunos > 0)
		for(int i=0; i < cont_alunos; i++){
			novaLinha();
			botaoCustom2("  > ", aluno[i].nome, "normal");
			botaoCustom_int("    - ID: ", i, 0.001, "normal");
		}
}
// -- Começo cadastro turmas --
void cadastroTurma_comp(int i){//Componente curricular
	printf("%s| TR %s%d%s |  Digite o componente:%s%s                ", bC, bold, turma[i].codigo, reset, bF, end);
	__fpurge(stdin); fgets(turma[i].comp_curr, 40, stdin);
	removeLinha(turma[i].comp_curr);
	title_cad_turm();
	novaLinha();
	botaoCustom2("> ", turma[i].comp_curr, "bold");
}
void cadastroTurma_fase(int i){//Fase
	printf("%s| TR %s%d%s |  Digite a fase: (1-8)%s%s                ", bC, bold, turma[i].codigo, reset, bF, end);
	scanf("%d", &turma[i].fase);
	sprintf(aux, "%d", turma[i].fase);
	title_cad_turm();
	novaLinha();
	botaoCustom2("> ", turma[i].comp_curr, "bold");
	botaoCustom2(" - Fase: ", aux, "normal");
}
void cadastroTurma_hrs_print(int i){//Impressao horarios
	if(strcmp(turma[i].horario.seg, "0") != 0)
		botaoCustom2("  - Seg: ", turma[i].horario.seg, "normal");
	if(strcmp(turma[i].horario.ter, "0") != 0)
		botaoCustom2("  - Ter: ", turma[i].horario.ter, "normal");
	if(strcmp(turma[i].horario.qua, "0") != 0)
		botaoCustom2("  - Qua: ", turma[i].horario.qua, "normal");
	if(strcmp(turma[i].horario.qui, "0") != 0)
		botaoCustom2("  - Qui: ", turma[i].horario.qui, "normal");
	if(strcmp(turma[i].horario.sex, "0") != 0)
		botaoCustom2("  - Sex: ", turma[i].horario.sex, "normal");
}
void cadastroTurma_hrs(int i){//Horario
	printf("%s| TR %s%d%s |  Digite os horários: %s%s", bC, bold, turma[i].codigo, reset, bF, end);
	printf("      Digite '0' se não existir horário no dia indicado\n");
	printf("        Segunda: ");
		__fpurge(stdin); fgets(turma[i].horario.seg, 40, stdin);
		removeLinha(turma[i].horario.seg);
	printf("        Terça:   ");
		__fpurge(stdin); fgets(turma[i].horario.ter, 40, stdin);
		removeLinha(turma[i].horario.ter);
	printf("        Quarta:  ");
		__fpurge(stdin); fgets(turma[i].horario.qua, 40, stdin);
		removeLinha(turma[i].horario.qua);
	printf("        Quinta:  ");
		__fpurge(stdin); fgets(turma[i].horario.qui, 40, stdin);
		removeLinha(turma[i].horario.qui);
	printf("        Sexta:   ");
		__fpurge(stdin); fgets(turma[i].horario.sex, 40, stdin);
		removeLinha(turma[i].horario.sex);
	title_cad_turm();
	novaLinha();
	botaoCustom2("> ", turma[i].comp_curr, "bold");
	botaoCustom2(" - Fase: ", aux, "normal");
	cadastroTurma_hrs_print(i);
}
void cadastroConf_turma(){//Confirmação cadastro turma (!= aluno)
	char confirm;
	printf("%s| %s\e[32mS %s%sou \e[31mN%s | Confirmar cadastro? %s%s                ", bC, bold, reset, bold, reset, bF, end);
	do{
		scanf(" %c", &confirm);
		if(confirm == 'S' || confirm == 's'){
			cod++;
			cont_turmas++;
			break;
		}else if(confirm == 'N' || confirm == 'n') break;
		else continue;
	}while(1);
}
void cadastroTurma(){
	turma[cont_turmas].codigo=cod;
	title_cad_turm();
	cadastroTurma_comp(cont_turmas);
	cadastroTurma_fase(cont_turmas);
	cadastroTurma_hrs(cont_turmas);
	cadastroConf_turma(cont_turmas);
}
// -- Fim do cadastro de turmas --
void listaTurmas_info(){//Lista todas as turmas com todas as informações
	char str[40], str2[40]; int op;
	title_visu_alunos();
	if(cont_turmas > 0){
		novaLinha();
		botaoCustom2("", "Turmas cadastradas", "normal");
		for(int i=0; i<cont_turmas; i++){
			sprintf(str2, " - %d/2017", turma[i].fase);
			strcpy(str, turma[i].comp_curr);
			strcat(str, str2);
			botaoCustom(str, turma[i].codigo);
			cadastroTurma_hrs_print(i);
			printf("   |                ----------------------                |\n");
		}
	}else{
		printf("%s| %s\e[31m✕%s |  %sVocê precisa de turmas%s  %s", bC, bold, reset, bold, reset, bF);
		printf("%s| 1 |  Cadastrar turma         %s", bC, bF);
	}
	printf("%s| %s\e[31m0%s |  Voltar                  %s%s                ", bC, bold, reset, bF, end);
	scanf("%d", &op);
	if(op == 1) cadastroTurma();
}
// -- Começo do cadastro de alunos --
void cadastroAluno_print(int i, int j, int k, int N){//Imprime nome, turma e matricula se receber 1 1 1 i
	title_cad_aluno();
	novaLinha();
	if(i) botaoCustom2(" - ", aluno[N].nome, "bold");
	if(j) botaoCustom_int("   > Turma: ", aluno[N].turma, 0.001, "normal");
	if(k) botaoCustom_int("   > Matricula: ", aluno[N].matricula, 0.001, "normal");
}
void cadastroAluno_turma(int i){//Turma
	int k=1;
	listaTurmas();
	printf("%s| TR %s--%s |  Informe a turma:    %s%s                ", bC, bold, reset, bF, end);
	do{
		scanf("%d", &aluno[i].turma);
		for(int j=0; j <= cont_turmas; j++)
			if(turma[j].codigo == aluno[i].turma){
				k=0;
				break;
			}
		title_cad_aluno();
		listaTurmas();
		printf("%s| %s\e[31m✕%s |  Turma inválida          %s%s                ", bC, bold, reset, bF, end);
	}while(k);
	title_cad_aluno();
}
void cadastroAluno_nome(int i){//Nome
	printf("%s| TR %s%d%s |  Digite o nome:      %s%s                ", bC, bold, aluno[i].turma, reset, bF, end);
	__fpurge(stdin); fgets(aluno[i].nome, 40, stdin);
	removeLinha(aluno[i].nome);
	cadastroAluno_print(1, 1, 0, i);
}
void cadastroAluno_matr(int i){//Matricula
	printf("%s| TR %s%d%s |  Digite a matrícula: %s%s                 ", bC, bold, aluno[i].turma, reset, bF, end);
	scanf("%d", &aluno[i].matricula);
	cadastroAluno_print(1, 1, 1, i);
}
void cadastroAluno_cpf(int i){//CPF
	int v=0;
	printf("%s| TR %s%d%s |  Digite o CPF: (num) %s%s                ", bC, bold, aluno[i].turma, reset, bF, end);
	do{
		scanf("%s", aluno[i].cpf);
		cadastroAluno_print(1, 1, 1, i);
		validadorCPF(aluno[i].cpf, &v);
		if(v) botaoCustom2("   > CPF: ", aluno[i].cpf, "normal");
		else printf("%s| %s\e[31m✕%s |  %sCPF inválido  %s          %s%s                ", bC, bold, reset, bold, reset, bF, end);
	}while(!v);
}
void cadastroConf_aluno(){//Confirmação alunos (!= turmas)
	char confirm;
	printf("%s| %s\e[32mS %s%sou \e[31mN%s | Confirmar cadastro? %s%s                ", bC, bold, reset, bold, reset, bF, end);
	do{
		scanf(" %c", &confirm);
		if(confirm == 'S' || confirm == 's'){
			cont_alunos++;
			break;
		}
		else if(confirm == 'N' || confirm == 'n') break;
		else{
			printf("\n                ");
			continue;}
	}while(1);
}
void cadastroAluno(){
	int op;
	title_cad_aluno();
	if(cont_turmas != 0){
		aluno[cont_alunos].notas.NP1 = 0.0;//Preciso zerar as notas pra não imprimir lixo de memória
		aluno[cont_alunos].notas.NP2 = 0.0;
		cadastroAluno_turma(cont_alunos);
		cadastroAluno_nome(cont_alunos);
		cadastroAluno_matr(cont_alunos);
		cadastroAluno_cpf(cont_alunos);
		cadastroConf_aluno();
	}else{//Caso queira cadastrar um aluno e estiver sem turmas disponíveis
		printf("%s| %s\e[31m✕%s |  %sVocê precisa de turmas%s  %s", bC, bold, reset, bold, reset, bF);
		printf("%s| 1 |  Cadastrar turma         %s", bC, bF);
		printf("%s| %s\e[31m0%s |  Voltar                  %s%s                ", bC, bold, reset, bF, end);
		scanf("%d", &op);
		if(op == 1) cadastroTurma();
	}
}
// -- Fim do cadastro de alunos --
void precisaAlunos(){
	int op;
	title_visu_alunos();
	printf("%s| %s\e[31m✕%s |  %sVocê precisa de alunos%s  %s", bC, bold, reset, bold, reset, bF);
	printf("%s| 1 |  Cadastrar aluno         %s", bC, bF);
	printf("%s| %s\e[31m0%s |  Voltar                  %s%s                ", bC, bold, reset, bF, end);
	scanf("%d", &op);
	if(op == 1) cadastroAluno();
}
void listaAlunos_info(){//Lista todos os alunos da turma escolhida
	int op, k=1;
	char matr[12], str[40];
	if(cont_alunos > 0){//Se tiver alunos
		title_visu_alunos();
		listaTurmas();
		printf("%s| TR %s--%s |  Informe a turma:    %s%s                ", bC, bold, reset, bF, end);
		do{
			scanf("%d", &op);
			for(int j=0; j <= cont_turmas; j++)
				if(turma[j].codigo == op){
					k=0;
					break;
				}
			title_visu_alunos();
			listaTurmas();
			printf("%s| %s\e[31m✕%s |  Turma inválida          %s%s                ", bC, bold, reset, bF, end);
		}while(k);
		title_visu_alunos();
		novaLinha();
		botaoCustom2("", "Alunos cadastrados:", "normal");
		novaLinha();
		for(int i=0; i <= cont_alunos; i++)
			if(aluno[i].turma == op){
				botaoCustom2("- ", aluno[i].nome, "bold");
				strcpy(str, "CPF: ");
				strcat(str, aluno[i].cpf);
				strcat(str, " - Mat: ");
				sprintf(matr, "%d", aluno[i].matricula);
				strcat(str, matr);
				botaoCustom2("  > ", str, "normal");
				printf("   |                ----------------------                |\n");
			}
		printf("%s| %s\e[31m0%s |  Voltar                  %s%s                ", bC, bold, reset, bF, end);
		scanf("%d", &op);

	}else if(cont_turmas > 0) precisaAlunos();//Se não tiver alunos, mas tiver turmas
	else cadastroAluno();//Se não tiver turmas, vai pedir pra cadastrar uma
}
//-----------------------
// -- Começo das notas --
void inserirNotas_exibir(int i, int j){
	botaoCustom2("  > ", aluno[i].nome, "normal");
	botaoCustom_int("    - ID: ", i, 0.001, "normal");
	botaoCustom_int("    - NP1: ", 1, aluno[i].notas.NP1, "normal");
	if(j==2) botaoCustom_int("    - NP2: ", 1, aluno[i].notas.NP2, "normal");
}
void inserirNP_menu(int P){//Inserir notas na NP2
	int turmaOP;
	title_inserir_nota(P);
	if(cont_alunos > 0){
		listaTurmas();
		novaLinha();
		printf("%s                ", end);
		scanf("%d", &turmaOP);
		title_inserir_nota(P);
		for(int i=0; i < cont_alunos; i++){
			if(aluno[i].turma == turma[turmaOP-20].codigo){
				inserirNotas_exibir(i, P);
				if(P == 1){
					if(aluno[i].notas.NP1 != 0.0) continue;
					printf("%s%s| _ |  Digite a nota da NP1    %s%s                ", reset, bC, bF, end);
					scanf("%lf", &aluno[i].notas.NP1);
				}else{
					if(aluno[i].notas.NP2 != 0.0) continue;
					printf("%s%s| _ |  Digite a nota da NP2    %s%s                ", reset, bC, bF, end);
					scanf("%lf", &aluno[i].notas.NP2);
				}
				title_inserir_nota(P);
				inserirNotas_exibir(i, P);
			}
		}
		title_inserir_nota(P);
		for(int i=0; i < cont_alunos; i++) 
			if(aluno[i].turma == turma[turmaOP-20].codigo) inserirNotas_exibir(i, P);
		printf("%s%s| \e[0m%s\e[32m✓%s | Notas salvas com sucesso!%s%s                ", reset, bC, bold, reset, bF, end);
		system("sleep 2");
	}else if(cont_turmas > 0) precisaAlunos();//Se não tiver alunos, mas tiver turmas
	else cadastroAluno();//Se não tiver turmas, vai pedir pra cadastrar uma
}
void exibirNotas(){
	int op;
	title_exibir_nota();
	for(int i=0; i < cont_alunos; i++)
		inserirNotas_exibir(i, 2);
	printf("%s| %s\e[31m0%s |  Voltar                  %s%s                ", bC, bold, reset, bF, end);
	scanf("%d", &op);
}
// -- Fim das notas --
// -- Começo das médias finais e diario --
int mediasFinais(int H){//Mostra ao usuario as medias finais dos alunos conforme suas notas
	int turmaOP, j=0, k=0;
	char aux[40];
	if(cont_alunos > 0){
		title_media_final();
		listaTurmas();
		novaLinha();
		printf("%s                ", end);
		scanf("%d", &turmaOP);
		title_media_final();
		for(int i=0; i < cont_alunos; i++){
			if(aluno[i].turma == turmaOP){
				botaoCustom2(" > ", aluno[i].nome, "bold");
				sprintf(aux, "Notas: %.2lf e %.2lf", aluno[i].notas.NP1, aluno[i].notas.NP2);
				botaoCustom2("    - ", aux, "normal");
				aluno[i].media = (aluno[i].notas.NP1 + aluno[i].notas.NP2) / 2;
				botaoCustom_int("    - Media: ", 1, aluno[i].media, "normal");
				k=1;
			}
		}
		if(!k){
			title_media_final();
			printf("%s| %s\e[31m✕%s |  Essa turma está vazia   %s", bC, bold, reset, bF);
			j=1;
		}
		novaLinha();
		if(!j) printf("%s%s| \e[0m%s\e[32m✓%s | Médias calculadas!       %s", reset, bC, bold, reset, bF);
		if(H){
			printf("%s| %s\e[31m0%s |  Voltar                  %s%s                ", bC, bold, reset, bF, end);
			scanf("%d", &j);
		}else printf("%s", end);
	}else if(cont_turmas > 0) precisaAlunos();//Se não tiver alunos, mas tiver turmas
	else cadastroAluno();
	return turmaOP;
}
void encerrarDiario(int turmaSel){//Usa a funcao de medias para mostrar se o aluno esta aprovado ou não
	char aux[40];
	int turmaOP;
	title_encerrar_diario();
	for(int i=0; i < cont_alunos; i++){
		if(aluno[i].turma == turmaSel){
			botaoCustom2(" > ", aluno[i].nome, "bold");
			sprintf(aux, "Notas: %.2lf e %.2lf", aluno[i].notas.NP1, aluno[i].notas.NP2);
			botaoCustom2("    - ", aux, "normal");
			botaoCustom_int("    - Media: ", 1, aluno[i].media, "normal");
			if(aluno[i].media >= 6.0){
				printf("\e[32m");
				botaoCustom2("        ", "APROVADO", "bold");}
			else{
				printf("\e[31m");
				botaoCustom2("        ", "REPROVADO", "bold");}
		}
	}
	printf("%s| %s\e[31m0%s |  Voltar                  %s%s                ", bC, bold, reset, bF, end);
	scanf("%d", &turmaOP);
}
// -- Fim das médias finais e diario --
// -- Começo dos menus --
void menuAlunos(){
	int op;
	do{
		system("clear");
		printf("%s   | %s\e[34m- Alunos -          %s                                 |\n", start, bold, reset);
		printf("%s| 1 |  Cadastrar novo aluno    %s", bC, bF);
		printf("%s| 2 |  Listar alunos           %s", bC, bF);
		printf("%s| 3 |  Inserir notas NP1       %s", bC, bF);
		printf("%s| 4 |  Inserir notas NP2       %s", bC, bF);
		printf("%s| 5 |  Exibir notas parciais   %s", bC, bF);
		printf("%s| 6 |  Calcular médias finais  %s", bC, bF);
		printf("%s| %s\e[31m0%s |  Voltar                  %s%s                ", bC, bold, reset, bF, end);
		scanf("%d", &op);
		if(op == 1) cadastroAluno();
		else if(op == 2) listaAlunos_info();
		else if(op == 3) inserirNP_menu(1);
		else if(op == 4) inserirNP_menu(2);
		else if(op == 5) exibirNotas();
		else if(op == 6) mediasFinais(1);
	}while(op != 0);
}
void menuTurmas(){
	int op;
	do{
		system("clear");
		printf("%s   | %s\e[34m- Turmas -          %s                                 |\n", start, bold, reset);
		printf("%s| 1 |  Cadastrar nova turma    %s", bC, bF);
		printf("%s| 2 |  Listar turmas           %s", bC, bF);
		printf("%s| %s\e[31m0%s |  Voltar                  %s%s                ", bC, bold, reset, bF, end);
		scanf("%d", &op);
		if(op == 1) cadastroTurma();
		else if(op == 2) listaTurmas_info();
	}while(op != 0);
}
int main(){
	int op;
	do{
		system("clear");
		printf("%s   | %s\e[34mMenu principal%s                                       |\n", start, bold, reset);
		printf("%s| 1 |  Alunos                  %s", bC, bF);
		printf("%s| 2 |  Turmas                  %s", bC, bF);
		printf("%s| 3 |  Encerrar diário         %s", bC, bF);
		printf("%s| %s\e[31m0%s |  Sair                    %s%s                ", bC, bold, reset, bF, end);
		scanf("%d", &op);
		if(op == 1) menuAlunos();
		else if(op == 2) menuTurmas();
		else if(op == 3) encerrarDiario(mediasFinais(0));
	}while(op != 0);
	return 0;
}
// -- FIM DOS MENUS --