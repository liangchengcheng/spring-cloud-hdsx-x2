package com.hdsx.appservice.getway.hystrix;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.hdsx.appservice.Contract.FAIL;

/**
 *  网关断路器
 */
@RestController
public class FallbackController {

    /*
     * @ClassName FallbackController
     * @Desc TODO   网关断路器
     * @Date 2019/6/23 19:35
     * @Version 1.0
     */
    @RequestMapping("/fallback")
    @ResponseBody
    public XbinResult fallback() {
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

}
