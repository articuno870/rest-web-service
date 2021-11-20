package com.test.myproject.restwebservice.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LogginAdvice {

	private static Logger logger = LogManager.getLogger();

	/**
	 * Below two methods for Student Controller
	 */
	@Pointcut(value = "execution(* com.test.myproject.restwebservice.controller.StudentController.*(..) )")
	public void myPointCut() {

	}

	@Around("myPointCut()")
	public Object applicationLogger(ProceedingJoinPoint jointPoint) throws Throwable {

		ObjectMapper mapper = new ObjectMapper();
		String className = jointPoint.getTarget().getClass().getName();
		String methodName = jointPoint.getSignature().getName();
		Object[] args = jointPoint.getArgs();
		logger.info("Method invoked of class {}:{} arguments: {}", className, methodName, Arrays.toString(args));
		Object response = jointPoint.proceed();
		logger.info("Leaving method of class {}:{} with Response: {}", className, methodName,
				mapper.writeValueAsString(response));
		return response;
	}

	/**
	 * Below two methods for Person Controller
	 */
	@Before(value = "execution(* com.test.myproject.restwebservice.controller.PersonController.*(..)) and args(id,name)")
	public void beforeAdvice(JoinPoint joinpoint, int id, String name) {
		logger.info("Before calling {}() with args id: {}, {}", joinpoint.getSignature().getName(), id, name);
	}

	@After(value = "execution(* com.test.myproject.restwebservice.controller.PersonController.*(..)) and args(id,name)")
	public void afterAdvice(JoinPoint joinpoint, int id, String name) {
		logger.info("After calling {}() with args id: {}, {}", joinpoint.getSignature().getName(), id, name);
	}

	@Pointcut(value = "execution(* com.test.myproject.restwebservice.controller.UserJpaController.*(..))")
	public void pointCutUser() {

	}

	@Around("pointCutUser()")
	public Object userLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String className = pjp.getTarget().getClass().getSimpleName();
		String methodName = pjp.getSignature().getName();
		String args = mapper.writeValueAsString(pjp.getArgs());
		logger.info("Before invoking {}:{}, with parameters {}", className, methodName, args);
		Object response = pjp.proceed();
		String resp = mapper.writeValueAsString(response);
		logger.info("After invoking {}:{}, with response {}", className, methodName, resp);
		return response;
	}

}
