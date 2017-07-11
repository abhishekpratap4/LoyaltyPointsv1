package data;

import model.Transaction;

import java.util.List;

/**
 * Created by pratap on 5/15/17.
 */
public abstract class DataExtractor {
    public abstract List<Transaction> extractData(String filePath);
}
