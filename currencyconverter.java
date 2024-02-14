import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
    public GUI() 
    {
        // Set the title of the window
        super("CURRENCY CONVERTER");

        // Set the size of the window
        setSize(350, 300);

        // Set the layout manager
        setLayout(new FlowLayout());
        // Create a label
        JLabel label = new JLabel("Input amount:");
        // Create a TextField
        JTextField nameField = new JTextField(15);
        //Set drop down menu
        String[] currencies = {"USD", "INR", "EUR", "GBP", "JPY"};
        JComboBox<String> currencyDropdown1 = new JComboBox<>(currencies);
        
        JLabel label2 = new JLabel("Convert to:");
        JComboBox<String> currencyDropdown2 = new JComboBox<>(currencies);
        // Add action listener to the dropdown menu
        currencyDropdown1.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                JComboBox comboBox = (JComboBox) e.getSource();
                String inputCurrency = (String) comboBox.getSelectedItem();
                String outputCurrency = (String) currencyDropdown2.getSelectedItem();
                String amount = nameField.getText();
            }
        });
        currencyDropdown2.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                JComboBox comboBox = (JComboBox) e.getSource();
                String outputCurrency = (String) comboBox.getSelectedItem();
                String inputCurrency = (String) currencyDropdown1.getSelectedItem();
                String amount = nameField.getText();
                double result = converter(inputCurrency, (String) currencyDropdown2.getSelectedItem(), amount);
                if(result==-1)
                    System.out.println("INVALID");
                else
                    System.out.println(amount+" "+inputCurrency+" = "+result+" "+outputCurrency);
            }
        });
        // Add the label to the frame
        add(label);
        
        //add(TextField);
        add(nameField);
        
        //add dropdown menu
        add(currencyDropdown1);
        add(label2);
        add(currencyDropdown2);
        // Set what happens when you close the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private double converter(String input,String output,String amount)
    {
        double amt=Integer.parseInt(amount);
        if(input==output || amt==0)
            return amt;
        if(amt<0)
            return -1;
        if(input=="USD" && output=="INR")
            return amt*83.04;
        if(input=="USD" && output=="EUR")
            return amt*0.93;
        if(input=="USD" && output=="GBP")
            return amt*0.80;
        if(input=="USD" && output=="JPY")
            return amt*150.72;
        return -1;
    }
    public static void main(String[] args) {
        // Create an instance of the GUI
        GUI gui = new GUI();

        // Make the window visible
        gui.setVisible(true);
    }
}
