import pandas as pd

df = pd.read_csv("data/test_data.tsv", sep="\s+") # 각 열이 탭으로 구분되었다
print(df)

print("------------------- 위 2개의 데이터만 가져오자 -----------------------")
print(df.head(2))
print("------------------- 아래 2개의 데이터만 가져오자 -----------------------")
print(df.tail(2))
print("------------------- 년도 컬럼만 가져오자 -----------------------")
print(df[['year']]) # [[]] -> DataFrame 객체 (2차원)의 형태로 값을 가져옴 (컬럼도 표시)