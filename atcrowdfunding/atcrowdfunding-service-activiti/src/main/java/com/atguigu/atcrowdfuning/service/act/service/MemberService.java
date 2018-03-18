package com.atguigu.atcrowdfuning.service.act.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfuning.common.bean.Member;

@FeignClient("eureka-member-service")
public interface MemberService {

	@RequestMapping("/queryMemberByPiid/{piid}")
	Member queryMemberByPiid(@PathVariable("piid")String piid);

}
