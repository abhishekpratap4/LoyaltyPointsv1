package processor;

import model.Transaction;
import org.apache.commons.io.FilenameUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pratap on 5/15/17.
 */
public class Utilities {
    public static List<Transaction> sortResult(List<Transaction> transactionList, String orderBy) {
        List<Transaction> sortedList = transactionList.stream().sorted(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                if (orderBy.equalsIgnoreCase("user_id"))
                    return o1.getUserId().compareToIgnoreCase(o2.getUserId());
                else if (orderBy.equalsIgnoreCase("account_id"))
                    return o1.getAccountId().compareToIgnoreCase(o2.getAccountId());
                else if (orderBy.equalsIgnoreCase("transaction_type"))
                    return o1.getTransactionType().compareToIgnoreCase(o2.getTransactionType());
                else if (orderBy.equalsIgnoreCase("transaction_date"))
                    return o1.getTransactionDate().compareTo(o2.getTransactionDate());
                else if (orderBy.equalsIgnoreCase("priority"))
                    return o1.getPriorityFlag().compareTo(o2.getPriorityFlag());
                else
                    return o1.getUserId().compareToIgnoreCase(o2.getUserId());
            }
        }).collect(Collectors.toList());
        System.out.println(sortedList);
        return sortedList;
    }

    public static String getFileExtension(String filePath) {
        return FilenameUtils.getExtension(filePath);
    }
}
