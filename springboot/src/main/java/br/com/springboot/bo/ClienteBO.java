package br.com.springboot.bo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ClienteDAO;
import br.com.springboot.model.Cliente;
import jakarta.transaction.Transactional;

@Service
public class ClienteBO implements CRUD<Cliente, Long> {

    @Autowired
    private ClienteDAO dao;

    // MÉTODO PRIVADO PARA REALIZAR O REPLACE E LIMPAR OS DADOS
    private void limparDadosDoCliente(Cliente cliente) {
        
        // Limpa o CPF: remove pontos (.) e hifens (-)
        if (cliente.getCpf() != null) {
            String cpfLimpo = cliente.getCpf().replaceAll("[^0-9]", "");
            cliente.setCpf(cpfLimpo);
        }
        
        // Limpa o Celular: remove parênteses, espaços e hifens
        if (cliente.getCelular() != null) {
            String celularLimpo = cliente.getCelular().replaceAll("[^0-9]", "");
            cliente.setCelular(celularLimpo);
        }
        
        // Limpa o Telefone: remove parênteses, espaços e hifens
        if (cliente.getTelefone() != null) {
            String telefoneLimpo = cliente.getTelefone().replaceAll("[^0-9]", "");
            cliente.setTelefone(telefoneLimpo);
        }
    }

    //---------------------------------------------------------

    @Override
    public Cliente pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    @Override
    public List<Cliente> lista() {
        return dao.lista();
    }

    @Override
    @Transactional // A transação é importante para o commit
    public void insere(Cliente cliente) {
        // CHAMA A LIMPEZA ANTES DE PERSISTIR
        limparDadosDoCliente(cliente); 
        dao.insere(cliente);
    }

    @Override
    @Transactional // A transação é importante para o commit
    public void atualiza(Cliente cliente) {
        // CHAMA A LIMPEZA ANTES DE PERSISTIR
        limparDadosDoCliente(cliente); 
        dao.atualiza(cliente);
    }

    @Override
    public void remove(Cliente cliente) {
        dao.remove(cliente);
    }

    public void inativa(Cliente cliente) {
        cliente.setAtivo(false);
        dao.atualiza(cliente);
    }
    public void ativa(Cliente cliente) {
        cliente.setAtivo(true);
        dao.atualiza(cliente);
    }
}