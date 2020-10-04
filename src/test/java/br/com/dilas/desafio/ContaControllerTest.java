package br.com.dilas.desafio;

import br.com.dilas.desafio.controller.dto.CriarContaDTO;
import br.com.dilas.desafio.model.TipoConta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCriarConta() throws Exception {
        CriarContaDTO criarContaDTO = new CriarContaDTO();
        criarContaDTO.setCpf("98363212172");
        criarContaDTO.setNome("Odilon Alves");
        criarContaDTO.setDataNascimento(new Date());
        criarContaDTO.setTipoConta(TipoConta.PESSOA_FISICA);

        String json = objectMapper.writeValueAsString(criarContaDTO);

        this.mockMvc
                .perform(post("/v1/conta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.dataCriacao").isNotEmpty());
    }

    @Test
    public void testCriarContaVazia() throws Exception {
        this.mockMvc
                .perform(post("/v1/conta"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
