 package br.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.bo.FornecedorBO;
import br.com.springboot.model.Fornecedor;
import jakarta.validation.Valid;

@Controller 
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorBO bo;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("fornecedor", new Fornecedor());
		return new ModelAndView("/fornecedor/formulario", model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String salva (@Valid @ModelAttribute Fornecedor fornecedor, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors())
			return "fornecedor/formulario";
		if (fornecedor.getId() == null) {
			bo.insere(fornecedor);
			attr.addFlashAttribute("feedback", "Fornecedor cadastrado com sucesso");
		}
		else {
			bo.atualiza(fornecedor);
			attr.addFlashAttribute("feedback", "Fornecedor foi atualizado com sucesso");

		}
		return "redirect:/fornecedores";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("fornecedores", bo.lista());
		return new ModelAndView("/fornecedor/lista", model);
	}
	
	@RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("fornecedor", bo.pesquisaPeloId(id));
		return new ModelAndView("/fornecedor/formulario", model);
	}
	
	@RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
	public String inativa(@PathVariable("id") Long id) {
		Fornecedor fornecedor = bo.pesquisaPeloId(id);
		bo.inativa(fornecedor);
		return "redirect:/fornecedores";
	}
	
	@RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
	public String ativa(@PathVariable("id") Long id) {
		Fornecedor fornecedor = bo.pesquisaPeloId(id);
		bo.ativa(fornecedor);
		return "redirect:/fornecedores";
	}
	
}
