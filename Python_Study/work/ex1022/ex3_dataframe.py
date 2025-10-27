from pandas import Series, DataFrame

emp = [[100, "마루치", "dev"],
    [220, "아라치", "dev"],
    [250, "마동탁", "dev"],
    [217, "이도", "CEO"]]

# 컬럼명만 따로 저장하자
c_name = ["empno", "ename", "job"]

# 위의 내용들을 가지고 DataFrame 생성
df = DataFrame.from_records(emp, columns=c_name)
print(df)
print("------ 행 삭제 ---------")
print(df.drop([0,2])) # 첫번째 행과 3번째 행을 삭제
print(df) # 사실 df에는 영향이 미치지 않는다.
# print(df) # 사실 df 에는 영향이 미치지 않고
print("------ job이 CEO 인 정복 삭제 -----")
print(df[df.job != "CEO"])
# 삭제하고 싶다면 df = df[df.job != 'CEO']