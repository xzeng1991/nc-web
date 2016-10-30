package model.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseDomain implements Serializable {
	private static final long serialVersionUID = -7102570522525461L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
