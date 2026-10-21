package online.salon.booking.system.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import online.salon.booking.system.dto.AppointmentDTO;
import online.salon.booking.system.dto.ProductDTO;
import online.salon.booking.system.dto.ServicesDTO;
import online.salon.booking.system.dto.UserDTO;
import online.salon.booking.system.exception.DuplicateRecordException;
import online.salon.booking.system.form.AppointmentForm;
import online.salon.booking.system.service.AppointmentServiceInt;
import online.salon.booking.system.service.ProductImpl;
import online.salon.booking.system.service.ServiceImpl;
import online.salon.booking.system.service.UserServiceImpl;

@Controller
public class AppointmentCtl extends BaseCtl {

	@Autowired
	public AppointmentServiceInt serviceint;
	
	@Autowired
	public UserServiceImpl userService;
	
	@Autowired
	public ProductImpl productService;

	@Autowired
	public ServiceImpl servicesService;
	
	@GetMapping("/emp/appointment")
	public String Appointment(@ModelAttribute("form") AppointmentForm form,
			@RequestParam(required = false) String operation, String employeeName, long user_id, long service_id, long product_id,  HttpSession session,
			Model model, HttpServletRequest request) {
		
		UserDTO userDTO = userService.FindByPk(user_id);
		ServicesDTO servicesDTO = servicesService.FindByPk(service_id);
		ProductDTO productDTO = productService.FindByPk(product_id);
 		model.addAttribute("userDTO", userDTO);
 		model.addAttribute("servicesDTO", servicesDTO);
 		model.addAttribute("productDTO", productDTO);
		
		return "addappointment";
	}

	@PostMapping("/bookappointment")
	public String submitProduct(@RequestParam String operation, @Valid @ModelAttribute("form") AppointmentForm form,
			BindingResult bindingResult, Model model, HttpSession session, HttpServletRequest request)
			throws IOException {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/addappointment";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				AppointmentDTO bean = (AppointmentDTO) populateDTO(form.getDTO(), request);
				System.out.println("AppointmentDTO Ctl: "+bean.getToBookingDate()+ " : "+ bean.getToBookingDate());
				long empId = bean.getUserid();
				
				//System.out.println("Time 2 :" + form.getTime());
				UserDTO uDto = (UserDTO) session.getAttribute("user");
				bean.setUserid(uDto.getId());
				bean.setUserName(uDto.getUserName());
				bean.setStatus("book");
				bean.setEmpid(empId);
				serviceint.add(bean);
				model.addAttribute("success", "Your Appointment Booked SuccessFully !!");
				model.addAttribute("Price", bean.getPrice());
				return "redirect:/pay?price="+bean.getPrice();
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "addappointment";
		}
		return "addappointment";
	}

	@RequestMapping(value = "/appointmentList", method = { RequestMethod.GET, RequestMethod.POST })
	public String UserList(@ModelAttribute("form") AppointmentForm form,
			@RequestParam(required = false) String operation, Long id, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/appointmentList";
		}

		if (OP_SEARCH.equalsIgnoreCase(form.getOperation())) {
			AppointmentDTO dto = (AppointmentDTO) form.getDTO();
			List<AppointmentDTO> list = serviceint.search(dto);
			model.addAttribute("list", list);
			return "appointmentlist";
		}
		UserDTO uDto = (UserDTO) session.getAttribute("user");
		long roleid = uDto.getRoleid();
		if (roleid == 2) {
			try {
				List<AppointmentDTO> list = serviceint.appointmentlist(uDto.getId());
				if (list.size() == 0) {
					model.addAttribute("error", "Record not found");
				}
				model.addAttribute("list", list);
				return "appointmentlist";
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(roleid ==3) {
			try {
				List<AppointmentDTO> list = serviceint.empappointmentlist(uDto.getUserName());
				if (list.size() == 0) {
					model.addAttribute("error", "Record not found");
				}
				model.addAttribute("list", list);
				return "appointmentlist";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			List<AppointmentDTO> list = serviceint.list();
			if (list.size() == 0) {
				model.addAttribute("error", "Record not found");
			}
			model.addAttribute("list", list);
		}
		return "appointmentlist";
	}

	@GetMapping("/appointment/Delete")
	public String Delete(@ModelAttribute("form") AppointmentForm form, @RequestParam(required = false) String operation,
			Long id, HttpSession session, Model model) {
		serviceint.Delete(id);
		model.addAttribute("success", "Deleted Successfully!!!");
		List<AppointmentDTO> list = serviceint.list();
		model.addAttribute("list", list);
		return "appointmentlist";
	}
	
	@GetMapping("/appointment/cancle")
	public String Cancle(@ModelAttribute("form") AppointmentForm form,
			@RequestParam(required = false) String operation, Long id, String status ,HttpSession session, Model model) {
		
		AppointmentDTO bean =  serviceint.FindByPk(id);
		bean.setStatus("Canceled");
		try {
			serviceint.Update(bean);
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserDTO uDto = (UserDTO) session.getAttribute("user");
		long roleid = uDto.getRoleid();
		
		System.out.println("roleid: "+roleid);
		
		if (roleid == 3) {
			try {
				List<AppointmentDTO> list = serviceint.empappointmentlist(uDto.getId());
				if (list.size() == 0) {
					model.addAttribute("error", "Record not found");
				}
				model.addAttribute("list", list);
				return "appointmentlist";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			List<AppointmentDTO> list = serviceint.list();
			model.addAttribute("list", list);
		}
	
		
		
		model.addAttribute("success", "Cancle Successfully!!!");
		return "appointmentlist";
	}
	
	@GetMapping("/appointment/confirm")
	public String Confirm(@ModelAttribute("form") AppointmentForm form,
			@RequestParam(required = false) String operation, Long id, String status ,HttpSession session, Model model) {
		AppointmentDTO bean =  serviceint.FindByPk(id);
		serviceint.Confirm(id,bean.getStatus());
		UserDTO uDto = (UserDTO) session.getAttribute("user");
		long roleid = uDto.getRoleid();
		
		System.out.println("roleid: "+roleid);
		if (roleid == 3) {
			try {
				List<AppointmentDTO> list = serviceint.empappointmentlist(uDto.getId());
				if (list.size() == 0) {
					model.addAttribute("error", "Record not found");
				}
				model.addAttribute("list", list);
				return "appointmentlist";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			List<AppointmentDTO> list = serviceint.list();
			model.addAttribute("list", list);
		}
		
		model.addAttribute("success", "Confirm Successfully!!!");	
		return "appointmentlist";
	}
	
}
