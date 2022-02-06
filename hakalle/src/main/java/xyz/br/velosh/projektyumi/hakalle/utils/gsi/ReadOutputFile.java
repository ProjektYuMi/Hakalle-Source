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

import okio.*;
import xyz.br.velosh.projektyumi.hakalle.utils.gsi.exception.ReadmeException;
import xyz.br.velosh.projektyumi.hakalle.utils.gsi.exception.ReadmeNoSuchFileException;

import java.io.File;
import java.io.IOException;

/**
 * @author Hakalle <xdhakalle@gmail.com>
 */
public class ReadOutputFile {

    /*
     * Variable used to get content of README.md.
     */
    private String mReadmeInfo;

    /*
     * Variable used to set README.md path.
     */
    private String mReadmePath;

    /*
     * Variable used to get device's name.
     */
    public static String mDeviceName;

    /*
     * Variable used to get device's build type.
     */
    public static String mCodename;

    /*
     * Variable used to get device's brand name.
     */
    public static String mBrandName;

    /*
     * Variable used to get device's build type.
     */
    public static String mBuildType;

    public ReadOutputFile() {
        mReadmeInfo = "";
        mReadmePath = null;
        mDeviceName = "";
    }

    /*
     * Method used to set the README.md path.
     */
    public void setReadmePath(String path) throws ReadmeNoSuchFileException, ReadmeException {
        // Create file variable for path string.
        File file = new File(path);

        // Check if the README.md file exists.
        if (!file.exists())
            throw new ReadmeNoSuchFileException("The README.md file does not exist! It is not possible to continue.");

        // Check if the README.md is really a file.
        if (!file.isFile())
            throw new ReadmeException("The defined path was expected to be a file.");

        // If all ok, then we set that path in readmePath variable.
        mReadmePath = path;
    }

    /*
     * Method used to read the README.md file.
     */
    public void read() throws ReadmeException {
        // Check if the readmePath var is null.
        if (mReadmePath == null) throw new ReadmeException("You didn't set the file path! Use setReadmePath() method for that.");

        // Create a StringBuilder variable, we'll use that to append the lines.
        StringBuilder stringBuilder = new StringBuilder();

        // Read the README.md file.
        try (BufferedSource source = Okio.buffer(FileSystem.SYSTEM.source(Path.get(mReadmePath)))) {
            for (String line; (line = source.readUtf8Line()) != null;) {
                if (line.startsWith("#")) continue; // The usage of markdown is because of SourceForge so, skip it.
                if (line.startsWith("- ")) line = line.substring(2); // Drop two chars ("- ").
                if (line.startsWith("Brand")) mBrandName = line.substring(7);
                if (line.startsWith("Codename")) mCodename = line.substring(10);
                if (line.startsWith("Model")) mDeviceName = line.substring(7);
                if (line.startsWith("Build Type")) mBuildType = line.substring(12);
                if (line.startsWith("Raw")) stringBuilder.append(line);
                else
                    stringBuilder.append(line).append("\n");
            }

            // All is ok so, define the readmeInfo var.
            mReadmeInfo = String.valueOf(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * That method returns GSI info output.
     */
    public String getOutput() throws ReadmeException {
        if (mReadmeInfo == null || mReadmeInfo.equals("")) throw new ReadmeException("It seems that the README.md file was not read! You can do this using read() method.");
        return mReadmeInfo;
    }

    /*
     * That method will be used to set correct model's name.
     */
    public void setDeviceName(String newModel) {
        mDeviceName = newModel;
    }

    /*
     * That method returns model's name.
     */
    public String getModelName() throws ReadmeException {
        if (mReadmeInfo == null || mReadmeInfo.equals("")) throw new ReadmeException("It seems that the README.md file was not read! You can do this using read() method.");
        return mDeviceName;
    }

    /*
     * That method returns model's brand (manufacturer) name.
     */
    public String getBrandName() throws ReadmeException {
        if (mReadmeInfo == null || mReadmeInfo.equals("")) throw new ReadmeException("It seems that the README.md file was not read! You can do this using read() method.");
        return mBrandName;
    }

    /*
     * That method returns model's build type.
     */
    public String getBuildType() throws ReadmeException {
        if (mReadmeInfo == null || mReadmeInfo.equals("")) throw new ReadmeException("It seems that the README.md file was not read! You can do this using read() method.");
        return mBuildType;
    }

    /*
     * That method returns model's build type.
     */
    public String getCodename() throws ReadmeException {
        if (mReadmeInfo == null || mReadmeInfo.equals("")) throw new ReadmeException("It seems that the README.md file was not read! You can do this using read() method.");
        return mCodename;
    }

    /*
     * That method will clear mReadInfo and mDeviceName variables.
     */
    public void clear() {
        mReadmeInfo = "";
        mDeviceName = "";
        mBuildType = "";
        mBrandName = "";
    }
}