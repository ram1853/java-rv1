package com.members.registry.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.members.registry.model.FileBucket;
import com.members.registry.model.Member;
import com.members.registry.service.MemberService;
import com.members.registry.utils.MemberRegistryUtils;

@Controller
@RequestMapping("/dgods")
public class HomeController {

	@Autowired
	MemberService memberService;

	@GetMapping("/home")
	public String home() {

		return "dgods-home";
	}

	@GetMapping("/showAllMembers")
	public synchronized String showAllMembers(Model model) {

		List<Member> allMembers = memberService.getAllMembers();
		model.addAttribute("members", allMembers);

		return "dgods-home";
	}

	@GetMapping("/showAddMemberPage")
	public String showAddMemberPage(Model model) {

		Member member = new Member();
		model.addAttribute("member", member);
		model.addAttribute("action", "create");
		return "add-member-page";

	}

	@PostMapping("/addOrUpdateMember")
	public String addOrUpdateMember(@Valid @ModelAttribute("member") Member member, 
			BindingResult bindingResult, @RequestParam Map<String,String> requestParams,
			FileBucket fileBucket, Model model) throws IOException {

		byte[] existingFileData = MemberRegistryUtils.getUtils().getFileData().get(member.getId());

		if(!bindingResult.hasErrors()) {
			if("update".equals(requestParams.get("mode"))) {
				processUpdate(member, fileBucket, model, existingFileData);
			}else {
				processCreate(member, fileBucket, model);
			}
			return "dgods-home";
		}else {
			return "add-member-page";
		}



	}

	private synchronized void processCreate(Member member, FileBucket fileBucket, Model model) throws IOException {

		List<Member> existingMembers = memberService.getAllMembers();
		//boolean isMemberPresent = existingMembers.stream().anyMatch(m -> m.getEmail().equals(member.getEmail()));
		int isMemberPresent = Collections.binarySearch(existingMembers, member);

		if(isMemberPresent == 0) {
			model.addAttribute("addMember", "no");
		}else {
			model.addAttribute("addMember", "yes");
			member.setContent(fileBucket.getFile().getBytes());
			memberService.addMember(member);
		}
	}

	private synchronized void processUpdate(Member member, FileBucket fileBucket, Model model, byte[] existingFileData)
			throws IOException, UnsupportedEncodingException {

		byte[] base64Decoded;
		String base64Encoded;
		model.addAttribute("updateMember", "yes");
		if(fileBucket.getFile().getBytes().length > 0) {

			member.setContent(fileBucket.getFile().getBytes());
		}else {

			if(existingFileData == null) {
				base64Encoded = memberService.getBase64image(member);
				base64Decoded = Base64.getDecoder().decode(base64Encoded.getBytes("UTF-8"));
				member.setContent(base64Decoded);
				model.addAttribute("base64image", base64Encoded);
			}else {

				if(existingFileData.length > 0) {
					System.out.println("reading from collection instead of hitting db");
					member.setContent(MemberRegistryUtils.getUtils().getFileData().get(member.getId()));
				}

			}
		}
		memberService.updateMember(member);
	}

	@GetMapping("/viewMember")
	public synchronized String viewMember(@RequestParam("name") String name, @RequestParam("email") String email, Model model) {

		Member member = memberService.getMember(name, email);
		if(member != null) {
			model.addAttribute("member", member);
			model.addAttribute("base64image", memberService.getBase64image(member));
			return "display-member";
		}else {
			model.addAttribute("memberNotAvailable", "yes");
			return "dgods-home";
		}
	}

	@GetMapping("/showUpdateMemberPage")
	public synchronized String showUpdateMemberPage(@RequestParam("memberId") int memberId, Model model) {

		Member existingMember = memberService.getMemberById(memberId);
		model.addAttribute("member", existingMember);
		model.addAttribute("action", "update");
		return "add-member-page";

	}

	@GetMapping("/deleteMember")
	public synchronized String deleteMember(@RequestParam("name") String name, @RequestParam("email") String email, Model model) {

		Member member = memberService.getMember(name, email);
		if(member != null) {
			memberService.deleteMember(member);
			model.addAttribute("memberDeleted", "yes");
		}else {
			model.addAttribute("memberNotAvailable", "yes");
		}

		return "dgods-home";
	}
}
