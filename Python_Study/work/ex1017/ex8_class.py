"""
초기(생성)자: def__init__(self): # 객체를 호출해 생성될 때 불려진다 (new 와 같음)
소멸자: def__del__(self): # 소멸자는 해당 객체를 참조하는 변수가 없어 객체가 소멸될때 불려진다.

위는 생략되어 있어서 보이진 않지만 자동으로 정의된다.
"""
class MyClass: # 클래스 정의 시작
    def setName(self, n): # 멤버메서드(기능, 동작) 정의
        self.name = n # 현재객체가 가지고 있는 name이라는 변수에 인자 n의 값을 대입

    def getName(self): # 멤버메서드
        if not hasattr(self, "name") or self.name is None:
            return "비었음"
        return self.name