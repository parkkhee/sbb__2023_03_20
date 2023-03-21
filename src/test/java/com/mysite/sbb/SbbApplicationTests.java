package com.mysite.sbb;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.answer.repository.AnswerRepository;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@BeforeEach // 아래 매서드는 각 테스트케이스가 실행되기 전에 실행된다.
	void beforEach() {

	}

//	@Test
//	void contextLoads() {
//
//		Question q1 = new Question();
//		q1.setSubject("sbb가 무엇인가요?");
//		q1.setContent("sbb에 대해서 알고 싶습니다.");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//
//
//		Question q2 = new Question();
//		q2.setSubject("내가만든 쿠키");
//		q2.setContent("안녕하세요");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
//	}

	@Transactional
	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		Assertions.assertEquals(1, answerList.size());
		Assertions.assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}


}
