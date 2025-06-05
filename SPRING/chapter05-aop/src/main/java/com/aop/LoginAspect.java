package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {
    /* @Pointcut : 여러 조인 포인트를 매치하기 위해 지정한 표현식 */
    // execution([수식어] 리턴 타입 [클래스 이름].이름(파라미터)
    // 수식어 생략 가능
    // *Service.*(..) : 매개변수 0개 이상인 모든 메소드 호출
    // *Service.*(*) : 매개변수 1개 이상인 모든 메소드 호출
    // *Service.*(*, ..) : 매개변수 2개 이상인 모든 메소드 호출
    // Pointcut은 반드시 반환형은 void이어야 한다.

    // * : 리턴 타입 상관 없다
    // com.aop.*Service : com.aop 밑에 있는 모든 Service 클래스
    // .* : 모든 메소드
    @Pointcut("execution(* com.aop.*Service.*(..))")
    public void logPointcut() {}

    /*
    JoinPoint : 포인트컷으로 패치한 실행 지점
    이렇게 매치된 조인포인트에서 해야 할 일이 어드바이스이다.
    매개변수로 전달한 JoinPoint 객체 현재 조인 포인트 메소드명, 인수값 등의 자세한 정보를 액세스 할 수 있다.
    */

    // LoginAspect.logPointcut() 메소드 이전에 동작할 메소드
    @Before("LoginAspect.logPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        // joinPoint.getTarget() : JointPoint가 타켓하고 있는 객체를 알 수 있다.
        System.out.println("Before joinPoint.getTarget() : " + joinPoint.getTarget());
        // joinPoint.getTarget() : JointPoint가 타켓하고 있는 메소드를 알 수 있다.
        System.out.println("Before joinPoin.getSignature : " + joinPoint.getSignature());

        //joinPoint.getArgs() > 0 : 매개변수가 있다
        if (joinPoint.getArgs().length > 0) {
            // JointPoint가 어떤 인자로 전달되고 있는지 알 수 있다.
            System.out.println("Before joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]);
        }
    }

    /*
    After 어드바이스는 포인트 컷을 동일한 클래스 내에서 사용하는 것이며 클래스명은 생략 가능하다.
    단, 패키지가 다르면 패키지를 포함한 클래스명을 기술해야 한다.
     */

    // logPointcut() 이후에 동작할 메소드 지정
    @After("logPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After joinPoint.getSignature() : " + joinPoint.getSignature());
    }

    /*
    returning 속성은 리턴값으로 받아올 오브젝트의 매개변수 이름과 동일해야 한다.
    또한 joinPoint는 반드시 첫 번째 매개변수로 선언해야 한다.
    */

    // logPointcut() 이후에 동작하는 메소드, 반환값 result
    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After Returning result : " + result);
    }

    /* throwing 속성의 이름과 매개변수의 이름이 동일해야 한다. */

    // exception 발생 이후에 발생하는 메소드, 예외가 발생하면 return 값이 없기 떄문에 @AfterReturning은 동작하지 않는다.
    @AfterThrowing(pointcut = "logPointcut()", throwing = "exception")
    public void logAfterThrowing(Throwable exception) {
        System.out.println("After Throwing exception : " + exception);
    }

    /*
    Around Advice는 가장 강력한 어드바이스이다.
    이 어드바이스는 조인포인트를 완전히 장악하기 때문에
    앞에 살펴본 어드바이스 모두 Around 어드바이스로 조합할 수 있다.
    심지어 원본 조인포인트를 언제 실행할지, 실행 자체를 안할지, 계속 실행하지 여부까지도 제어한다.
    조인포인트 매개변수를 ProceedingJoinPoint로 고정되어 있다.
    JoinPoint의 하위 인터페이스르ㅗ 원본 조인포인트의 진행 시점을 제어할 수 있다.
    */

    // 반환형은 Object이다.
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around Before : " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed(); // 원본 조인포인트를 실행한다.
        System.out.println("Around After : " + joinPoint.getSignature().getName());

        /* 원본 조인포인트를 호출한 쪽 혹은 다른 어드바이스가 다시 실행할 수 있도록 반환한다. E*/
        return  result;
    }
}
