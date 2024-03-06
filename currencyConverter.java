package org.example.gui;
//import org.example.gui.ExchangeRateApiClient;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class currencyConverter extends JFrame {
    public currencyConverter()
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
        String[] currencies = {"USD", "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP", "CNY", "COP", "CRC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "FOK", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KID", "KMF", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SLL", "SOS", "SRD", "SSP", "STN", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TVD", "TWD", "TZS", "UAH", "UGX", "UYU", "UZS", "VES", "VND", "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWL"};
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
                if(amount.isEmpty())
                    JOptionPane.showMessageDialog(null, "No Input");
                else {
                    ExchangeRateApiClient obj = new ExchangeRateApiClient();
                    HashMap<String, Double> map = obj.getExchangeRates();
                    double result = converter(inputCurrency, (String) currencyDropdown2.getSelectedItem(), amount, obj.map);
                    if (result == -1)
                        JOptionPane.showMessageDialog(null, "INVALID INPUT");
                    else {
                        JOptionPane.showMessageDialog(null, amount + " " + inputCurrency + " = " + result + " " + outputCurrency + "\n Last Updated: "+ obj.date);
                    }
                }
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
    private double any_to_dollar(String input,Double amt,HashMap<String, Double> map)
    {
        Double val=map.get(input);
        return (1/val)*amt;
    }
    private double dollar_to_any(String output,Double amt, HashMap<String, Double> map)
    {
        Double val=map.get(output);
        return val*(amt);
    }
    private double converter(String input, String output, String amount, HashMap<String, Double> map)
    {
        double amt=Double.parseDouble(amount);
        if(input==output || amt==0)
            return amt;
        if(amt<0)
            return -1;
        else
        {
            double indollar=any_to_dollar(input, amt,map);
            double dollarto=dollar_to_any(output, indollar, map);
            return dollarto;
        }
    }
    public static void main(String[] args) {
        // Create an instance of the class
        currencyConverter gui = new currencyConverter();

        // Make the window visible
        gui.setVisible(true);
    }
}
