package springangularjwt.auth.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseServiceModel {

    private String id;
}
