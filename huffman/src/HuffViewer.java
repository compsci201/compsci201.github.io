
/**
 *	GUI code for Huffman
 *
 *	@author Brian Lavallee
 *	@since 16 November 2015
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HuffViewer {

	private static final double TAB_HEIGHT = 23;

	private static final double TAB_PADDING = 70;
	private static final double INTERNAL_PADDING = 7;
	private static final double MARGIN = 10;

	private String processor;

	public HuffViewer(String processor) {
		this.processor = processor;
	}

	public Group createLayout(double width, double height) {
		Group root = new Group();

		TabPane holder = new TabPane();
		holder.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		holder.setMinSize(width, height);
		holder.setTabMinWidth((width - TAB_PADDING) / 4.0);
		holder.setTabMinHeight(TAB_HEIGHT);

		double contentWidth = width - 2 * MARGIN;
		double contentHeight = height - TAB_HEIGHT - 2 * MARGIN;

		Tab compress = new Tab("Compress");
		compress.setContent(createCompressTab(contentWidth, contentHeight));

		Tab decompress = new Tab("Decompress");
		decompress.setContent(createDecompressTab(contentWidth, contentHeight));

		Tab compare = new Tab("Compare");
		compare.setContent(createCompareTab(contentWidth, contentHeight));

		Tab test = new Tab("Test");
		test.setContent(createTestTab(contentWidth, contentHeight));

		holder.getTabs().addAll(compress, decompress, compare, test);
		root.getChildren().add(holder);
		return root;
	}

	private VBox createCompressTab(double contentWidth, double contentHeight) {
		VBox tabContent = new VBox(MARGIN);
		tabContent.setMinSize(contentWidth, contentHeight);
		tabContent.setTranslateX(MARGIN);
		tabContent.setTranslateY(MARGIN);

		HBox upper = new HBox();
		upper.setMinHeight(80);

		HuffPanel panel = new HuffPanel(contentWidth, contentHeight - 150);

		StatusBar status = new StatusBar(contentWidth - 200);

		VBox inputField = new VBox(INTERNAL_PADDING);
		inputField.setMinWidth(200);
		HuffChooser chooser = new HuffChooser(true, "Compress");
		Button compress = new Button("Compress");
		compress.setOnAction((clicked) -> compress(chooser.getChosenFile(), status, panel));
		inputField.getChildren().addAll(chooser.render(), compress);

		upper.getChildren().addAll(inputField, status.render());

		tabContent.getChildren().addAll(upper, panel.render(new String[] { "Info" }));
		return tabContent;
	}

	private VBox createDecompressTab(double contentWidth, double contentHeight) {
		VBox tabContent = new VBox(MARGIN);
		tabContent.setMinSize(contentWidth, contentHeight);
		tabContent.setTranslateX(MARGIN);
		tabContent.setTranslateY(MARGIN);

		HBox upper = new HBox();
		upper.setMinHeight(80);

		HuffPanel panel = new HuffPanel(contentWidth, contentHeight - 150);

		StatusBar status = new StatusBar(contentWidth - 200);

		VBox inputField = new VBox(INTERNAL_PADDING);
		HuffChooser chooser = new HuffChooser(true, "Decompress");
		Button decompress = new Button("Decompress");
		decompress.setOnAction((clicked) -> decompress(chooser.getChosenFile(), status, panel));
		inputField.getChildren().addAll(chooser.render(), decompress);

		upper.getChildren().addAll(inputField, status.render());

		tabContent.getChildren().addAll(upper, panel.render(new String[] { "Info" }));
		return tabContent;
	}

	private VBox createCompareTab(double contentWidth, double contentHeight) {
		VBox tabContent = new VBox(MARGIN);
		tabContent.setMinSize(contentWidth, contentHeight);
		tabContent.setTranslateX(MARGIN);
		tabContent.setTranslateY(MARGIN);

		HBox upper = new HBox();
		upper.setMinHeight(80);

		HuffPanel panel = new HuffPanel(contentWidth, contentHeight - 150);

		StatusBar status = new StatusBar(contentWidth - 200);

		VBox inputField = new VBox(INTERNAL_PADDING);
		HuffChooser chooserA = new HuffChooser(true, "File A");
		HuffChooser chooserB = new HuffChooser(true, "File B");
		Button compare = new Button("Compare");
		compare.setOnAction((clicked) -> compare(chooserA.getChosenFile(), chooserB.getChosenFile(), status, panel));
		inputField.getChildren().addAll(chooserA.render(), chooserB.render(), compare);

		upper.getChildren().addAll(inputField, status.render());

		tabContent.getChildren().addAll(upper, panel.render(new String[] { "Info" }));
		return tabContent;
	}

	private VBox createTestTab(double contentWidth, double contentHeight) {
		VBox tabContent = new VBox(MARGIN);
		tabContent.setMinSize(contentWidth, contentHeight);
		tabContent.setTranslateX(MARGIN);
		tabContent.setTranslateY(MARGIN);

		HBox upper = new HBox();
		upper.setMinHeight(80);

		HuffPanel panel = new HuffPanel(contentWidth, contentHeight - 150);

		StatusBar status = new StatusBar(contentWidth - 200);

		VBox inputField = new VBox(INTERNAL_PADDING);
		HuffChooser chooser = new HuffChooser(false, "Directory");
		CheckBox hf = new CheckBox("test .hf files");
		Button test = new Button("Test");
		test.setOnAction((clicked) -> test(chooser.getChosenFile(), hf.isSelected(), status, panel));
		inputField.getChildren().addAll(chooser.render(), hf, test);

		upper.getChildren().addAll(inputField, status.render());

		tabContent.getChildren().addAll(upper, panel.render(new String[] { "Info" }));
		return tabContent;
	}

	private VBox getInfo(double[] times, File[] originalFiles, File[] newFiles) {
		VBox holder = new VBox(INTERNAL_PADDING);

		int totalOriginalLength = 0;
		double totalTime = 0;
		int totalNewLength = 0;
		for (int i = 0; i < times.length; i++) {
			totalOriginalLength += originalFiles[i].length();
			totalTime += times[i];
			totalNewLength += newFiles[i].length();
		}

		double percentSaved = 1.0 - ((double) totalNewLength) / ((double) totalOriginalLength);
		percentSaved *= 100.0;

		holder.getChildren().add(new Text("Total time: " + totalTime / 1000.0 + "s"));
		holder.getChildren().add(new Text("Total original length: " + totalOriginalLength + " bytes"));
		holder.getChildren().add(new Text("Total new length: " + totalNewLength + " bytes"));
		holder.getChildren().add(new Text(String.format("Percent space saved: %.2f", percentSaved) + "%"));

		return holder;
	}

	private class ProgressUpdater extends AnimationTimer {
		private Generator generator;
		private StatusBar status;
		private HuffPanel panel;
		private double progress;

		public ProgressUpdater(StatusBar status, HuffPanel panel) {
			this.status = status;
			this.panel = panel;
			generator = () -> {
				return 0;
			};
		}

		public void handle(long time) {
			status.setProgress(generator.generate());
		}

		public void setGenerator(Generator generator) {
			this.generator = generator;
		}

		public void updateStatus(Status s, String message) {
			Platform.runLater(() -> {
				status.setStatus(s, message);
			});
		}

		public void addContent(String label, Node content) {
			Platform.runLater(() -> {
				panel.addContent(label, content);
			});
		}

		public double progress() {
			return progress;
		}

		public void setProgress(double progress) {
			this.progress = progress;
		}
	}

	private interface Generator {
		public double generate();
	}

	private Processor getProcessor() {
		try {
			return (Processor) Class.forName(processor).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void compress(File original, StatusBar status, HuffPanel panel) {
		if (original == null) {
			return;
		}

		status.initialize();
		panel.clear();

		File compressed = new File(original.getPath() + ".hf");

		try {
			BitInputStream in = new BitInputStream(original);
			BitOutputStream out = new BitOutputStream(compressed);

			ProgressUpdater updater = new ProgressUpdater(status, panel);

			Thread thread = new Thread(() -> {
				updater.updateStatus(Status.Working, "compressing " + original.getName());
				updater.start();
				updater.setGenerator(() -> {
					return in.getBitsRead() / (8.0 * original.length());
				});

				try {
					Processor processor = getProcessor();
					double start = System.currentTimeMillis();
					processor.compress(in, out);
					updater.addContent("Info", getInfo(new double[] { System.currentTimeMillis() - start },
							new File[] { original }, new File[] { compressed }));
					updater.stop();
					updater.updateStatus(Status.Complete, "compression successful");
				} catch (HuffException e) {
					compressed.delete();
					updater.stop();
					updater.updateStatus(Status.Failed, e.getMessage());
				} catch (Exception e) {
					compressed.delete();
					updater.stop();
					updater.updateStatus(Status.Failed, "unknown error");
					e.printStackTrace();
				}

				out.close();
				in.close();
			});
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
			compressed.delete();
		}
	}

	private void decompress(File original, StatusBar status, HuffPanel panel) {
		if (original == null) {
			return;
		}

		status.initialize();
		panel.clear();

		String name = original.getPath();
		name = name.endsWith(".hf") ? name.substring(0, name.length() - 3) : name;
		File decompressed = new File(name + ".dehf");

		try {
			BitInputStream in = new BitInputStream(original);
			BitOutputStream out = new BitOutputStream(decompressed);

			ProgressUpdater updater = new ProgressUpdater(status, panel);

			Thread thread = new Thread(() -> {
				updater.setGenerator(() -> {
					return in.getBitsRead() / (8.0 * original.length());
				});
				updater.updateStatus(Status.Working, "decompressing " + original.getName());
				updater.start();

				try {
					Processor processor = getProcessor();
					double start = System.currentTimeMillis();
					processor.decompress(in, out);
					updater.addContent("Info", getInfo(new double[] { System.currentTimeMillis() - start },
							new File[] { original }, new File[] { decompressed }));
					updater.stop();
					updater.updateStatus(Status.Complete, "decompression successful");
				} catch (HuffException e) {
					decompressed.delete();
					updater.stop();
					updater.updateStatus(Status.Failed, e.getMessage());
				} catch (Exception e) {
					updater.stop();
					e.printStackTrace();
					decompressed.delete();
					updater.updateStatus(Status.Failed, "unknown error");
				}

				in.close();
				out.close();
			});
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
			decompressed.delete();
		}
	}

	private void compare(File fileA, File fileB, StatusBar status, HuffPanel panel) {
		if (fileA == null || fileB == null) {
			return;
		}

		status.initialize();

		BitInputStream inA = new BitInputStream(fileA);
		BitInputStream inB = new BitInputStream(fileB);

		ProgressUpdater updater = new ProgressUpdater(status, panel);

		Thread thread = new Thread(() -> {
			updater.setGenerator(() -> {
				return inA.getBitsRead() / (8.0 * fileA.length());
			});
			updater.updateStatus(Status.Working, "comparing " + fileA.getName() + " and " + fileB.getName());
			updater.start();

			try {
				inA.reset();
				int bitA = 0;
				int bitB = 0;
				double start = System.currentTimeMillis();
				int count = 0;
				while ((bitA = inA.readBits(1)) != -1 & (bitB = inB.readBits(1)) != -1) {
					if (bitA != bitB) {
						updater.stop();
						updater.updateStatus(Status.Failed, "files differ somewhere");
						inA.close();
						inB.close();
						updater.addContent("Info", getSimpleInfo(System.currentTimeMillis() - start, fileA.length(),
								fileB.length(), count));
						return;
					}
					count++;
				}
				updater.addContent("Info",
						getSimpleInfo(System.currentTimeMillis() - start, fileA.length(), fileB.length(), -1));
				updater.stop();
				inA.close();
				inB.close();
				if (bitA == bitB) {
					updater.updateStatus(Status.Complete, "files are the same");
				} else {
					updater.updateStatus(Status.Failed, "files differ at the end");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}

	private VBox getSimpleInfo(double time, long lengthA, long lengthB, int firstDifference) {
		VBox holder = new VBox(INTERNAL_PADDING);
		holder.getChildren().add(new Text("Time: " + time + "s"));
		holder.getChildren().add(new Text("File A length: " + lengthA + " bytes"));
		holder.getChildren().add(new Text("File B length: " + lengthB + " bytes"));
		holder.getChildren().add(new Text("First difference at bit " + firstDifference));
		return holder;
	}

	private void test(File directory, boolean useHF, StatusBar status, HuffPanel panel) {
		if (directory == null) {
			return;
		}

		status.initialize();
		panel.clear();

		File[] files = directory.listFiles();
		List<File> toCompress = new ArrayList<>();
		for (File file : files) {
			if (file.isDirectory() || file.getName().endsWith(".dehf") || (file.getName().endsWith(".hf") && !useHF)
					|| (!file.getName().endsWith(".hf") && useHF)) {
				continue;
			}
			toCompress.add(file);
		}

		int total = 0;
		for (File file : toCompress) {
			total += file.length();
		}
		final int sum = total;

		ProgressUpdater updater = new ProgressUpdater(status, panel);

		@SuppressWarnings("resource")
		Thread thread = new Thread(() -> {
			updater.start();
			double[] times = new double[toCompress.size()];
			File[] compressed = new File[toCompress.size()];
			for (int i = 0; i < toCompress.size(); i++) {
				File file = toCompress.get(i);
				try {
					updater.updateStatus(Status.Working, "compressing " + file.getName());
					BitInputStream in = new BitInputStream(file);
					compressed[i] = new File(file.getPath() + ".hf");
					BitOutputStream out = new BitOutputStream(compressed[i]);
					updater.setGenerator(() -> {
						return (updater.progress() + in.getBitsRead()) / (8.0 * sum);
					});
					Processor processor = getProcessor();
					double start = System.currentTimeMillis();
					processor.compress(in, out);
					times[i] = System.currentTimeMillis() - start;
					updater.setProgress(updater.progress() + file.length() * 8.0);
				} catch (Exception e) {
					System.err.println("problem compressing " + file.getName());
				}
			}

			updater.stop();
			updater.updateStatus(Status.Complete, "test complete");
			updater.addContent("Info", getInfo(times, toCompress.toArray(new File[toCompress.size()]), compressed));
		});
		thread.start();
	}

	public class HuffChooser {

		private final File INITIAL_DIRECTORY = new File("data");

		private final Color VALID = Color.DARKGREEN;
		private final Color INVALID = Color.DARKRED;
		private final Color NEUTRAL = Color.BLACK;

		private static final String DEFAULT = "No File Chosen";
		private static final String CHOOSE = "Choose New File";

		private static final double WIDTH = 200;

		private boolean choosingFile;
		private File chosenFile;
		private String label;

		public HuffChooser(boolean choosingFile, String label) {
			this.choosingFile = choosingFile;
			this.label = label;
		}

		public Node render() {
			HBox holder = new HBox();
			holder.setMinWidth(WIDTH);

			Text title = new Text();
			setText(title, label + ": ", NEUTRAL);

			Text fileName = new Text();
			setText(fileName, DEFAULT, INVALID);

			fileName.setOnMouseEntered((entered) -> fadeText(fileName, CHOOSE, NEUTRAL));
			fileName.setOnMouseExited((exited) -> handleExit(fileName));
			fileName.setOnMouseClicked((clicked) -> handleClick(fileName));

			holder.getChildren().addAll(title, fileName);
			return holder;
		}

		public File getChosenFile() {
			return chosenFile;
		}

		private void handleExit(Text fileName) {
			if (chosenFile == null) {
				fadeText(fileName, DEFAULT, INVALID);
			} else {
				fadeText(fileName, chosenFile.getName(), VALID);
			}
		}

		private void handleClick(Text fileName) {
			fileName.setOnMouseExited(null);

			File temp;
			if (choosingFile) {
				FileChooser chooser = new FileChooser();
				chooser.setInitialDirectory(INITIAL_DIRECTORY);
				chooser.setTitle("Choose File");
				temp = chooser.showOpenDialog(new Stage());
			} else {
				DirectoryChooser chooser = new DirectoryChooser();
				chooser.setInitialDirectory(INITIAL_DIRECTORY);
				chooser.setTitle("Choose Folder");
				temp = chooser.showDialog(new Stage());
			}

			if (temp != null) {
				chosenFile = temp;
			}
			handleExit(fileName);

			fileName.setOnMouseExited((exited) -> handleExit(fileName));
		}

		private void setText(Text text, String value, Color fill) {
			text.setText(value);
			text.setFill(fill);
		}

		private void fadeText(Text text, String value, Color fill) {
			FadeTransition fade = new FadeTransition(Duration.millis(50), text);
			fade.setToValue(0.0);
			fade.setOnFinished((finished) -> {
				setText(text, value, fill);
				fade.setToValue(1.0);
				fade.setOnFinished(null);
				fade.play();
			});
			fade.play();
		}
	}

	public class HuffPanel {

		private static final double HEIGHT = 25;
		private static final double ARC = 10;
		private static final double PADDING = 8;

		private double width, height;
		private Map<String, Node> content;
		private ScrollPane contentHolder;
		private String selected;

		public HuffPanel(double width, double height) {
			this.width = width;
			this.height = height;
			content = new HashMap<>();
		}

		public Node render(String[] options) {
			VBox holder = new VBox();
			holder.setMinSize(width, height);
			contentHolder = new ScrollPane();
			contentHolder.setMinSize(width, height);
			contentHolder.setMaxSize(width, height);
			Group menuBar = createMenu(options);
			holder.getChildren().addAll(menuBar, contentHolder);
			return holder;
		}

		private Group createMenu(String[] options) {
			Rectangle background = new Rectangle(width, HEIGHT, Color.DARKGRAY);
			background.setStroke(Color.BLACK);
			background.setArcHeight(ARC);
			background.setArcWidth(ARC);

			HBox titleHolder = new HBox();
			titleHolder.setMinSize(width, HEIGHT);
			titleHolder.setAlignment(Pos.CENTER_LEFT);

			Text title = new Text();
			titleHolder.getChildren().add(title);

			HBox menuHolder = new HBox(PADDING);
			menuHolder.setMinSize(width - PADDING, HEIGHT);
			menuHolder.setAlignment(Pos.CENTER_RIGHT);

			Text[] buttons = new Text[options.length];
			for (int i = 0; i < options.length; i++) {
				buttons[i] = new Text(options[i]);
			}

			selected = options[0];
			for (Text option : buttons) {
				menuHolder.getChildren().add(option);
				if (option.getText() != selected) {
					option.setOpacity(0.5);
				}
				option.setOnMouseClicked((clicked) -> {
					selected = option.getText();
					for (Text t : buttons) {
						FadeTransition fade = new FadeTransition(Duration.millis(50), t);
						fade.setToValue(0.5);
						fade.play();
					}
					FadeTransition fade = new FadeTransition(Duration.millis(50), option);
					fade.setToValue(1.0);
					fade.play();
					contentHolder.setContent(null);
					if (content.containsKey(option.getText())) {
						contentHolder.setContent(content.get(option.getText()));
					}
				});
			}

			Group menuRoot = new Group();
			menuRoot.getChildren().addAll(background, titleHolder, menuHolder);
			return menuRoot;
		}

		public void addContent(String label, Node node) {
			content.put(label, node);
			if (selected == label) {
				contentHolder.setContent(node);
			}
		}

		public void clear() {
			content.clear();
			contentHolder.setContent(null);
		}
	}

	public enum Status {

		Complete(Color.DARKGREEN, "100%"), Working(Color.DARKGRAY, ""), Failed(Color.DARKRED, "  0%");

		private Color color;
		private String percent;

		Status(Color color, String percent) {
			this.color = color;
			this.percent = percent;
		}

		public Color color() {
			return color;
		}

		public String percent() {
			return percent;
		}
	}

	public class StatusBar {

		private static final double OUTER = 33;
		private static final double INNER = 30.5;
		private static final double BUFFER = 1.25;

		private static final double PADDING = 5;

		private double width;

		private Rectangle progress;
		private Text percent;
		private Text status;

		public StatusBar(double width) {
			this.width = width;
		}

		public Node render() {
			VBox holder = new VBox(PADDING);
			holder.setMinWidth(width);

			status = new Text();
			percent = new Text();
			progress = new Rectangle();

			initialize();

			HBox statusHolder = new HBox();
			statusHolder.setMinWidth(width - INNER);
			statusHolder.setMaxWidth(width - INNER);
			statusHolder.setTranslateX(INNER / 2);
			statusHolder.setAlignment(Pos.CENTER_RIGHT);
			statusHolder.getChildren().add(status);

			HBox percentHolder = new HBox();
			percentHolder.setAlignment(Pos.CENTER);
			percentHolder.setMinSize(width, OUTER);
			percentHolder.getChildren().add(percent);

			Group progressBar = new Group();

			Rectangle background = new Rectangle(width, OUTER);
			background.setFill(Color.LIGHTGRAY);
			background.setStroke(Color.BLACK);
			background.setStrokeWidth(BUFFER);
			background.setArcHeight(OUTER);
			background.setArcWidth(OUTER);

			progressBar.getChildren().addAll(background, progress, percentHolder);
			holder.getChildren().addAll(progressBar, statusHolder);
			return holder;
		}

		public void initialize() {
			percent.setText("  0%");
			status.setText("");
			progress.setHeight(INNER);
			progress.setWidth(0);
			progress.setFill(Color.DARKGRAY);
			progress.setTranslateX(BUFFER);
			progress.setArcHeight(INNER);
			progress.setArcWidth(INNER);
		}

		public void setProgress(double percentComplete) {
			double progressWidth = percentComplete * (width - 2 * BUFFER);

			if (progressWidth < INNER) {
				double height = Math.sqrt(Math.pow(INNER, 2) - (Math.pow(progressWidth - INNER, 2)));
				progress.setHeight(height);
				progress.setTranslateY(BUFFER + (INNER - height) / 2);
			} else {
				progress.setHeight(INNER);
				progress.setTranslateY(BUFFER);
			}

			progress.setWidth(progressWidth);
			percent.setText((int) (100 * percentComplete) + "%");
		}

		public void setStatus(Status updatedStatus, String message) {
			if (updatedStatus != Status.Working) {
				progress.setFill(updatedStatus.color());
				progress.setWidth(width - 2 * BUFFER);
				progress.setHeight(INNER);
				progress.setTranslateY(BUFFER);
				percent.setText(updatedStatus.percent());
			}
			FadeTransition fade = new FadeTransition(Duration.millis(50), status);
			fade.setToValue(0.0);
			fade.setOnFinished((finished) -> {
				status.setText(message);
				fade.setToValue(1.0);
				fade.setOnFinished(null);
				fade.play();
			});
			fade.play();
		}
	}
}