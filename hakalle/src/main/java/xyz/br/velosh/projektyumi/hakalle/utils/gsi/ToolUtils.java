/*
    Project YuMi
    Copyright (C) 2022 Hakalle

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package xyz.br.velosh.projektyumi.hakalle.utils.gsi;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @author Hakalle <xdhakalle@gmail.com>
 */
public class ToolUtils {
    /**
     * ErfanGSIs tool path.
     */
    public static final Path mPath = FileSystems.getDefault().getPath("ErfanGSIs").toAbsolutePath();

    /*
     * Check if the yGSI tool path exists.
     */
    public static boolean toolExists() {
        File file = new File(String.valueOf(mPath));
        return file.exists();
    }
}