import os

cpath = os.getcwd() # 현재 소스파일의 위치 값
print("현재 위치:", cpath)
print("---------------------------")

sub_list = os.listdir(cpath) # 현재 위치 안에 있는 하위들
for s in sub_list:
    print(s)