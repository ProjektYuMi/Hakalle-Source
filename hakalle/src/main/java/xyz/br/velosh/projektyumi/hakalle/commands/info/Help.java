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
package xyz.br.velosh.projektyumi.hakalle.commands.info;

import com.annimon.tgbotsmodule.api.methods.answerqueries.AnswerCallbackQueryMethod;
import com.annimon.tgbotsmodule.api.methods.updatingmessages.EditMessageTextMethod;
import com.annimon.tgbotsmodule.commands.CommandBundle;
import com.annimon.tgbotsmodule.commands.CommandRegistry;
import com.annimon.tgbotsmodule.commands.SimpleCallbackQueryCommand;
import com.annimon.tgbotsmodule.commands.SimpleCommand;
import com.annimon.tgbotsmodule.commands.authority.For;
import com.annimon.tgbotsmodule.commands.context.MessageContext;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static xyz.br.velosh.projektyumi.hakalle.bot.Hakalle.botConfig;

/**
 * @author Hakalle <xdhakalle@gmail.com>
 */
@SuppressWarnings("SpellCheckingInspection")
public class Help implements CommandBundle<For>  {
    @Override
    public void register(@NotNull CommandRegistry<For> registry) {
        registry.register(new SimpleCallbackQueryCommand("help", callbackQueryContext -> {
            String switchArg = callbackQueryContext.argumentsAsString();
            switch(switchArg) {
                case "gsi" -> {
                    // Set up our Keyboard Markup.
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                    // Set up our Keyboard Button list for listing.
                    List<List<InlineKeyboardButton>> buttonsList = new ArrayList<>();

                    // Set up our Keyboard Button list.
                    List<InlineKeyboardButton> firstButtonListRow = new ArrayList<>();

                    // Create GSI help button.
                    InlineKeyboardButton backButton = new InlineKeyboardButton();
                    backButton.setText("Back");
                    backButton.setCallbackData("back");

                    // Add both buttons into Keyboard Button List listing.
                    firstButtonListRow.add(backButton);

                    // Add the only list of rows inline buttons into markup.
                    buttonsList.add(firstButtonListRow);

                    // Define our only list of rows inline buttons to the keyboard markup.
                    inlineKeyboardMarkup.setKeyboard(buttonsList);

                    // Edit it.
                    EditMessageTextMethod editMessageTextMethod = new EditMessageTextMethod();
                    editMessageTextMethod.setMessageId(callbackQueryContext.message().getMessageId());
                    editMessageTextMethod.setChatId(callbackQueryContext.message().getChatId());
                    editMessageTextMethod.setReplyMarkup(inlineKeyboardMarkup);
                    editMessageTextMethod.enableHtml(true);
                    editMessageTextMethod.setText("""
                            <b>— Help</b>
                            This command is only for the bot developer himself. Unfortunately there is no way to authorize other users for now.
                            Alias: /url2gsi, /url2sgsi and /gsi.
                            
                            <b>— User argument</b>
                            > <code>queue</code> - It'll show all the GSIs in queue, if have. (That argument has been blocked due some bugs)
                            
                            <b>— Admin (or builder) arguments</b>
                            > <code>-a</code> - Will build A-only GSI only. That override <code>-b</code> argument.
                            > <code>-b</code> - Will build AB GSI only. That override <code>-a</code> argument.
                            > <code>-c</code> - Clean after extract.
                            > <code>-m</code> - Previously <code>-d</code>, this argument can only be used on firmware that contains partitions that are considered dynamic (if they are not inside the system image), such as: <code>system_ext</code>, <code>product</code>, <code>reserve</code> and other partitions.
                            
                            <b>— Example</b>
                            > <code>/gsi https://dl.google.com/dl/android/aosp/sargo-sq1a.211205.008-factory-2358b75b.zip Pixel -m -b -c</code> - As the Google Pixel 3a (<code>sargo</code>) is a dynamic device, after Google retrofits its partitions, it is necessary to use the <code>-m</code> argument as the <code>system_ext</code> and <code>product</code> partitions need to be within the system partition/image for GSI to work and boot.
                            """).callAsync(callbackQueryContext.sender);
                }
                case "ordering" -> {
                    // Set up our Keyboard Markup.
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                    // Set up our Keyboard Button list for listing.
                    List<List<InlineKeyboardButton>> buttonsList = new ArrayList<>();

                    // Set up our Keyboard Button list.
                    List<InlineKeyboardButton> firstButtonListRow = new ArrayList<>();

                    // Create GSI help button.
                    InlineKeyboardButton backButton = new InlineKeyboardButton();
                    backButton.setText("Back");
                    backButton.setCallbackData("back");

                    // Add both buttons into Keyboard Button List listing.
                    firstButtonListRow.add(backButton);

                    // Add the only list of rows inline buttons into markup.
                    buttonsList.add(firstButtonListRow);

                    // Define our only list of rows inline buttons to the keyboard markup.
                    inlineKeyboardMarkup.setKeyboard(buttonsList);

                    // Edit it.
                    EditMessageTextMethod editMessageTextMethod = new EditMessageTextMethod();
                    editMessageTextMethod.setMessageId(callbackQueryContext.message().getMessageId());
                    editMessageTextMethod.setChatId(callbackQueryContext.message().getChatId());
                    editMessageTextMethod.setReplyMarkup(inlineKeyboardMarkup);
                    editMessageTextMethod.enableHtml(true);
                    editMessageTextMethod.setText("""
                            <b>— Help</b>
                            If you want to learn how to order GSI/SGSI, you are at the right place! It is not difficult to order, just take a look.
                            First of all, you need to have the firmware (or ROM) link in a way that is directly downloadable (or accessible to).
                            These commands will only work in a certain group.
                            Alias: /request & /order.
                            
                            <b>— Examples</b>
                            > <code>/order https://dl.google.com/dl/android/aosp/sargo-sq1a.211205.008-factory-2358b75b.zip</code> — No additional information.
                            > <code>/order https://dl.google.com/dl/android/aosp/sargo-sq1a.211205.008-factory-2358b75b.zip Hello, can you make Pixel GSI from Google Pixel 3a? Thanks.</code> — With additional information if you need it.
                            
                            <b>— Additional infomartion</b>
                            After submitting, you'll receive a message saying that the order has been successfully sent. You must wait a while for your order to be approved, it can take a while.
                            """).callAsync(callbackQueryContext.sender);
                }
                case "calc" -> {
                    // Set up our Keyboard Markup.
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                    // Set up our Keyboard Button list for listing.
                    List<List<InlineKeyboardButton>> buttonsList = new ArrayList<>();

                    // Set up our Keyboard Button list.
                    List<InlineKeyboardButton> firstButtonListRow = new ArrayList<>();

                    // Create GSI help button.
                    InlineKeyboardButton backButton = new InlineKeyboardButton();
                    backButton.setText("Back");
                    backButton.setCallbackData("back");

                    // Add both buttons into Keyboard Button List listing.
                    firstButtonListRow.add(backButton);

                    // Add the only list of rows inline buttons into markup.
                    buttonsList.add(firstButtonListRow);

                    // Define our only list of rows inline buttons to the keyboard markup.
                    inlineKeyboardMarkup.setKeyboard(buttonsList);

                    // Edit it.
                    EditMessageTextMethod editMessageTextMethod = new EditMessageTextMethod();
                    editMessageTextMethod.setMessageId(callbackQueryContext.message().getMessageId());
                    editMessageTextMethod.setChatId(callbackQueryContext.message().getChatId());
                    editMessageTextMethod.setReplyMarkup(inlineKeyboardMarkup);
                    editMessageTextMethod.enableHtml(true);
                    editMessageTextMethod.setText("""
                            <b>— Help</b>
                            Just a silly command to calculate something.
                            
                            <b>— Examples</b>
                            > <code>/calc 1 +1</code>
                            """).callAsync(callbackQueryContext.sender);
                }
                case "shell" -> {
                    // Set up our Keyboard Markup.
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                    // Set up our Keyboard Button list for listing.
                    List<List<InlineKeyboardButton>> buttonsList = new ArrayList<>();

                    // Set up our Keyboard Button list.
                    List<InlineKeyboardButton> firstButtonListRow = new ArrayList<>();

                    // Create GSI help button.
                    InlineKeyboardButton backButton = new InlineKeyboardButton();
                    backButton.setText("Back");
                    backButton.setCallbackData("back");

                    // Add both buttons into Keyboard Button List listing.
                    firstButtonListRow.add(backButton);

                    // Add the only list of rows inline buttons into markup.
                    buttonsList.add(firstButtonListRow);

                    // Define our only list of rows inline buttons to the keyboard markup.
                    inlineKeyboardMarkup.setKeyboard(buttonsList);

                    // Edit it.
                    EditMessageTextMethod editMessageTextMethod = new EditMessageTextMethod();
                    editMessageTextMethod.setMessageId(callbackQueryContext.message().getMessageId());
                    editMessageTextMethod.setChatId(callbackQueryContext.message().getChatId());
                    editMessageTextMethod.setReplyMarkup(inlineKeyboardMarkup);
                    editMessageTextMethod.enableHtml(true);
                    editMessageTextMethod.setText("""
                            <b>— Help</b>
                            Command originally developed to run Unix commands and get the output and send/update in a message.
                            Only the developer can run this command.
                            
                            <b>— Examples</b>
                            > <code>/shell uname -a</code>
                            """).callAsync(callbackQueryContext.sender);
                }
            }

            // Answer the queryId.
            AnswerCallbackQueryMethod answerCallbackQueryMethod = new AnswerCallbackQueryMethod();
            answerCallbackQueryMethod
                    .setCallbackQueryId(callbackQueryContext.queryId())
                    .callAsync(callbackQueryContext.sender);
        }));
        registry.register(new SimpleCallbackQueryCommand("back", callbackQueryContext -> {
            // Set up our Keyboard Markup.
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            // Set up our Keyboard Button list for listing.
            List<List<InlineKeyboardButton>> buttonsList = new ArrayList<>();

            // Set up our Keyboard Button list.
            List<InlineKeyboardButton> firstButtonListRow = new ArrayList<>();
            List<InlineKeyboardButton> secondButtonListRow = new ArrayList<>();

            // Create GSI help button.
            InlineKeyboardButton gsiButton = new InlineKeyboardButton();
            gsiButton.setText("GSI");
            gsiButton.setCallbackData("help:gsi");

            // Create Ordering help button.
            InlineKeyboardButton orderButton = new InlineKeyboardButton();
            orderButton.setText("Orders");
            orderButton.setCallbackData("help:ordering");

            // Create Ordering help button.
            InlineKeyboardButton calcButton = new InlineKeyboardButton();
            calcButton.setText("Calculator");
            calcButton.setCallbackData("help:calc");

            // Create unix help button.
            InlineKeyboardButton shButton = new InlineKeyboardButton();
            shButton.setText("Shell");
            shButton.setCallbackData("help:shell");

            // Add both buttons into Keyboard Button List listing.
            firstButtonListRow.add(gsiButton);
            firstButtonListRow.add(orderButton);
            secondButtonListRow.add(calcButton);
            secondButtonListRow.add(shButton);

            // Add the only list of rows inline buttons into markup.
            buttonsList.add(firstButtonListRow);
            buttonsList.add(secondButtonListRow);

            // Define our only list of rows inline buttons to the keyboard markup.
            inlineKeyboardMarkup.setKeyboard(buttonsList);

            // Edit it.
            EditMessageTextMethod editMessageTextMethod = new EditMessageTextMethod();
            editMessageTextMethod.setMessageId(callbackQueryContext.message().getMessageId());
            editMessageTextMethod.setChatId(callbackQueryContext.message().getChatId());
            editMessageTextMethod.setReplyMarkup(inlineKeyboardMarkup);
            editMessageTextMethod.enableHtml(true);
            editMessageTextMethod.setText(
                            """
                                    <b>— Help</b>
                                    Hello, first let me introduce myself to you. My name is <b>Hakalle</b>. I'm a bot programmed specifically for automation of complex GSI/SGSI building stages. My only function is to fulfill requests, and do GSI/SGSI builds. I still under constant development, any bugs or problems you should report.
                                    
                                    <b>— Main commands</b>
                                    > /url2gsi (or /url2sgsi & /gsi): Build GSI based on two stages.
                                    > /order (or /request): Order a GSI.
                                    
                                    In case you don't know: I was developed by <a href="https://t.me/Velosh">Velosh</a>.
                                    """
                    ).enableHtml(true)
                    .setReplyMarkup(inlineKeyboardMarkup)
                    .callAsync(callbackQueryContext.sender);

            // Answer the queryId.
            AnswerCallbackQueryMethod answerCallbackQueryMethod = new AnswerCallbackQueryMethod();
            answerCallbackQueryMethod
                    .setCallbackQueryId(callbackQueryContext.queryId())
                    .callAsync(callbackQueryContext.sender);
        }));
        registry.register(new SimpleCommand("/help", this::help));
        registry.register(new SimpleCommand("/start", this::help));
    }

