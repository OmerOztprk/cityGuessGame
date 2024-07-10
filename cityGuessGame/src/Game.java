import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game extends JFrame implements KeyListener {

	private JPanel panel1, panel2;
	private JButton[] button;
	private JTextField textField;
	private char[] choosedcitychar;
	private int choosedcitylength;
	private String[] cities = { "adana", "adıyaman", "afyon", "ağrı", "amasya", "ankara", "antalya", "artvin", "aydın",
			"balıkesir", "bilecik", "bingöl", "bitlis", "bolu", "burdur", "bursa", "çanakkale", "çankırı", "çorum",
			"denizli", "diyarbakır", "edirne", "elazığ", "erzincan", "erzurum", "eskişehir", "gaziantep", "giresun",
			"gümüşhane", "hakkari", "hatay", "ısparta", "mersin", "istanbul", "izmir", "kars", "kastamonu", "kayseri",
			"kırklareli", "kırşehir", "kocaeli", "konya", "kütahya", "malatya", "manisa", "kahramanmaraş", "mardin",
			"muğla", "muş", "nevşehir", "niğde", "ordu", "rize", "sakarya", "samsun", "siirt", "Sinop", "sivas",
			"tekirdağ", "tokat", "trabzon", "tunceli", "şanlıurfa", "uşak", "van", "yozgat", "zonguldak", "aksaray",
			"bayburt", "karaman", "kırıkkale", "batman", "şırnak", "bartın", "ardahan", "ığdır", "yalova", "karabük",
			"kilis", "osmaniye", "düzce" };
	private int point = 100;
	private int winpoint = 0;
	private List<Character> usedChars;

	public Game() {

		Random random = new Random();
		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createTitledBorder("Panel1"));
		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createTitledBorder("Panel2"));
		textField = new JTextField(20);
		panel2.add(textField);
		textField.addKeyListener(this);
		

		panel1.setLayout(new FlowLayout());
		setLayout(new GridLayout(2, 1));

		String choosedcity = cities[random.nextInt(82)];
		choosedcitylength = choosedcity.length();
		button = new JButton[choosedcitylength];

		choosedcitychar = new char[choosedcitylength];
		usedChars = new ArrayList<>();

		for (int i = 0; i < choosedcitylength; i++) {
			choosedcitychar[i] = choosedcity.charAt(i);
			button[i] = new JButton("");
			button[i].setPreferredSize(new Dimension(50, 50));
			panel1.add(button[i]);
		}
		panel2.setBackground(Color.red);

		add(panel1);
		add(panel2);

		setVisible(true);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

	}

	public static void main(String[] args) {
		new Game();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		char keyChar = e.getKeyChar();

		if (!Character.isLetter(keyChar)) {
			System.out.println("Lütfen sadece harf giriniz.");
			return;
		}

		if (usedChars.contains(keyChar)) {
			System.out.println("Bu harf zaten kullanıldı. Başka bir harf deneyiniz.");
			return;
		}

		boolean value = false;
		for (int i = 0; i < choosedcitylength; i++) {
			if (keyChar == choosedcitychar[i]) {
				button[i].setText(String.valueOf(choosedcitychar[i]));
				value = true;
				winpoint++;
			}
		}

		usedChars.add(keyChar);

		if (value) {
			System.out.println("Tebrikler bir harf buldun.");
			point += 10;
		} else {
			System.out.println("Başka bir harf deneyiniz.");
			point -= 10;

		}
		System.out.println("Toplam Puan: " + point);

		if (winpoint == choosedcitylength) {
			System.out.println("Kazandın!");
			textField.disable();
		}

		if (point == 0) {
			for (int i = 0; i < choosedcitylength; i++) {
				button[i].setText(String.valueOf(choosedcitychar[i]));
			}
			System.out.println("Oyun bitti. Puanınız 0'a ulaştı.");
			textField.disable();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
