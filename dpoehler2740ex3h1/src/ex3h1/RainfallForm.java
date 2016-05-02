package ex3h1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class RainfallForm extends JFrame implements ActionListener, ListSelectionListener {

	private JPanel contentPane;
	private JList rainfallList;
	private JLabel totalLabel;
	private JLabel averageLabel;
	private JButton calculateButton;
	private JTextField inputMonthTextField;
	private JButton updateButton;
	private String [] strRainfall = {
			"1.2", "2.7", "2.2", "3.1", "2.9", "5.1", 
			"3.2", "2.7", "3.6", "1.8", "2.2", "1.7" };
	private JLabel maxLabel;
	private JLabel minLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RainfallForm frame = new RainfallForm();
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
	public RainfallForm() {
		setTitle("DPoehler 2740 Ex3H Rainfall");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 326, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Monthly rainfall");
		lblNewLabel.setBounds(12, 26, 99, 16);
		contentPane.add(lblNewLabel);
		
		JList monthList = new JList();
		monthList.setBackground(UIManager.getColor("Label.background"));
		monthList.setEnabled(false);
		monthList.setModel(new AbstractListModel() {
			String[] values = new String[] {"01 Jan", "02 Feb", "03 Mar", "04 Apr", "05 May", "06 Jun", "07 Jul", "08 Aug", "09 Sep", "10 Oct", "11 Nov", "12 Dec"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		monthList.setBounds(22, 55, 53, 229);
		contentPane.add(monthList);
		
		rainfallList = new JList(strRainfall);
		rainfallList.addListSelectionListener(this);
		rainfallList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		rainfallList.setBounds(74, 55, 53, 229);
		contentPane.add(rainfallList);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(154, 59, 53, 16);
		contentPane.add(lblTotal);
		
		JLabel lblNewLabel_1 = new JLabel("Average:");
		lblNewLabel_1.setBounds(154, 103, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Maximum:");
		lblNewLabel_2.setBounds(154, 149, 71, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Minimum:");
		lblNewLabel_3.setBounds(154, 192, 68, 16);
		contentPane.add(lblNewLabel_3);
		
		totalLabel = new JLabel("0.0");
		lblTotal.setLabelFor(totalLabel);
		totalLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel.setBounds(226, 55, 62, 24);
		contentPane.add(totalLabel);
		
		averageLabel = new JLabel("0.0");
		lblNewLabel_1.setLabelFor(averageLabel);
		averageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		averageLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		averageLabel.setBounds(226, 99, 62, 24);
		contentPane.add(averageLabel);
		
		maxLabel = new JLabel("0.0");
		lblNewLabel_2.setLabelFor(maxLabel);
		maxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		maxLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		maxLabel.setBounds(226, 145, 62, 24);
		contentPane.add(maxLabel);
		
		minLabel = new JLabel("0.0");
		lblNewLabel_3.setLabelFor(minLabel);
		minLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		minLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		minLabel.setBounds(226, 188, 62, 24);
		contentPane.add(minLabel);
		
		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(this);
		calculateButton.setBounds(192, 230, 97, 25);
		contentPane.add(calculateButton);
		
		inputMonthTextField = new JTextField();
		inputMonthTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		inputMonthTextField.setText("0.0");
		inputMonthTextField.setBounds(74, 287, 53, 22);
		contentPane.add(inputMonthTextField);
		inputMonthTextField.setColumns(10);
		
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		updateButton.setBounds(141, 286, 97, 25);
		updateButton.setEnabled(false);
		contentPane.add(updateButton);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == updateButton) {
			do_updateButton_actionPerformed(arg0);
		}
		if (arg0.getSource() == calculateButton) {
			do_calculateButton_actionPerformed(arg0);
		}
	}
	protected void do_calculateButton_actionPerformed(ActionEvent arg0) {
		Rainfall rainfall = new Rainfall(strRainfall);
		
		DecimalFormat fmt = new DecimalFormat("0.0");
		totalLabel.setText(fmt.format(rainfall.getTotal()));
		averageLabel.setText(fmt.format(rainfall.getAverage()));
		maxLabel.setText(fmt.format(rainfall.getHighest()));
		minLabel.setText(fmt.format(rainfall.getLowest()));
	}
	protected void do_updateButton_actionPerformed(ActionEvent arg0) {
		int selectedIndex = rainfallList.getSelectedIndex();
		double r = Double.parseDouble(inputMonthTextField.getText());
		strRainfall[selectedIndex] = Double.toString(r);
		rainfallList.repaint();
		
		inputMonthTextField.setText("0.00");
		updateButton.setEnabled(false);
		totalLabel.setText("");
		averageLabel.setText("");
		maxLabel.setText("");
		minLabel.setText("");
		
	}
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == rainfallList) {
			do_rainfallList_valueChanged(arg0);
		}
	}
	protected void do_rainfallList_valueChanged(ListSelectionEvent arg0) {
		updateButton.setEnabled(true);
		inputMonthTextField.setText((String) rainfallList.getSelectedValue());
		inputMonthTextField.requestFocus();
		inputMonthTextField.selectAll();
	}
}
