package com.mysite.sbb.question.controller;

import com.mysite.sbb.answer.base.AnswerForm;
import com.mysite.sbb.question.base.QuestionForm;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
//    @ResponseBody
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
//        List<Question> questionList = this.questionService.getList();
//        model.addAttribute("questionList", questionList);

        Page<Question> paging = questionService.getList(page);
        model.addAttribute("paging", paging);

        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {

        Question question = questionService.getQuestion(id);

        model.addAttribute("question",question);

        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }


}
