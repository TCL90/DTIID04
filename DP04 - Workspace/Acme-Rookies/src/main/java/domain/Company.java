
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Company extends Actor {

	private String	companyName;
	private double	score;


	@NotBlank
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}

	@NotNull
	@Range(min = (long) 0.0, max = (long) 1.0)
	public double getScore() {
		return this.score;
	}

	public void setScore(final double score) {
		this.score = score;
	}

}
