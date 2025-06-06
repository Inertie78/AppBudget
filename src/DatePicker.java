import javax.swing.*;

import java.awt.FlowLayout;
import java.util.Calendar;

import javax.swing.JComboBox;

public class DatePicker extends JPanel{

	private static final long serialVersionUID = 1L;

	public DatePicker(BudgetController controller) {
		
		setLayout(new FlowLayout());

        // Day ComboBox
		JComboBox<Integer> dayComboBox = new JComboBox<>();
        dayComboBox.setFont(controller.tahomaFont12);
        dayComboBox.setForeground(controller.colorSelect);

        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }

        // Month ComboBox
        JComboBox<String> monthComboBox = new JComboBox<>(new String[]{
           "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
         });
         monthComboBox.setFont(controller.tahomaFont12);
         monthComboBox.setForeground(controller.colorSelect);

        // Year ComboBox
         JComboBox<Integer> yearComboBox = new JComboBox<>();
        yearComboBox.setFont(controller.tahomaFont12);
        yearComboBox.setForeground(controller.colorSelect);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear - 50; i <= currentYear + 100; i++) {
            yearComboBox.addItem(i);
        }
        
        dayComboBox.addActionListener(_ -> {
        	int value = (int) dayComboBox.getSelectedItem();
        	String day;
        	if(value < 10) {
        		day = "0" + String.valueOf(value);
        	}else {day = String.valueOf(value);}
        	controller.setDay(day);
        });
        
        monthComboBox.addActionListener(_ -> {
        	controller.setMonth((String) monthComboBox.getSelectedItem());
        });
        
        yearComboBox.addActionListener(_ -> {
        	controller.setYear(String.valueOf(yearComboBox.getSelectedItem()));
        });
        
        add(dayComboBox);
        add(monthComboBox);
        add(yearComboBox);
	}
}