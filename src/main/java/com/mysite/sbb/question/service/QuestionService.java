package com.mysite.sbb.question.service;

import com.mysite.sbb.base.exception.DataNotFoundException;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {

        List<Question> ql = questionRepository.findAll();
        return ql;
    }

    public Question getQuestion(Integer id) {

        Optional<Question> q = questionRepository.findById(id);

        if (q.isPresent()) {
            return q.get();
        }else{
            throw new DataNotFoundException("question not found");
        }

    }

}
