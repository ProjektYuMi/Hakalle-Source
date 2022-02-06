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
package xyz.br.velosh.projektyumi.hakalle.bot.gsi;

import com.annimon.tgbotsmodule.commands.context.MessageContext;

/**
 * @author VegaBobo <matheus.shimoyama@etec.sp.gov.br>
 * Modified by Velosh <daffetyxd@gmail.com>
 */
@SuppressWarnings("all")
public class GSICmdObj {

    private String mURL;
    private String mGsiName;
    private String mArgs;
    private MessageContext messageContext;
    private String mBuilderId;
    private String mBuilderName;
    private String mNotice;

    public GSICmdObj() {}

    public String getUrl() {
        return mURL;
    }

    public void setUrl(String url) {
        mURL = url;
    }

    public String getGsi() {
        return mGsiName;
    }

    public String getNotice() {
        return mNotice;
    }

    public void setNotice(String notice) {
        mNotice = notice;
    }

    public void setGsi(String gsi) {
        mGsiName = gsi;
    }

    public void setBuilderName(String username) {
        mBuilderName = username;
    }

    public void setBuilderId(String userid) {
        mBuilderId = userid;
    }

    public String getBuilderId() {
        return mBuilderId;
    }

    public String getBuilderName() {
        return mBuilderName;
    }

    public String getArgs() {
        return mArgs;
    }

    public void setParam(String args) {
        mArgs = args;
    }

    public MessageContext getMessageContext() {
        return messageContext;
    }

    public void setUpdate(MessageContext messageContext) {
        this.messageContext = messageContext;
    }

    public void clean() {
        mURL = null;
        mGsiName = null;
        mArgs = null;
        messageContext = null;
        mNotice = null;
    }
}