# dan = input("단:")
# dan = int(dan)
# for i in range(1, 10):
#     print("{}*{}={}".format(dan, i, (dan*i)))

print("------------------------------")
# 다중 for를 이용하여 2단부터 9단까지 출력하자!
for i in range(1, 10):
    str = ""
    for j in range(2, 10):
        str += "{}*{}={}\t".format(j,i,(i*j))
    print(str)