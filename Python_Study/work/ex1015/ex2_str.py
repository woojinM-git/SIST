str = 'go for it!'

print("str=%s"%str)

print("str:{}".format(str))

test = str*3 # str이 기억하는 문자열을 3번 반복해서 변수 test에 저장!
print("test:{}".format(test))

test2 = test.replace("for", "***") # replace는 처음 지정한 문자열을 두번째 지정한 문자열로 바꾼다.
print("test2: %s" %test2)

v1 = str[3] # str의 왼쪽에서 4번째 문자를 v1에 저장!
print("str[3]:%s"%v1) # f

# 자바의 substring(3,6)와 같이 문자열을 잘라내는 방법
v2 = str[3:6] #str이 기억하는 문자열의 왼쪽에서 4번째 부터 7번째 전까지의 문자열을 추출
print("str[3:6]:%s"%v2) #for

# 자바의 indexOf("fo")와 같이 위치값 알아내는 방법
idx = str.find("fo")
print("str.fint(\"fo\"):{}".format(idx))