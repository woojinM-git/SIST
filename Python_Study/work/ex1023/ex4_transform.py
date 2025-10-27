from pandas import Series, DataFrame

emp_dict = [
    {'empno':100, 'name':'마루치', 'job':'teacher', 'age': 32},
    {'empno':120, 'name':'아라치', 'job':'student', 'age': 18},
    {'empno':345, 'name':'을불', 'job':'student', 'age': 17},
    {'empno':210, 'name':'창조리', 'job':'teacher', 'age': 32},
    {'empno':349, 'name':'홍길동', 'job':'step', 'age': 22},
    {'empno':218, 'name':'아수라', 'job':'student', 'age': 17},
    {'empno':545, 'name':'을불', 'job':'teacher', 'age': None},
    {'empno':445, 'name':'마이클', 'job':'teacher', 'age': 29},
    {'empno':410, 'name':'어두일미', 'job':'student', 'age': None}
]
df = DataFrame(emp_dict)
print(df)

print("--------------------")
# df = df.age.fillna(0) # 프로그램 적으로는 이상이 없지만 유용하지 못한 자원이 된다.
df['age'].fillna(df.groupby('job')['age'].transform('median'), inplace=True) # inplace=True df를 바꾼다는 의미
print(df)