package thread;

public class Application {
    public static void main(String[] args) {
        /* 프로세스와 스레드 */
        /*
        프로세스 : 실행 중인 프로그램
        스레드 : 프로세스 내에서 실제로 작업을 수행하는 주체
        따라서 모든 프로세스는 한 개 이상의 스레드가 존재하며 두 개 이상의 스레드를 가지는 프로세스를 멀티스레드라고 한다.

        스레드를 생성하는 방법
        1. Thread 클래스를 상속받는 방법
        2. Runnabla 인터페이스를 상속받는 방법
        */

        /* 1. 첫번째 방법 */
        Car car = new Car();
        Tank tank = new Tank();
        Plane plane = new Plane();

        /* Thread 타입의 인스턴스로 변환 */
//        Thread t1 = car;
//        Thread t2 = tank;
//        Thread t3 = new Thread(plane); // 인터페이스를 이용했기 떄문에 Thread 클래스를 통해 인스턴스 생성

        /* 2. 두번째 방법*/
        Thread t1 = new Car();
        Thread t2 = new Tank();
        Thread t3 = new Thread(plane);

//        t1.run();
//        t2.run();
//        t3.run();

        t1.start();
        t2.start();
        t3.start();

    }
}
