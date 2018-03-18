package com.atguigu.atcrowdfuning.service.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.atcrowdfuning.common.bean.Cert;
import com.atguigu.atcrowdfuning.common.bean.Member;
import com.atguigu.atcrowdfuning.common.bean.MemberCert;
import com.atguigu.atcrowdfuning.common.bean.Ticket;
import com.atguigu.atcrowdfuning.service.member.service.ActService;
import com.atguigu.atcrowdfuning.service.member.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private ActService actService;
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/finishAuth")
	void finishAuth(@RequestBody Map<String, Object> varMap) {
		// 更新会员的实名认证状态
		Integer memberid = (Integer)varMap.get("memberid");
		Member member = memberService.queryById(memberid);
		member.setAuthstatus("2");
		memberService.updateAuthstatus(member);
		
		// 让流程继续执行
		String taskid = (String)varMap.get("taskid");
		actService.endProcess(taskid);
		
		// 更新流程审批单状态
		Ticket t = memberService.queryTicketByMemberid(memberid);
		t.setStatus("1");
		memberService.updateTicketStatus(t);
	}
	
	@RequestMapping("/queryMemberCertsByMemberid/{memberid}")
	List<MemberCert> queryMemberCertsByMemberid(@PathVariable("memberid")Integer memberid) {
		return memberService.queryMemberCertsByMemberid(memberid);
	}
	
	@RequestMapping("/queryMemberByPiid/{piid}")
	Member queryMemberByPiid(@PathVariable("piid")String piid) {
		return memberService.queryMemberByPiid(piid);
	}
	
	@RequestMapping("/updateAuthstatus")
	public void updateAuthstatus(@RequestBody Member loginMember) {
		// 更新会员实名认证状态
		memberService.updateAuthstatus(loginMember);
		// 让流程继续执行
		actService.process(loginMember.getLoginacct());
	}
	
	@RequestMapping("/updateTicketAuthcode")
	public void updateTicketAuthcode(@RequestBody Ticket t) {
		memberService.updateTicketAuthcode(t);
	}
	
	@RequestMapping("/updateEmail")
	public void updateEmail(@RequestBody Member loginMember) {
		// 更新邮箱地址
		memberService.updateEmail(loginMember);

		Ticket t = memberService.queryTicketByMemberid(loginMember.getId());
		t.setPstep("checkcode");
		memberService.updateStep(t);
		// 让流程继续执行
		String authcode = actService.sendMail(loginMember);
		t.setAuthcode(authcode);
		memberService.updateTicketAuthcode(t);
	}
	
	@RequestMapping("/insertMemberCerts")
	public void insertMemberCerts(@RequestBody List<MemberCert> mcs) {
		// 增加会员证明文件数据
		memberService.insertMemberCerts(mcs);
		
		Integer memberid = mcs.get(0).getMemberid();
		Member loginMember = memberService.queryById(memberid);
		// 更新流程步骤
		Ticket t = memberService.queryTicketByMemberid(memberid);
		t.setPstep("email");
		memberService.updateStep(t);
		// 让流程继续执行
		actService.nextProcess(loginMember.getLoginacct());
	}
	
	@RequestMapping("/queryCertsByAccountType/{accttype}")
	public List<Cert> queryCertsByAccountType(@PathVariable("accttype")String accttype) {
		return memberService.queryCertsByAccountType(accttype);
	}
	
	@RequestMapping("/updateBasicinfo")
	public void updateBasicinfo(@RequestBody Member loginMember) {
		// 更新会员基本信息
		memberService.updateBasicinfo(loginMember);
		// 更新流程步骤
		Ticket t = memberService.queryTicketByMemberid(loginMember.getId());
		t.setPstep("cert");
		memberService.updateStep(t);
		// 让流程继续执行
		actService.nextProcess(loginMember.getLoginacct());
	}
	
	@RequestMapping("/updateAccoutType")
	public void updateAccoutType(@RequestBody Member loginMember) {
		// 更新会员账户类型
		memberService.updateAccoutType(loginMember);
		// 更新流程步骤
		Ticket t = memberService.queryTicketByMemberid(loginMember.getId());
		t.setPstep("basicinfo");
		memberService.updateStep(t);
		// 让流程继续执行
		actService.process(loginMember.getLoginacct());
	}
	
	@RequestMapping("/insertTicket")
	public void insertTicket(@RequestBody Ticket t) {
		memberService.insertTicket(t);
	}
	
	@RequestMapping("/queryTicketByMemberid/{id}")
	public Ticket queryTicketByMemberid(@PathVariable("id")Integer id) {
		
		Ticket t = memberService.queryTicketByMemberid(id);
		return t;
	}
	
	@RequestMapping("/login/{loginacct}")
	public Object login( @PathVariable("loginacct")String loginacct ) {
		Member member = memberService.queryMemberByLoginacct(loginacct);
		return member;
	}
}
