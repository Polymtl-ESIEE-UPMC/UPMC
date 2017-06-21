----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:06:15 03/07/2016 
-- Design Name: 
-- Module Name:    game_mgr - Behavioral 
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity game_mgr is
    Port ( Game_Rqt : in  STD_LOGIC;
           Clk25 : in  STD_LOGIC;
           Reset : in  STD_LOGIC;
           Game_Type : out  STD_LOGIC);
end game_mgr;

architecture Behavioral of game_mgr is

	type etat is(E0,E1,E2,E3);
	signal EP,EF:etat;

begin
	process(clk25,Reset)
	begin
		if Reset = '0' then EP <= E0;
		elsif rising_edge(clk25) then EP <= EF;
		end if;
	end process;
	process(EP,Game_Rqt)
		begin
		case(EP) is
			when E0 => if (Game_Rqt='1') then EF <= E1; 
						  else EF <= E0;
						  end if;
			when E1 => if (Game_Rqt='0') then EF <= E2; 
						  else EF <= E1;
						  end if; 
			when E2 => if (Game_Rqt='1') then EF <= E3; 
						  else EF <= E2;
						  end if;
			when E3 => if (Game_Rqt='0') then EF <= E0; 
						  else EF <= E3;
						  end if;
		end case;
	end process;
	process(EP)
		begin
		case(EP) is
			when E0 => Game_Type <= '0';
			when E1 => Game_Type <= '1'; 
			when E2 => Game_Type <= '1';
			when E3 => Game_Type <= '0';
		end case;
	end process;

end Behavioral;

