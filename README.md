<div align="center">

<br />

<img src="https://raw.githubusercontent.com/dennis0324/jebi/main/src/main/resources/images/logo-horizontal.png" alt="갈피끈 로고"><br>

[![버전 배지](https://img.shields.io/github/v/release/dennis0324/jebi)](https://github.com/dennis0324/jebi/releases)
[![코드 크기 배지](https://img.shields.io/github/languages/code-size/dennis0324/jebi?color=brightgreen)](https://github.com/dennis0324/jebi)
[![라이선스 배지](https://img.shields.io/github/license/dennis0324/jebi)](https://github.com/dennis0324/jebi/blob/main/LICENSE)

'갈피끈'은 [JavaFX](https://openjfx.io/)와 [Cloud Firestore](https://firebase.google.com/docs/firestore)로 만든 도서 관리 소프트웨어입니다.

</div> <br />

## 주요 기능

- 로그인, 회원 가입, 이메일 및 비밀번호 찾기 기능 지원
- 사용자 계정을 일반 계정과 관리자 계정으로 분류, 일반 계정에는 제한된 기능만 부여
- 관리자 계정을 통한 사용자 및 도서 데이터를 실시간으로 생성, 수정 및 삭제 가능

## 스크린샷

### `로그인` 기능
<details>
  <summary>스크린샷 보기</summary>

  ![image](https://user-images.githubusercontent.com/50710829/173194665-ffa64501-52aa-4c34-9e66-578c5aadc0f8.png)
</details>

### `회원 가입` 기능
<details>
  <summary>스크린샷 보기</summary>
  
  ![image](https://user-images.githubusercontent.com/50710829/173194676-53a3a273-2aa7-42a2-a874-506718f98853.png)
</details>

### `도서 대출` 기능
<details>
  <summary>스크린샷 보기</summary>
  
  ![image](https://user-images.githubusercontent.com/50710829/173194694-25735577-1d59-4176-b6d1-19ed4ed333d4.png)
</details>

### `사용자 관리` 기능
<details>
  <summary>스크린샷 보기</summary>
  
  ![image](https://user-images.githubusercontent.com/50710829/173194703-3d94e2f5-168f-4943-acee-c7e04f581368.png)
</details>

### `사용자 정보 수정` 기능
<details>
  <summary>스크린샷 보기</summary>
  
  ![image](https://user-images.githubusercontent.com/50710829/173194718-c9ffa4d4-3bd7-4110-a491-9e1a3cb17f6d.png)
</details>

### `도서 정보 수정` 기능
<details>
  <summary>스크린샷 보기</summary>
  
  ![image](https://user-images.githubusercontent.com/50710829/173194713-07f1b281-4cf7-4cc6-9e0d-9d9e3b09d78c.png)
</details>

## 빌드 방법

```console
$ git clone https://github.com/dennis0324/jebi && cd jebi
$ mvn compile package
```

## 라이선스

GNU General Public License, version 3

```
Copyright (c) 2022, Dennis Ko (https://github.com/dennis0324)
Copyright (c) 2022, Jaedeok Kim (https://github.com/jdeokkim)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <https://www.gnu.org/licenses/>.
```