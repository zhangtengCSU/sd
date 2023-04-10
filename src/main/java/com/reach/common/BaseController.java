package com.reach.common;

import com.reach.common.exception.ReachException;
import com.reach.common.response.ReachResponse;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.Function;

public abstract class BaseController {
    /**
     * dealWithException
     */
    protected <P, R> ReachResponse<R> dealWithException(P param, Function<P, R> func, String logPre) {
        try {
            R rst = func.apply(param);
            return ReachResponse.SUCCEED(rst).build();
        } catch (ReachException e) {
            getLog().warn(String.format(logPre + " business exception：%s", e.getMsg()), e);
            return ReachResponse.<R>FAILED(e.getCode(), e.getMsg()).build();
        } catch (IllegalArgumentException e) {
            ReachResponse.HttpResponseState code = ReachResponse.HttpResponseState.PARM_ILLEGAL;
            String message = Optional.ofNullable(e.getMessage()).orElse(code.getDesc());
            getLog().warn(String.format(logPre + " param illegal：%s", message), e);
            return ReachResponse.<R>FAILED(code, message).build();
        } catch (Exception e) {
            getLog().warn(logPre + " unknown exception", e);
            return ReachResponse.<R>SYSTEM_ERROR().build();
        }
    }

    /**
     * getLog
     */
    protected abstract Logger getLog();

}