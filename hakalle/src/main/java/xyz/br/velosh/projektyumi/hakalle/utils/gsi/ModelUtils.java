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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.br.velosh.projektyumi.hakalle.utils.gsi.exception.ReadmeException;

import java.util.Objects;

import static xyz.br.velosh.projektyumi.hakalle.Main.readOutputFile;

/**
 * @author Hakalle <xdhakalle@gmail.com>
 * This class will have only one method: Try, find & fix GSI model's name.
 */
public class ModelUtils {
    /*
     * Private variables which will be used to set, fix the correct model name to ReadOutputFile.setModelName().
     */
    private String mDeviceName;
    private String mBrandName = "";
    private String mBuildType = "";

    /*
     * Here we go.
     */
    public ModelUtils(ReadOutputFile readOutputFile) {
        try {
            mDeviceName = readOutputFile.getModelName();
            mBrandName = readOutputFile.getBrandName();
            mBuildType = readOutputFile.getBuildType();
        } catch (ReadmeException readmeException) {
            /*
             * Logger: To send warning, info & errors to terminal.
             */
            Logger logger = LoggerFactory.getLogger(ModelUtils.class);

            // Show the error!
            logger.error(readmeException.getMessage());

            // Set as Unknown model, since it failed so.
            mDeviceName = "Unknown (?)";
        }
    }

