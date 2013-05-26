/*
 * @class : MyAlarmClock.java
 * @Version: 2013.5.26.0.1
 * @Author: Lei Jian
 * @Date :  May 26,2013 1:18 AM EST
 * @eMail : lei.jianray@gmail.com
 *
*/
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmClock{

  private static final 
		SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("MM/dd/yyyy, HH:mm, z");

	public static void main(String[] args) {
		process();
	}
	
	private static void process(){
		System.out.println("Add Alarm?  Y/N ");
		try{
			Scanner sc = new Scanner(System.in);
			String isSet = sc.nextLine();
			if (isSet.equalsIgnoreCase("y")){
				new SetAlarm();
			}else if(isSet.equalsIgnoreCase("n")){
				System.out.println("Thank for using~");
			}
			sc.close();
		}catch(NoSuchElementException e){
			System.out.println("PLEASE TYPE Y or N!");
			process();
		}
	}
	
	private static class SetAlarm extends Timer{
		
		public SetAlarm(){
			myAlarm();
		}
		
		public void myAlarm(){
			System.out.println("Please tell me when do you wanna wake up?\n " 
								+ "Tell me a time in format: \"MM/dd/yyyy, HH:mm, z\"");
			try{
				Scanner sc = new Scanner(System.in);
				Date setTime = TIME_FORMAT.parse(sc.nextLine());
				if (setTime.before(new Date())){
					System.out.println("Invalid time, please check & input again!\n");
					myAlarm();
				}else{
					System.out.println("Alarm is set up on: " 
										+ TIME_FORMAT.format(setTime) 
										+ "\n");
					this.schedule(isReach, setTime);
					process();
				}
				sc.close();
			}catch(Exception e){
				System.out.println("Invalid Input, Try agian!");
				myAlarm();
			}
		}
		
		TimerTask isReach = new TimerTask(){
			public void run() {
				System.out.println("It's time : " 
										+ TIME_FORMAT.format(this.scheduledExecutionTime())
										+ "\t WAKE UP!");
				this.cancel();
			}
		};
	}
}
