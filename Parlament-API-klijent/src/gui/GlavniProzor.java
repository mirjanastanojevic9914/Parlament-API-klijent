package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GlavniProzor extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnGetMembers;
	private JButton btnFillTable;
	private JButton btnUpdateMembers;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JScrollPane scrollPane_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzor frame = new GlavniProzor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GlavniProzor() {
		setTitle("Parlament Members");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.EAST);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
		contentPane.add(getScrollPane_1(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(140, 10));
			panel.add(getBtnGetMembers());
			panel.add(getBtnFillTable());
			panel.add(getBtnUpdateMembers());
		}
		return panel;
	}
	private JButton getBtnGetMembers() {
		if (btnGetMembers == null) {
			btnGetMembers = new JButton("Get members");
			btnGetMembers.setPreferredSize(new Dimension(125, 25));
		}
		return btnGetMembers;
	}
	private JButton getBtnFillTable() {
		if (btnFillTable == null) {
			btnFillTable = new JButton("Fill table");
			btnFillTable.setPreferredSize(new Dimension(125, 25));
		}
		return btnFillTable;
	}
	private JButton getBtnUpdateMembers() {
		if (btnUpdateMembers == null) {
			btnUpdateMembers = new JButton("Update members");
			btnUpdateMembers.setPreferredSize(new Dimension(125, 25));
		}
		return btnUpdateMembers;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(10, 85));
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel_1;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return textArea;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTable());
		}
		return scrollPane_1;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Name", "Last name", "Birth date"
				}
			));
			table.setFillsViewportHeight(true);
		}
		return table;
	}
}
