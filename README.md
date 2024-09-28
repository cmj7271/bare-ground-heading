# requirement
`env/db.dnv`  
docker  
env 파일은 요청해주세요

# How to Run
1. `docker` 실행여부 확인
2. `./gradlew build` 로 jar 파일 생성   
3. `docker compose up`

끌 때는,  
`docker compose down`  

*주의*  
코드상 변경 사항이 있다면,  
도커의 컨테이너, 볼륨 등을 제거해야합니다.
