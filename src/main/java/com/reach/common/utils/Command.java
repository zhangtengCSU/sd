package com.reach.common.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Command {
    private static final String SUDO_CMD = "sudo";
    private static final String SPACE_SEPARATOR = " ";

    public static String mv(String originPath, String fileName, String targetPath) {
        return SUDO_CMD + SPACE_SEPARATOR + "mv" + SPACE_SEPARATOR
                + originPath + fileName + SPACE_SEPARATOR
                + targetPath + fileName;
    }
    public static String cp(String originPath, String fileName, String targetPath) {
        return SUDO_CMD + SPACE_SEPARATOR + "cp" + SPACE_SEPARATOR
                + originPath + fileName + SPACE_SEPARATOR
                + targetPath + fileName;
    }
    public static String rm(String path, String file) {
        return SUDO_CMD + SPACE_SEPARATOR + "rm" + SPACE_SEPARATOR
                + path + file;
    }
}
