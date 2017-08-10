/*
* $dumpvars(); - Função que mostra as variáveis no gráfico
*				 Se não houver argumentos, ele mostrará tudo
*
* #<tempo> $finish - O tempo deve ser especificado como unidades de tempo,
*					 $finish serve para que não ocorra um loop infinito
*
* initial begin - É o escopo do código que é executado antes dos loops, deve terminar com um 'end'
*
* always <argumento> begin - É o escopo do código de loop,
*							 'argumento' é o tempo entre cada execução do loop
*							 Também deve sempre acabar com um 'end'
*
* reg = <valor>; - Registrador
* wire = <valor>; - Fio
*/


module onda;

reg clock = 0;
reg x = 0;
reg k = 0;

initial begin
    $dumpvars(0, x, k, clock);
    #100 $finish;
end

always #2 begin
    clock = ~clock;
end

always @(posedge clock) begin
    x = ~x;
end

always @(posedge x) begin
    k = ~k;
end

endmodule
