package reboot;

//Skapad av Mattias & Victor
//2015-03-18
//
//Detta �r en klass som visar posterna i schemat, till denna klass ska det tilldelas animationer och logik f�r att f� in korrekta v�rden
//
//


import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.EventQueue;
//for images
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CanvasTest extends JFrame {
	private JPanel contentPane;
	private Panel controlPanel;
	// diverse bra variabler att ha
	static int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
	final static float DPI = 72; // Pixel density 96 �r standard p� moderna
	public int antalElement=12;
	
	
	// monitors, 72 �r gamla
	final static float PT = 7; // font size pt
	final static int SCREEN_WIDTH = 1080;// old, 768px f�r LG monitorn
	final static int SCREEN_HEIGHT = 1920;// old, 1024px f�r LG monitorn
	final static int MIN_MODULE_HEIGHT = 80; // minimum module height
	final static int fieldHeight = 80; // field height
	
	//h�r importerar du en bild.
	private Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/reboot/images/cancelIcon.png"));
	
	//font stuff
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	public int fontSize = (int) Math.round(PT * screenRes / DPI);
	public Font futuraBook = new Font("Futura LT Light", Font.PLAIN, fontSize);
	public Font futuraBold = new Font("Futura LT Bold", Font.PLAIN, fontSize);
	public Font futuraMedium = new Font("Futura LT Medium", Font.PLAIN,fontSize);// typsnittet vi ska anv�nda
	
	private Font fieldFont = futuraBook.deriveFont(Font.PLAIN, 25);
	private Font headerFont = futuraBold.deriveFont(Font.PLAIN, 30);
	// F�rger
	private Color whiteColor = Color.decode("#ffffff");
	private Color headerYellowTextColor = Color.decode("#E5DA9F");
	private Color headerFieldBackgroundColor = Color.decode("#3A3A39");
	private Color blueFieldColor = Color.decode("#D6ECF3");
	private Color redEditText = Color.decode("#C52033");
	//private ArrayList<Post> displayPost= new ArrayList<Post>();
	private String[] fieldValues = { "- -:- - - -:- -", "LOADING...", "Sal..." };
	private ArrayList<String[]> valueList = new ArrayList<String[]>();
	private  MyCanvas demo= new MyCanvas();
	// m�tt
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CanvasTest frame = new CanvasTest();
					frame.setVisible(false);
					CanvasTest awtControlDemo = new CanvasTest();
					
					awtControlDemo.showCanvasDemo();
					awtControlDemo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CanvasTest() {
		System.out.println("construct");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		for (int i = 0; i < antalElement; i++) {
			valueList.add(fieldValues);
		}
		for (int i = 0; i < antalElement; i++) {
			shapeList.add(new Rectangle2D.Float(0, fieldHeight+(i * fieldHeight), SCREEN_WIDTH, fieldHeight));
		}
		prepareGUI();
	}

	
	private void prepareGUI() {
		
		// contentPane �r huvudrutan
		contentPane.setBackground(Color.WHITE);
		// SetSize
		contentPane.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		contentPane.setLayout(new GridLayout(1, 1));
		controlPanel = new Panel();
		controlPanel.setBackground(Color.GRAY);
		controlPanel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		contentPane.add(controlPanel);
		controlPanel.setLayout(null);
		contentPane.setVisible(true);
	}

	void showCanvasDemo() {
		controlPanel.add(demo);
		contentPane.setVisible(true);
	}

	class MyCanvas extends Canvas {
		public MyCanvas() {
			setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
			setBackground(Color.white);
		}

		public void paint(Graphics g) {
			
			System.out.println("paint!!!!");
			Graphics2D g2;
			g2 = (Graphics2D) g;
			
			
			//mjuka upp texten - beh�ll som det �r
			RenderingHints rh = new RenderingHints(
					RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
			g2.setRenderingHints(rh);
			
			
			// g2.drawLine(10, 10, 200, 200);
			// g2.setStroke();
			g2.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT - 1000);
			// rader
			Stroke stroke = new BasicStroke(1, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_BEVEL, 0, new float[] { 1, 0 }, 0);
			g2.setStroke(stroke);
			// L�gger till header-f�ltet m. text osv
			g2.setFont(headerFont);
			Shape headField = new Rectangle2D.Float(0, 0, SCREEN_WIDTH, fieldHeight);
		
			//set color med variabel
			g2.setColor(headerYellowTextColor);
			
			//testa denna lite - skriver ut objektet Shape som deklareras preciis ovanf�r 
			g2.fill(headField);

		//	boolean colorTurn = false;
			g2.setFont(fieldFont);
		
				
				//set color...
				g2.setColor(Color.black);// write out time
				
				//rita ut skrift
				g2.drawString("hej lars",100,100);// write out course
			
				//set font...
				g2.setFont(fieldFont);
				// infoga image-varibeln (img i detta fall), var den ska ritas ut och Observer ska vara "this"
				g2.drawImage(img, 940, 90, this);
				g2.drawLine(710, 120, 775, 120);

		}

		private void BasicStroke(int i) {
			// TODO Auto-generated method stub
		}
	}
}
