package com.atguigu.atcrowdfuning.service.member.service;

import java.util.List;

import com.atguigu.atcrowdfuning.common.bean.Cert;
import com.atguigu.atcrowdfuning.common.bean.Member;
import com.atguigu.atcrowdfuning.common.bean.MemberCert;
import com.atguigu.atcrowdfuning.common.bean.Ticket;

public interface MemberService {

	Member queryMemberByLoginacct(String loginacct);

	Ticket queryTicketByMemberid(Integer id);

	void insertTicket(Ticket t);

	void updateAccoutType(Member loginMember);

	void updateStep(Ticket t);

	void updateBasicinfo(Member loginMember);

	List<Cert> queryCertsByAccountType(String accttype);

	Member queryById(Integer memberid);

	void insertMemberCerts(List<MemberCert> mcs);

	void updateEmail(Member loginMember);

	void updateAuthcodeAndStep(Ticket t);

	void updateTicketAuthcode(Ticket t);

	void updateAuthstatus(Member loginMember);

	Member queryMemberByPiid(String piid);

	List<MemberCert> queryMemberCertsByMemberid(Integer memberid);

	void updateTicketStatus(Ticket t);

}
