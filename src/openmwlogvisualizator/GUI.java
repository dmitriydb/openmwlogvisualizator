package openmwlogvisualizator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import static openmwlogvisualizator.ResourceList.*;

/**
 * Main GUI class
 *
 * @author Dmitriy D
 */
public class GUI extends JPanel implements MapStateChangeListener {

    /**
     * true, if save to gif checkbox is checked
     */
    public static boolean WRITE_TO_GIF = false;

    private static GUI gui;

    public static synchronized GUI getGUI() {
        if (gui == null) {
            gui = new GUI(Map.getMap());

        }
        return gui;
    }

    /**
     * original map image
     */
    private BufferedImage mapImage;
    /**
     * drawing gif image
     */
    private volatile BufferedImage gifImage;
    /**
     * original gif image
     */
    private volatile BufferedImage gifOriginalImage;
    /**
     * drawing loading cell marker
     */
    private BufferedImage img;

    ImageOutputStream output;
    /**
     * gif output stream
     */
    GifSequenceWriter writer;

    private Map map;

    private JFrame frame;
    private JPanel panel;

    private JPanel sidePanel;
    private JTextField statusField;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    private JCheckBox saveGifCheckBox;

    private GUI(Map map) {
        this.map = map;
        try {
            gifImage = ImageIO.read(new File(mapLocation));
            gifOriginalImage = ImageIO.read(new File(mapLocation));
            mapImage = ImageIO.read(new File(mapLocation));
            output = new FileImageOutputStream(new File(gifLocation));
            writer = new GifSequenceWriter(output, gifImage.getType(), 100, true);
            writer.writeToSequence(gifImage);
            img = ImageIO.read(new File(imgLocation));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void start() {
        frame = new JFrame(windowTitle);
        frame.setSize(1000, 750);
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        button1 = new JButton("Open openmw.log");
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser f = new JFileChooser("Select openmw.log location");
                f.setFileSelectionMode(JFileChooser.FILES_ONLY);
                f.showOpenDialog(panel);
                String newLocation = f.getSelectedFile().getAbsolutePath();
                //  System.out.println(newLocation);
                ResourceList.logLocation = newLocation;
            }
        });

        button2 = new JButton("Start");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                class XXX implements Runnable {
                    public void run() {
                        LogReader logReader = new LogReader();
                        logReader.readLog();
                    }
                }
                Thread thread = new Thread(new XXX());
                thread.start();

            }
        });
        saveGifCheckBox = new JCheckBox("Save to gif");
        saveGifCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                WRITE_TO_GIF = !WRITE_TO_GIF;
            }
        });
        sidePanel.add(button1);
        sidePanel.add(button2);
        sidePanel.add(saveGifCheckBox);

        frame.add(BorderLayout.CENTER, this);
        frame.add(BorderLayout.EAST, sidePanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        try {

            if (WRITE_TO_GIF) {
                gifImage = ImageIO.read(new File(mapLocation));

            }
            Graphics gif = gifImage.createGraphics();
            g.drawImage(mapImage, 0, 0, this);
            for (int i = 0; i < Map.ROWS; i++) {
                for (int j = 0; j < Map.COLUMNS; j++) {
                    if (Map.getMap().getCell(i, j)) {
                        g.drawImage(img, offsetX + (j * rectSizeX), offsetY + (i * rectSizeY), this);
                        if (WRITE_TO_GIF) {
                            gif.drawImage(img, offsetX + (j * rectSizeX), offsetY + (i * rectSizeY), this);
                        }
                    }
                }
            }
            if (WRITE_TO_GIF) {
                writer.writeToSequence(gifImage);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
