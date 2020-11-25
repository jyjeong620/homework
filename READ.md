# 29CM 백엔드 개발자 과제 (Java / Spring)
## 프로젝트 구조
- kr.co.cm29.homework
  - common
    - DataSetting : csv 파일 데이터를 읽어와 초기 세팅하는 클래스
    - OrderRunner : 프로그램 구동 시 실제 서비스가 실행되는 클래스
  - controller 
    - OrderController : OrderControllerImpl 의 설계 interface
    - OrderControllerImpl : 주문 입력시 동작하는 메소드가 실행되는 클래스
  - exception
    - SoldOutException : 상품주문시 재고가 없을경우 발생하는 Exception 관리 클래스
  - model 
    - ProductDto : 상품의 정보를 저장하는 Dto 클래스
  - repository
    - OrderRepository : 상품의 정보를 가져와 처리하는 클래스 
  - Service
    - OrderService : OrderServiceImpl 의 설계 interface
    - OrderServiceImpl : repository 에서 가져온 데이터를 처리하는 클래스
---
## 구현방향 
1. 상품목록 저장 방법 설정(파일을 읽어서 객체에 저장해서 사용, 맨처음 구동시 실행 할 수 있도록)
2. 각 요구사항에 필요한 Controller 메소드 설계(전체 상품을 출력하는 메소드, 상품번호와 수량을 입력받아 처리하는 메소드, 주문완료시 Display 하는 메소드등..)
3. Controller 에서 필요한 기능 Service 설계(상품번호를 입력하면 상품목록에서 상품명,가격 데이터를 가져오는 메소드등..)
4. Service 에서 필요한 데이터를 가져오는 Repository 설계(저장된 상품목록에서 필요한 데이터를 가져오거나 변경하는 메소드등..)
5. 설계된 메소드 구현 및 테스트 진행 

## 이슈
- 결제시 실행예시 글자에는 상품번호와 수량모두 SPACE+ENTER(입력) 이라고 되어있지만
  사진에서는 상품번호에만 SPACE+ENTER(입력) 하면 주문내역이 출력되는것으로 나와있어 사진에따랐습니다.