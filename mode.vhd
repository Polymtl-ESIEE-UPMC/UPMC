----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:40:13 03/07/2016 
-- Design Name: 
-- Module Name:    mode - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity mode is
    Port ( clk25 : in  STD_LOGIC;
           reset : in  STD_LOGIC;
           pause_rqt : in  STD_LOGIC;
           endframe : in  STD_LOGIC;
           lost : in  STD_LOGIC;
           no_brick : in  STD_LOGIC;
           game_lost : out  STD_LOGIC;
           brick_win : out  STD_LOGIC;
           pause : out  STD_LOGIC);
end mode;

architecture Behavioral of mode is
	type etat is(E0,E1,E2,E3,E4,E5,E6);
	signal EP,EF:etat;
	signal timer_lost: integer :=0;
	signal update_pause: std_logic;
	signal raz_pause: std_logic;
	signal update_lost: std_logic;
	signal raz_lost: std_logic;
	signal fin_tempo: integer :=0;


begin
	process(clk25,Reset)
		begin
			if Reset = '0' then EP <= E0;
			elsif rising_edge(clk25) then EP <= EF;
			end if;
	end process;
	
	process(EP,pause_rqt,endframe,lost,no_brick,timer_lost)
		begin
			case(EP) is
				when	E0 => EF <= E0; if (timer_lost /= 0 and endframe = '1') then EF <= E6;
											elsif pause_rqt = '1' then EF <= E1;
											end if;
				when	E1 => EF <= E1; if (fin_tempo > 1022 and pause_rqt ='0') then EF <= E2;
											end if;
				when	E2 => EF <= E2; if pause_rqt = '1' then EF <= E3;
											elsif no_brick = '1' then EF <= E4;
											elsif lost = '1' then EF <= E5;
											end if;
				when	E3 => EF <= E3; if (pause_rqt = '0' and fin_tempo > 1022) then EF <= E0;
											end if;
				when	E4 => EF <= E4;
				when	E5 => EF <= E0;
				when	E6 => EF <= E6; if (timer_lost = 0) then EF <= E0;
											end if;
			end case;
	end process;
	
	process(EP)
		begin
		case(EP) is
			when E0 => 	pause <= '1'; 
							brick_win <= '0';
							update_pause <= '0';
							raz_pause <= '1';
							raz_lost <= '0';
							update_lost <= '0';
							game_lost <= '0';
			when E1 => 	pause <= '0';
							update_pause <= '1';
							raz_pause <= '0';
			when E2 => 	pause <= '0';
							update_pause <= '0';
							raz_pause <= '1';
			when E3 => 	pause <= '1';
							update_pause <= '1';
							raz_pause <= '0';
			when E4 => 	brick_win <= '1';
							update_pause <= '0';
							raz_pause <= '0';
			when E5 => 	raz_lost <= '1';
							update_lost <= '0';
							update_pause <= '0';
							raz_pause <= '0';
			when E6 => 	game_lost <= '1';
							raz_lost <= '0';
							update_lost <= '1';
		end case;
	end process;
	
	process(clk25,Reset,update_pause, raz_pause)
		begin
			if Reset = '0' then fin_tempo <= 0;
			elsif rising_edge(clk25) then 
				if(raz_pause = '1') then fin_tempo <= 0;
				elsif(update_pause = '1') then fin_tempo <= fin_tempo +1;
				end if;
			end if;
		end process;
	
	process(clk25,Reset,update_lost, raz_lost, endframe)
	begin
		if Reset = '0' then timer_lost <= 0;
			elsif rising_edge(clk25) then 
				if(raz_lost = '1') then timer_lost <= 63;
				elsif(update_lost = '1' and endframe = '1') then timer_lost <= timer_lost -1;
				end if;
			end if;
	end process;
	
	end Behavioral;

