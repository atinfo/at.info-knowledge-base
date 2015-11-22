package com.blogspot.notes.automation.qa.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Serhii Kuts
 */
public class IOUtils {
    private static final String IP_MASK = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final Logger LOGGER = Logger.getLogger(IOUtils.class.getName());

    private static String replaceLast(String input, String regex, String replacement) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            return input;
        }

        int lastMatchStart;
        do {
            lastMatchStart = matcher.start();
        } while (matcher.find());

        matcher.find(lastMatchStart);

        final StringBuffer sb = new StringBuffer(input.length());
        matcher.appendReplacement(sb, replacement);
        matcher.appendTail(sb);

        return sb.toString();
    }

    public static boolean updateLastIP(String path, String newIp) {
        try {
            String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
            String newContent = replaceLast(content, IP_MASK, newIp);
            Files.write(Paths.get(path), newContent.getBytes());
        } catch (Exception e) {
            LOGGER.severe("Can't update IP " + newIp + " in " + path + ": " + e.getMessage());
            return false;
        }

        return true;
    }
}
