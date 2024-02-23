## Currency converter
The Currency Converter is a Java application that allows users to convert between different currencies using exchange rates obtained from an external API.

# Features
Allows users to input an amount in a chosen currency and convert it to another chosen currency.
Provides a dropdown menu for selecting input and output currencies from a wide range of options.
Retrieves exchange rates from an external API for accurate currency conversion.

# Usage
1> Compile the Java file 'currencyConverter.java' .
  javac currencyConverter.java

2> Run the compiled class file.
  java currencyConverter
  
3> Input the amount to be converted in the "Input amount" field.

4> Select the input currency from the dropdown menu labeled "Convert from".

5> Select the desired output currency from the dropdown menu labeled "Convert to".

6> The converted amount will be displayed upon selection of the output currency.

# Dependencies
Java Development Kit (JDK) version 8 or higher.
Internet connection for fetching exchange rates from the API.

# Notes
The exchange rates are obtained from an external API. Ensure a stable internet connection for accurate conversion.
Invalid inputs or errors during conversion will be handled by displaying appropriate error messages.
