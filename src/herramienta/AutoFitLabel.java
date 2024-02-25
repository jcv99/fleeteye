package herramienta;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JLabel;

public class AutoFitLabel extends JLabel {
	public AutoFitLabel(String text) {
		super(text);
		setFont(new Font("Arial", Font.PLAIN, 14)); // Set your preferred font here
		setSize(getPreferredSize());
	}

	@Override
	public Dimension getPreferredSize() {
		FontMetrics metrics = getFontMetrics(getFont());
		int width = metrics.stringWidth(getText());
		int height = metrics.getHeight();
		return new Dimension(width, height);
	}
}
