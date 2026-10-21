package online.salon.booking.system.controller;

import java.util.Iterator;
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

import online.salon.booking.system.dto.CartDTO;
import online.salon.booking.system.dto.OrderDTO;
import online.salon.booking.system.dto.PaymentDTO;
import online.salon.booking.system.dto.UserDTO;
import online.salon.booking.system.exception.DuplicateRecordException;
import online.salon.booking.system.form.OrderForm;
import online.salon.booking.system.form.PaymentForm;
import online.salon.booking.system.service.CartInt;
import online.salon.booking.system.service.OrderServiceInt;
import online.salon.booking.system.service.PaymentServiceInt;


@Controller
public class PaymentCtl extends BaseCtl{
	
	
	@Autowired
	private CartInt booking;
	
	@Autowired
	private OrderServiceInt service;
	
	@Autowired
	private PaymentServiceInt payment;

	
	@GetMapping("/pay")
	public String payment(@ModelAttribute("form") PaymentForm form,
		@RequestParam(required = false) HttpSession session, Model model,HttpServletRequest request, long price) {
	    model.addAttribute("Price", price);
	    System.out.println("Price: "+price);
        return "payment";		
	}
	
	@PostMapping("/submitpayment")
	public String submitPayment(@RequestParam String operation, @Valid @ModelAttribute("form") PaymentForm form,
			BindingResult bindingResult, Model model, HttpSession session, HttpServletRequest request) {
	
		try {
			PaymentDTO bean = (PaymentDTO) populateDTO(form.getDTO(), request);
			UserDTO uDto = (UserDTO) session.getAttribute("user");
			System.out.println(bean.getUsername());
			bean.setUserid(uDto.getId());
			bean.setUsername(uDto.getUserName());
			bean.setStatus("Paid");
            payment.add(bean);
			model.addAttribute("success", "Payment done Successfully !!");
			CartDTO cartDTO=new CartDTO();
			List<CartDTO> cList = booking.userorderlist(uDto.getId());
			Iterator<CartDTO> it=cList.iterator();
			while (it.hasNext()) {
				System.out.println("In loop");
				cartDTO = (CartDTO) it.next();
				OrderDTO orderBean = new OrderDTO();
				orderBean.setProductname(cartDTO.getProductname());
				orderBean.setServicecharge(cartDTO.getServicecharge());
				orderBean.setStatus(cartDTO.getStatus());
				orderBean.setTotalcharge(cartDTO.getTotalcharge());
				orderBean.setUserid(cartDTO.getUserid());
				orderBean.setUserName(cartDTO.getUserName());
				service.add(orderBean);
				booking.delete(cartDTO);
			}
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return "payment";
	}
	
	
	@RequestMapping(value = "/paymentlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String PaymentList(@ModelAttribute("form") PaymentForm form,
			@RequestParam(required = false) String operation, Long id, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/paymentlist";
		}
		
		if (OP_SEARCH.equalsIgnoreCase(form.getOperation())) {
			PaymentDTO dto = (PaymentDTO) form.getDTO();
		     List<PaymentDTO> list = payment.search(dto);
			 model.addAttribute("list", list);
			return "paymentList";
		}
		UserDTO uDto = (UserDTO) session.getAttribute("user");
		long roleid = uDto.getRoleid();
		if (roleid == 2) {
			try {
				List<PaymentDTO> list = payment.userpaymentlist(uDto.getId());
				if (list.size() == 0) {
					model.addAttribute("error", "Record not found");
				}
				model.addAttribute("list", list);
				
				return "paymentList";
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else{
		List<PaymentDTO> list = payment.list();
		if (list.size() == 0) {
			model.addAttribute("error", "Record not found");
		}
		model.addAttribute("list", list);
		}
		return "paymentList";
	}
	
	
	@GetMapping("/payment/Delete")
	public String Delete(@ModelAttribute("form") PaymentForm form,
			@RequestParam(required = false) String operation, Long id, HttpSession session, Model model) {
		payment.Delete(id);
		model.addAttribute("success", "Deleted Successfully!!!");
		List<PaymentDTO> list = payment.list();
		model.addAttribute("list", list);
		return "paymentList";
	}
	
	@RequestMapping(value = "/orderlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String OrderList(@ModelAttribute("form") OrderForm form,
			@RequestParam(required = false) String operation, Long id, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/orderList";
		}
		
		if (OP_SEARCH.equalsIgnoreCase(form.getOperation())) {
			OrderDTO dto = (OrderDTO) form.getDTO();
		     List<OrderDTO> list = service.search(dto);
			 model.addAttribute("list", list);
			return "orderList";
		}
		UserDTO uDto = (UserDTO) session.getAttribute("user");
		long roleid = uDto.getRoleid();
		if (roleid == 2) {
			try {
				System.out.println("User List");
				List<OrderDTO> list = service.userorderlist(uDto.getId());
				if (list.size() == 0) {
					model.addAttribute("error", "Record not found");
				}
				model.addAttribute("list", list);
				return "orderList";
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else{
		List<OrderDTO> list = service.list();
		if (list.size() == 0) {
			model.addAttribute("error", "Record not found");
		}
		model.addAttribute("list", list);
		}
		return "orderList";
	}
	
	@GetMapping("/orders/Delete")
	public String Delete(@ModelAttribute("form") OrderForm form,
			@RequestParam(required = false) String operation, Long id, HttpSession session, Model model) {
		service.Delete(id);
		model.addAttribute("success", "Order Delete Successfully!!!");
		List<OrderDTO> list = service.list();
		model.addAttribute("list", list);
		return "orderList";
	}
	
	
	
	

}
