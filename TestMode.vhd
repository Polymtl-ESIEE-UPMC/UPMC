--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   15:40:58 03/21/2016
-- Design Name:   
-- Module Name:   H:/Projet 3E201/Projet_TRAN_TRINH/TestMode.vhd
-- Project Name:  Projet_TRAN_TRINH
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: mode
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
 
ENTITY TestMode IS
END TestMode;
 
ARCHITECTURE behavior OF TestMode IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT mode
    PORT(
         clk25 : IN  std_logic;
         reset : IN  std_logic;
         pause_rqt : IN  std_logic;
         endframe : IN  std_logic;
         lost : IN  std_logic;
         no_brick : IN  std_logic;
         game_lost : OUT  std_logic;
         brick_win : OUT  std_logic;
         pause : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal clk25 : std_logic := '0';
   signal reset : std_logic := '0';
   signal pause_rqt : std_logic := '0';
   signal endframe : std_logic := '0';
   signal lost : std_logic := '0';
   signal no_brick : std_logic := '0';

 	--Outputs
   signal game_lost : std_logic;
   signal brick_win : std_logic;
   signal pause : std_logic;

   -- Clock period definitions
   constant clk25_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: mode PORT MAP (
          clk25 => clk25,
          reset => reset,
          pause_rqt => pause_rqt,
          endframe => endframe,
          lost => lost,
          no_brick => no_brick,
          game_lost => game_lost,
          brick_win => brick_win,
          pause => pause
        );

--   -- Clock process definitions
--   clk25_process :process
--   begin
--		clk25 <= '0';
--		wait for clk25_period/2;
--		clk25 <= '1';
--		wait for clk25_period/2;
--   end process;
-- 
--
--   -- Stimulus process
--   stim_proc: process
--   begin		
--      -- hold reset state for 100 ns.
--      wait for 100 ns;	
--
--      wait for clk25_period*10;
--
--      -- insert stimulus here 
--
--      wait;
--   end process;

	clk25 <= not Clk25 after 100 ns;
	reset <= '1' after 100 ns;
	pause_rqt <= '1' after 10 ms , '0' after 11 ms, '1' after 40ms , '0' after 41 ms, '1' after 45ms , '0' after 46 ms, '1' after 70ms , '0' after 71 ms ; 
   endframe <= not endframe after 170 ns;
	lost <= '1' after 65ms, '0' after 66 ms;


END;
