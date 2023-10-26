

# The A Team 쇼핑몰 프로젝트

이 프로젝트는 신선한 식품을 제공하는 EzMeal 식품 쇼핑몰의 웹사이트를 목표로 진행되었습니다.


## 목차
1. 프로젝트 정보
2. 맡은 역할
3. 기능설명
4. 개발 환경 및 기술 스택
5. 맡은 역할
6. 시연 영상 또는 스크린샷
7. 문제 해결 및 배운 점
8. 기타

## 프로젝트 정보
- 프로젝트 기간: 2023년 5월 30일 - 2023년 8월 6일 (총 69일)

- 팀 구성: 
  - 백엔드: 권태완(팀장), 홍형주(부팀장), 백현욱
  - 프론트엔드: 이나경


## 맡은 역할

- 홍형주(부팀장)
  - 회원 및 적립금/쿠폰 ERD 개발
  - 상품 관련 페이지의 백엔드 개발
  - 상품 관련 페이지의 프론트엔드 개발
  - 관리자 상품 리스트 및 상품 등록 페이지의 백엔드 개발
  - 관리자 공통 UI의 프론트엔드 개발
  - 팀 노션 작성 및 관리


## 기능 설명

- 회원 관리(ERD 개발)
  - 회원가입, 로그인, 프로필 관리 등의 데이터 구조 설계
- 적립금 & 쿠폰 관리(ERD 개발)
  - 적립금 및 쿠폰의 데이터 구조 설계
- 상품 관련 페이지(백엔드 및 프론트엔드 개발)
  - 상품 목록, 상세 정보 페이지, 메인 추천 상품 페이지 구현
- 관리자 인터페이스(백엔드 및 프론트엔드 개발)
  - 백엔드 및 프론트엔드 개발: 상품 리스트 및 상품 등록 페이지 구현
  - 프론트엔드 개발: 공통 UI 구현

## 개발 환경 및 기술 스택

- Backend
  - Java(11)
  - Spring(5.2.21)
  - MyBatis
  - MySQL
- Frontend
  - HTML/CSS
  - JavaScript ES6
  - JSP
- Server
  - Tomcat


## 시연 영상 또는 스크린샷

### 1. 회원 관리(ERD 개발)
### 2. 적립금 & 쿠폰 관리(ERD 개발)
  - ![데이터 구조 설계(노란색 테이블)](https://github.com/HongHyeongJu/ezmeal/readme_src/ezmeal_ERD.png?raw=true)

### 3. 상품 관련 페이지(백엔드 및 프론트엔드 개발)
- **기능 설명**
  - 상품 목록, 상세 정보 페이지, 메인 추천 상품 페이지 구현
- **시연 gif 및 영상**
  - ![메인 추천 상품/상품 상세](https://github.com/HongHyeongJu/ezmeal/readme_src/MainRecommendedProductAndDetails.gif?raw=true)
  - ![주제별 상품 목록](https://github.com/HongHyeongJu/ezmeal/readme_src/ListOfProductsBySubject.gif?raw=true)
  - ![관리자 상품 등록 -> 상품 담기 기능](https://github.com/HongHyeongJu/ezmeal/readme_src/ManagerProductRegistration.gif?raw=true)

### 4. 관리자 인터페이스(백엔드 및 프론트엔드 개발)
- **기능 설명**
  - 백엔드 및 프론트엔드 개발: 상품 리스트 및 상품 등록 페이지 구현
  - 프론트엔드 개발: 공통 UI 구현
- **시연 영상/스크린샷**
  - 관리자 상품 리스트, 관리자 상품 등록
  - ![관리자 공통 UI](https://youtu.be/ctJqvUV2_ec?si=aj0nRMensnjVfTmd&t=1389)

## 문제 해결 및 배운 점

- 데이터 처리 성능 개선
  - 문제: 클라이언트 단에서의 상품 정렬 속도 저하.
  - 해결: 서버 단에서 데이터를 먼저 정렬한 후 클라이언트로 전송.
  - 배운 점: 데이터 처리 위치에 따라 사용자 경험에 큰 영향을 준다.

- 옵션 상품 자동 생성 로직
  - 문제: 할인 적용을 위한 옵션 상품 관리의 번거로움.
  - 해결: 관리자 페이지에서 옵션 상품이 자동 생성되도록 로직 구현.
  - 배운 점: 사용자 편의를 위한 자동화 기능은 중요하다.

- CSS 충돌 문제
  - 문제: 협업 페이지 CSS 코드 사용으로 인한 검색창 크기 문제.
  - 해결: 개발자 도구와 소거법을 활용한 문제의 원인 파악 후 수정.
  - 배운 점: 공통코드 작성 시 유의점, 협업을 위한 사전 약속의 중요성.

## 기타
- 팀프로젝트 발표 영상
  - https://youtu.be/ctJqvUV2_ec?si=zRS-Yo95Hku__Q2x
  
- 팀프로젝트 발표 PPT
  - https://github.com/HongHyeongJu/ezmeal/readme_src/ezmeaPPT.pdf?raw=true

- 기능 구현 요약
  - https://github.com/HongHyeongJu/ezmeal/readme_src/ezmeaPPT_32.png?raw=true
