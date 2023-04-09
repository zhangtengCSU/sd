package com.reach.common.utils;

import com.reach.common.exception.ReachException;
import com.reach.common.response.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;

@Slf4j
public class LinuxCmdExecutor {


    public static String execute(String command,String[] params) {
        try {
            // 1.init command
            String[] cmd = new String[]{command};
            // 2.add params
            cmd = ArrayUtils.addAll(cmd, params);
            // 3.execute
            Process ps = Runtime.getRuntime().exec(cmd);
            if (ps.waitFor() != 0) {
                throw ReachException.build(ResponseEnum.CUSTOM, "generate failed");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }




}
