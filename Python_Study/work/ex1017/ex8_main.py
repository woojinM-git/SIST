from ex8_class import MyClass

mc1 = MyClass() # 객체 생성1
mc2 = MyClass() # 객체 생성2

print(mc1.getName())

mc1.setName("ㅁㄹ")
print(mc1.getName())
mc2.setName("Micheal")
print("mc2.getName():{}".format(mc2.getName()))