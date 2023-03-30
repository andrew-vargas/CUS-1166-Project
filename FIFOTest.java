/* Project: Project Milestone 4: Class Implementation
* Class: FIFOTest.java
* Author: Andrew Vargas
* Date: October 22nd, 2022
* FIFO algorithm
* EDITED: Panel and frame created by Katherine Dumancela
*/ 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class FIFOTest {
	public static void main(String[] args) {
		new FIFOTest().new completeTime();
	}

	
	private Queue<Job> queue = new LinkedList<Job>();
	
	public FIFOTest()
	{
		queue = new LinkedList<Job>();
	}
	
	public void queueAdd(Job job)
	{
		queue.add(job);
	}
	
	public Job queueRemove()
	{
		return queue.remove();
	}
	
	public Job getCurrentJob()
	{
		return queue.element();
	}
	
	/* The method below calculates the amount of time it will take for the job entered to be completed (including the duration
	of the job entered). */
	public int getCompletionTime(Job job)
	{
		int time = job.getJobDuration();
		Queue<Job> tempqueue = new LinkedList<>(queue);
		Job currentJob = tempqueue.remove();
		
		while (currentJob.getJobID() != job.getJobID())
		{
			time += currentJob.getJobDuration();
		//	System.out.println(job.getJobID()+" " + time);
			currentJob = tempqueue.remove();
		}
		
		return time;
	}
	
	// This method returns the job at the entered position in the queue.
	public Job getJob(int position)
	{
		Queue<Job> tempqueue = new LinkedList<>(queue);
		Job currentJob = new Job();
		
		for (int i = 0; i < position; i++)
		{
			currentJob = tempqueue.remove();
		}
		
		return currentJob;
	}

	public class completeTime {
		
			public List<Job> jobList = FileReader.Job("SavedInfo/JobInfo.txt");
		   
			public completeTime() {
		    	JFrame frame = new JFrame();
		    	JPanel jobInfoPanel;
		    	JLabel /*joblabel,*/ jobInstructions, finalTime;
		    	JTable table; 
		        frame.setTitle("Job Completion Time");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setLayout(null);
		        frame.setSize(700,500);
		        JButton backButton;
				    
		        // setting the bounds for the JFrame
		        frame.setBounds(100,100,645,470);
		        frame.setLocationRelativeTo(null);
		        frame.setResizable(false);   
		        Border br = BorderFactory.createLineBorder(Color.black);
		        Container c=frame.getContentPane();
			frame.getContentPane().setBackground(new Color (0x663a82));
		        
		        // Creating a JPanel for the JFrame
		        jobInfoPanel=new JPanel();
		        backButton = new JButton("Home");
		 		ActionListener listener1 = new BackListener();
		 		backButton.addActionListener(listener1);
		 		backButton.setBounds(560, 380, 70, 25);
		 		
		 		// Time Completed 
		 		
		        FIFOTest tester = new FIFOTest();
		  
			jobInfoPanel.setLayout(new BoxLayout(jobInfoPanel, BoxLayout.Y_AXIS));
		        String[] columnNames = {"Job ID", "Job Name", "Job Info", "Job Duration", "Completion Time"};
		        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		    
		        int id, ct=0, duration; String name; 
				String info;
		    	for(int i=0; i<jobList.size(); i++) {
		    		id = jobList.get(i).getJobID();
		    		name = jobList.get(i).getJobName();
		    		duration = jobList.get(i).getJobDuration();
		    		info = jobList.get(i).getJobInfo();
						tester.queueAdd(jobList.get(i));
						ct = tester.getCompletionTime(jobList.get(i));
		    		Object[] data = {id,name,info, duration,ct};
		    		tableModel.addRow(data);
		    	}
		    
		    	table = new JTable(tableModel);
		    	JScrollPane scrollPane = new JScrollPane(table);
		    	
		    	finalTime = new JLabel("Final Completion Time: " + Integer.toString(ct), SwingConstants.CENTER);
		    	finalTime.setBounds(395, 300, 200, 25);
		    	frame.add(finalTime);
				frame.add(backButton);
				jobInfoPanel.add(finalTime);
				
				Color ivory = new Color(0xBFB9FA);
				table.setOpaque(true);
				table.setFillsViewportHeight(true);
				table.setBackground(ivory);
				
		        // adding a label element to the panel
				
				jobInfoPanel.add(scrollPane, BorderLayout.CENTER);
				
		        // Panel 1
		        jobInfoPanel.setBackground(new Color(145,115,235));
		        jobInfoPanel.setBounds(10,10,550,450);
		        
		        // Panel border
		        jobInfoPanel.setBorder(br);
		        
		        //adding the panel to the Container of the JFrame
		        c.add(jobInfoPanel);
		     
		        
		        frame.setVisible(true);
		    }
			   
		    
		    public class BackListener implements ActionListener {
		    	public void actionPerformed(ActionEvent event)
		    	{
		    		JComponent comp = (JComponent) event.getSource();
		    		Window win = SwingUtilities.getWindowAncestor(comp);
		    		win.dispose();
		    		new HomePage().new CloudControllerHome();
		    	}
		    }
		}
}
