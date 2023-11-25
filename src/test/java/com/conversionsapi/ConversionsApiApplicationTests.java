package com.conversionsapi;

import com.conversionsapi.domain.conversion.Conversion;
import com.conversionsapi.repositories.ConversionRepositorie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConversionsApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ConversionRepositorie conversionRepositorie;

	@Test
	public void testSaveConversion() throws Exception {
		Conversion conversion = new Conversion();
		conversion.setRealCurr("BRL");
		conversion.setConvertedCurr("EUR");
		conversion.setRealValue(BigDecimal.valueOf(78.92));

		String json = new ObjectMapper().writeValueAsString(conversion);

		mockMvc.perform(post("/conversion")
						.contentType("application/json")
						.content(json)
				)
				.andExpect(status().isCreated());
	}

	@Test
	public void testGetAllConversions() throws Exception {
		Conversion conversion1 = new Conversion();
		conversion1.setRealCurr("BTC");
		conversion1.setConvertedCurr("USD");
		conversion1.setRealValue(BigDecimal.valueOf(54.20));
		conversionRepositorie.save(conversion1);

		Conversion conversion2 = new Conversion();
		conversion2.setRealCurr("EUR");
		conversion2.setConvertedCurr("USD");
		conversion2.setRealValue(BigDecimal.valueOf(19.98));
		conversionRepositorie.save(conversion2);

		mockMvc.perform(get("/conversion"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
		;
	}

	@Test
	public void testUpdateConversion() throws Exception {
		Conversion conversion1 = new Conversion();
		conversion1.setRealCurr("BTC");
		conversion1.setConvertedCurr("USD");
		conversion1.setRealValue(BigDecimal.valueOf(54.20));
		conversionRepositorie.save(conversion1);

		Long id = conversion1.getId();

		Conversion conversion2 = new Conversion();
		conversion2.setRealCurr("BRL");
		conversion2.setConvertedCurr("EUR");
		conversion2.setRealValue(BigDecimal.valueOf(78.92));

		String json = new ObjectMapper().writeValueAsString(conversion2);

		mockMvc.perform(put("/conversion/{id}", id)
						.contentType("application/json")
						.content(json))
				.andExpect(status().isOk());

		// Verifica se a tarefa foi atualizada no banco de dados
		mockMvc.perform(get("/conversion"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id").exists())
				.andExpect(jsonPath("$[0].convertedValue").exists())
				.andExpect(jsonPath("$[0].realCurr").value("BRL"))
				.andExpect(jsonPath("$[0].realValue").value(78.92))
				.andExpect(jsonPath("$[0].convertedCurr").value("EUR"));
	}

	@Test
	public void testDeleteConversion() throws Exception {
		Conversion conversion = new Conversion();
		conversion.setRealCurr("BTC");
		conversion.setConvertedCurr("USD");
		conversion.setRealValue(BigDecimal.valueOf(54.20));
		conversionRepositorie.save(conversion);

		Long id = conversion.getId();

		mockMvc.perform(delete("/conversion/{id}", id))
				.andExpect(status().isNoContent());

		// Verifica se a tarefa foi removida do banco de dados
		mockMvc.perform(get("/conversion"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

}
