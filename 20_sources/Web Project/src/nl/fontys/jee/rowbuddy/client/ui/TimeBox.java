package nl.fontys.jee.rowbuddy.client.ui;

/*
 * Copyright 2007 Future Earth, info@future-earth.eu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import java.util.Date;

import nl.fontys.jee.rowbuddy.client.util.Calendar;
import nl.fontys.jee.rowbuddy.client.util.GregorianCalendar;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.TextBox;

public class TimeBox extends TextBox {

        public final static String DEFAULT_TIME_FORMAT = "HH:mm";

        private String format = DEFAULT_TIME_FORMAT;

        private DateTimeFormat date = null;

        public TimeBox() {
                this(DEFAULT_TIME_FORMAT);
        }

        public TimeBox(String format) {
        super.sinkEvents(Event.ONKEYDOWN);
        setFormat(format);
    }

        public void onBrowserEvent(Event event) {
                int eventType = DOM.eventGetType(event);
                int keyCode = DOM.eventGetKeyCode(event);

                switch (eventType) {
                case Event.ONKEYDOWN: {

                        if (keyCode == 37 || keyCode == 39 || keyCode == 18 || keyCode == 9) {
                                super.onBrowserEvent(event);
                                return;
                        } else {
                                if (isTimeKey(keyCode)) {
                                        final int cursor = super.getCursorPos();
                                        if (cursor >= format.length()) {

                                        } else {
                                                final String temp = super.getText();
                                                final char rep = format.charAt(cursor);
                                                if (rep == ':') {
                                                        super.setCursorPos(cursor + 1);
                                                } else {
                                                        char next = (char)-1;
                                                        if(format.length() > cursor + 1){
                                                                next = format.charAt(cursor + 1);
                                                        }
                                                        char newChar = ((char) convert(keyCode));
                                                        boolean allow = false;
                                                        if (rep == 'H' && next == 'H') {
                                                                if (newChar == '0' || newChar == '1' || newChar == '2') {
                                                                        allow = true;
                                                                }
                                                        } else {
                                                                if (rep == 'H') {
                                                                        char prev = temp.charAt(cursor - 1);
                                                                        if (prev == '2') {
                                                                                if (newChar == '0' || newChar == '1' || newChar == '2' || newChar == '3') {
                                                                                        allow = true;
                                                                                }
                                                                        } else {
                                                                                allow = true;
                                                                        }

                                                                }
                                                        }

                            if (rep == 'h' && next == 'h') {
                                if (newChar == '0' || newChar == '1') {
                                    allow = true;
                                }
                            } else {
                                if (rep == 'h') {
                                    char prev = temp.charAt(cursor - 1);
                                    if (prev == '1') {
                                        if (newChar == '0' || newChar == '1' || newChar == '2') {
                                            allow = true;
                                        }
                                    } else {
                                        allow = true;
                                    }

                                }
                            }
                                                        
                                                        if (rep == 'm' && next == 'm') {
                                                                if (newChar == '0' || newChar == '1' || newChar == '2' || newChar == '3' || newChar == '4' || newChar == '5' || newChar == '6') {
                                                                        allow = true;
                                                                }
                                                        } else {
                                                                if (rep == 'm') {
                                                                        final char prev = temp.charAt(cursor - 1);
                                                                        if (prev == '0' || prev == '1' || prev == '2' || prev == '3' || prev == '4' || prev == '5') {
                                                                                allow = true;
                                                                        } else {
                                                                                if (newChar == '0') {
                                                                                        allow = true;
                                                                                }
                                                                        }
                                                                }
                                                        }

                            if (rep == 'a' && next == 'a') {
                                if (newChar == 'A' || newChar == 'a') {
                                    newChar = 'A';
                                    allow = true;
                                } else if (newChar == 'P' || newChar == 'p') {
                                    newChar = 'P';
                                    allow = true;
                                }

                            } else {
                                if (rep == 'a') {
                                    if (newChar == 'M' || newChar == 'm') {
                                        newChar = 'M';
                                        allow = true;
                                    }
                                }
                            }

                            if (allow) {
                                                                final String newString = temp.substring(0, cursor) + newChar + temp.substring(cursor + 1, format.length());
                                                                super.setText(newString);
                                                                if (next == ':') {
                                                                        super.setCursorPos(cursor + 2);
                                                                } else {
                                                                        super.setCursorPos(cursor + 1);
                                                                }

                                                        }

                                                }
                                        }
                                        DOM.eventPreventDefault(event);
                                        return;

                                } else {
                                        DOM.eventPreventDefault(event);
                                        return;
                                }

                        }

                }
                default:
                        super.onBrowserEvent(event);
                }
        }

        private int convert(int keyCode) {
        // convert 0 - 9 keys on number pad down into normal 0 - 9 range  
        if (keyCode >= 96 && keyCode <= 105) {
            return keyCode - 48;
        }
        return keyCode;
        }

        private boolean isTimeKey(int keyCode) {
            // 0 - 9 keys above qwerty
                if (keyCode >= 48 && keyCode <= 57) {
                        return true;
                }
                // 0 - 9 keys on number pad 
        if (keyCode >= 96 && keyCode <= 105) {
            return true;
        }
        // A, M, P keys
        if (keyCode == 65 || keyCode == 77 || keyCode == 80) {
            return true;
        }
                return false;
        }

        public void setDate(Date newValue) {
                if (newValue != null) {
                        super.setText(date.format(newValue));
                } else {
                        super.setText(format);
                }
        }

        public Date getValue(Date date) {
                if (getText() != null && getText().length() > 0) {
                        try {
                            final Date time = DateTimeFormat.getFormat(format).parse(getText());
                final GregorianCalendar thelper = new GregorianCalendar();
                thelper.setTime(time);
                final GregorianCalendar helper = new GregorianCalendar();
                helper.setTime(date);
                helper.set(Calendar.HOUR_OF_DAY, thelper.get(Calendar.HOUR_OF_DAY));
                helper.set(Calendar.MINUTE, thelper.get(Calendar.MINUTE));
                helper.set(Calendar.SECOND, 0);
                helper.set(Calendar.MILLISECOND, 0);
                            return helper.getTime();
                        } catch (Exception ex) {
                                return null;
                        }
                }
                return null;
        }

        public String getFormat() {
                return format;
        }

        public void setFormat(String format) {
                this.format = format;
                date = DateTimeFormat.getFormat(format);
                super.setText(format);
                super.setMaxLength(format.length());
                super.setVisibleLength(format.length());

        }
}
