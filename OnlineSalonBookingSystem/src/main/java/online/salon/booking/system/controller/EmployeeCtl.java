package online.salon.booking.system.controller;

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

import online.salon.booking.system.dto.ProductDTO;
import online.salon.booking.system.dto.ServicesDTO;
import online.salon.booking.system.dto.UserDTO;
import online.salon.booking.system.exception.DuplicateRecordException;
import online.salon.booking.system.form.UserForm;
import online.salon.booking.system.service.EmployeeServiceInt;
import online.salon.booking.system.service.ProductImpl;
import online.salon.booking.system.service.ServiceImpl;
import online.salon.booking.system.service.UserServiceInt;


@Controller
public class EmployeeCtl extends BaseCtl{
	
	@Autowired
	public EmployeeServiceInt employeeServiceInt;
	
	
	@Autowired
	public UserServiceInt userServiceInt;
	
	@Autowired
	public ServiceImpl serviceImpl;
	
	@Autowired
	public ProductImpl productService;
	
	@GetMapping("/employee")
	public String display(@ModelAttribute("form") UserForm form, Model model) {
		return "addemployee";
	}
	
	@PostMapping("/submitemployee")
	public String submit(@RequestParam String operation, @Valid @ModelAttribute("form") UserForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/employee";
		}
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "addemployee";
		}
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				UserDTO entity = (UserDTO) populateDTO(form.getDTO(), request);
				entity.setRoleid(3);
				entity.setRolename("Employee");
				userServiceInt.add(entity);
				model.addAttribute("success", "Employee Added Successfully");
				return "addemployee";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "addemployee";
		}
		return "addemployee";

	}
	
	@RequestMapping(value = "/employeeList", method = { RequestMethod.GET, RequestMethod.POST })
	public String UserList(@ModelAttribute("form") UserForm form,
			@RequestParam(required = false) String operation, Long id, long service_id, long product_id,long product_price, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/employeeList";
		}
		
		if (OP_SEARCH.equalsIgnoreCase(form.getOperation())) {
			UserDTO dto = (UserDTO) form.getDTO();
			List<UserDTO> list = userServiceInt.search(dto);
			model.addAttribute("list", list);
			return "employeelist";
		}
		UserDTO uDto = (UserDTO) session.getAttribute("user");
			try {
				List<UserDTO> list = userServiceInt.employeelist(uDto.getId());
				if (list.size() == 0) {
					model.addAttribute("error", "Record not found");
				}
				model.addAttribute("list", list);
				
				//Service DTO
				ServicesDTO servicesDTO = serviceImpl.FindByPk(service_id);
				model.addAttribute("servicesDTO", servicesDTO);
				
				//Product DTO
				ProductDTO productDTO = productService.FindByPk(product_id);
				model.addAttribute("productDTO", productDTO);
				
				return "employeelist";
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "employeelist";
	}
	
	@GetMapping("/emp/Delete")
	public String Delete(@ModelAttribute("form") UserForm form,
			@RequestParam(required = false) String operation, Long id, HttpSession session, Model model) {
		userServiceInt.Delete(id);
		model.addAttribute("success", "Deleted Successfully!!!");
		UserDTO uDto = (UserDTO) session.getAttribute("user");
		List<UserDTO> list = userServiceInt.employeelist(uDto.getId());
		model.addAttribute("list", list);
		return "employeelist";
	}
	

}
