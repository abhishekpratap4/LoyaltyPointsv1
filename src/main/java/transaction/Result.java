package transaction;

/**
 * Created by pratap on 5/15/17.
 */
public class Result {

    private boolean isSuccess;
    private String filePath;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Result(String path, boolean success) {
        filePath = path;
        isSuccess = success;
    }
}
