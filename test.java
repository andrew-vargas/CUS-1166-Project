
import java.sql.Timestamp;
import java.util.*;

public class test implements Runnable {
	
	public void run()
	{
		while (true)
		{
			try
			{
				Timestamp newTime = new Timestamp(System.currentTimeMillis());
				System.out.println(newTime);
				Thread.sleep(490);
			}
			catch (InterruptedException e)
			{
				System.out.println("failed");
			}
		}
	}
	
	public static void main(String[] args) {
		
		Timestamp time = new Timestamp(System.currentTimeMillis());
		System.out.println(time);
		
		test testobject = new test();
		Thread thread = new Thread(testobject);
		thread.start();
		
		time = new Timestamp(System.currentTimeMillis());
		System.out.println(time);
	}

}
