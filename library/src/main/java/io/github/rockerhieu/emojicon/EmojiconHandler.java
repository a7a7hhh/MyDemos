/*
 * Copyright 2014 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.rockerhieu.emojicon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Hieu Rocker (rockerhieu@gmail.com)
 */
public final class EmojiconHandler {
    private EmojiconHandler() {
    }

    @SuppressLint("NewApi")
    private static final ArrayMap<String, Integer> sEmojiImportantMap = new ArrayMap<>(5);

    static {
        sEmojiImportantMap.put("[/吐舌头]", R.drawable.tushetou);
        sEmojiImportantMap.put("[/微笑]", R.drawable.weixiao);
        sEmojiImportantMap.put("[/满意]", R.drawable.emoji_00a9);
        sEmojiImportantMap.put("[/id]", R.drawable.id);
        sEmojiImportantMap.put("[/cool]", R.drawable.cool);
        sEmojiImportantMap.put("[/new]", R.drawable.new1);
        sEmojiImportantMap.put("[/o]", R.drawable.o);
    }



    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param emojiAlignment
     * @param textSize
     * @param useSystemDefault
     */
    public static void addEmojis(Context context, Spannable text, int emojiSize, int emojiAlignment, int textSize, boolean useSystemDefault) {
        addEmojis(context, text, emojiSize, emojiAlignment, textSize, 0, -1, useSystemDefault);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param emojiAlignment
     * @param textSize
     * @param index
     * @param length
     * @param useSystemDefault
     */
    public static void addEmojis(Context context, Spannable text, int emojiSize, int emojiAlignment, int textSize, int index, int length, boolean useSystemDefault) {
        if (useSystemDefault || text == null) {
            return;
        }

        int textLength = text.length();
        int textLengthToProcessMax = textLength - index;
        int textLengthToProcess = length < 0 || length >= textLengthToProcessMax ? textLength : (length + index);

        // remove spans throughout all text
        EmojiconSpan[] oldSpans = text.getSpans(0, textLength, EmojiconSpan.class);
        for (int i = 0; i < oldSpans.length; i++) {
            text.removeSpan(oldSpans[i]);
        }

        int skip = 0;
        int icon = 0;
        String str = text.toString();
        int indexPosition = 0;
        System.out.println("xwb"+System.currentTimeMillis());
        for (ArrayMap.Entry<String, Integer> m : sEmojiImportantMap.entrySet()) {
            int hasIndex = 0;
            while (true) {
                if (str.indexOf(m.getKey(), hasIndex) != -1) {
                    indexPosition = str.indexOf(m.getKey(), hasIndex);
                    skip = m.getKey().length();
                    icon = m.getValue();
                    hasIndex = indexPosition + skip;
                    if (icon > 0) {
                        text.setSpan(new EmojiconSpan(context, icon, emojiSize, emojiAlignment, textSize), indexPosition, hasIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                } else {
                    break;
                }
            }
        }
        System.out.println("xwb"+System.currentTimeMillis());


    }



}
