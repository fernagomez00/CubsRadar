package v1;

import javax.swing.JOptionPane;

public class main {
	
	static Date date = new Date();

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null,"Welcome to CubsRadar! Developed by Fernando! The current Date is : " + date);
			int confirm = JOptionPane.showConfirmDialog(null, "Would you like to select a date to view?");
			if(confirm == 0) {
				String month = JOptionPane.showInputDialog("Please Enter all values as a number\nPlease Enter the desired month: ");
				String day = JOptionPane.showInputDialog("Please Enter the desired day: ");
				String year = JOptionPane.showInputDialog("Please Enter the desired year: ");
				DataPuller dp = new DataPuller(year, month, day);
			}else {
				DataPuller dp = new DataPuller();
			}
		
	}

}
