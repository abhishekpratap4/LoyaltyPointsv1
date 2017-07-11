package transaction;

import data.DataExtractor;
import data.DataExtractorFactory;
import model.Transaction;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import processor.PointCalculator;
import processor.Utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by pratap on 5/15/17.
 */

@RestController
public class LoyaltyPointController {

    @RequestMapping(value = "/getReport", method = RequestMethod.GET)
    public Result getReport(@RequestParam(value = "filePath", defaultValue = "/Users/pratap/Desktop/transactions.csv") String aggregateDataFilePath) {

        DataExtractor extractor = getExtractor(aggregateDataFilePath);
        List<Transaction> transactionList = extractor.extractData(aggregateDataFilePath);
        List<Transaction> loyaltyPointsList = PointCalculator.calculateLoyaltyPoints(transactionList);
        Result result = new Result("", false);
        if (loyaltyPointsList == null)
            return result;
        try {
            System.out.println(aggregateDataFilePath);
            FileWriter fWriter = new FileWriter(new File(Constants.REPORT_PATH));
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(loyaltyPointsList.toString());
            bWriter.close();
            result.setIsSuccess(true);
            result.setFilePath(Constants.REPORT_PATH);
        } catch (IOException i) {
            i.printStackTrace();
            result.setIsSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/getSortedReport", method = RequestMethod.GET)
    public Result getSortedReport(@RequestParam(value = "filePath", defaultValue = "/Users/pratap/Desktop/transactions.csv") String aggregateDataFilePath, @RequestParam(value = "orderBy", defaultValue = "user_id") String orderBy) {

        DataExtractor extractor = getExtractor(aggregateDataFilePath);
        List<Transaction> transactionList = extractor.extractData(aggregateDataFilePath);
        List<Transaction> loyaltyPointsList = PointCalculator.calculateLoyaltyPoints(transactionList);

        List<Transaction> sortedList = Utilities.sortResult(loyaltyPointsList, orderBy);

        Result result = new Result(Constants.REPORT_PATH, false);
        try {
            FileWriter fWriter = new FileWriter(new File(result.getFilePath()));
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(sortedList.toString());
            bWriter.close();
            result.setIsSuccess(true);
        } catch (IOException i) {
            i.printStackTrace();
            result.setIsSuccess(false);
        }
        return result;
    }

    private DataExtractor getExtractor(String filePath) {
        String extension = Utilities.getFileExtension(filePath);
        DataExtractorFactory factory = new DataExtractorFactory();
        String extractorType = factory.getExtractorType(extension);
        return factory.getDataExtractor(extractorType);
    }
}