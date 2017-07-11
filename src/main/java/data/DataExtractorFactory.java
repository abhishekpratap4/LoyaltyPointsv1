package data;

/**
 * Created by pratap on 5/15/17.
 */

import transaction.Constants;

public class DataExtractorFactory {
    public DataExtractor getDataExtractor(String extractorType) {
        DataExtractor extractor = null;
        switch (extractorType) {
            case Constants.CSVDataExtractor:
                extractor = new CsvDataExtractor();
                break;
            default:
                extractor = new CsvDataExtractor();
        }
        return extractor;
    }


    public String getExtractorType(String fileType) {
        String extractor;
        switch (fileType) {
            case "csv":
                extractor = Constants.CSVDataExtractor;
                break;
            case "json":
                extractor = Constants.JSONDataExtractor;
                break;
            case "xml":
                extractor = Constants.XMLDataExtractor;
                break;
            case "xlsx":
                extractor = Constants.ExcelDataExtractor;
                break;
            default:
                extractor = Constants.CSVDataExtractor;
        }
        return extractor;
    }
}
