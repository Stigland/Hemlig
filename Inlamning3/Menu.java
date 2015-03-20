package inlämning3;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.xml.ws.Holder;

public class Menu extends JFrame{
	static final String gapList[] = {"0", "10", "15", "20"};
	final static int maxGap = 20;
	GridLayout ruta = new GridLayout(7,2);
	JComboBox  horGapComboBox;
	JComboBox  verGapComboBox;
	static Menu frame = new Menu("GridLayoutDemo");
	public static int menuLevel = 0;
	static String mode;
	final JLabel search = new JLabel( "Search");
	final JButton searchID = new JButton( "Search by ID");
	final JButton searchFamily = new JButton( "Search by family name");
	final JButton searchLeader = new JButton( "List by ID");
	final JButton listID = new JButton( "List by family name");
	final JButton listName = new JButton( "Info about a team");
	final JLabel change = new JLabel( "Change");
	final JButton newMember= new JButton( "Create new Member");
	final JButton updateMember = new JButton( "Update existing member");
	final JButton activateMember = new JButton( "Activate/deactivate member");
	final JButton updateMail = new JButton( "Update mail");
	final JButton updateFunction = new JButton( "Update function");
	final JButton deleteMember = new JButton( "Delete member");
	final JButton apply = new JButton( "Affirmative");
	final JButton back = new JButton( "<<<");
	final JTextField t1 = new JTextField(20);
	final JTextField t2 = new JTextField();
	final JTextField t3 = new JTextField();
	final JTextField t4 = new JTextField();
	final JTextField t5 = new JTextField();
	final JTextField t6 = new JTextField();
	final JTextField t7 = new JTextField();
	final JTextField t8 = new JTextField();
	final JLabel l1 = new JLabel("hej");
	final JLabel l2 = new JLabel();
	final JLabel l3 = new JLabel();
	final JLabel l4 = new JLabel();
	final JLabel l5 = new JLabel();
	final JLabel l6 = new JLabel();
	final JLabel l7 = new JLabel();
	final JLabel l8 = new JLabel();
	public void initGaps() {
		horGapComboBox = new JComboBox(gapList);
		verGapComboBox = new JComboBox(gapList);
	}
	public void addComponentsToPane(final Container pane) {
		initGaps();
		final JPanel view1 = new JPanel();
		final JPanel view2 = new JPanel();
		final JPanel view3 = new JPanel();
		view1.setLayout(ruta);
		view2.setLayout(ruta);
		view3.setLayout(ruta);
		JPanel controls = new JPanel();
		view3.setLayout(new GridLayout(7,2,5,5));
		//set up components size
		JButton b = new JButton ("fake");
		Dimension buttonSize = b.getPreferredSize();
		view1.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,(int)(buttonSize.getHeight()*3.5)+maxGap*2));
		view2.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,(int)(buttonSize.getHeight()*3.5)+maxGap*2));
		view3.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,(int)(buttonSize.getHeight()*3.5)+maxGap*2));
		//add buttons with gridlayout
		view1.add(search);
		view1.add(searchID );
		searchID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				menuLevel++;
				mode="searchID";
				view1.setVisible(false);
				view2.setVisible(false);
				view3.setVisible(true);
				l1.setText("Sök efter ID");
				System.out.println("hej");
			} });
		//View 1
		view1.add(searchFamily);
		view1.add(searchLeader);
		view1.add(listID);
		view1.add(listName);
		//View 2
		view2.add(change);
		view2.add(newMember);
		view2.add(updateMember);
		view2.add(activateMember);
		view2.add(updateMail);
		view2.add(updateFunction);
		view2.add(deleteMember);
		//View3
		view3.add(l1);
		view3.add(t1);
		view3.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				view1.setVisible(true);
				view2.setVisible(true);
				view3.setVisible(false);
			} });
	
		view3.add(apply);
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				switch(mode) {
				case "searchID":
					System.out.println("searchID");
					break;
				case "searchFamily":
					System.out.println("searchFamily");
					break;
				case "searchLeader":
					System.out.println("searchLeader");
					break;
				case "listID":
					System.out.println("listID");
					break;
				case "listName":
					System.out.println("listName");
					break;
				case "newMember":
					System.out.println("newMember");
					break;
				case "updateMember":
					System.out.println("updateMember");
					break;
				case "activateMember":
					System.out.println("activateMember");
					break;
				case "updateMail":
					System.out.println("updateMail");
					break;
				case "updateFunction":
					System.out.println("updateFunction");
					break;
				case "deleteMember":
					System.out.println("deleteMember");
					break;
				
				
				}
				view1.setVisible(true);
				view2.setVisible(true);
				view3.setVisible(false);
			} });
		//add controls to hori and cert gaps
		controls.add(new Label("Horizontal"));
		controls.add(new Label("Vertical"));
		controls.add(new Label(" "));
		controls.add(horGapComboBox);
		controls.add(verGapComboBox);
		//controls.add(applyButton);
		//Main menu
		pane.add(view1, BorderLayout.WEST);
		pane.add(view2, BorderLayout.EAST);
		//Sub menus
		view3.setVisible(false);
		pane.add(view3, BorderLayout.NORTH);
		//  pane.add(new JSeparator(), BorderLayout.CENTER);
		//  pane.add(controls, BorderLayout.SOUTH);
	}
	public static void createAndShowGUI() {
		//Create the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Upg 3");
		frame.setSize(500,550);
		frame.setLocationRelativeTo(null);
		frame.addComponentsToPane(frame.getContentPane());
		//frame.pack();
		frame.setVisible(true);
	}
	public Menu(String name) {
		super(name);
		setResizable(true);
	}
	public static void main(String[] args) {
		createAndShowGUI();
	}
}