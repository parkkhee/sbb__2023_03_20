package com.mysite.sbb.question.service;

import com.mysite.sbb.base.exception.DataNotFoundException;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import com.mysite.sbb.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

//    public List<Question> getList() {
//
//        List<Question> ql = questionRepository.findAll();
//        return ql;
//    }

    public Page<Question> getList(int page) {

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return questionRepository.findAll(pageable);

    }

    public Question getQuestion(Integer id) {

        Optional<Question> q = questionRepository.findById(id);

        if (q.isPresent()) {
            return q.get();
        }else{
            throw new DataNotFoundException("question not found");
        }

    }

    public void create(String subject, String content, SiteUser user) {

        Question question = new Question();
        question.setSubject(subject);
        question.setCreateDate(LocalDateTime.now());
        question.setContent(content);
        question.setAuthor(user);
        questionRepository.save(question);
    }

    public void modify(Question question, String subject, String content) {

        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());

        questionRepository.save(question);

    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        questionRepository.save(question);
    }


}
