/*
* Tudo acontece ao mesmo tempo
* Ele vai executar até o tempo definido no initial (#500)
*
* Comando assign faz com q a mudança em uma variavel atinja outra tb
*/
module pisca_leds(input clk_in1, output led_out1);

	reg contador = 0;

	assign led_out1 = contador;

	always @(posedge clk_in1) begin
		contador = ~contador;
	end

endmodule


module testbench;

	wire led;	
	reg clk = 0;

	pisca_leds Leds1(clk,led);

	always #2 clk = ~clk; //Comportamento, a cada 2 un de tempo ele nega a si próprio

	initial begin
		$dumpvars;
		clk <= 0;
		#500; //500 un de tempo
		$finish;
	end

endmodule