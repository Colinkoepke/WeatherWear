
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UI extends JFrame{
	
	private JFrame main;
	private JButton start;
	private JLabel header;
	private JLabel cityLabel;
	private JLabel stateLabel;
	private static JTextField city;
	private static JTextField states;
	
	private JLabel summary;
	private JLabel rain;
	private JLabel temp;
	private JLabel feelsLike;
	private JLabel humidity;
	private JLabel wind;
	private JLabel cloudCoverage;
	private JLabel uv;
	private JLabel clothes;
	
	public UI() {
		
		main  = new JFrame();
		main.setTitle("A Distributed System");
		main.setSize(500, 500);
		main.setLayout(new GridLayout(6,1));
		
		header = new JLabel("", JLabel.CENTER);
		header.setText("Weather Wear");
		header.setFont(new Font("Serif", Font.BOLD, 42));
		header.setForeground(Color.blue);
		
		cityLabel = new JLabel("City:", JLabel.CENTER);
		cityLabel.setFont(new Font("Serif", Font.BOLD, 25));
		stateLabel = new JLabel("State:", JLabel.CENTER);
		stateLabel.setFont(new Font("Serif", Font.BOLD, 25));
		
		city = new JTextField();
		states = new JTextField();
		
		start = new JButton("Start Search");
		start.setName("startButton");
		start.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	String city = getCity().getText();
		    	String state = getStates().getText();
		        main.dispose();
		        
		        main  = new JFrame();
				main.setTitle("A Distributed System");
				main.setSize(500, 500);
				main.setLayout(new GridLayout(11,1));
				
				header = new JLabel("", JLabel.CENTER);
				header.setText("Weather Wear");
				header.setFont(new Font("Serif", Font.BOLD, 42));
				header.setForeground(Color.blue);
				
				cityLabel = new JLabel("", JLabel.CENTER);
				cityLabel.setText(city + " " + state);
				cityLabel.setFont(new Font("Serif", Font.BOLD, 25));
				
				summary = new JLabel("Summary:", JLabel.CENTER);
				summary.setFont(new Font("Serif", Font.PLAIN, 16));
				
				rain = new JLabel("Chance Of Showers:", JLabel.CENTER);
				rain.setFont(new Font("Serif", Font.PLAIN, 16));
				
				temp = new JLabel("Current Temperature:", JLabel.CENTER);
				temp.setFont(new Font("Serif", Font.PLAIN, 16));
				
				feelsLike = new JLabel("Feels Like:", JLabel.CENTER);
				feelsLike.setFont(new Font("Serif", Font.PLAIN, 16));
				
				humidity = new JLabel("Humidity Type:", JLabel.CENTER);
				humidity.setFont(new Font("Serif", Font.PLAIN, 16));
				
				wind = new JLabel("Wind Strength:", JLabel.CENTER);
				wind.setFont(new Font("Serif", Font.PLAIN, 16));
				
				cloudCoverage = new JLabel("Cloud Coverage:", JLabel.CENTER);
				cloudCoverage.setFont(new Font("Serif", Font.PLAIN, 16));
				
				uv = new JLabel("UV Index:", JLabel.CENTER);
				uv.setFont(new Font("Serif", Font.PLAIN, 16));
				
				clothes = new JLabel("Suggested Clothing:", JLabel.CENTER);
				clothes.setFont(new Font("Serif", Font.PLAIN, 16));
				
				main.add(header);
				main.add(cityLabel);
				main.add(summary);
				main.add(rain);
				main.add(temp);
				main.add(feelsLike);
				main.add(humidity);
				main.add(wind);
				main.add(cloudCoverage);
				main.add(uv);
				main.add(clothes);
				main.setVisible(true);
				main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    }
		});
		
		main.add(header);
		main.add(cityLabel);
		main.add(city);
		main.add(stateLabel);
		main.add(states);
		main.add(start);
		main.setVisible(true);
		
	    //To clean up interface and close it when close the window
		//main.pack();
	    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}
	
	public static JTextField getCity() {
		return city;
	}
	
	public static JTextField getStates() {
		return states;
	}
	
	
}
