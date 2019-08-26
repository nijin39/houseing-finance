package com.tandem6.housingfinance.creditguaranteesummary.application;

public class CreditGuaranteeSummaryExcpetion extends Exception {
    private static final long serialVersionUID = 7718828512143293558L;

    private final Long code;
    public CreditGuaranteeSummaryExcpetion(Long code) {
        super();
        this.code = code;
    }
    public CreditGuaranteeSummaryExcpetion(String message, Throwable cause, Long code) {
        super(message, cause);
        this.code = code;
    }
    public CreditGuaranteeSummaryExcpetion(String message, Long code) {
        super(message);
        this.code = code;
    }
    public CreditGuaranteeSummaryExcpetion(Throwable cause, Long code) {
        super(cause);
        this.code = code;
    }
    public Long getCode() {
        return this.code;
    }
}