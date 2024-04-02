package com.admin.qna.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.admin.qna.app.qna.dto.CreateQnaDTO;
import com.admin.qna.app.qna.dto.ReadQnaDTO;
import com.admin.qna.app.qna.dto.UpdateQnaDTO;
import com.admin.qna.app.qna.service.QnaService;
import com.admin.utils.ModelUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QnaClientControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QnaService qnaService;
    @Value("classpath:test/test-dummy-data/qna/save-qna-data.json")
    private Resource createQnaJson;
    private CreateQnaDTO createQnaDTO = null;
    private ReadQnaDTO readQnaDTO = null;

    private String ENCODE="UTF-8";

    @BeforeEach
    public void setUp() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, String> map = mapper.readValue(createQnaJson.getFile(), Map.class);
           createQnaDTO = ModelUtil.map(map, CreateQnaDTO.class);
           readQnaDTO = ModelUtil.map(map, ReadQnaDTO.class);

        } catch (Exception e) { 
            e.printStackTrace();
        }

    }


    @Test
    @DisplayName("addNewQnaTests")
    public void addNewQnaTests() throws Exception {
        String url = "/api/v1/qna";
        //request body dto type 과 response body dto type 이 다를때, 일치 여부 ( eqauls ) 명시 필요 
        when(qnaService.save(createQnaDTO)).thenReturn(ModelUtil.map(createQnaDTO, ReadQnaDTO.class));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
            .characterEncoding(ENCODE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(ModelUtil.toJSON(createQnaDTO)))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn();

        result.getResponse().setCharacterEncoding(ENCODE);
        ReadQnaDTO responseDto = ModelUtil.toObject(result.getResponse().getContentAsString(), ReadQnaDTO.class);
        BDDMockito.verify(qnaService).save(createQnaDTO);

        assertEquals(responseDto.getTitle(), createQnaDTO.getTitle());
        assertEquals(responseDto.getContent(), createQnaDTO.getContent());
    }

    @Test
    @DisplayName("getOneQnaTest")
    public void getOneQnaTest() throws Exception {
        int qnaId = 1;
        String url = "/api/v1/qna/"+qnaId;

        when(qnaService.getOne(qnaId)).thenReturn(readQnaDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        result.getResponse().setCharacterEncoding(ENCODE);
        ReadQnaDTO responseDto = ModelUtil.toObject(result.getResponse().getContentAsString(), ReadQnaDTO.class);

        BDDMockito.verify(qnaService).getOne(qnaId);

        assertEquals(responseDto.getQnaId(), readQnaDTO.getQnaId());
        assertEquals(responseDto.getTitle(), readQnaDTO.getTitle());
        assertEquals(responseDto.getContent(), readQnaDTO.getContent());
        assertEquals(responseDto.getProductCode(), readQnaDTO.getProductCode());
        assertEquals(responseDto.getRegDate(), readQnaDTO.getRegDate());
        assertEquals(responseDto.getLatestUpdateDate(), readQnaDTO.getLatestUpdateDate());
        assertEquals(responseDto.getUserId(), readQnaDTO.getUserId());

    }

    @Test
    public void modifyQnaOneTest() throws Exception {
        int qnaId = 1;
        String url = "/api/v1/qna/"+qnaId;

        
        UpdateQnaDTO updateDto = new UpdateQnaDTO("modifiedTitle0","modifiedContent");
        
        qnaService.modify(qnaId, updateDto);
        // when(qnaService.modify(qnaId, updateDto));
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        BDDMockito.verify(qnaService).modify(qnaId, updateDto);

    }
}
