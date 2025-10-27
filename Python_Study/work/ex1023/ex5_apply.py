from pandas import Series, DataFrame

df = DataFrame([
    {'b_day':'1977-10-23'},
    {'b_day':'2000-08-15'},
    {'b_day':'2003-09-23'},
    {'b_day':'2008-04-22'}
])
print(df)
print("-----------------")

def clip_year(col):
    return col.split('-')[0] # 년도만 반환

df['year'] = df['b_day'].apply(clip_year) # clip_year(df['b_day'])형태다
# 즉 apply 함수를 붙이면 앞의 컬럼 값이 우선 인자로 전달된다.
print(df)
print("-------------------------")

def get_age(year, c_year):
    return c_year - int(year) # 현재년도에서 생년을 뺀 나이 반환

df['age'] = df['year'].apply(get_age, c_year=2025)
            # 첫번째인자                두번째인자
print(df)
print("----------- 함수 호출 시 인자를 행 전체를 전달할 때가 있다. -----------")

def get_age2(row):
    return str(row.year) + "년생은 " + str(row.age) + "세"

df['etc'] = df.apply(get_age2, axis=1)
print(df)