import javax.swing.*;

import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.JComboBox;

public class DatePicker extends JPanel implements PropertyChangeListener{

	private static final long serialVersionUID = 1L;

	public DatePicker(BudgetModel model, BudgetController controller) {
		
		setLayout(new FlowLayout());

        // Day ComboBox
		JComboBox<Integer> dayComboBox = new JComboBox<>();
        dayComboBox.setFont(model.tahomaFont12);
        dayComboBox.setForeground(model.colorSelect);

        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }

        // Month ComboBox
        JComboBox<String> monthComboBox = new JComboBox<>(new String[]{
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
         });
         monthComboBox.setFont(model.tahomaFont12);
         monthComboBox.setForeground(model.colorSelect);

        // Year ComboBox
         JComboBox<Integer> yearComboBox = new JComboBox<>();
        yearComboBox.setFont(model.tahomaFont12);
        yearComboBox.setForeground(model.colorSelect);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear - 50; i <= currentYear + 100; i++) {
            yearComboBox.addItem(i);
        }
        
        dayComboBox.addActionListener(_ -> {
        	controller.setDay((String)dayComboBox.getSelectedItem());
        });
        
        monthComboBox.addActionListener(_ -> {
        	controller.setMonth((String)monthComboBox.getSelectedItem());
        });
        
        yearComboBox.addActionListener(_ -> {
        	controller.setYear((String)yearComboBox.getSelectedItem());
        });
        
        model.addPropertyChangeListener(this);

        add(dayComboBox);
        add(monthComboBox);
        add(yearComboBox);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}