    private void help(MessageContext messageContext) {
        // If the chat's is the same as user's id.
        if (messageContext.user().getId().equals(messageContext.chatId())) {
            // Set up our Keyboard Markup.
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            // Set up our Keyboard Button list for listing.
            List<List<InlineKeyboardButton>> buttonsList = new ArrayList<>();

            // Set up our Keyboard Button list.
            List<InlineKeyboardButton> firstButtonListRow = new ArrayList<>();
            List<InlineKeyboardButton> secondButtonListRow = new ArrayList<>();

            // Create GSI help button.
            InlineKeyboardButton gsiButton = new InlineKeyboardButton();
            gsiButton.setText("GSI");
            gsiButton.setCallbackData("help:gsi");

            // Create Ordering help button.
            InlineKeyboardButton orderButton = new InlineKeyboardButton();
            orderButton.setText("Orders");
            orderButton.setCallbackData("help:ordering");

            // Create Ordering help button.
            InlineKeyboardButton calcButton = new InlineKeyboardButton();
            calcButton.setText("Calculator");
            calcButton.setCallbackData("help:calc");

            // Create unix help button.
            InlineKeyboardButton shButton = new InlineKeyboardButton();
            shButton.setText("Shell");
            shButton.setCallbackData("help:shell");

            // Add both buttons into Keyboard Button List listing.
            firstButtonListRow.add(gsiButton);
            firstButtonListRow.add(orderButton);
            secondButtonListRow.add(calcButton);
            secondButtonListRow.add(shButton);

            // Add the only list of rows inline buttons into markup.
            buttonsList.add(firstButtonListRow);
            buttonsList.add(secondButtonListRow);

            // Define our only list of rows inline buttons to the keyboard markup.
            inlineKeyboardMarkup.setKeyboard(buttonsList);

            // Now send the message.
            messageContext.reply(
                    """
                            <b>— Help</b>
                            Hello, first let me introduce myself to you. My name is <b>Hakalle</b>. I'm a bot programmed specifically for automation of complex GSI/SGSI building stages. My only function is to fulfill requests, and do GSI/SGSI builds. I still under constant development, any bugs or problems you should report.
                            
                            <b>— Main commands</b>
                            > /url2gsi (or /url2sgsi & /gsi): Build GSI based on two stages.
                            > /order (or /request): Order a GSI.
                            
                            In case you don't know: I was developed by <a href="https://t.me/Velosh">Velosh</a>.
                            """
            ).enableHtml(true)
                    .setReplyMarkup(inlineKeyboardMarkup)
                    .callAsync(messageContext.sender);
        } else {
            // Set up our Keyboard Markup.
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            // Set up our Keyboard Button list for listing.
            List<List<InlineKeyboardButton>> buttonsList = new ArrayList<>();

            // Set up our Keyboard Button list.
            List<InlineKeyboardButton> buttonList = new ArrayList<>();

            // Create accept button.
            InlineKeyboardButton helpButton = new InlineKeyboardButton();
            helpButton.setText("❔ Help");
            helpButton.setUrl("http://t.me/" + botConfig.getUsername());

            // Add both buttons into Keyboard Button List listing.
            buttonList.add(helpButton);

            // Add the only list of rows inline buttons into markup.
            buttonsList.add(buttonList);

            // Define our only list of rows inline buttons to the keyboard markup.
            inlineKeyboardMarkup.setKeyboard(buttonsList);

            // Now send the message.
            messageContext.replyToMessage("Hello! Go to my PM and type <code>/help</code> to find out about my commands.")
                    .setReplyMarkup(inlineKeyboardMarkup)
                    .enableHtml(true)
                    .callAsync(messageContext.sender);
        }
    }
}