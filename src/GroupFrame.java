import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;





class GroupPanel extends JPanel{
	
	private GroupZero gz;
	private int np;
	private double p10, p100, p1000, plit;
	
	public GroupPanel(){
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int X = getWidth();
		int Y = getHeight();
		int h = 70;
		
		gz  = new GroupZero(getNp());
		p10 = getP10();
		p100 = getP100();
		p1000 = getP1000();
		plit = getPlit();
		
		g.setColor(Color.YELLOW);
		g.fillRect(0, Y/2-h*2, (int)(p10*X), h);
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, Y/2-h, (int)(p100*X), h);
		
		g.setColor(Color.RED);
		g.fillRect(0, Y/2, (int)(p1000*X), h);
		
		g.setColor(Color.BLUE);
		g.fillRect(0, Y/2+h, (int)(plit*X), h);
	}
	
	
	
	public void setNp(int np){
		this.np = np;
		repaint();
	}
	public int getNp(){
		return np;
	}
	public double getP10(){
		return GroupZero.altProb(10, getNp());
	}
	public double getP100(){
		return GroupZero.altProb(100, getNp());
	}
	public double getP1000(){
		return GroupZero.altProb(1000, getNp());
	}
	public double getPlit(){
		return gz.bProb();
	}
	
}



public class GroupFrame extends JFrame{


	private int X = 800, Y = 500;
	GroupPanel gPan;
	JLabel label;
	JTextField intext;
	JPanel control;
	
	
	public GroupFrame(){
		
		label = new JLabel("Enter the number of people: ");
		intext = new JTextField(20);
		
		intext.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				intext.setText("");
			}
		});
		
		control = new JPanel();
		gPan = new GroupPanel();
		
		control.add(label);
		control.add(intext);
		
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setLayout(new BorderLayout());
		getContentPane().add(gPan, BorderLayout.CENTER);
		getContentPane().add(control, BorderLayout.SOUTH);
		pack();
		
		setBackground(Color.GRAY);
		setTitle("Birthday Probability");
		setSize(X, Y);
		setLocation(200, 0);
		setVisible(true);
		
		reProb();
	}
	
	public void reProb(){
		intext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				int np = Integer.parseInt(intext.getText());
				gPan.setNp(np);
				repaint();
			}
		});		
	}
	
}
