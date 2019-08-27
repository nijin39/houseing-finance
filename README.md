<!--
*** Thanks for checking out this README Template. If you have a suggestion that would
*** make this better, please fork the repo and create a pull request or simply open
*** an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->





<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Architecture](#architecture)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)



<!-- ABOUT THE PROJECT -->
## About The Project

 해당 어플리케이션은 국낸 주택금융 신용보증 기관으로부터 년도별 각 금융기관(은행)에서 신용보증한 금액을 제공 받아. 현재 현황에 대한 리포팅 및 검색 기능과 과거의 데이터를 기반으로 하여 미래의 특점시점에 신용보증 금액에 대한 예측을 수행하는 것이 목표입니다.

### Built With
* [Spring Boot](https://getbootstrap.com)
* [React](https://jquery.com)
* [Spring Data Cache](https://laravel.com)
* [JJWT](https://laravel.com)
* [Lombok](https://laravel.com)
* [Springfox-Swagger2](https://laravel.com)
* [qlrm](https://laravel.com)
* [assertj-core](https://laravel.com)
* [apache common math](https://laravel.com)


<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

* yarn 
```sh
yarn install
```

### Installation

1. Clone the repo
```sh
https://github.com/nijin39/houseing-finance.git
```
2. Start Backend 
```sh
./gradlew bootRun
```
3. Start Frontend
```sh
cd client; yarn start
```
4. Build & Packging
```sh
./gradlew build buildClient
```



<!-- USAGE EXAMPLES -->
## Usage

### JWT Token

#### 1. Registration Account
```sh
curl -X POST \
  http://localhost:8080/register \
  -H 'content-type: application/json' \
  -d '{
	"username":"nijin39",
	"password":"korea123"
}'
```

#### 2. Singin(publishing token)
```sh
curl -X POST \
  http://localhost:8080/authenticate \
  -H 'content-type: application/json' \
  -d '{
	"username":"nijin39",
	"password":"korea123"
}'
```

#### 3. Refresh Token
```sh
curl -X PUT \
  http://localhost:8080/refreshToken \
  -H 'authorization: Bearer Token eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaWppbjM5IiwiZXhwIjoxNTY2OTMyNzE0LCJpYXQiOjE1NjY5MTQ3MTR9.uFsurKI8VwbE02vz1vdQJNAN7b4_dnorG-5-8CX-C-kCXtFb7bHxWKInV7vtq6y4hNg2h8y6l8bQg44-RrRowA' \
  -H 'content-type: application/json' \
  -d '{
	"username":"nijin39",
	"password":"korea123"
}'
```

### API

#### 1. Upload CSV(테스트완료)
```sh
curl -X POST \
  http://localhost:8080/api/upload \
  -H 'authorization: Bearer  eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaWppbjM5IiwiZXhwIjoxNTY2OTYxNTE2LCJpYXQiOjE1NjY5NDM1MTZ9.I0QEqKZ8lD1iZMw-NkmneW1E14aIgvuIakdnEsyejvl-lRiHGy5VgNqifO1D32_UJ944qx_SBIEN_w9IPdYKpA' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F file=@aa.csv \
  -F charset=UTF-8
```

#### 2. Show all institute(테스트완료)
```sh
curl -X GET \
  http://localhost:8080/api/institutes \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaWppbjM5IiwiZXhwIjoxNTY2NjgxMjI4LCJpYXQiOjE1NjY2NjMyMjh9.A87NDjDHW6x_0AE6IcZMmTiczbIHnHHx-fmDsnhnCGyQYcSE67hPL4Lx4v7EWO6aDBv5w9AzbiErQVkzS_vfhg' \
```

#### 3. Show Annual Report(테스트완료)
```sh
curl -X GET \
  http://localhost:8080/api/creditGuarantee/annualReport \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaWppbjM5IiwiZXhwIjoxNTY2NjgxMjI4LCJpYXQiOjE1NjY2NjMyMjh9.A87NDjDHW6x_0AE6IcZMmTiczbIHnHHx-fmDsnhnCGyQYcSE67hPL4Lx4v7EWO6aDBv5w9AzbiErQVkzS_vfhg' \
```

#### 4. Max Amount Institute(테스트완료)
```sh
curl -X GET \
  http://localhost:8080/api/creditGuarantee/institute/max-amount \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaWppbjM5IiwiZXhwIjoxNTY2NjgxMjI4LCJpYXQiOjE1NjY2NjMyMjh9.A87NDjDHW6x_0AE6IcZMmTiczbIHnHHx-fmDsnhnCGyQYcSE67hPL4Lx4v7EWO6aDBv5w9AzbiErQVkzS_vfhg' \
  ```

#### 5. Average Credit Guarantee by institute(테스트 완료)
```sh
curl -X GET \
  http://localhost:8080/api/creditGuarantee/institute/%EC%99%B8%ED%99%98%EC%9D%80%ED%96%89/max-min-average \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaWppbjM5IiwiZXhwIjoxNTY2OTM3NzQ4LCJpYXQiOjE1NjY5MTk3NDh9.4iKm9Vhdh7fNq_F2wEcu__IXNn42XemC0GilPb4b_H1o19vyv4EnNDCJgeAcSQ8MgUQqvFDLqDSjopq8gL4SZQ' \
  -H 'content-type: application/json' \
  -d '{
	"username":"nijin39",
	"password":"korea123"
}'
```

#### 6. Credit Guarantee predicat(테스트 완료)

```sh
curl -X POST \
  http://localhost:8080/api/creditGuarantee/predicate \
  -H 'authorization: Bearer  eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaWppbjM5IiwiZXhwIjoxNTY2OTYyMDczLCJpYXQiOjE1NjY5NDQwNzN9.Ab9HY1eY3RDbnxVSXRAwX9L4od6Cq5R_SltHasdPpMHqP9pMpIT59EAf3s6-Adj9uxS5hmuv4rk_CIO06e_n-w' \
  -H 'content-type: application/json' \
  -d '{
	"bank":"국민은행",
	"month" : 3
}'
```


<!-- Archetecure -->
## Architecture

### Package

패키징은 기본적으로 DDD Architecture 가이드를 따라 설계되었으며, 아래와 같이 구성된다. 금융기관은 별도로 모델링 되어지도록 제약이 주어졌다.
creditguarantee(주택금융 공급)에는 통계 원본 데이터가 적재되며, 추후 연산 요청이 많은 경우를 대비하여 통계에 대한 별도 서머리 엔티디를 만들었다.

CreditGuarantee Domain에 대해 주로 요청되어지는 작업이 조회성 작업이기 때문에 효율성을 고려하여 CQRS를 적용하였다.
```
.
└── housingfinance
    ├── account(계정)
    │   ├── application
    │   ├── domain
    │   └── ui
    ├── admin(Admin Client)
    │   └── ui
    ├── common
    │   ├── config
    │   ├── domain
    │   └── util
    ├── creditguarantee(주택금융 공급)
    │   ├── command
    │   ├── infra
    │   ├── query
    │   └── ui
    ├── creditguaranteesummary(주택금융 공급 통계)
    │   ├── application
    │   ├── domain
    │   ├── infra
    │   └── ui
    ├── HousingFinanceApplication.java
    └── institute(금융기관)
        ├── application
        ├── domain
        ├── infra
        └── ui
```

### DDD

institute / creditguarantee / creditguaranteesummary를 각각의 도메인으로 정리 하였고, JPA의 관계 설정에서 일어나는 문제(eager, lazy loding, 그래프 검색의 남용에 따른 도메인 로직의 응집도 저하)를 막기 위해 다른 도메인을 참조할 때는 ID 참조를 이용하였다. 또한 creditguarantee, creditguaranteesummary에서는 비즈니스의 의도가 드러나도록 Composite Key를 사용하였다.

### CQRS

JPA를 사용한다 하여도, 명령(Command), 조회(Query)에 대해서 효율적인 모델링을 적용할 수 있다. 아래와 같이 동일한 도메인에 대해서 Command, Query에 따라 별도의 모델링을 적용하여 유연하게 시스템을 설계하고자 하였다.  
```
├── command
│   ├── application
│   └── domain
├── infra
│   ├── CreditGuaranteeJpaRepository.java
│   └── CreditGuaranteePredicateApache.java
├── query
│   ├── application
│   ├── dao
│   └── dto
└── ui
    └── CreditGuaranteeRestController.java
```

### Domain Event

현재 어플리케이션에 조회성 기능이 많고 동시에 조회성 기능에 대한 요구가 증가했을 경우 시스템의 부하에 관한 문제를 일으킬 수 있기 때문에
데이터의 변경이 있을 경우에 통계 도메인을 업데이트 하게 하였다. 이 두 도메인의 연동은 JPA의 도메인 이벤트를 활용하여 결합도를 낮추었다.
```
    @DomainEvents
    Collection<Object> domainEvents() {
        List<Object> result = new ArrayList<Object>();
        result.add(new CreditGuaranteeSavedEvent(this));
        return result;
    }
    
    @EventListener
    public void creditGuaranteeSavedEventHandler(CreditGuaranteeSavedEvent event) {
```

<!-- ROADMAP -->

## Roadmap

### 예측 모델의 고도화
### React UI 개발

## TODO
- [X] 개발환경 구성 : Spring Boot2, React
- [X] 요구사항 분석 및 Domain 파악
- [X] 각 모듈에 대한 패키지 구성
  - [X] Institute : 금융기관
  - [X] CreditGuarantee : 주택금융지원액
  - [X] CreditGuaranteeSummary : 주택금융지원통계
  - [X] Admin : 사용자 UI
  - [X] Account : 사용자 계정 관련
- [X] 도메인/도메인 서비스에 대한 단위 테스트 적용
- [X] CreditGuarantee CQRS 패턴 적용
- [X] CreditGuarantee qlrm을 통한 조회모델 적용
- [X] 예측모델 적용(Lianer Reggression)
- [X] Jwt 인증 적용
  - [X] refresh 
- [X] 통합테스트 
- [X] 예측모델 형변환
- [X] Readme 


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Kim Jong IL - [@nijin39](https://twitter.com/nijin39) - nijin39@gmail.com

Project Link: [https://github.com/nijin39/houseing-finance](https://github.com/nijin39/houseing-finance)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [ddd-cqrs](https://nesoy.github.io/articles/2018-07/DDD-CQRS)
* [Json Web Token](https://velopert.com/2389)
* [Linear Regression](http://woowabros.github.io/study/2018/08/01/linear_regression_qr.html)





<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/nijin39/houseing-finance.svg?style=flat-square
[contributors-url]: https://github.com/nijin39/houseing-finance/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/nijin39/houseing-finance.svg?style=flat-square
[forks-url]: https://github.com/nijin39/houseing-finance/network/members
[stars-shield]: https://img.shields.io/github/stars/nijin39/houseing-finance.svg?style=flat-square
[stars-url]: https://github.com/nijin39/houseing-finance/stargazers
[issues-shield]: https://img.shields.io/github/issues/nijin39/houseing-finance.svg?style=flat-square
[issues-url]: https://github.com/nijin39/houseing-finance/issues
[license-shield]: https://img.shields.io/github/license/nijin39/houseing-finance.svg?style=flat-square
[license-url]: https://github.com/nijin39/houseing-finance/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png

