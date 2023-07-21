package v1;

import javax.swing.JOptionPane;

public class main {
	
	static Date date = new Date();

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null,"Welcome to CubsRadar! Developed by Fernando! The Date is : " + date +"\nPulling game data for today now!");
		DataPuller dp = new DataPuller();
		dp.pull();
	}

}
