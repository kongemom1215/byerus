# 프로젝트 소개
>Bye-rus는 2020년에 등장한 COVID-19 바이러스 감염증에 예방하기 위한 방역 용품을 판매하는 쇼핑몰 사이트입니다.

## 역할
+ 임주혜(GOD) - 조장, 관리자 기능
+ 박용수(DRAGON) - 검색, 메인화면, 찜, 통합
+ 김반야(HALF) - 상품 목록, 상세페이지, 레이아웃 설계
+ 윤홍주(RED) - 장바구니, 결제창
+ 이정윤(HEART) - 마이페이지
+ 김희수(WATER) - 로그인, 게시판

## 개발환경 / 사용 언어
![image](https://user-images.githubusercontent.com/72897088/107852846-388dec00-6e56-11eb-8f28-b0bd106c7181.png)

## 프로젝트 제공 기능
1. 관리자 by 임주혜
  + 회원관리
    - 회원 정보 조회/수정/삭제/등록/검색
    - 회원 검색 - 회원번호/회원 이름/이메일 옵션 선택 후 검색값 입력
    - 쿠폰 발급 - 전체회원, 특정회원인지 선택후 쿠폰 등록
  + 상품관리
    - 상품 정보 조회/수정/검색/삭제/등록
    - 상품 모든 정보 수정 - 이미지, 이름 등등
  + 게시판관리
    - 공지 게시판 작성/수정/삭제
    - 문의, 리뷰 게시판 댓글 작성/수정/삭제
  + 주문&배송 관리
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
등록된 관리자 계정으로 로그인
![image](https://user-images.githubusercontent.com/72897088/107858503-ca5a2100-6e77-11eb-949f-b89cf06a7c63.png)
상단의 관리자페이지로 이동할 수 있는 메뉴 오픈

### 관리자 페이지
![image](https://user-images.githubusercontent.com/72897088/107858521-e493ff00-6e77-11eb-8b7a-03ece4d7f857.png)




