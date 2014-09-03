package app.solutions.model;

import app.solutions.validation.annotations.ExistsInListEntries;

/**
 * Created by niveditha on 5/1/14.
 */
public class FeeType extends ObjectType {

    @ExistsInListEntries(value = "FeePaymentCycle")
    private String paymentCycle;

    // No of days after which parents will be communicated about fee payment reminder
    private int reminderDays;

    @ExistsInListEntries(value ="ParentCommunicationMode")
    private String communicationMode;

    private int feePaymentCycleStartMonth;

    private int feePaymentCycleStartDay;


}
