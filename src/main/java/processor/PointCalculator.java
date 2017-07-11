package processor;

import model.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by pratap on 5/15/17.
 */
public class PointCalculator {
    public static List<Transaction> calculateLoyaltyPoints(List<Transaction> transactionList) {
        DateFormat sourceFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<Transaction> tempList = null;
        if (transactionList == null)
            return tempList;

        tempList = transactionList.stream()
                .filter(transaction -> !transaction.getTransactionType().equalsIgnoreCase("debit"))
                .map(Transaction::calculateLoyaltyPoint)
                .collect(Collectors.toList());

        List<List<String>> masterList = tempList
                .stream()
                .filter(t -> t.getTransactionType().equalsIgnoreCase("credit_card") || t.getTransactionType().equalsIgnoreCase("debit_card"))
                .collect(Collectors.groupingBy(x -> {
                    return new ArrayList<String>(
                            Arrays.asList(
                                    x.getUserId(),
                                    x.getAccountId(),
                                    x.getTransactionType(),
                                    sourceFormat.format(x.getTransactionDate())));
                }, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        for (List<String> strList : masterList) {
            for (Transaction transaction : tempList) {
                if (transaction.getUserId().equalsIgnoreCase(strList.get(0)) && transaction.getAccountId().equalsIgnoreCase(strList.get(1)) && transaction.getTransactionType().equalsIgnoreCase(strList.get(2))) {
                    int point = transaction.getLoyaltyPoint() < 100 ? 0 : transaction.getLoyaltyPoint() - 100;
                    transaction.setLoyaltyPoint(point);
                    break;
                }
            }
        }
        return tempList;
    }
}
