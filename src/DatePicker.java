import javax.swing.*;

import java.awt.FlowLayout;
import java.util.Calendar;

import javax.swing.JComboBox;

//fonction qui permet d'afficher les menus déroulants pour le choix du jour, mois, année
public class DatePicker extends JPanel{

	private static final long serialVersionUID = 1L;              

	public DatePicker(BudgetController controller) {                       // défini le Panel et le Layout 
		
		setLayout(new FlowLayout());

        //jour ComboBox
		JComboBox<Integer> dayComboBox = new JComboBox<>();
        dayComboBox.setFont(controller.tahomaFont12);
        dayComboBox.setForeground(controller.colorSelect);

        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }

        //mois ComboBox
        JComboBox<String> monthComboBox = new JComboBox<>(new String[]{
        	"janvier", "février", "mars", "avril", "mai", "juin",
            "juillet", "août", "septembre", "octobre", "novembre", "décembre"
         });
         monthComboBox.setFont(controller.tahomaFont12);
         monthComboBox.setForeground(controller.colorSelect);

        //année ComboBox
         JComboBox<Integer> yearComboBox = new JComboBox<>();
        yearComboBox.setFont(controller.tahomaFont12);
        yearComboBox.setForeground(controller.colorSelect);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear - 10; i <= currentYear + 100; i++) {
            yearComboBox.addItem(i);
        }
        
        //inscrire l'événement jour (si change)
        dayComboBox.addActionListener(e -> {
        	int value = (int) dayComboBox.getSelectedItem();
        	String day;
        	if(value < 10) {
        		day = "0" + String.valueOf(value);
        	}else {day = String.valueOf(value);}
        	controller.setDay(day);
        });
        
        //inscrire l'événement mois (si change)
        monthComboBox.addActionListener(e -> {
        	controller.setMonth((String) monthComboBox.getSelectedItem());
        });
        
        //inscrire l'événement année (si change)
        yearComboBox.addActionListener(e -> {
        	controller.setYear(String.valueOf(yearComboBox.getSelectedItem()));
        });
        
        add(dayComboBox);
        add(monthComboBox);
        add(yearComboBox);
	}
}