# 부울형: bool(Boolean형)
b1 = True
b2 = False
print("b1={}".format(b1))

a = 100
b = 32
print(a+b) # 132

c = a*b
print("a*b=%d" %c) # 3200

c = a/b
print("a/b={}".format(c))

c = a%b
print("a%b={}".format(c))

### 비교연산자
print(a==b)
print(a>=b)
print(a<=b)
print(a!=b)

### 논리연산 (&: AND, |:OR, !:NOT)
print("a={}, b={}".format(a, b))
print((a>b) & (a>=80))
print((a>b) | (a>=80))

# 주의) Python에서는 증감연산자가 지원되지 않음
#       파이썬에서는 가독성과 편리성을 중시하기 때문임