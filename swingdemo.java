package mini_project;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.*;

class NegativeNumber extends Exception{
	
	int Violated=0;
	
	public NegativeNumber() {
		Violated=1;
	}
	
	public String printMessage() {
		switch(Violated) {
		case 1: return("Amount Cannot be Negative");
		}
		return("");
	}
	
}
public class swingdemo {

double price;
double finalrate;
static JTextField amount;
String from_country="AED",to_country="AED";
String countries[]= {"AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN","BHD","BIF","BMD"
		,"BND","BOB","BRL","BSD","BTC","BTN","BWP","BYR","BZD","CAD","CDF","CHF","CLF","CLP","CNY","COP","CRC","CUC","CUP"
		,"CVE","CZK","DJF","DKK","DOP","DZD","EGP","ERN","ETB","EUR","FJD","FKP","GBP","GEL","GGP","GHS","GIP","GMD","GNF"
		,"GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","IMP","INR","IQD","IRR","ISK","JEP","JMD","JOD","JPY","KES","KGS","KHR"
		,"KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LTL","LVL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO"
		,"MUR","MVR","MWK","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR"
		,"RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP","SLL","SOS","SRD","STD","SVC","SYP","SZL","THB","TJS","TMT"
		,"TND","TOP","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV","WST","XAF","XAG","XAU","XCD","XDR","XOF"
		,"XPF","YER","ZAR","ZMK","ZMW","ZWL"};	
swingdemo()
{
JFrame jfrm=new JFrame("Currency Convertor");
//jfrm.setExtendedState(JFrame.MAXIMIZED_BOTH);
jfrm.setSize(500,500);
JLabel ij=new JLabel();
BufferedImage b;
try {
	b = ImageIO.read(new File("src/mini_project/image6.png"));
	ImageIcon im=new ImageIcon(b);
	ij.setIcon(im);
} catch (IOException e) {
	e.printStackTrace();
}
jfrm.setContentPane(ij);
JLabel jlab;
jlab=new JLabel(" Exchange Rate ");
jlab.setBounds(200,50,100,30);
jfrm.add(jlab);

JTextField txtf=new JTextField();
txtf.setBounds(new Rectangle(200,80,100,30));
txtf.setEditable(false);
txtf.setHorizontalAlignment(SwingConstants.CENTER);
jfrm.add(txtf);

amount = new JTextField();
amount.setText("0");
amount.setBounds(new Rectangle(125,150,250,30));
amount.setEditable(true);
jfrm.add(amount);

JLabel jlab2=new JLabel("Amount :");
jlab2.setBounds(70,150,100,30);
jfrm.add(jlab2);

JLabel from=new JLabel("FROM");
from.setBounds(70,200,100,30);
jfrm.add(from);
JLabel f=new JLabel(new ImageIcon("src/mini_project/flags/AED.png"));
f.setBounds(20,230,40,30);
jfrm.getContentPane().add(f);
JComboBox<String> fromlist=new JComboBox<String>(countries);
fromlist.addActionListener(new ActionListener()
		  {
			public void actionPerformed(ActionEvent e) {
			from_country=(String) fromlist.getSelectedItem();
			f.setIcon(new ImageIcon("src/mini_project/flags/"+from_country+".png"));
			}
});
fromlist.setBounds(70,230,100,30);
jfrm.add(fromlist);

JLabel to=new JLabel("TO");
to.setBounds(300,200,100,30);
jfrm.add(to);
JLabel t=new JLabel(new ImageIcon("src/mini_project/flags/AED.png"));
t.setBounds(410,230,40,30);
jfrm.add(t);
JComboBox<String> tolist=new JComboBox<String>(countries);
tolist.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent e) {
		to_country=(String) tolist.getSelectedItem();
		t.setIcon(new ImageIcon("src/mini_project/flags/"+to_country+".png"));
	}
});
tolist.setBounds(300,230,100,30);
jfrm.add(tolist);


JLabel image=new JLabel();
BufferedImage bimage;
try {
	bimage = ImageIO.read(new File("src/mini_project/arrow3.png"));
	ImageIcon i=new ImageIcon(bimage);
	image.setIcon(i);
	image.setBounds(220,230,30,30);
	jfrm.add(image);
	
} catch (IOException e) {
	e.printStackTrace();
}

JTextField display=new JTextField();
display.setBounds(100,350,280,30);
display.setHorizontalAlignment(SwingConstants.CENTER);
display.setEditable(false);

JLabel lu=new JLabel("Last Updated on : ");
lu.setBounds(325,395,150,30);
lu.setForeground(Color.WHITE);
lu.setHorizontalAlignment(SwingConstants.CENTER);

JTextField lastupdated=new JTextField();
lastupdated.setBounds(325,425,150,30);
lastupdated.setEditable(false);

JButton button=new JButton();
button.setText("CONVERT");
button.setFont(new Font(null,Font.BOLD, 10));
button.setBounds(190,290,100,30);
jfrm.add(button);
	button.addActionListener(new ActionListener()
			{
		public void actionPerformed(ActionEvent e) {
			try {
				price=Double.valueOf(amount.getText());
				API api=new API();
				double rate = api.sendLiveRequest(from_country,to_country);
				DecimalFormat a=new DecimalFormat("#.000000");
				DecimalFormat b=new DecimalFormat("#.00");
				String ex=a.format(rate);
				txtf.setText(ex);
				finalrate=price*rate;
				String newrate=b.format(finalrate);
				if(Double.parseDouble(amount.getText())>0)
				display.setText(price+" "+from_country+" = "+newrate+" "+to_country);
				lastupdated.setText(API.formattedDate);
				isValid();
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				JFrame popup=new JFrame("Error!!");
				popup.setSize(300,100);
				popup.setLocationRelativeTo(null);
				JLabel error1=new JLabel("Invalid Input");
				error1.setBounds(100,10,100,30);
				popup.add(error1);
				popup.setLayout(null);
				popup.setVisible(true);
			}
			catch(NegativeNumber e2)
			{
				JFrame popup=new JFrame("Error!!");
				popup.setSize(300,100);
				popup.setLocationRelativeTo(null);
				JLabel error1=new JLabel(e2.printMessage());
				error1.setBounds(20,10,250,30);
				popup.add(error1);
				popup.setLayout(null);
				popup.setVisible(true);
			}
		     }
	       });


jfrm.add(lu);
jfrm.add(lastupdated);
jfrm.add(display);

jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
jfrm.setLayout(null);
jfrm.setVisible(true);
}
public static void main(String args[])
{
SwingUtilities.invokeLater(new Runnable(){ 
public void run()
{
new swingdemo();
}
});
}
private static void isValid() throws NegativeNumber {
	if (Double.parseDouble(amount.getText())<0) {
		throw new NegativeNumber();
	}
	
	}
}







