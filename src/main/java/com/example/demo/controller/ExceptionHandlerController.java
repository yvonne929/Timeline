package com.example.demo.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.ex.ServiceException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler({BindException.class})
	public String handleBindException(
			Throwable e, 
			RedirectAttributes redirectAttributes) {
		
		BindingResult results = ((BindException) e).getBindingResult();

		// 取得全部例外訊息
		for(FieldError er :results.getFieldErrors()) {
			System.err.println(er.getDefaultMessage());
			System.err.println(er.getField());
		}
		
		// 取得第一個例外訊息
		String message = results.getFieldErrors().get(0).getDefaultMessage();
		redirectAttributes.addFlashAttribute("MESSAGE", message);
		return "redirect:login";
	}
	
	@ExceptionHandler({ServiceException.class, Exception.class})
	public String handleServiceException(
			Throwable e, 
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("MESSAGE", e.getMessage());
		return "redirect:login";
	}
	
}
