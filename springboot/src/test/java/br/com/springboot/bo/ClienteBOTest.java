package br.com.springboot.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.springboot.model.Cliente;
import br.com.springboot.model.Sexo;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class ClienteBOTest {

    @Autowired
    private ClienteBO bo;

    @Test
    @Order(1)
    public void insere() {
        Cliente cliente = new Cliente();
        cliente.setNome("José da Silva");
        cliente.setCpf("01234567890");
        cliente.setDataDeNascimento(LocalDate.of(2000, 1, 8));
        cliente.setSexo(Sexo.MASCULINO);
        cliente.setTelefone("0123456789");
        cliente.setCelular("01234567890");
        cliente.setAtivo(true);
        bo.insere(cliente);
        assertNotNull(cliente.getId());
        System.out.println("Cliente inserido com sucesso: " + cliente.getId());
    }

    @Test
    @Order(2)
    public void pesquisaPeloId() {
        Cliente cliente = bo.pesquisaPeloId(1L);
        assertNotNull(cliente);
        System.out.println("Cliente encontrado: " + cliente);
    }

    @Test
    @Order(3)
    public void atualiza() {
        Cliente cliente = bo.pesquisaPeloId(1L);
        cliente.setCpf("98765432100");
        bo.atualiza(cliente);
        System.out.println("Cliente atualizado: " + cliente.getCpf());
    }
}