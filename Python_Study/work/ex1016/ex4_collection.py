## Python의 자료구조
"""
    - List : 배열과 같고 순서를 가짐: [값1, 값2, ..., 값n]
    - Tuple : List와 같지만 읽기전용 : (값1, 값2, ..., 값n)
    - Set : List와 유사하지만 교집합 구분 : {값1, 값2, ..., 값n}
    - dict : 키와 값이 쌍을 이루어 저장됨 :
                dict(키1=값1, 키2=값2, ...)
                {"키1":":값1", "키2":"값2", ...}
"""
ar1 = [10, 3.14, "TEST"]
print("ar1={}".format(ar1))

ar2 = [10, 20, 30, 3.3, "Michael"]
var1 = ar2[3] # ar2의 3번지에 있는 3.3 값이 var1에 저장
print("ar2[3]={}".format(var1))
print("ar2의 길이:{}".format(len(ar2)))

# 리스트구조가 있지만 길이가 얼마인지 모르는 상태다.
# 이때 마지막 요소의 값을 출력하려 한다.
print("ar2의 마지막요소는:{}".format(ar2[len(ar2)-1]))

# 기억: len(리스트) : 길이를 구해주며 정수로 반환됨

tuple1 = (100, "tuple", 200, 10.5)

ar2[4] = "KOREA" # 리스트의 요소를 변경
ar2.append(1000) # 리스트에 요소 추가
print("ar2={}".format(ar2))

# 하지만 튜플은 수정/추가를 하지 못한다.
# tuple1[2] = "TEST" # 오류!!!!!!

print("------------------------------")

# 복사하기
ar3 = ar2[:] # ar2의 내용을 복사해서 ar3에 저장
print("ar2={}".format(ar2))
print("ar3={}".format(ar3))

chk = ar2 is ar3 # 주소 비교
print("ar2 is ar3:{}".format(chk)) # False

tuple2 = tuple1[:]
print("tuple1={}".format(tuple1))
print("tuple2={}".format(tuple2))

chk2 = tuple1 is tuple2
print("tuple1 is tuple2:{}".format(chk2)) # True

print("-----------------------------")

### Set구조 : 집합을 의미한다.
icecream1 = {"체리쥬빌레", "엄마는외계인", "슈팅스타", "엄마는외계인"}
print("icecream1의 길이:{},{}".format(len(icecream1), icecream1))

# icecream1안에 "쿠키앤크림"이라는 정보가 있는지? 확인하자!
res = "쿠키앤크림" in icecream1
print("\"쿠키앤크림\" in icecream1:", res) # False

res = "쿠키앤크림" not in icecream1
print("\"쿠키앤크림\" not in icecream1:", res) # True

print("----------------------------")

# Set구조의 집합연산을 학습하자!
t1 = set('1234567')
t2 = set('4567890')
print("t1:", t1)
print("t2:", t2)

res1 = t1 & t2 # 두 set 구조의 교집합
print("t1 & t2 : {}".format(res1))

res2 = t1 | t2 # 두 set 구조의 합집합
print("t1 | t2 : {}".format(res2))

res2 = t1 - t2 # 두 set 구조의 차집합
print("t1 - t2 : {}".format(res2))

### dict
d1 = {"k1" : 100, "k2":200, 3:300}
print("d1:", d1)

d1[3] = 11000 # 3이라는 키의 값을 11000으로 변경
d1[4] = 2200 # 4라는 키가 없다면 추가
print("d1:", d1)

res4 = d1.get(3) # d1에 3이라는 키의 값을 가져와서 res4에 저장
print("d1.get(3):",res4)

keys = d1.keys() # d1의 키들
print("d1.keys():", keys)

values = d1.values() # d1의 값들
print("d1.values():", values)

# 딕셔너리의 키들을 list구조로 얻어내는 법
key_list = list(d1.keys())
print("list(d1.keys()):", key_list)