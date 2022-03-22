package com.qfhp.openfeign.feign;

import com.qfhp.service.IBookController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("storage")
public interface BookService extends IBookController {

}
