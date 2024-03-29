package com.admin.qna.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.admin.qna.dao.QnaDAO;
import com.admin.qna.dto.CreateQnaDTO;
import com.admin.qna.dto.ReadQnaDTO;
import com.admin.qna.dto.UpdateQnaDTO;
import com.admin.qna.models.Qna;
import com.admin.qna.repository.QnaRepository;
import com.admin.utils.ModelUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QnaServiceTests {

    @Autowired
    private QnaDAO qnaDao;

    @Autowired
    private QnaRepository qnaRepository;

    @Value("classpath:test/test-dummy-data/qna/save-qna-data.json")
    private Resource jsonTestFile;

    private CreateQnaDTO createQnaDTO = null;

    private long testQnaId = 1; 

    @BeforeEach
    public void setUp() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, String> map = mapper.readValue(jsonTestFile.getFile(), Map.class);
            createQnaDTO = ModelUtil.map(map, CreateQnaDTO.class);

            Qna foundQna = qnaRepository.findById(testQnaId).orElse(null);
            if (foundQna == null) {
                Qna t = ModelUtil.map(createQnaDTO, Qna.class);
                assertNotNull(t);
                qnaRepository.save(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void saveQnaTest() {
        Qna targetQna = ModelUtil.map(createQnaDTO, Qna.class);
        qnaDao.insert(targetQna);
        long qnaId = targetQna.getQnaId();
        Qna foundQna = qnaRepository.findById(qnaId).orElseThrow(()-> new NoSuchElementException());

        assertEquals(targetQna.getQnaId(), foundQna.getQnaId());
        assertEquals(targetQna.getTitle(), foundQna.getTitle());
        assertEquals(targetQna.getContent(), foundQna.getContent());
        assertEquals(targetQna.getLatestUpdateDate(), foundQna.getLatestUpdateDate());
        assertEquals(targetQna.getRegDate(), foundQna.getRegDate());
        assertEquals(targetQna.getProductCode(), foundQna.getProductCode());
        assertEquals(targetQna.getUserId(), foundQna.getUserId());
    }

    @Test
    public void saveQnaByJPATest() {
        Qna targetQna = ModelUtil.map(createQnaDTO, Qna.class);
        if (targetQna != null) qnaRepository.save(targetQna);
    }

    @Test
    public void getQnaTest() {
        long qnaId = testQnaId;
        ReadQnaDTO targetDto = ModelUtil.map(qnaRepository.findById(qnaId).orElseThrow(()-> new NoSuchElementException()), ReadQnaDTO.class);

        assertEquals(createQnaDTO.getTitle(), targetDto.getTitle());
        assertEquals(createQnaDTO.getContent(), targetDto.getContent());
        assertEquals(createQnaDTO.getUserId(), targetDto.getUserId());
        assertEquals(createQnaDTO.getProductCode(), targetDto.getProductCode());
    }

    @Test
    // @Transactional
    public void deleteQnaTest() {

        Qna foundQna = qnaRepository.findById(testQnaId).orElseThrow(()-> new NoSuchElementException());
        foundQna.deleteExcute();
        qnaRepository.save(foundQna);

        assertEquals(foundQna.isDeleted(), true);
    }

    @Test
    public void modifyQnaTest() {

        Qna foundQna = qnaRepository.findById(testQnaId).orElse(null);
        assertNotNull(foundQna);
        
        String title = "new title";
        String content = "new content";
        
        UpdateQnaDTO dto = new UpdateQnaDTO();
        dto.setTitle(title);
        dto.setContent(content);

        foundQna.updateService(ModelUtil.map(dto, Qna.class));
        qnaRepository.save(foundQna);

        Qna savedQna = qnaRepository.findById(testQnaId).orElse(null);
        assertNotNull(savedQna);

        assertEquals(savedQna.getTitle(), dto.getTitle());
        assertEquals(savedQna.getContent(), dto.getContent());

    }

}
