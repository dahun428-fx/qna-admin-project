package com.admin.qna.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.admin.qna.dao.QnaDAO;
import com.admin.qna.dto.CreateQnaDTO;
import com.admin.qna.dto.ReadQnaDTO;
import com.admin.qna.dto.UpdateQnaDTO;
import com.admin.qna.models.Qna;
import com.admin.qna.repository.QnaRepository;
import com.admin.utils.ModelUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QnaService {

    private final QnaDAO qnaDAO;
    private final QnaRepository qnaRepository;

    /**
     * insert / update qna
     * @param dto Qna
     */
    // @Transactional(rollbackFor = {Exception.class})
    public ReadQnaDTO save(CreateQnaDTO dto) {
        Qna targetQna = ModelUtil.map(dto, Qna.class);
        // qnaDAO.insert(targetQna);
        if (targetQna != null) qnaRepository.save(targetQna);
        return ModelUtil.map(targetQna, ReadQnaDTO.class);
    }

    /**
     * get one qna
     * @param qnaId long
     * @return ReadQnaDTO
     */
    public ReadQnaDTO getOne(long qnaId) {
        return ModelUtil.map(qnaRepository.findById(qnaId).orElseThrow(()-> new NoSuchElementException()), ReadQnaDTO.class);
    }

    /**
     * delete one qna
     * @param qnaId long
     */
    public void deleteOne(long qnaId) {
       Qna foundQna = qnaRepository.findById(qnaId).orElseThrow(()-> new NoSuchElementException());
       foundQna.deleteExcute();
       qnaRepository.save(foundQna);
    }

    /**
     * update qna
     * @param dto
     */
    public void modify(UpdateQnaDTO dto) {
        long qnaId = dto.getQnaId();
        Qna foundQna = qnaRepository.findById(qnaId).orElseThrow(()-> new NoSuchElementException());
        foundQna.updateService(ModelUtil.map(dto, Qna.class));
        qnaRepository.save(foundQna);
    }

}
