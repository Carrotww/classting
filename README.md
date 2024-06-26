# classting
classting assignment

# 프로젝트 환경
- java version 17
- spring boot 3.1.11
- Gradle

# 설치 방법
## 필수 조건
- java 17 설치
- git 설치

## 설치
1. 빈 파일 생성 후 프로젝트 클론
```bash
git clone https://github.com/Carrotww/classting.git
cd classting
```
2. 프로젝트 빌드
```bash
./gradlew build
```

3. 프로젝트 실행
```bash
java -jar build/libs/school-news-feed-0.0.1-SNAPSHOT.jar
```

# API 문서
## Swagger 사용 url - API Sheet
- 이 프로젝트의 API문서는 Swagger를 통해 제공됩니다. 프로젝트를 실행 후 아래 url로 접속해주세요.
http://localhost:8080/swagger-ui/index.html



# 데이터 모델 설계
1. 학교
- 목적 : 학교 정보 관리, 학교는 각각의 고유한 News를 발행할 수 있습니다.
- 필드 : Id, 이름, 지역

2. 학생
- 목적 : 학생 사용자의 정보를 관리, 학생은 여러 학교를 구독할 수 있으며 학교또한 여러 학생을 구독자로 가질 수 있습니다.
- 필드 : Id, 이름

3. 뉴스
- 목적 : 학교에서 발행하는 news 입니다. 뉴스는 특정 학교에 속하며 학교는 여러 뉴스를 발행할 수 있습니다.
- 필드 : Id, 제목, 내용, 발행 날짜, 수정 날짜, 속한 학교

4. 구독
- 목적 : 학생과 구독 중인 학교의 관계를 나타내는 중간 테이블 입니다.
- 필드 : Id, 학생, 학교, 구독 시작 시간

5. 학생 뉴스 피드
- 목적 : 학생별 맞춤 뉴스피드 구성을 위한 중간 테이블 입니다. 학생이 구독하는 학교의 뉴스를 학생의 뉴스 피드에 추가합니다.
- 필드 : Id, 학생, 뉴스, 피드에 추가된 시간

# 전반적인 구현 로직 설명

## 학교 및 뉴스 관리
- 학교 관리자는 학교 정보를 학교 이름, 학교 지역으로 생성, 수정, 삭제 할 수 있습니다. 이는 SchoolService 를 통해 관리됩니다.
- 학교에서 새로운 news를 발행할 수 있으며, 이 news는 해당 학교를 구독중인 학생들의 뉴스피드에 자동으로 추가됩니다. 뉴스 발행은 NewsService에서 처리됩니다.

## 구독 및 뉴스 피드
- 학생은 여러 학교를 구독할 수 있으며 이는 SubscriptionService 를 통해 관리됩니다. 학생이 특정 학교를 구독하게 되면 해당 학교의 뉴스가 학생의 뉴스 피드에 추가됩니다.
- 요구사항에 맞게 학생이 구독한 학교의 뉴스는 구독 이후 시점부터 포함되며 구독을 취소하더라도 이는 studentnewsfeed 테이블을 통해 남아있어 이전에 소식은 유지됩니다.

## 학생 기능
- 요구사항에는 없었으나 구독 및 학생의 뉴스 피드를 관리하기 위해 Student 도메인을 간단하게 만들어 학생 생성 로직을 추가하였습니다.

# DB 설계
- 학생과 학교의 다대다 관계를 Subscription(구독) 테이블을 통해 관리하였습니다.
- 사용자 맞춤형 뉴스 피드를 StudentNewsFeed 테이블을 통해 학생별 맞춤형 뉴스 피드를 구성할 수 있도록 하였습니다.