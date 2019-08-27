package com.tandem6.housingfinance.institute.domain;

import com.tandem6.housingfinance.common.domain.TimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class Institute extends TimeEntity implements Serializable{

    @Id
    @GenericGenerator(name = "client_id", strategy = "com.tandem6.housingfinance.common.util.ClientIdGenerator")
    @GeneratedValue(generator = "client_id")
    @Column(name="institute_code")
    private String instituteCode;

    @NotBlank(message = "기관명을 입력하셔야 합니다.")
    @Column(name="institute_name", unique = true)
    private String instituteName;

    public Institute(String instituteName) {
        this.instituteName = instituteName;
    }
}
