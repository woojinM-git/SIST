"""
데이터를 수정하고 목적에 맞도록 가공이 편리한
파이썬 라이브러리, 내부적으로 Numpy를 사용해서
숫자 데이터들의 연산이 상당히 원활하다

분석할 데이터의 양(Volume)이 커지고,
데이터의 입출력 속도(Velocity)가 빨라지고,
데이터의 종류가 다양(Variety)한 정보들을 분석

위의 빅데이터 개념에서 분석할 때 많이 쓰이는 것이 Pandas다.

Pandas를 이해하는데 있어 DataFrame을 이해해야 한다.
그럼 DataFrame은 뭘까? MySQL과 같은 데이터베이스에서 보자면
하나의 테이블과 같은 것이 DataFrame이다.

이런 테이블의 열을 의미하는 것이 바로 Series라고 함!
Series는 1차원 배열과 같다. 그래서 Pytho에서 얘기하는
List와 dict와 같은 것들이 포함된다.
"""
import pandas as pd

ko = pd.Series([96200,92450,95465,92300])
print(ko)

"""
만약 Pandas버전에 관련된 오류가 발생할 때...
pip uninstall pandas

pip install pandas==1.0.1
"""