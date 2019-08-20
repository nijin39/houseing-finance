package com.tandem6.housingfinance.institute.domain;

import com.tandem6.housingfinance.common.domain.TimeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class Institute extends TimeEntity implements Serializable{

    @Id
    @GenericGenerator(name = "client_id", strategy = "com.tandem6.housingfinance.common.util.ClientIdGenerator")
    @GeneratedValue(generator = "client_id")
    @Column(name="institute_code")
    private String instituteCode;

    @Column(name="institute_name")
    private String instituteName;

    public Institute(String instituteName) {
        this.instituteName = instituteName;
    }
}
