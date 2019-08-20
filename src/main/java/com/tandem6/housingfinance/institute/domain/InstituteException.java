package com.tandem6.housingfinance.institute.domain;

public class InstituteException extends Exception {
    private static final long serialVersionUID = 7718828512143293558L;

    private final Long code;
    public InstituteException(Long code) {
        super();
        this.code = code;
    }
    public InstituteException(String message, Throwable cause, Long code) {
        super(message, cause);
        this.code = code;
    }
    public InstituteException(String message, Long code) {
        super(message);
        this.code = code;
    }
    public InstituteException(Throwable cause, Long code) {
        super(cause);
        this.code = code;
    }
    public Long getCode() {
        return this.code;
    }
}