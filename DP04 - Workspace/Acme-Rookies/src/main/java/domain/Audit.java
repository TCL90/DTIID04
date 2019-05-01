
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Audit extends DomainEntity {

	private String	text;
	private Integer	score;
	private Date	moment;
	private boolean	finalMode;


	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}
	@NotNull
	@Range(min = 0, max = 10)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(final Integer score) {
		this.score = score;
	}

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public boolean isFinalMode() {
		return this.finalMode;
	}

	public void setFinalMode(final boolean finalMode) {
		this.finalMode = finalMode;
	}

}
