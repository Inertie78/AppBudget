package appBudget;

import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JComboBox;

public class DatePicker extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JComboBox<Integer> dayComboBox;
	public JComboBox<String> monthComboBox;
	public JComboBox<Integer> yearComboBox;
	
	public DatePicker() {
		
		
		setLayout(new FlowLayout());

        // Sélecteur du jour
        dayComboBox = new JComboBox<>();
        dayComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dayComboBox.setForeground(Color.black);

        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }

        // Sélecteur du mois
         monthComboBox = new JComboBox<>(new String[]{
            "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
            "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
         });
         monthComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
         monthComboBox.setForeground(Color.black);

        // Sélecteur d'année
        yearComboBox = new JComboBox<>();
        yearComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        yearComboBox.setForeground(Color.black);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear - 50; i <= currentYear + 100; i++) {
            yearComboBox.addItem(i);
        }

        add(dayComboBox);
        add(monthComboBox);
        add(yearComboBox);
	}
	
	public String DateSelec() {
		
		int day = (int) dayComboBox.getSelectedItem();
		int month = (int) monthComboBox.getSelectedItem();
		int year = (int) yearComboBox.getSelectedItem();
		
		return String.format("%02d %s % %d",  day, month, year);
		
		
	}
}