"""
[결과]
1 2 3 4 5
1 2 3 4 5
1 2 3 4 5
"""
for i in range(1, 4):
    msg = ""
    for j in range(1, 6):
        msg += "{}\t".format(j)
    print(msg)
print("--------------------------")

for i in range(1, 4):
    msg = ""
    for j in range(1, 6):
        msg += "{}\t".format(j)
        if j == 3:
            break # j가 3일때 탈출
    print(msg)