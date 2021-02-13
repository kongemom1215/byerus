# 프로젝트 소개
_Bye-rus는 2020년에 등장한 COVID-19 바이러스 감염증에 예방하기 위한 방역 용품을 판매하는 **쇼핑몰 사이트**입니다._

> 개발 기간 : 2020.12.28~2021.01.22(26일)

> 개발 인원 : 6명

## 목차
[1. 역할](#역할)

[2. 개발환경/사용언어](#개발환경사용언어)

[3. 프로젝트 제공 기능](#프로젝트-제공-기능)

[4. 시스템 프로세스](#시스템-프로세스)

[5. ERD](#ERD)

[6. 화면 구현](#화면)

[7. 코드 위치](#코드-위치)

[8. 주요기능 코드 설명](#주요기능-코드-설명)

## 역할
+ **임주혜(GOD) - 조장, 관리자 기능**
+ 박용수(DRAGON) - 검색, 메인화면, 찜, 통합
+ 김반야(HALF) - 상품 목록, 상세페이지, 레이아웃 설계
+ 윤홍주(RED) - 장바구니, 결제창
+ 이정윤(HEART) - 마이페이지
+ 김희수(WATER) - 로그인, 게시판

## 개발환경/사용언어
![image](https://user-images.githubusercontent.com/72897088/107852846-388dec00-6e56-11eb-8f28-b0bd106c7181.png)

## 프로젝트 제공 기능
[1. 관리자 by 임주혜](#관리자-페이지)
  + [회원관리](#회원-관리-페이지)
    - 회원 정보 조회/수정/삭제/등록/검색
    - 회원 검색 - 회원번호/회원 이름/이메일 옵션 선택 후 검색값 입력
    - 쿠폰 발급 - 전체회원, 특정회원인지 선택후 쿠폰 등록
  + [상품관리](#상품-관리-페이지)
    - 상품 정보 조회/수정/검색/삭제/등록
    - 상품 모든 정보 수정 - 이미지, 이름 등등
  + [게시판관리](#게시판-관리-페이지)
    - 공지 게시판 작성/수정/삭제
    - 문의, 리뷰 게시판 댓글 작성/수정/삭제
  + [주문&배송 관리](#결제-관리-페이지)
    - 결제 정보 조회 - 검색 or 목록을 통해
    - 배송 정보 수정 - ex) 결제 대기중 -> 결제완료
    - 매출 조회(일/월/연도별) - 구글 차트 API 이용
2. 회원가입, 마이페이지
  + 회원 탈퇴
    - 회원정보 수정 및 회원 탈퇴
  + 마이페이지
    - 내가 쓴 글 조회(문의, 리뷰)
    - 리뷰 작성 가능여부 조회
    - 리뷰 작성
    - 구매 내역, 주문자 정보 조회
    - 결제 완료 전 주문 취소
    - 배송 완료 후 구매 확정
    - 보유 쿠폰 조회
3. 로그인, 게시판
  + 회원가입
    - 우편 번호 api 적용
    - 이름, 생년월일, 전화번호, 주소, 이메일 수집
  + 로그인
    - 이메일, 비밀번호 찾기
  + 게시판
    - 문의, 리뷰 게싶판 작성/조회/수정/삭제
4. 장바구니, 결제창
  + 장바구니
    - 제품상세 -> 장바구니 담기
    - 장바구니 화면 구현
    - 전체 삭제, 선택 삭제, 선택
  + 결제창
    - 바로구매(장바구니 없이 결제)
    - 장바구니를 통한 결제
    - 주문정보 조회
    - 쿠폰 적용
    - 결제수단 선택
    - 결제완료 후 주문 정보 확인
5. 검색, 메인, 통합
  + 상품 검색
    - 상품명 검색, 초성 검색, 정렬
  + 메인
    - 인기 제품 슬라이드
    - 코로나 api
  + 통합
    - 각 페이지 공통 상단 메뉴바 제작
    - 프로젝트 통합
  + 기타 기능
    - 찜하기 
6. 상품목록, 상세, 레이아웃
  + 상품 분류
    - 상품 카테고리별 분류
    - 상품 정렬(최신순, 판매량순, 가격 오름차순/내림차순)
  + 상품 상세
    - 옵션유무/할인여부 노출
    - 해당 상품의 문의/리뷰글 조회
    - 해당 상품과 같은 카테고리의 인기 상품 조회
    - "내가 본 상품" 리모콘 기능
    - 로그인여부/해당상품 구매여부/해당 상품 리뷰 작성여부 판별
  + 레이아웃
    - 각 페이지 레이아웃 설계
    
## 시스템 프로세스
![image](https://user-images.githubusercontent.com/72897088/107854541-ce7a4480-6e5f-11eb-92d8-de9ef23d890c.png)

## ERD
![image](https://user-images.githubusercontent.com/72897088/107854556-f7023e80-6e5f-11eb-9e31-78f0b3259b01.png)

## 화면
### 메인페이지
![image](https://user-images.githubusercontent.com/72897088/107857953-d4c6eb80-6e74-11eb-822f-e618fe648be1.png)

### 로그인페이지
![image](https://user-images.githubusercontent.com/72897088/107857970-f6c06e00-6e74-11eb-86a9-20963d686d4d.png)

### 상품 목록 페이지
![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/72897088/107858167-27ed6e00-6e76-11eb-941e-633b7c3ae8aa.gif)

### 상품 상세 페이지
![image](https://user-images.githubusercontent.com/72897088/107858201-59fed000-6e76-11eb-94bd-1aa2071b1072.png)

### 장바구니 페이지
![image](https://user-images.githubusercontent.com/72897088/107858230-7dc21600-6e76-11eb-877d-615853feddd2.png)

### 결제 페이지
![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/72897088/107858294-e8735180-6e76-11eb-9616-872c16f00840.gif)

### 게시판 페이지
![image](https://user-images.githubusercontent.com/72897088/107858399-4bfd7f00-6e77-11eb-872a-c4e2241166c1.png)

### 마이페이지
![image](https://user-images.githubusercontent.com/72897088/107858444-77806980-6e77-11eb-839b-f71845eb511e.png)

## 관리자 페이지 
### 로그인 > 메인
![image](https://user-images.githubusercontent.com/72897088/107858484-b0204300-6e77-11eb-91c0-c2db9625e03c.png)
```
등록된 관리자 계정으로 로그인
```
![image](https://user-images.githubusercontent.com/72897088/107858503-ca5a2100-6e77-11eb-949f-b89cf06a7c63.png)
```
상단의 관리자페이지로 이동할 수 있는 메뉴 오픈
```
### 관리자 페이지
![image](https://user-images.githubusercontent.com/72897088/107858521-e493ff00-6e77-11eb-8b7a-03ece4d7f857.png)
```
회원관리/상품관리/게시판관리/결제관리 로 나눠짐
```

### 회원 관리 페이지
![image](https://user-images.githubusercontent.com/72897088/107858669-99c6b700-6e78-11eb-8447-dbd37772a328.png)
```
회원 조회 페이지
> 전체 회원을 조회 기능, 가입한 회원 수 확인 기능, 페이징 기능
> 회원 번호, 회원 이름, 이메일을 선택해서 검색하여 회원 정보를 조회할 수 있다
```

#### 회원 조회 페이지
![image](https://user-images.githubusercontent.com/72897088/107858769-37ba8180-6e79-11eb-97e2-ad4328781307.png)
```
- 회원 상세 정보 조회가능
- 새 창으로 해당 회원의 쿠폰 조회 가능
- 회원 정보 수정과 삭제 기능
```
#### 회원 검색 기능
![image](https://user-images.githubusercontent.com/72897088/107858847-b0214280-6e79-11eb-9fb9-7db90b27667f.png)
```
'2'를 검색하면 회원 번호에 2가 포함된 회원들 목록을 조회할 수 있다
```

#### 회원 추가 페이지
![image](https://user-images.githubusercontent.com/72897088/107859285-e069e080-6e7b-11eb-81c7-c230d4f820f0.png)
```
회원가입시 필요한 정보 입력
```
![ezgif com-gif-maker (6)](https://user-images.githubusercontent.com/72897088/107861230-dbab2980-6e87-11eb-800a-dbbdfae80c8d.gif)
![ezgif com-gif-maker (7)](https://user-images.githubusercontent.com/72897088/107861232-de0d8380-6e87-11eb-9062-6e38e3da7e4b.gif)
```
- 이메일 입력 후 중복체크 확인 기능
- 다음 우편주소 API를 이용한 주소검색
```

### 쿠폰 등록 페이지
![ezgif com-gif-maker (5)](https://user-images.githubusercontent.com/72897088/107861174-532c8900-6e87-11eb-909a-508cb444d3e3.gif)
```
- 전체회원/특정회원 선택해서 쿠폰 등록 
- 특정회원인 경우 회원번호를 , 로 구분해서 입력
- 날짜선택은 Jquery UI datepicker 사용
- 쿠폰 이미지 선택해서 등록
```

### 상품 관리 페이지
![image](https://user-images.githubusercontent.com/72897088/107859624-f5e00a00-6e7d-11eb-9c8b-01f17b4b4ea0.png)
```
상품 조회 페이지
> 전체 상품을 조회 기능, 등록된 상품 수 확인 기능, 페이징 기능
> 상품 번호, 상품 이름을 선택해서 검색하여 상품 정보를 조회할 수 있다
```

#### 상품 상세 페이지
![image](https://user-images.githubusercontent.com/72897088/107861266-32186800-6e88-11eb-880f-2d3687348aa3.png)
```
[상품 상세 정보 열람 기능]
썸네일 '조회' 버튼 > 새창으로 이미지 열기
썸네일 '다운로드' 버튼 > 이미지 다운로드 기능
상세이미지 '페이지로확인' 버튼 > 회원들이 열람하는 상품 상세 페이지로 이동
```

#### 상품 수정 페이지
![ezgif com-gif-maker (8)](https://user-images.githubusercontent.com/72897088/107861450-119cdd80-6e89-11eb-8fe2-882a3482d9e4.gif)
```
상품 전체 정보 수정 페이지
상품 상세 페이지는 썸네일 이미지와 상세 이미지로 구성되어있음
해당 이미지 삭제 및 이미지 변경 가능
```

#### 상품 추가 페이지
![image](https://user-images.githubusercontent.com/72897088/107861473-401ab880-6e89-11eb-8054-c427aaeacd3c.png)
```
상품을 등록하는 페이지
```

### 게시판 관리 페이지
![image](https://user-images.githubusercontent.com/72897088/107861487-693b4900-6e89-11eb-93b3-496b956a71ea.png)
```
공지 게시판 - 글 조회, 작성, 수정, 삭제
문의 게시판 - 글 조회, 댓글 작성/수정/삭제
리뷰 게시판 - 글 조회, 댓글 작성/수정/삭제
```
![image](https://user-images.githubusercontent.com/72897088/107861544-ccc57680-6e89-11eb-84af-90b584e50f38.png)
```
공지게시판
비공개 글은 제목과 (비공개)가 함께 출력
```

#### 공지게시판 글 작성 페이지
![image](https://user-images.githubusercontent.com/72897088/107861602-3fceed00-6e8a-11eb-9792-b84b4ba173ae.png)
```
제목, 내용, 파일삽입, 공개여부 설정 기능
```


#### 공지게시판 글 조회 페이지
![image](https://user-images.githubusercontent.com/72897088/107861653-9b997600-6e8a-11eb-86e0-b43da40b7283.png)
```
글 정보와 이전 게시글, 다음 게시글 이동 버튼
글 수정, 삭제 기능
```

#### 문의 게시판 관리 페이지
![image](https://user-images.githubusercontent.com/72897088/107861712-e1eed500-6e8a-11eb-8d1c-965febc009c3.png)
```
글 분류 - 전체 / 미답변 / 카테고리별
답변완료된 게시글은 제목과 [답변완료]가 함께 출력
```
![ezgif com-gif-maker (9)](https://user-images.githubusercontent.com/72897088/107861768-414ce500-6e8b-11eb-8f70-fe507bb79762.gif)
```
기본값 - 전체 문의(최신순)
미답변 - 답변이 안된 글 목록(최신순으로)
카테고리별 - 상품/결제/기타 선택 열람
```

#### 문의 게시판 글 조회 페이지
![image](https://user-images.githubusercontent.com/72897088/107861790-7f4a0900-6e8b-11eb-8a02-5f4ed225795e.png)
```
문의 글 정보 조회와 답변 기능
```
![ezgif com-gif-maker (10)](https://user-images.githubusercontent.com/72897088/107861909-5118f900-6e8c-11eb-955e-c2e38437afbb.gif)
```
답변 기능 - 답변 등록, 수정, 삭제
```

#### 리뷰 게시판 관리 페이지
![image](https://user-images.githubusercontent.com/72897088/107861947-9c330c00-6e8c-11eb-8fd7-c4798a64a674.png)
```
글 분류 - 전체 / 미답변 / 상품별
답변완료된 게시글은 제목과 [답변완료]가 함께 출력
```
![image](https://user-images.githubusercontent.com/72897088/107861959-b5d45380-6e8c-11eb-8c5d-2d90fd3c0288.png)
```
'상품별로 보기' 클릭 시 상품번호로 검색 기능
```

#### 리뷰 게시판 글 조회 페이지
![image](https://user-images.githubusercontent.com/72897088/107861978-ddc3b700-6e8c-11eb-94c7-d5da1deee7eb.png)
```
리뷰 상세 글 조회
답변 기능(문의 게시판과 동일)
```

### 결제 관리 페이지
![image](https://user-images.githubusercontent.com/72897088/107862068-87a34380-6e8d-11eb-8f40-a0eeff750bf5.png)
```
회원 번호, 주문 번호로 결제 내역 조회 가능
```

#### 결제 검색 페이지
![image](https://user-images.githubusercontent.com/72897088/107862097-b28d9780-6e8d-11eb-9830-64526230db44.png)
```
회원 검색시 해당 회원의 주문 정보 확인 가능
```
![image](https://user-images.githubusercontent.com/72897088/107862112-d51fb080-6e8d-11eb-9a66-4cc1d44fd341.png)
```
주문 번호 검색 시 주문 상세 정보 조회 가능
진행상태 변경 가능
ex) 입금대기중 -> 입금완료
    입금완료 -> 배송출발
    배송중 -> 배송완료
```

#### 결제 내역 전체 조회 페이지
![image](https://user-images.githubusercontent.com/72897088/107862168-4e1f0800-6e8e-11eb-806e-1b61e4c00de4.png)
```
주문 내역 분류 - 진행상태별/날짜별/상품별
```
![image](https://user-images.githubusercontent.com/72897088/107862181-65f68c00-6e8e-11eb-83a5-50f7e3357e64.png)
```
진행상태 선택시 해당 주문상태의 주문내역 출력
```
![image](https://user-images.githubusercontent.com/72897088/107862191-7b6bb600-6e8e-11eb-8a59-d2570e6d3e83.png)
```
날짜별 클릭 시 
날짜 선택 칸에 datepicker를 이용하여 검색 가능
```
![image](https://user-images.githubusercontent.com/72897088/107862205-91797680-6e8e-11eb-92ea-6262588e0e21.png)
```
상품별 클릭시
상품번호로 검색 가능
```

#### 결제 내역 상세 조회 페이지
![ezgif com-gif-maker (11)](https://user-images.githubusercontent.com/72897088/107862250-e9b07880-6e8e-11eb-96c7-f04ea03afed8.gif)
```
주문정보, 수령자 정보, 상품 주문내역 확인 가능
```

### 매출 조회 페이지
![image](https://user-images.githubusercontent.com/72897088/107862285-14023600-6e8f-11eb-8c80-01414b252a97.png)
```
GOOGLE CHART API를 이용
일별/월별/연도별 매출액을 시각적으로 표현하기 위한 그래프 출력
```
![image](https://user-images.githubusercontent.com/72897088/107862303-401db700-6e8f-11eb-9b00-017cea88bad2.png)
```
그래프 바에 마우스를 올리면 상세 매출액 확인 가능
```

## 코드 위치
![image](https://user-images.githubusercontent.com/72897088/107864364-df967600-6e9e-11eb-8e46-c03464de129b.png)
![image](https://user-images.githubusercontent.com/72897088/107864369-ef15bf00-6e9e-11eb-85b5-26edb0c13072.png)
![image](https://user-images.githubusercontent.com/72897088/107864381-f8069080-6e9e-11eb-8c02-7104c0061cef.png)
![image](https://user-images.githubusercontent.com/72897088/107864385-fe950800-6e9e-11eb-9a97-5568f923e191.png)

## 주요기능 코드 설명
