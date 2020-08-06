# Class101 키트 주문 프로그램

## Contents
* [Specifications](#chapter-1)
* [How to run](#chapter-2)
* [Requirement](#chapter-3) 
* [Strategy](#chapter-4)
* [Domain](#chapter-5)
* [Entity](#chapter-6)
* [Better Planning](#chapter-7)

### <a name="chapter-1"></a>Specifications 
````
 OpenJDK11
 Spring Boot 2.3.2.RELEASE
 Gradle
 lombok
 MySql / h2Database
 Domain Driven Design
````

### <a name="chapter-2"></a>How to Run
```
Profile
- dev, none

1. 실행
./gradlew bootrun

# Using IntelliJ
1. Sync gradle
2. Run Application

2. Test 
./gradlew test
```

### <a name="chapter-3"></a>Requirement 
````
- 상품은 고유의 상품번호와 상품명, 판매가격, 재고수량 정보를 가지고 있습니다**
    - 단, 재고수량 개념은 키트 상품에만 존재하고, 클래스의 경우 무제한을 표현하기 위해 재고수를 99999 로 가정합니다
- 한 번에 여러개의 상품을 같이 주문할 수 있어야 합니다**
- 상품번호, 수량은 반복적으로 입력 받을 수 있습니다**
    - 단, 클래스의 경우는 한번의 결제에 동일한 클래스 1개 수량만 주문이 가능합니다
    - 동일 클래스 추가 주문 시 중복 주문하는 것을 막아주어야 합니다
    - 결제가 완료되고, 다음 주문에선 이전에 결제와 무관하게 클래스 주문이 되어야 합니다
- 주문은 상품번호, 수량을 입력받습니다**
    - empty 입력 (space + ENTER)이 되었을 경우 해당 건에 대한 주문이 완료되고, 결제하는 것으로 판단합니다
    - 결제 시 재고확인을 하여야 하며 재고가 부족할 경우 결제를 시도하면 **SoldOutException**이 발생되어야 합니다
- 키트 상품의 경우, 주문 금액이 5만원 미만일 경우 배송료 5,000원이 추가되어야 합니다**
    - 클래스와 키트를 함께 주문할 시 배송료는 포함되지 않아야 합니다
    - [상품번호 9236 하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누]를 1개 구입할 경우에는 9,900 * 1개 + 5,000원이 되어야 합니다
    - [상품번호 45947 일러스트레이터 집시의 매력적인 얼굴 그리기] 와 [상품번호 91008 작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트] 1개를 구입할 경우에는 249,500 + 28,000이 되어 5만원이 넘어가므로 배송료는 추가되지 않아야 합니다
- 주문이 완료 되었을 경우, 주문 내역과 주문 금액, 결제 금액 (배송비 포함) 을 화면에 display 합니다**
- 'q'를 입력하면 프로그램이 종료되어야 합니다**
- Test에서는 반드시 multi thread 요청으로 SoldOutException이 정상 동작하는지 확인하는 단위테스트가 작성 되어야 합니다**
- 상품의 데이터는 하단에 주어지는 데이터를 사용해주세요**
    - 데이터를 불러오는 방식은 자유입니다
    - 코드에 포함이 되어도 되고, 파일을 불러도 되고, in memory db를 사용하셔도 됩니다.
    - 하지만 상품에 대한 상품번호, 상품명, 가격, 재고 데이터는 그대로 사용하셔야 합니다.
````

### <a name="chapter-4"></a>Strategy 
```` 
- DDD(Domain Driven Design) 적용
- Product, Kit, Class 도메인으로 분리 
    - JPA fetch join 적용
...
````

### <a name="chapter-5"></a>Domain 
```
상품(Product)
   상품번호
   상품명

클래스(Klass)
   판매가격
   재고수량

키트(Kit)
   판매가격
   재고수량   
```

## <a name="chapter-6"></a>Entity
```
상품(Product)
   상품번호
   상품명

클래스(Klass)
   판매가격
   재고수량

키트(Kit)
   판매가격
   재고수량
```

### <a name="chapter-7"></a>Better Planning 
```
...
```