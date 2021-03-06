
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import repositories.PositionDataRepository;
import domain.PositionData;

public class StringToPositionDataConverter implements Converter<String, PositionData> {

	@Autowired
	PositionDataRepository	mr;


	@Override
	public PositionData convert(final String text) {
		PositionData result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.mr.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;

	}
}
