package com.tandem6.housingfinance.creditguarantee.query.dao;

public class AmountDaoException extends Exception {
    private static final long serialVersionUID = 2842724671007809898L;

    private final Long code;
    public AmountDaoException(Long code) {
        super();
        this.code = code;
    }
    public AmountDaoException(String message, Throwable cause, Long code) {
        super(message, cause);
        this.code = code;
    }
    public AmountDaoException(String message, Long code) {
        super(message);
        this.code = code;
    }
    public AmountDaoException(Throwable cause, Long code) {
        super(cause);
        this.code = code;
    }
    public Long getCode() {
        return this.code;
    }
}