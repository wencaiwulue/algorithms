package computationalgeometry;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class GeneralPathDemo extends JPanel {

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x1 = this.getWidth() / 2;
        int y1 = 20;
        int x2 = this.getWidth() / 5;
        int y2 = this.getHeight() - 20;
        int x3 = x2 * 4;
        int y3 = this.getHeight() - 20;
        int x4 = 20;
        int y4 = this.getHeight() / 3;
        int x5 = this.getHeight() - 20;
        int y5 = y4;

        int[] x1Points = {x1, x2, x5, x4, x3};
        int[] yPoints = {y1, y2, y5, y4, y3};
        g2d.setPaint(Color.red);
        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
        polygon.moveTo(x1Points[0], yPoints[0]);
        for (int i = 1; i < x1Points.length; i++) {
            polygon.lineTo(x1Points[i], yPoints[i]);

        }

        polygon.closePath();
        g2d.draw(polygon);
        g2d.dispose();
    }

    public static void main(String[] args) {
        JFrame ui = new JFrame("DEMO");
        ui.setIconImage(new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.getContentPane().setLayout(new BorderLayout());
        ui.getContentPane().add(new GeneralPathDemo(), BorderLayout.CENTER);
        ui.setPreferredSize(new Dimension(380, 380));
        ui.pack();
        ui.setVisible(true);
    }
}
