"""
파이썬에 파일을 읽기/쓰기 하는 방법
- 파일을 열기(open)했으면 반드시 닫기(close)해야 함!
    (파일을 열기할 때는 반드시 읽기를 하기 위함인지?
    쓰기를 하기 위함인지?를 명시해야 함!)
- mode는 다음과 같이 구분된다.
    ** w : 쓰기
    ** r : 읽기
    ** a : 추가
    ** rb : 바이너리로 읽기
"""
print("-----------------파일 형식이 UTF-8일 경우-----------------")
with open('ex1_test.txt', "r", encoding='utf-8') as fs: # 파일 연결
    content = fs.read() # 파일로 부터 데이터를 모두 읽어서
                    # 변수 content에 저장!
fs.close() # 파일과 연결된 객체를 닫기

print(content)
print("-----------------파일 형식이 ANSI일 경우-----------------")
fs = open("ex1_test2.txt", "r") # 파일 연결
content = fs.read()
fs.close()
print(content)