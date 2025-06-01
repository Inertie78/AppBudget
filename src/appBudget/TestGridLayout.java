package appBudget;
import java.awt.Color;
import javax.swing.*;

public class TestGridLayout extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestGridLayout() {
    	JPanel left = new JPanel();
    	left.setBackground(Color.RED);
    	JPanel right = new JPanel();
    	right.setBackground(Color.BLUE);

    	JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
    	split.setResizeWeight(0.3); // 30% left, 70% right

    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(600, 400);
    	frame.getContentPane().add(split);
    	frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestGridLayout::new);
    }
}
