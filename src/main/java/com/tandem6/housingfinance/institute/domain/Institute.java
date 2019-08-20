package com.tandem6.housingfinance.institute.domain;

import com.tandem6.housingfinance.common.domain.TimeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class Institute extends TimeEntity implements Serializable{

}
