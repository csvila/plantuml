/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2024, Arnaud Roques
 *
 * Project Info:  https://plantuml.com
 *
 * If you like this project or if you find it useful, you can support us at:
 *
 * https://plantuml.com/patreon (only 1$ per month!)
 * https://plantuml.com/paypal
 *
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 */
package net.sourceforge.plantuml.tim.stdlib;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.json.JsonArray;
import net.sourceforge.plantuml.theme.ThemeUtils;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public class GetAllTheme extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%get_all_theme", 0);
	}

	@Override
	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 0;
	}

	@Override
	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		final JsonArray result = new JsonArray();
		try {
			for (String theme : ThemeUtils.getAllThemeNames()) {
				result.add(theme);
			}
			return TValue.fromJson(result);
		} catch (IOException e) {
			Logme.error(e);
			return TValue.fromJson(result);
		}
	}
}
