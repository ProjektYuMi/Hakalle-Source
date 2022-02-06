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
package xyz.br.velosh.projektyumi.hakalle.commands.owner;

import com.annimon.tgbotsmodule.api.methods.send.SendMessageMethod;
import com.annimon.tgbotsmodule.api.methods.updatingmessages.EditMessageTextMethod;
import com.annimon.tgbotsmodule.commands.CommandBundle;
import com.annimon.tgbotsmodule.commands.CommandRegistry;
import com.annimon.tgbotsmodule.commands.SimpleCommand;
import com.annimon.tgbotsmodule.commands.authority.For;
import com.annimon.tgbotsmodule.commands.context.MessageContext;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static xyz.br.velosh.projektyumi.hakalle.bot.Hakalle.botConfig;

/**
 * @author Hakalle <xdhakalle@gmail.com>
 */
public class Shell implements CommandBundle<For> {
    @Override
    public void register(@NotNull CommandRegistry<For> registry) {
        registry.register(new SimpleCommand("/shell", this::shell));
    }

    private void shell(MessageContext messageContext) {
        // User which who asked to accept user's order.
        final String runner = String.valueOf(messageContext.user().getId());

        // Check if the denyOrderUser is the creator.
        if (!runner.equals(botConfig.getCreatorId().toString())) {
            messageContext.replyToMessage("You're not authorized to do this action.")
                    .callAsync(messageContext.sender);
            return;
        }

        // Here we go.
        StringBuilder stringBuilder = new StringBuilder();
        String command = messageContext.message().getText().substring(7);
        stringBuilder.append("<code>").append(runBash("whoami")).append("</code>").append(" (<code>").append(runBash("uname -n")).append("</code>)").append(" ~ ").append(command).append("\n");
        ProcessBuilder processBuilder;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        processBuilder = new ProcessBuilder("/bin/bash", "-c", command);

        // Initialize SendMessageTextMethod.
        SendMessageMethod sendMessageMethod = new SendMessageMethod();
        sendMessageMethod.setChatId(messageContext.message().getChatId());
        sendMessageMethod.enableHtml(true);
        sendMessageMethod.disableWebPagePreview();
        int messageID = sendMessageMethod.setText(stringBuilder.toString())
                .call(messageContext.sender).getMessageId();

        // Initialize EditMessageTextMethod.
        EditMessageTextMethod editMessageTextMethod = new EditMessageTextMethod();
        editMessageTextMethod.setChatId(messageContext.message().getChatId());
        editMessageTextMethod.setMessageId(messageID);
        editMessageTextMethod.enableHtml(true);
        editMessageTextMethod.disableWebPagePreview();

        // Start the real process.
        try {
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            inputStream = process.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            // Dummy string for lines.
            String line;

            // Edit the message.
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append("<code>").append(line).append("</code>").append("\n");
                if (!(line.length() > 4096)) {
                    editMessageTextMethod.setText(stringBuilder.toString())
                            .callAsync(messageContext.sender);
                }
            }

            process.waitFor();
            SendMessageMethod sendMessageMethodPost = new SendMessageMethod();
            sendMessageMethodPost.setReplyToMessageId(messageID);
            sendMessageMethodPost.setText("Return code: <code>" + process.exitValue() +"</code>.");
            sendMessageMethodPost.enableHtml(true);
            sendMessageMethodPost.setChatId(messageContext.message().getChatId())
                    .callAsync(messageContext.sender);
        } catch (Exception ignored) {} finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {}
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException ignored) {}
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ignored) {}
            }
        }
    }

    public static String runBash(String command) {
        StringBuilder baseCommand = new StringBuilder();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            /*
             * Process base
             */
            ProcessBuilder pb;
            pb = new ProcessBuilder("/bin/bash", "-c", command);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            /*
             * Stream base
             */
            inputStream = process.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                baseCommand.append(line);
            }
            return String.valueOf(baseCommand);
        } catch (Exception ignored) {} finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {}
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException ignored) {}
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ignored) {}
            }
        }
        return null;
    }
}