/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2016, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
 * KleeGroup, Centre d'affaire la Boursidiere - BP 159 - 92357 Le Plessis Robinson Cedex - France
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kleegroup.formation.services.mail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import io.vertigo.dynamo.file.FileManager;
import io.vertigo.dynamo.file.model.VFile;
import io.vertigo.dynamo.file.util.FileUtil;
import io.vertigo.dynamo.file.util.TempFile;
import io.vertigo.lang.Assertion;
import io.vertigo.lang.WrappedException;

/**
 * Utilitaire pour construire des cas de tests.
 *
 * @author npiedeloup
 */
public final class UtilMail {
	/**
	 * Constructeur privé pour class utilitaire
	 *
	 */
	private UtilMail() {
		super();
	}

	/**
	 * Crée un VFile relativement d'un class de base.
	 * @param fileName Nom/path du fichier
	 * @param baseClass Class de base pour le chemin relatif
	 * @return VFile
	 */
	public static VFile createVFile(final FileManager fileManager, final String fileName, final Class<?> baseClass) {
		try (final InputStream in = baseClass.getResourceAsStream(fileName)) {
			Assertion.checkNotNull(in, "fichier non trouvé : {0}", fileName);
			final File file = new TempFile("tmp", '.' + FileUtil.getFileExtension(fileName));
			FileUtil.copy(in, file);
			return fileManager.createFile(file);
		} catch (final IOException e) {
			throw WrappedException.wrap(e);
		}
	}

}
