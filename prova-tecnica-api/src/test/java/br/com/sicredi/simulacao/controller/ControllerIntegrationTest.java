package br.com.sicredi.simulacao.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIntegrationTest {

  @Autowired
  MockMvc mvc;

  @Test
  public void QuandoGetCPFSemRestricaoDeveRetornar204() throws Exception {
    mvc
    .perform(get("/api/v1/restricoes/111111111"))
    .andExpect(status().isNoContent());
  }
  
  @Test
  public void QuandoGetCPFComRestricaoDeveRetornar200() throws Exception {
    mvc
    .perform(get("/api/v1/restricoes/97093236014"))
    .andExpect(status().isOk());
  }

  @Test
  public void QuandoCriarSimulacaoDeveRetornar201() throws Exception {
    mvc
    .perform(
        post("/api/v1/simulacoes")
        .content("{\"cpf\":\"2222222\",\"nome\":\"ze\",\"email\":\"ze@gmail.com\",\"valor\":\"1000\",\"parcelas\":\"5\",\"seguro\":\"true\"}")
        .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isCreated());
  }
  
  @Test
  public void QuandoCriarSimulacaoComCampoInvalidoDeveRetornar400() throws Exception {
    mvc
    .perform(
        post("/api/v1/simulacoes")
        .content("{\"cpf\":\"2222222\",\"nome\":\"ze\",\"email\":\"XXXXX\",\"valor\":\"1\",\"parcelas\":\"5\",\"seguro\":\"true\"}")
        .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isBadRequest());
  }
  
  @Test
  public void QuandoAlterarSimulacaoDeveRetornar200() throws Exception {
    mvc
    .perform(
        put("/api/v1/simulacoes/66414919004")
        .content("{\"cpf\":\"66414919004\",\"nome\":\"fulano alterado\",\"email\":\"ze@gmail.com\",\"valor\":\"1\",\"parcelas\":\"5\",\"seguro\":\"true\"}")
        .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().is2xxSuccessful());
  }
  
  @Test
  public void QuandoGetTodasSimulacoesDeveRetornar204() throws Exception {
    mvc
    .perform(get("/api/v1/simulacoes"))
    .andExpect(status().is2xxSuccessful());
  }
  
  @Test
  public void QuandoRemoverSimulacaoDeveRetornar204() throws Exception {
    mvc
    .perform(delete("/api/v1/simulacoes/12"))
    .andExpect(status().is2xxSuccessful());
  }
}
