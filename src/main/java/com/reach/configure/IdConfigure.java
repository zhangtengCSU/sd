package com.reach.configure;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;

public class IdConfigure {
    public static void launch() {
        IdGeneratorOptions options = new IdGeneratorOptions(Short.valueOf("1"));
        options.WorkerIdBitLength = 1;
        YitIdHelper.setIdGenerator(options);
    }
}
