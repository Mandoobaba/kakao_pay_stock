개발 환경
------------------
* Java 8
* Gradle 5.6.4
* Spring Boot 2.2.6
* Lombok
* mybatis
* AWS EC2 - Ubuntu 16.04.6 LTS (DB Server)
* MySql-server
* JUnit5
* Mockito

DB 테이블 구조
================
>kakaoPayStock
> >Tables
> > >account_list_tbl (데이터_계좌정보.csv 데이터 테이블)   
> > >branch_list_tbl (데이터_관리점정보.csv 데이터 테이블)   
> > >branch_merge_info_tbl (분당점의 판교점으로의 관리점 이관 정보를 담는 테이블)   
> > >trade_list_tbl (데이터_거래내역.csv 데이터 테이블)   

API 조회 URL
================
* 1번 API URL : GET  /trade/account/1
* 2번 API URL : GET  /trade/account/2
* 3번 API URL : GET  /trade/branch/1
* 4번 API URL : GET  /trade/branch/2?brName=#{지점명}

문제 해결 방법
=================
* In Memory DB 테이블 구성을 위해 각 테이블의 Engine을 Memory로 선언
* 생성된 테이블에 .csv Import로 주어진 데이터 삽입
* 4번 API의 분당점-판교점 통폐합 처리를 위해 branch_merge_info_tbl 정규 테이블 추가, 분당점의 거래/계좌 데이터를 판교점의 데이터로 조회하기 용이하도록 함
* 4번 API의 brName 검색 결과가 없는(분당점) 조회 시, HasNoBrCodeException를 발생시켜 선언해놓은 Handler에서 요구사항 처리하도록 개발

실행방법
=================
test.zip 다운로드 후 Gradle Project Import 하여 spring boot 앱 실행(localhost:8080)



