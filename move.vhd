----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:07:37 02/22/2016 
-- Design Name: 
-- Module Name:    move - Behavioral 
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

entity move is
    Port ( Clk25 : in  STD_LOGIC;
           Reset : in  STD_LOGIC;
           QA : in  STD_LOGIC;
           QB : in  STD_LOGIC;
           Rot_Left : out  STD_LOGIC;
           Rot_Right : out  STD_LOGIC);
end move;

architecture Behavioral of move is
	type etat is(E0,E1,E2,E3,E4,E5);
	signal EP,EF:etat;

begin
	process(clk25,Reset)
	begin
		if Reset = '0' then EP <= E0;
		elsif rising_edge(clk25) then EP <= EF;
		end if;
	end process;
	process(EP,QA,QB)
		begin
		case(EP) is
			when E0 => EF <= E0; if (QA = '1' and QB = '0') then EF <= E1; 
										elsif (QA = '1' and QB = '1') then EF <= E2;
										end if;
			when E1 => EF <= E3; 
			when E2 => EF <= E3;
			when E3 => EF <= E3; if (QA = '0' and QB = '1') then EF <= E4; 
										elsif (QA = '0' and QB = '0') then EF <= E5;
										end if;
			when E4 => EF <= E0;
			when E5 => EF <= E0;
		end case;
	end process;
	process(EP)
		begin
		case(EP) is
			when E0 => Rot_Left <= '0'; Rot_Right <= '0';
			when E1 => Rot_Left <= '1'; Rot_Right <= '0';
			when E2 => Rot_Left <= '0'; Rot_Right <= '1';
			when E3 => Rot_Left <= '0'; Rot_Right <= '0';
			when E4 => Rot_Left <= '1'; Rot_Right <= '0';
			when E5 => Rot_Left <= '0'; Rot_Right <= '1';
		end case;
	end process;
			
end Behavioral;

