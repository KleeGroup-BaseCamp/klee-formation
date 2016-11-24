package com.kleegroup.formation.ui.util;

import io.vertigo.dynamo.domain.metamodel.DataType;
import io.vertigo.dynamo.domain.metamodel.Formatter;
import io.vertigo.dynamo.domain.metamodel.FormatterException;
import io.vertigo.lang.Assertion;
import io.vertigo.util.StringUtil;

/**
 * Gestion des formattages des heures.
 *
 * @author cmoutte
 */
public final class FormatterHeure implements Formatter {

	/**
	 * Constructeur.
	 */
	public FormatterHeure(final String args) {
		//rien
	}

	/** {@inheritDoc} */
	@Override
	public String valueToString(final Object objValue, final DataType dataType) {
		Assertion.checkArgument(dataType == DataType.Integer, "Formatter ne s'applique qu'aux Integer");
		//-----
		return dateToString((Integer) objValue);
	}

	/** {@inheritDoc} */
	@Override
	public Object stringToValue(final String strValue, final DataType dataType) throws FormatterException {
		Assertion.checkArgument(dataType == DataType.Integer, "Formatter ne s'applique qu'aux Integer");
		//-----
		final String sValue = StringUtil.isEmpty(strValue) ? null : strValue.trim();
		return stringToDate(sValue);
	}

	private Integer stringToDate(final String sValue) {
		if (sValue.length() == 5) {
			final int i = Integer.parseInt(sValue.substring(0, 2)) * 60 + Integer.parseInt(sValue.substring(3, 5));
			return i;
		} else {
			final int i = Integer.parseInt(sValue.substring(0, 1)) * 60 + Integer.parseInt(sValue.substring(2, 4));
			return i;
		}
	}

	private String dateToString(final Integer objValue) {
		String str = new String();
		final float i = (float) objValue / (float) 60;
		/*int tmp = String.valueOf(i).length();
		if (tmp == 1 || tmp == 2 || tmp == 3) {
			tmp = tmp - 1;
		}
		if (tmp > 3 && tmp < 6) {
			tmp = tmp - 2;
		}*/
		//tmp
		str = (int) i + ":" + String.valueOf(i).substring(String.valueOf(i).length() - 1, String.valueOf(i).length()) + "0";
		return str;
	}
}
