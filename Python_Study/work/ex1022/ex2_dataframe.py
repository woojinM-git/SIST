from pandas import Series, DataFrame

emp = [[100, "마루치", "dev"],
    [220, "아라치", "dev"],
    [250, "마동탁", "dev"],
    [217, "이도", "dev"]]

# 컬럼명만 따로 저장하자
c_name = ["empno", "ename", "job"]

# 위의 내용들을 가지고 DataFrame 생성
df = DataFrame.from_records(emp, columns=c_name)
print(df)
"""
이쯤에서 정리해보면 DataFrmae을 두 번에 걸쳐 만들어 봤다.
- 딕셔너리를 가지고 DataFrame 생성 시 바로 넣어 만드는 법
- 리스트로 된 데이터와 컬럼명을 가지고 DataFrmae을 만드는 법
"""
print("------------- df.loc[1] ------------")
print(df.loc[1]) # 인덱스가 1인 행의 정보를 얻어냄

print("------------ 특정 행에서 특정 행까지(범위 설정) ------------")
print(df.loc[1:3])

print("-----------------------------")
print(df[1:3]) # 4번째는 포함

print("---------- 조건부에(사번이 250번 이상) ----------")
print(df[df.empno >= 250])

print("----- 사번이 220번 이상이며 직종이 dev인 사원 -----")
print(df[(df.empno >= 250) & (df.job == "dev")])

"""
지금까지는 DataFrame의 행을 선택하는 법을 했다.
지금부터는 열을 선택해 보자!
"""
print("---- 열 선택 ----")
print(df["ename"]) # 이름이라는 열을 선택하여 출력함
print("---- 다중 열 선택 ----")
print(df[["ename", "job"]])
print("------------")
print(df)
print("------- 모든 행을 포함하고, 열은 첫번째와 두번쨰만 출력 -------")
print(df.iloc[: , 0:2]) # iloc[행범위, 열범위]