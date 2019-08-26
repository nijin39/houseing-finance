package com.tandem6.housingfinance.creditguarantee.command.application;

public class CreditGuaranteeServiceExcpetion extends Exception {
    private static final long serialVersionUID = 7718828512143293558L;

    private final Long code;
    public CreditGuaranteeServiceExcpetion(Long code) {
        super();
        this.code = code;
    }
    public CreditGuaranteeServiceExcpetion(String message, Throwable cause, Long code) {
        super(message, cause);
        this.code = code;
    }
    public CreditGuaranteeServiceExcpetion(String message, Long code) {
        super(message);
        this.code = code;
    }
    public CreditGuaranteeServiceExcpetion(Throwable cause, Long code) {
        super(cause);
        this.code = code;
    }
    public Long getCode() {
        return this.code;
    }
}