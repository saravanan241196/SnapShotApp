/**
 *
 */
package com.SnapShotApp;

/**
 * @author TJ
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SnapShotApp extends JFrame implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JLabel developerNameLabel;
	private JLabel filePathLabel = null;
	private JButton takeSnapeButtonVeriable;
	private JButton choosePathVeriable;
	private static int a;

	String isPath = null;
	boolean isfilePathCounterCreater;

	private int count;
	static String countValues;

	public static final String ERRORMSG = "Did you not select the path?";
	public static final String TITLE = "SnapShot-0.2v - sthiruko@visa.com - Tj,Saravanakumar";
	public static final String MACUSERNAME = System.getProperty("user.name");

	public SnapShotApp() {
		setTitle(TITLE);
		count = 0;

		choosePathVeriable = new JButton("Path");
		choosePathVeriable.setBounds(15, 13, 120, 30);
		choosePathVeriable.addActionListener(this);

		filePathLabel = new JLabel();
		filePathLabel.setBounds(20, 43, 1024, 30);

		takeSnapeButtonVeriable = new JButton("Take Snap");
		takeSnapeButtonVeriable.setBounds(130, 13, 120, 30);
		takeSnapeButtonVeriable.addActionListener(this);

		developerNameLabel = new JLabel("sthiruko@visa.com - Tj,Saravanakumar");
		developerNameLabel.setBounds(13, 42, 300, 30);
		add(choosePathVeriable);
		add(filePathLabel);
		add(takeSnapeButtonVeriable);
		//add(developerNameLabel);
		setSize(270, 110);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
	}

	public void actionPerformed(ActionEvent ae) {
		JFileChooser fileChooser = new JFileChooser();
		File file = null;

		if (ae.getSource() == choosePathVeriable) {

			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser.showOpenDialog(SnapShotApp.this);
			if (option == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				filePathLabel.setText(file.getAbsolutePath());
				isPath = file.getAbsolutePath();

			} else {
				// filePathLabel.setText(ERRORMSG);
			}

		}

		if (null != isPath && ae.getSource() == takeSnapeButtonVeriable) {

			count++;
			countValues = String.valueOf(count);

			setVisible(false);
			try {
				Robot r = new Robot();

				String pathofCounter = "/Users/" + MACUSERNAME + "/SnapShotCache/";
				String path = filePathLabel.getText();
				new File(pathofCounter).mkdir();
				if (new File(pathofCounter + "cache.txt").createNewFile()) {
					FileWriter myWriter = new FileWriter(pathofCounter + "cache.txt");
					myWriter.write("1");
					myWriter.close();

				} else {

					File file1 = new File("/Users/" + MACUSERNAME + "/SnapShotCache" + "/" + "cache.txt");
					FileInputStream fin = new FileInputStream(file1);
					byte fileContent[] = new byte[(int) file1.length()];
					fin.read(fileContent);
					String s = new String(fileContent);
					a = Integer.parseInt(String.valueOf(s));
					fin.close();

					FileWriter myWriter = new FileWriter(pathofCounter + "cache.txt");
					myWriter.write(String.valueOf(++a));
					myWriter.close();

				}

				File file2 = new File("/Users/" + MACUSERNAME + "/SnapShotCache/" + "cache.txt");
				FileInputStream fin1 = new FileInputStream(file2);
				byte fileContent1[] = new byte[(int) file2.length()];
				fin1.read(fileContent1);
				String countValues1 = new String(fileContent1);
				fin1.close();

				Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage Image = r.createScreenCapture(capture);
				ImageIO.write(Image, "jpg", new File(path + "/" + countValues1 + ".jpg"));

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		}
		if (null == isPath) {
			JOptionPane.showMessageDialog(new JFrame(), ERRORMSG, TITLE, JOptionPane.ERROR_MESSAGE);
		}

		setVisible(true);
	}

	public static void main(String[] args) {
		new SnapShotApp();
	}
}