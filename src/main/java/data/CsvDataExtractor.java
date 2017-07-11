package data;

import model.Transaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by pratap on 5/15/17.
 */
public class CsvDataExtractor extends DataExtractor {

    public static final String type = "CsvDataExtractor";

    @Override
    public List<Transaction> extractData(String filePath) {
        final Pattern pattern = Pattern.compile(",");
        DateFormat sourceFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<Transaction> transactionDetailsList = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));) {
            transactionDetailsList = bufferedReader.
                    lines().
                    skip(1).
                    map(line -> {
                        String[] arr = pattern.split(line);
                        try {
                            return new Transaction(arr[0].trim(), arr[1].trim(), arr[2].trim(), arr[3].trim(), sourceFormat.parse(arr[4].trim()), Double.parseDouble(arr[5].trim()), arr[6].trim());
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return new Transaction();
                        }
                    }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionDetailsList;
    }
}
