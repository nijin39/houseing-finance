package com.tandem6.housingfinance.creditguarantee.query.application;

public class AmountReportException extends Exception {
    private static final long serialVersionUID = 7718828512143293558L;

    private final Long code;
    public AmountReportException(Long code) {
        super();
        this.code = code;
    }
    public AmountReportException(String message, Throwable cause, Long code) {
        super(message, cause);
        this.code = code;
    }
    public AmountReportException(String message, Long code) {
        super(message);
        this.code = code;
    }
    public AmountReportException(Throwable cause, Long code) {
        super(cause);
        this.code = code;
    }
    public Long getCode() {
        return this.code;
    }
}