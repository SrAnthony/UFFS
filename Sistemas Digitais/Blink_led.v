/*
*  ============================
*  Anthony Nadaletti - https://github.com/SrAnthony
*  Bárbara Pegoraro  - https://github.com/barbs-pm
*  Pâmela Gheno      - https://github.com/pamelgh
*  ============================
*/

module blink(
    input CLOCK_50,
    input [3:0] KEY,
    output [7:0] LEDG
);
    reg [26:0] contador = 0;
    reg state = 1;
    
    assign LEDG[0] = state;
    assign LEDG[1] = state;
    assign LEDG[2] = state;
    assign LEDG[3] = state;
    assign LEDG[4] = state;
    assign LEDG[5] = state;
    assign LEDG[6] = state;
    assign LEDG[7] = state;
     
     reg [32:0] MAX = 50000000;

    always @(posedge CLOCK_50) begin
        
        if(~KEY[0])
            MAX <= 50000000;    
        else if(~KEY[1])
            MAX <= 2500000;
        else if(~KEY[2])
            MAX <= 100000000;
        else if(~KEY[3])
            MAX <= 16666666;
            
        if(contador >= MAX) begin
             state <= ~state;
             contador <= 0;
        end
        else
            contador <= contador + 1;
        
     end

endmodule