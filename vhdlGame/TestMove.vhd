--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   17:11:07 02/22/2016
-- Design Name:   
-- Module Name:   H:/3E201/Mini-Projet/Projet_TRAN_TRINH/TestMove.vhd
-- Project Name:  Projet_TRAN_TRINH
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: move
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
 
ENTITY TestMove IS
END TestMove;
 
ARCHITECTURE behavior OF TestMove IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT move
    PORT(
         Clk25 : IN  std_logic;
         Reset : IN  std_logic;
         QA : IN  std_logic;
         QB : IN  std_logic;
         Rot_Left : OUT  std_logic;
         Rot_Right : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal Clk25 : std_logic := '0';
   signal Reset : std_logic := '0';
   signal QA : std_logic := '0';
   signal QB : std_logic := '0';

 	--Outputs
   signal Rot_Left : std_logic;
   signal Rot_Right : std_logic;

   -- Clock period definitions
   constant Clk25_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: move PORT MAP (
          Clk25 => Clk25,
          Reset => Reset,
          QA => QA,
          QB => QB,
          Rot_Left => Rot_Left,
          Rot_Right => Rot_Right
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
	QA <= '1' after 60ns, '0' after 160ns, '1' after 220ns, '0' after 300ns, '1' after 380ns, '0' after 480ns, '1' after 560ns; 
	QB <= '1' after 80ns, '0' after 180ns, '0' after 280ns, '1' after 360ns, '0' after 420ns, '1' after 520ns;

END;
