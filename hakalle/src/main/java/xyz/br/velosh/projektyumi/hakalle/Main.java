/*
    Project YuMi
    Copyright (C) 2021 Enzo Aquino

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
package xyz.br.velosh.projektyumi.hakalle;

import com.annimon.tgbotsmodule.BotHandler;
import com.annimon.tgbotsmodule.BotModule;
import com.annimon.tgbotsmodule.Runner;
import com.annimon.tgbotsmodule.beans.Config;
import com.annimon.tgbotsmodule.services.YamlConfigLoaderService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.br.velosh.projektyumi.hakalle.bot.Hakalle;
import xyz.br.velosh.projektyumi.hakalle.bot.config.BotConfig;
import xyz.br.velosh.projektyumi.hakalle.bot.gsi.StatusGSI;
import xyz.br.velosh.projektyumi.hakalle.utils.gsi.ReadOutputFile;
import xyz.br.velosh.projektyumi.hakalle.utils.gsi.ToolUtils;
import xyz.br.velosh.projektyumi.hakalle.utils.gsi.exception.GSIException;

import java.util.List;

/**
 * @author Hakalle <xdhakalle@gmail.com>
 */
public class Main implements BotModule {
    /**
     * Logger: To send warning, info & errors to terminal.
     */
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * ReadmeOutputFile: To read/get the README.md file.
     */
    public static ReadOutputFile readOutputFile = new ReadOutputFile();

    /**
     * GSICmdObj: Used for GSI info setup.
     * Also queue.
     */
    public static StatusGSI statusGSI;

    /**
     * Here we go.
     */
    public static void main(String[] args) {
        logger.info("Checking that the yGSI tool folder exists...");
        /*
         * First, set the README.md path permanently.
         * But check if yGSI tool folder exists.
         */
        if (ToolUtils.toolExists()) {
            statusGSI = new StatusGSI();
        } else {
            try {
                throw new GSIException("The tool (ErfanGSIs) used to make system images doesn't exist! Please check it out.");
            } catch (GSIException gsiException) {
                logger.error(gsiException.getMessage());
                System.exit(1);
            }
        }

        /*
         * Start the bot.
         */
        logger.warn("Starting the bot...");
        Runner.run("",
                List.of(new Main())
        );
    }

    @Override
    public @NotNull BotHandler botHandler(@NotNull Config config) {
        final var configLoader = new YamlConfigLoaderService();
        final var configFile = configLoader.configFile("hakalle", config.getProfile());
        final var botConfig = configLoader.loadFile(configFile, BotConfig.class);
        return new Hakalle(botConfig);
    }
}