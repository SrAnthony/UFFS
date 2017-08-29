//Trabalho 1 de Sistemas Digitais
//Feito por Anthony Nadaletti - 2/2017

module piscaleds1(
	input CLOCK_50, 
	input [3:0] KEY, 
	input [9:0] SW, 
	output [7:0] LEDG, 
	output [7:0] LEDR
);

	reg [25:0] cont = 0;
	reg l = 0;
	assign LEDR = 1;

	always @(posedge CLOCK_50) begin
		cont = cont + 1;
		if(cont == 0)
			l = ~l;
	end


endmodule