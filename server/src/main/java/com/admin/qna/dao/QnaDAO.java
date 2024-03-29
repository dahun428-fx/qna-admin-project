package com.admin.qna.dao;

import org.apache.ibatis.annotations.Mapper;

import com.admin.qna.models.Qna;

@Mapper
public interface QnaDAO {
    void insert(Qna qna);
}
