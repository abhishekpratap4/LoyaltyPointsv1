package model;

import java.util.Date;

/**
 * Created by pratap on 5/15/17.
 */
public class Transaction {
    private static final long serialVersionUID = -7788619177798333712L;
    private String ExternalTransactionId;
    private String UserId;
    private String AccountId;
    private String TransactionType;
    private Date TransactionDate;
    private double Value;
    private String PriorityFlag;

    public Transaction calculateLoyaltyPoint() {
        int point = this.getPriorityFlag().equalsIgnoreCase("Y") ? 1000 : this.getPriorityFlag().equalsIgnoreCase("N") && (this.getTransactionType().equalsIgnoreCase("Credit_Card") || this.getTransactionType().equalsIgnoreCase("Debit_Card")) ? 500
                : this.getPriorityFlag().equalsIgnoreCase("N") && (this.getTransactionType().equalsIgnoreCase("Credit") || this.getTransactionType().equalsIgnoreCase("DD")) ? 200 : 0;
        this.setLoyaltyPoint(point);
        return this;
    }

    public int getLoyaltyPoint() {
        return LoyaltyPoint;
    }

    public void setLoyaltyPoint(int loyaltyPoint) {
        LoyaltyPoint = loyaltyPoint;
    }

    private int LoyaltyPoint;

    public String getExternalTransactionId() {
        return ExternalTransactionId;
    }

    public void setExternalTransactionId(String externalTransactionId) {
        ExternalTransactionId = externalTransactionId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public Date getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        TransactionDate = transactionDate;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public String getPriorityFlag() {
        return PriorityFlag;
    }

    public void setPriorityFlag(String priorityFlag) {
        PriorityFlag = priorityFlag;
    }

    public Transaction(String externalTransactionId, String user_Id, String accountId, String transactionType, Date transactionDate, double value, String priorityFlag) {
        ExternalTransactionId = externalTransactionId;
        UserId = user_Id;
        AccountId = accountId;
        TransactionType = transactionType;
        TransactionDate = transactionDate;
        Value = value;
        PriorityFlag = priorityFlag;
    }

    public Transaction() {

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getUserId()).append('|').append(this.getAccountId()).append('|').append(this.getTransactionType()).append('|').append(this.getTransactionDate()).append('|').append(this.getPriorityFlag()).append('|').append(this.getLoyaltyPoint());
        return stringBuilder.toString();
    }
}
