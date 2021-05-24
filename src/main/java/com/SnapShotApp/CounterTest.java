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

public class CounterTest extends JFrame implements ActionListener {
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
	public static final String TITLE = "SnapShot-0.2v";
	public static final String WINDOWSUSERNAME = System.getProperty("user.name");
	
	final JFrame jfrm= new JFrame(TITLE);


	public CounterTest() {
		//setTitle(TITLE);
		count = 0;

		choosePathVeriable = new JButton("Path");
		choosePathVeriable.setBounds(15, 17, 100, 30);
		choosePathVeriable.addActionListener(this);

		filePathLabel = new JLabel();
		filePathLabel.setBounds(20, 43, 1024, 30);

		takeSnapeButtonVeriable = new JButton("Take Snap");
		takeSnapeButtonVeriable.setBounds(130, 17, 100, 30);
		takeSnapeButtonVeriable.addActionListener(this);

		developerNameLabel = new JLabel("Developed By Saravanakumar Tj");
		developerNameLabel.setBounds(20, 110, 300, 30);
		jfrm.add(choosePathVeriable);
		jfrm.add(filePathLabel);
		jfrm.add(takeSnapeButtonVeriable);
		// add(developerNameLabel);
		jfrm.setSize(256, 110);
		jfrm.setLocationRelativeTo(null);
		jfrm.setLayout(null);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setVisible(true);
		jfrm.setResizable(true);
	}

	public void actionPerformed(ActionEvent ae) {
		synchronized (ae) {

			JFileChooser fileChooser = new JFileChooser();
			File file = null;

			if (ae.getSource() == choosePathVeriable) {

				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showOpenDialog(CounterTest.this);
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

				try {
					Robot r = new Robot();

					String pathofCounter = "C:\\" + "Users\\" + WINDOWSUSERNAME + "\\" + "SnapShotAppCache" + "\\";
					String path = filePathLabel.getText();
					new File(pathofCounter).mkdir();
					if (new File(pathofCounter + "cache.txt").createNewFile()) {
						FileWriter myWriter = new FileWriter(pathofCounter + "cache.txt");
						myWriter.write("1");
						myWriter.close();

					} else {

						File file1 = new File(
								"C:\\" + "Users\\" + WINDOWSUSERNAME + "\\" + "SnapShotAppCache" + "\\" + "cache.txt");
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

					File file2 = new File(
							"C:\\" + "Users\\" + WINDOWSUSERNAME + "\\" + "SnapShotAppCache" + "\\" + "cache.txt");
					FileInputStream fin1 = new FileInputStream(file2);
					byte fileContent1[] = new byte[(int) file2.length()];
					fin1.read(fileContent1);
					String countValues1 = new String(fileContent1);
					fin1.close();
					jfrm.setVisible(false);
					try {Thread.sleep(150);} catch (InterruptedException ie) {Thread.currentThread().interrupt();}
					Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
					BufferedImage Image = r.createScreenCapture(capture);
					ImageIO.write(Image, "jpg", new File(path + "\\" + "" + countValues1 + ".jpg"));
					jfrm.setVisible(true);

				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			}
			if (null == isPath) {
				JOptionPane.showMessageDialog(new JFrame(), ERRORMSG, TITLE, JOptionPane.ERROR_MESSAGE);
			}

			
		}
	}

	public static void main(String[] args) {
		new CounterTest();
	}
}
