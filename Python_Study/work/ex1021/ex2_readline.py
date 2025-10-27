fs = open("ex1_test2.txt", "r") #파일 연결

while True:
    content = fs.readline() # 파일로부터 데이터를 한줄만 읽어옴
    if content != "": # 만약 읽은 데이터가 공백이 아니면
        print(content, end="") # 마지막에 엔터값을 공백으로 대체하여 출력한다
    else:
        break # 무한 반복문 탈출

fs.close()
print("\n파일 읽기 끝")