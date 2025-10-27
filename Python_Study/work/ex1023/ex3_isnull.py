from pandas import Series, DataFrame

emp_dict = [
    {'empno':100, 'name':'마루치', 'job':'IT_PROG'},
    {'empno':120, 'name':'아라치', 'job':'Analysis'},
    {'empno':345, 'name':'을불', 'job':'IT_PROG'},
    {'empno':210, 'name':'창조리', 'job':'Sales'},
    {'empno':349, 'name':'홍길동', 'job':'IT_PROG'},
    {'empno':345, 'name':'쌍용', 'job': None},
    {'empno':445, 'name':'마이클', 'job':'IT_PROG'},
    {'empno':410, 'name':'어두일미', 'job':'Analysis'}
]
df = DataFrame(emp_dict)
print(df)
print("----- None이 있는 값 알아내기 -----")
print(df.isnull()) #print(df.isna()) # isna는 (is Not Available)
print("----- None을 다른 값으로 변경 -----")
df.job = df.job.fillna("대기")
print(df)