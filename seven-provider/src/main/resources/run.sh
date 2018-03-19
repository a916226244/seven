#!/usr/bin/env bash

exec java  -Djava.ext.dirs=lib -classpath . com.seven.wx.AppStarter >> /root/seven/webroot/logs/seven.out.log 2>&1