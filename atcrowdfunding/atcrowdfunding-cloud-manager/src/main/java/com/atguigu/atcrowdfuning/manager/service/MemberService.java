package com.atguigu.atcrowdfuning.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfuning.common.bean.MemberCert;

@FeignClient("eureka-member-service")
public interface MemberService {

	@RequestMapping("/queryMemberCertsByMemberid/{memberid}")
	List<MemberCert> queryMemberCertsByMemberid(@PathVariable("memberid")Integer memberid);

	@RequestMapping("/finishAuth")
	void finishAuth(@RequestBody Map<String, Object> varMap);

}
