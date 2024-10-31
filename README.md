# 일정 관리 앱

## API 명세서


#### 1. 일정 생성 및 조회

##### 일정 생성
- **URL**: `/events`
- **Method**: `POST`
- **상태코드**: '200: 정상 등록, 400: 등록 실패'
- **요청**



  |이름|타입|설명|필수|
  |:----:|:----:|:-------:|:--:|
  |username|String|사용자 이름|O|
  |password|String|비밀번호 설정|O|
  |title|String|일정 제목|O|
  |content|String|일정 내용|X|

  ```json
  {
    "username": "허준", 
    "password": "복잡한 비밀번호",
    "title": "운동가기"
    "content": "일어나서 운동가기"
  }
- **응답**

  
  |이름|타입|설명|
  |:----:|:----:|:-------:|
  |message|String|일정 생성시 확인 알림 문구|
  |event_id|integer|일정 고유 아이디 생성|
  |title|String|제목 확인|
  |content|String|내용 확인|
  |create_date|datetime|일정 생성 날짜 저장|
  |upadte_date|datetime|일정 수정 날짜 저장|
  
  ```json
  {
    "message": "일정이 생성 되었습니다", 
    "event_id": "1234",
    "title": "운동가기"
    "content": "일어나서 운동가기"
    "create_date": "2024-10-31"
    "update-date": "2024-11-01"
  }

##### 전체 일정 조회
- **URL**: `/events`
- **Method**: `GET`
- **상태코드**: '200: 정상 조회, 400: 조회 실패'
- **요청**

  
  |이름|타입|설명|필수|
  |:----:|:----:|:-------:|:--:|
  |username|String|사용자 이름 확인|O|
  |password|String|비밀번호 확인|O|
  ```json
  {
    "username": "허준", 
    "update_date": "2024-10-31"
  }
- **응답**

  
  |이름|타입|설명|
  |:----:|:----:|:-------:|
  |event_id|integer|일정 고유 아이디 확인|
  |title|String|제목 확인|
  |content|String|내용 확인|
  |create_date|datetime|일정 생성 날짜 확인|
  |upadte_date|datetime|일정 수정 날짜 확인|
  ```json
  {
    "event_id": "1234", 
    "title": "운동가기"
    "content": "일어나서 운동가기"
    "create_date": "2024-10-31"
    "update-date": "2024-11-01"
  }


##### 선택 일정 조회
- **URL**: `/events/{event_id}`
- **Method**: `GET`
- **상태코드**: '200: 정상 조회, 400: 조회 실패'
- **요청**

  
  |이름|타입|설명|필수|
  |:----:|:----:|:-------:|:--:|
  |event_id|Integer|일정 아이디 확인|O|
  ```json
  {
    "event_id": "허준"
  }
- **응답**

  
  |이름|타입|설명|
  |:----:|:----:|:-------:|
  |event_id|integer|일정 고유 아이디 확인|
  |title|String|제목 확인|
  |content|String|내용 확인|
  |create_date|datetime|일정 생성 날짜 확인|
  |upadte_date|datetime|일정 수정 날짜 확인|
  ```json
  {
    "event_id": "1234", 
    "title": "운동가기"
    "content": "일어나서 운동가기"
    "create_date": "2024-10-31"
    "update-date": "2024-11-01"
  }

#### 2. 일정 수정 및 삭제


##### 선택 일정 수정
- **URL**: `/events/{event_id}`
- **Method**: `PUT`
- **상태코드**: '200: 정상 수정, 400: 수정 실패'
- **요청**

  
  |이름|타입|설명|필수|
  |:----:|:----:|:-------:|:--:|
  |title|String|수정 할 제목|X|
  |username|String|수정 할 이름|X|
  |password|String|비밀번호 확인|O|
  ```json
  {
    "title": "운동가기"
    "username": "허준"
    "password": "복잡한 비밀번호"
  }
- **응답**

  
  |이름|타입|설명|
  |:----:|:----:|:-------:|
  |message|String|수정 완료시 알림|
  |title|String|제목 수정|
  |content|String|내용 수정|
  |create_date|datetime|일정 생성 날짜 확인|
  |upadte_date|datetime|일정 수정 날짜 갱신|
  ```json
  {
    "message": "일정이 수정 되었습니다", 
    "title": "밥먹기"
    "content": "일어나서 밥먹기"
    "create_date": "2024-10-31"
    "update-date": "2024-11-02"
  }

  
##### 선택 일정 삭제
- **URL**: `/events/{event_id}`
- **Method**: `DELETE`
- **상태코드**: '200: 정상 삭제, 400: 삭제 실패'
- **요청**

  
  |이름|타입|설명|필수|
  |:----:|:----:|:-------:|:--:|
  |title|String|삭제 하고 싶은 일정 제목|X|
  |event_id|Integer|삭제 하고 싶은 일정 아이디|X|
  |password|String|비밀번호 확인|O|
  ```json
  {
    "title": "운동 가기"
    "event_id": "1234"
    "password": "복잡한 비밀번호"
  }
- **응답**

  
  |이름|타입|설명|
  |:----:|:----:|:-------:|
  |message|String|삭제 완료시 알림|
  ```json
  {
    "message": "일정이 삭제 되었습니다", 
  }



