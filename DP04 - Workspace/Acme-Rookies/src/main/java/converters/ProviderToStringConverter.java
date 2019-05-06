
package converters;

import org.springframework.core.convert.converter.Converter;

import domain.Provider;

public class ProviderToStringConverter implements Converter<Provider, String> {

	@Override
	public String convert(final Provider h) {
		String result;

		if (h == null)
			result = null;
		else
			result = String.valueOf(h.getId());
		return result;
	}

}
