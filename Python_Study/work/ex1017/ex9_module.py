"""
모듈이란? 보통 비슷한 동작을 하는 함수들을 하나로 묶어서
다른 곳에서 사용할 수 있도록 함

모듈들
    - date : 날짜 관련 모듈
    - time : 시간 관련 모듈
    - math : 수학 관련 모듈
    - fractions : 분수 관련 모듈
    - decimal : 십진수 관련 모듈
    - random : 난수 관련 모듈
    - file : 파일 관련 모듈
    - os : 운영체제 관련 모듈
    - sys : 파이썬 번역기 관련 모듈
    - threading : 스레드 관련 모듈
    - unittest : 단위 테스트
    - http : http 프로토콜 관련 모듈
    - xml : xml 문서 파싱 관련 모듈
    - sqlite3 : 데이터베이스 관련 모듈
"""
import math

v1 = 131.23
v2 = math.ceil(v1)
print("math.ceil(v1):{}".format(v2))