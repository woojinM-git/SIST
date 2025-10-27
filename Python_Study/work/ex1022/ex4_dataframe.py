from pandas import Series, DataFrame

ar = [
    {"id":10101, "name":"마루치", "Java":89, "Python":82, "Fast":90},
    {"id":21422, "name":"이도", "Java":96, "Python":90, "Fast":92},
    {"id":25631, "name":"을불", "Java":92, "Python":89, "Fast":92},
    {"id":12422, "name":"강태풍", "Java":98, "Python":92, "Fast":94}
]
df = DataFrame(ar)
print(df)
print("-------- 총점[Total] 컬럼 추가 ----------")
df["Total"] = df["Java"] + df["Python"] + df["Fast"]
print(df)
print("-------- 평균[Ave] 컬럼 추가 ----------")
df["Ave"] = (df["Total"]/3).round(2) # 소수점 2자리까지만 나옴
print(df)

"""
다음의 조건으로 등급(grade)를 지정하자!
[조건]
    평균이 96점이상이며 'A+',
        90~95 : 'A',
        86~89 : 'B+',
        80~85 : 'B',
        76~79 : 'C+',
        70~75 : 'C',
        66~69 : 'D+',
        60~65 : 'D',
                'F'
"""
grades = [] # 등급들이 들어갈 저장소

for var in df["Ave"]:
    if var > 95:
        grades.append("A+")
    elif var > 89:
        grades.append("A")
    elif var > 89:
        grades.append("B+")
    elif var > 85:
        grades.append("B")
    elif var > 79:
        grades.append("C+")
    elif var > 75:
        grades.append("C")
    elif var > 69:
        grades.append("D+")
    elif var > 65:
        grades.append("D")
    else:
        grades.append("F")
df["grade"] = grades # 각 자원들의 등급 추가
print(df)