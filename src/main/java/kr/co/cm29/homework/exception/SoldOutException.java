package kr.co.cm29.homework.exception;

public class SoldOutException extends Throwable {
    public SoldOutException() {
        System.out.println("SoldOUtException 발생. 주문한 상품량이 재고량보다 큽니다.");
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
