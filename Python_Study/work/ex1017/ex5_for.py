"""
for의 구성      while문의 구성
-----------------------------
for 변수 in 배열:       while 조건식:
    반복할 문장1            조건에 만족시 수행하는 문장
    반복할 문장1
for문과 상관없는 문장
"""
ar1 = [1, 100, 230, 55]
str = "ABCDEF"
for i in ar1:
    print(i)
print("-----------------")

for i in str:
    print(i)

"""
특정 범위를 지정할 때 range를 사용한다.
range(시작값, 종료값+1, step값)
    - 예) 1부터 10까지의 범위를 가지고 싶다면...
        range(1, 11, 1)
"""
for i in range(1, 11):
    print(i)
print("--------------------------")

# 배열을 반복할 때 index값과 value를 같이 출력하고자 한다면
# 이때 enumerate를 사용함!
ar = ["python", "SciPy", "Tensor", "Pandas"]
for v in ar:
    print(v)
print("--------------------------")
for v in enumerate(ar):
    print(v)
print("--------------------------")
for i,v in enumerate(ar):
    print("inedx:{}" )