    /*
     * The only method.
     */
    @SuppressWarnings({"SpellCheckingInspection"})
    public void fix() {
        /*
         * Check if the model have special codename
         */
        if (Objects.requireNonNull(mDeviceName).length() < 1)
            mDeviceName = "Generic";
        else if (mDeviceName.toLowerCase().contains("x00qd"))
            mDeviceName = "Asus Zenfone 5";
        else if (mDeviceName.toLowerCase().contains("qssi"))
            mDeviceName = "Qualcomm Single System Image";
        else if (mDeviceName.toLowerCase().contains("miatoll"))
            mDeviceName = "Redmi Note 9S/Redmi Note 9 Pro/Redmi Note 9 Pro Max/POCO M2 Pro";
        else if (mDeviceName.toLowerCase().contains("surya"))
            mDeviceName = "Poco X3";
        else if (mDeviceName.toLowerCase().contains("lavender"))
            mDeviceName = "Redmi Note 7";
        else if (mDeviceName.toLowerCase().contains("ginkgo"))
            mDeviceName = "Redmi Note 8";
        else if (mDeviceName.toLowerCase().contains("raphael"))
            mDeviceName = "Mi 9T Pro";
        else if (mDeviceName.toLowerCase().contains("mainline"))
            mDeviceName = "AOSP/Pixel (Mainline) Device";
        else if (mDeviceName.toLowerCase().contains("sm6250"))
            mDeviceName = "Atoll device";
        else if (mDeviceName.toLowerCase().contains("msi"))
            mDeviceName = "Motorola System Image";
        else if (mDeviceName.toLowerCase().contains("mssi"))
            mDeviceName = "MIUI Single System Image";
        else if (mDeviceName.equals("a30"))
            mDeviceName = "Samsung Galaxy A30";
        else if (mDeviceName.equals("a20"))
            mDeviceName = "Samsung Galaxy A20";
        else if (mDeviceName.equals("a10"))
            mDeviceName = "Samsung Galaxy A10";
        else if (mDeviceName.equals("LE2123"))
            mDeviceName = "OnePlus 9 Pro";
        else if (mDeviceName.equals("LE2110"))
            mDeviceName = "OnePlus 9 (5G)";
        else if (mDeviceName.toLowerCase().contains("apollo"))
            mDeviceName = "Mi 10T/Mi 10T Pro/Redmi K30S";
        else if (mDeviceName.toLowerCase().contains("gauguin"))
            mDeviceName = "Mi 10T Lite/Mi 10i 5G/Redmi Note 9 5G";
        else if (mDeviceName.equals(" "))
            mDeviceName = "Generic";

        if (Objects.requireNonNull(mBrandName).equals("google")
            || Objects.requireNonNull(mBrandName).equals("Android")) {
            if (mDeviceName.equals("AOSP/Pixel (Mainline) Device")) {
                switch (Objects.requireNonNull(mBuildType)) {
                    case "raven-user", "aosp_raven-user", "aosp_raven-userdebug", "aosp_raven-eng"
                            -> mDeviceName = "Google Pixel 6 Pro";
                    case "oriel-user", "aosp_oriel-user", "aosp_oriel-userdebug", "aosp_oriel-eng"
                            -> mDeviceName = "Google Pixel 6";
                    case "barbet-user", "aosp_barbet-user", "aosp_barbet-userdebug", "aosp_barbet-eng"
                            -> mDeviceName = "Google Pixel 5a";
                    case "redfin-user", "aosp_redfin-user", "aosp_redfin-userdebug", "aosp_redfin-eng"
                            -> mDeviceName = "Google Pixel 5";
                    case "bramble-user", "aosp_bramble-user", "aosp_bramble-userdebug", "aosp_bramble-eng"
                            -> mDeviceName = "Google Pixel 4a 5G";
                    case "sunfish-user", "aosp_sunfish-user", "aosp_sunfish-userdebug", "aosp_sunfish-eng"
                            -> mDeviceName = "Google Pixel 4a";
                    case "coral-user", "aosp_coral-user", "aosp_coral-userdebug", "aosp_coral-eng"
                            -> mDeviceName = "Google Pixel 4 XL";
                    case "flame-user", "aosp_flame-user", "aosp_flame-userdebug", "aosp_flame-eng"
                            -> mDeviceName = "Google Pixel 4";
                    case "bonito-user", "aosp_bonito-user", "aosp_bonito-userdebug", "aosp_bonito-eng"
                            -> mDeviceName = "Google Pixel 3a XL";
                    case "sargo-user", "aosp_sargo-user", "aosp_sargo-userdebug", "aosp_sargo-eng"
                            -> mDeviceName = "Google Pixel 3a";
                    case "crosshatch-user", "aosp_crosshatch-user", "aosp_crosshatch-userdebug", "aosp_crosshatch-eng"
                            -> mDeviceName = "Google Pixel 3 XL";
                    case "blueline-user", "aosp_blueline-user", "aosp_blueline-userdebug", "aosp_blueline-eng"
                            -> mDeviceName = "Google Pixel 3";
                    case "taimen-user", "aosp_taimen-user", "aosp_taimen-userdebug", "aosp_taimen-eng"
                            -> mDeviceName = "Google Pixel 2 XL";
                    case "walleye-user", "aosp_walleye-user", "aosp_walleye-userdebug", "aosp_walleye-eng"
                            -> mDeviceName = "Google Pixel 2";
                    case "marlin-user", "aosp_marlin-user", "aosp_marlin-userdebug", "aosp_marlin-eng"
                            -> mDeviceName = "Google Pixel XL";
                    case "sailfish-user", "aosp_sailfish-user", "aosp_sailfish-userdebug", "aosp_sailfish-eng"
                            -> mDeviceName = "Google Pixel";
                    default
                            -> mDeviceName = "Google Pixel (?)";
                }
            }
        } else if (Objects.requireNonNull(mBrandName).equals("Redmi")) {
            if (mDeviceName.equals("Atoll device")) {
                mDeviceName = "Redmi Note 9S/Redmi Note 9 Pro/Redmi Note 9 Pro Max/POCO M2 Pro";
            }
        }

        /*
         * Set the model's name if all ok.
         */
        readOutputFile.setDeviceName(mDeviceName);

        /*
         * First check
         */
        String stringToCheck = mDeviceName.toLowerCase();
        boolean testPass = false;

        char[] characterSearch = {
                'q', 'w', 'e', 'r', 't', 'y', 'u',
                'i', 'o', 'p', 'a', 's', 'd', 'f',
                'g', 'h', 'j', 'k', 'l', 'z', 'x',
                'c', 'v', 'b', 'n', 'm'
        };

        for (int i = 0; i < stringToCheck.length(); i++) {
            char character = stringToCheck.charAt(i);
            for (char search : characterSearch) {
                if (search == character) {
                    testPass = true;
                    break;
                }
            }
        }

        // If test failed, return the mDeviceName as Generic.
        if (!testPass) readOutputFile.setDeviceName("Generic");
    }
}