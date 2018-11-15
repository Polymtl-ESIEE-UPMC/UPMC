--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   15:48:12 03/07/2016
-- Design Name:   
-- Module Name:   H:/3E201/Mini-Projet/Projet_TRAN_TRINH/TestGameMgr.vhd
-- Project Name:  Projet_TRAN_TRINH
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: game_mgr
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY TestGameMgr IS
END TestGameMgr;
 
ARCHITECTURE behavior OF TestGameMgr IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT game_mgr
    PORT(
         Game_Rqt : IN  std_logic;
         Clk25 : IN  std_logic;
         Reset : IN  std_logic;
         Game_Type : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal Game_Rqt : std_logic := '0';
   signal Clk25 : std_logic := '0';
   signal Reset : std_logic := '0';

 	--Outputs
   signal Game_Type : std_logic;

   -- Clock period definitions
   constant Clk25_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: game_mgr PORT MAP (
          Game_Rqt => Game_Rqt,
          Clk25 => Clk25,
          Reset => Reset,
          Game_Type => Game_Type
        );

--   -- Clock process definitions
--   Clk25_process :process
--   begin
--		Clk25 <= '0';
--		wait for Clk25_period/2;
--		Clk25 <= '1';
--		wait for Clk25_period/2;
--   end process;
-- 
--
--   -- Stimulus process
--   stim_proc: process
--   begin		
--      -- hold reset state for 100 ns.
--      wait for 100 ns;	
--
--      wait for Clk25_period*10;
--
--      -- insert stimulus here 
--
--      wait;
--   end process;

	Clk25 <= not Clk25 after 10ns;
	Reset <= '1' after 10 ns;
	Game_Rqt <= '1' after 60ns, '0' after 80ns, '1' after 160ns, '0' after 220ns, '1' after 260ns;
	
END;
