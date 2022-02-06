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
package xyz.br.velosh.projektyumi.hakalle.bot;

import com.annimon.tgbotsmodule.BotHandler;
import com.annimon.tgbotsmodule.commands.CommandRegistry;
import com.annimon.tgbotsmodule.commands.authority.For;
import com.annimon.tgbotsmodule.commands.authority.SimpleAuthority;

import org.jetbrains.annotations.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import xyz.br.velosh.projektyumi.hakalle.bot.config.BotConfig;
import xyz.br.velosh.projektyumi.hakalle.commands.gsi.Order;
import xyz.br.velosh.projektyumi.hakalle.commands.gsi.yGSI;
import xyz.br.velosh.projektyumi.hakalle.commands.info.Help;
import xyz.br.velosh.projektyumi.hakalle.commands.misc.Calculator;
import xyz.br.velosh.projektyumi.hakalle.commands.owner.Shell;

/**
 * @author Hakalle <xdhakalle@gmail.com>
 */
public class Hakalle extends BotHandler {

    /**
     * Logger: To send warning, info & errors to terminal.
     */
    private static final Logger logger = LoggerFactory.getLogger(Hakalle.class);

    /**
     * Other variables.
     */
    public static BotConfig botConfig = null;
    private final CommandRegistry<For> commands;

    public Hakalle(BotConfig botConfig) {
        Hakalle.botConfig = botConfig;
        final var authority = new SimpleAuthority(this, botConfig.getCreatorId());
        commands = new CommandRegistry<>(this, authority);

        logger.info("Initializing the commands...");
        commands.registerBundle(new Calculator());
        commands.registerBundle(new yGSI());
        commands.registerBundle(new Order());
        commands.registerBundle(new Help());
        commands.registerBundle(new Shell());
        logger.info("Done!");

        addMethodPreprocessor(SendMessage.class, sendMessage -> {
            sendMessage.setAllowSendingWithoutReply(true);
            sendMessage.disableWebPagePreview();
        });
        addMethodPreprocessor(EditMessageText.class, EditMessageText::disableWebPagePreview);

        logger.info("Hakalle successfully initialized!");
    }

    @Override
    protected BotApiMethod<?> onUpdate(@NotNull Update update) {
        new Thread(() -> commands.handleUpdate(update)).start();
        return null;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}