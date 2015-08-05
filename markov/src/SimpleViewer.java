import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This is the GUI for the Markov Text Generation Programs 
 * for Compsci 100. First used in Spring 2008, but based
 * on SimpleViewer classes used since 2004.
 * @author Owen Astrachan
 * @date Jan 12, 2008
 *
 */

@SuppressWarnings("serial")
public class SimpleViewer extends JFrame implements IView {
	protected JTextField myInput;
	protected JTextArea myOutput;
	protected IModel myModel;
	protected String myTitle;
	protected String myLabelString;
	protected JTextField myMessage;
	protected JButton myButton;

	protected static JFileChooser ourOpenChooser = new JFileChooser(System
			.getProperties().getProperty("user.dir"));
	protected static JFileChooser ourSaveChooser = new JFileChooser(System
			.getProperties().getProperty("user.dir"));

	public SimpleViewer(String title, String prompt) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(new BorderLayout());
		setTitle(title);
		myTitle = title;
		myLabelString = prompt;

		if (!prompt.equals("")) {
			panel.add(makeInput(), BorderLayout.NORTH);
		}
		panel.add(makeOutput(), BorderLayout.CENTER);
		panel.add(makeMessage(), BorderLayout.SOUTH);
		makeMenus();
		connectEvents();

		pack();
		setSize(400, 400);
		setVisible(true);
	}

	public void setModel(IModel model) {
		myModel = model;
		model.addView(this);
	}

	protected JPanel makeMessage() {
		JPanel p = new JPanel(new BorderLayout());
		myMessage = new JTextField(30);
		p.setBorder(BorderFactory.createTitledBorder("message"));
		p.add(myMessage, BorderLayout.CENTER);
		return p;
	}

	protected JPanel makeInput() {
		JPanel p = new JPanel(new BorderLayout());
		JLabel label = new JLabel(myLabelString);
		myInput = new JTextField(12);
		myButton = new JButton("GO");
		p.setBorder(BorderFactory.createTitledBorder("input"));
		p.add(label, BorderLayout.WEST);
		p.add(myInput, BorderLayout.CENTER);
		p.add(myButton, BorderLayout.EAST);
		return p;
	}

	protected JPanel makeOutput() {
		JPanel p = new JPanel(new BorderLayout());
		myOutput = new JTextArea(10, 40);
		p.setBorder(BorderFactory.createTitledBorder("output"));
		p.add(new JScrollPane(myOutput), BorderLayout.CENTER);
		return p;

	}

	@SuppressWarnings("serial")
	protected JMenu makeFileMenu() {
		JMenu fileMenu = new JMenu("File");

		fileMenu.add(new AbstractAction("Open File") {
			public void actionPerformed(ActionEvent ev) {
				int retval = ourOpenChooser.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = ourOpenChooser.getSelectedFile();

					ProgressMonitorInputStream pmis;
					try {
						pmis = getMonitorableStream(
								new FileInputStream(file), "reading file " + file.getName());
					} catch (FileNotFoundException e) {
						showError("error reading "+file.getName());
						return;
					}
					doProcess(pmis,"file: "+file.getName());
				}
			}
		});

		fileMenu.add(new AbstractAction("Open URL") {
			public void actionPerformed(ActionEvent ev) {
				String address = JOptionPane
						.showInputDialog("Please enter URL:");
				try {
					if (! address.startsWith("http://")){
						address = "http://"+address;
					}
					URI uri = new URI(address);
					URL url = uri.toURL();
					int size = url.openConnection().getContentLength();
					ProgressMonitorInputStream pmis = 
						getMonitorableStream(url.openStream(),"reading url: "+address);
					pmis.getProgressMonitor().setMaximum(size);
					doProcess(pmis, "url: "+url);
				} catch (URISyntaxException e1) {
					showError(e1.getMessage());
				} catch (MalformedURLException e2) {
					showError(e2.getMessage());
				} catch (IOException e3) {
					showError("error reading from URL: " + address);
				}
			}
		});
		
		fileMenu.add(new AbstractAction("Save"){

			public void actionPerformed(ActionEvent e) {
				int retval = ourSaveChooser.showSaveDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = ourSaveChooser.getSelectedFile();
					try {
						PrintWriter pw = new PrintWriter(file, "UTF8");
						String[] lines = myOutput.getText().split("\\n");
						for(String s : lines){
							pw.println(s);
						}
						pw.close();
					} catch (FileNotFoundException e1) {
						SimpleViewer.this.showError("could not open "+file);
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e2) {
						SimpleViewer.this.showError("problem with UTF8 encoding");
						e2.printStackTrace();
					} 
				}
			}
			
		});

		fileMenu.add(new AbstractAction("Quit") {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		return fileMenu;
	}
	
	protected void doProcess(final ProgressMonitorInputStream pmis, String message){
		final ProgressMonitor progress = pmis.getProgressMonitor();
		Thread fileReaderThread = new Thread() {
			public void run() {
				myModel.initialize(new Scanner(pmis,"UTF-8"));
				if (progress.isCanceled()) {
					SimpleViewer.this
							.showError("reading cancelled");
				}
			}
		};
		showMessage(message);
		fileReaderThread.start();
	}
	

	protected void makeMenus() {
		JMenuBar bar = new JMenuBar();
		bar.add(makeFileMenu());
		setJMenuBar(bar);
	}

	protected void connectEvents() {
		if (myInput == null)
			return;

		myInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				myModel.process(ev.getActionCommand());
			}
		});
		myButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				myModel.process(myInput.getText());
			}
		});
	}

	public void clear(){
	    myOutput.setText("");
	}
	
	public void update(String s){
		myOutput.setLineWrap(true);
		myOutput.setWrapStyleWord(true);
		myOutput.append(s);
	}

	public void showMessage(String s) {
		myMessage.setText(s);
	}

	public void showError(String s) {
		JOptionPane.showMessageDialog(this, s, "Model Error",
				JOptionPane.ERROR_MESSAGE);
		if (myInput != null) {
			myInput.setText("");
		}
	}

	private ProgressMonitorInputStream getMonitorableStream(InputStream stream,
			String message) {
			final ProgressMonitorInputStream pmis = new ProgressMonitorInputStream(
					this, message, stream);

			ProgressMonitor progress = pmis.getProgressMonitor();
			progress.setMillisToDecideToPopup(1);
			progress.setMillisToPopup(1);

			return pmis;
	}
}
