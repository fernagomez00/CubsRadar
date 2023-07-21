package v1;
import java.sql.Time;

import javax.swing.JOptionPane;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class DataPuller extends Date{
	
	private String date;
	private String aorp = "";
	private final String API_KEY = "30852a756edb4613990469d8c08b51b0";
	private String API_URL = "https://api.sportsdata.io/v3/mlb/scores/json/GamesByDate/" + date + "?key=" + API_KEY;
	
	private String endMessage = "";
	
	public DataPuller() {super();date = super.currentDate.getYear() + "-" + super.currentDate.getMonthValue() + "-" + super.currentDate.getDayOfMonth(); API_URL = "https://api.sportsdata.io/v3/mlb/scores/json/GamesByDate/" + date + "?key=" + API_KEY; pull();}
	public DataPuller(String year, String month, String day) {super(); date = year + "-"+month +"-"+day; System.out.println("Testing: " + date); API_URL = "https://api.sportsdata.io/v3/mlb/scores/json/GamesByDate/" + date + "?key=" + API_KEY; pull();}
	
	public void pull() {
		try {
			System.out.println("Trying...");
            // Fetch the game schedule for the given date
            HttpResponse<String> response = Unirest.get(API_URL + date)
                    .header("User-Agent", "Mozilla/5.0")
                    .header("Ocp-Apim-Subscription-Key", API_KEY)
                    .asString();

            // Check if the request was successful
            if (response.getStatus() == 200) {
                String[] r = response.getBody().split(",");
//                System.out.println("Testing: " + r[0].replace("[{", ""));
                String tempDate = "";
                for(int i = 0; i < r.length; i++) {
                	if(r[i].contains("DateTime")){
                		System.out.println(r[i]);
                		tempDate = r[i];
                	}
                	if(r[i].contains("CHC")) {
                		
                		@SuppressWarnings("deprecation")
						Time time = new Time((int)(Integer.parseInt(tempDate.substring(23,25))-13), Integer.parseInt(tempDate.substring(26,28)), Integer.parseInt(tempDate.substring(29,31)));
                		System.out.println(time);
                		endMessage+= "There is a cubs game on "+ date + " at " + time;
                		break;
                	}
                }
                System.out.println(Integer.parseInt(tempDate.substring(23,24)));
                if(Integer.parseInt(tempDate.substring(23,25)) > 12) {
        			aorp = "PM";
        		}else if(Integer.parseInt(tempDate.substring(23,25)) < 12) {
        			aorp = "AM";
        		}
                if(endMessage.equals("")) {
                	endMessage += "No game was found on "+ date +"! Enjoy a cubs free day!";
                }else {
                	 endMessage += " " + aorp +"\n\nGood luck finding parking!";
                }
                JOptionPane.showMessageDialog(null, endMessage);
                
            } else {
                System.out.println("Failed to fetch the schedule. Status code: " + response.getStatus());
            }
        } catch (Exception e) {
        	System.out.println("Failed");
            e.printStackTrace();
        }
	}
}
