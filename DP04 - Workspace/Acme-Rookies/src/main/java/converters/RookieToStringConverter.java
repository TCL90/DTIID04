
package converters;

import org.springframework.core.convert.converter.Converter;

import domain.Rookie;

public class RookieToStringConverter implements Converter<Rookie, String> {

	@Override
	public String convert(final Rookie h) {
		String result;

		if (h == null)
			result = null;
		else
			result = String.valueOf(h.getId());
		return result;
	}

}
