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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author VegaBobo <vegazstestando@gmail.com>
 * Kinda lazy to make one.
 */
public class FileUtils {
    public void gzipFile(String source, String dest) {
        FileOutputStream fileOutputStream = null;
        GZIPOutputStream gzipOutputStream = null;
        FileInputStream fileInput = null;

        byte[] buffer = new byte[1024];

        try {
            fileOutputStream = new FileOutputStream(dest);
            gzipOutputStream = new GZIPOutputStream(fileOutputStream);
            fileInput = new FileInputStream(source);

            int bytes_read;

            while ((bytes_read = fileInput.read(buffer)) > 0) {
                gzipOutputStream.write(buffer, 0, bytes_read);
            }

            fileInput.close();
        } catch (Exception ignored) {} finally {
            try {
                if (gzipOutputStream != null) {
                    gzipOutputStream.finish();
                    gzipOutputStream.close();
                }
                if (fileInput != null) fileInput.close();
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            } catch (Exception ignored) {}
        }
    }
}