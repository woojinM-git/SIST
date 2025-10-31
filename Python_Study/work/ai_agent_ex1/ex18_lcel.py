"""
LCEL (LangChain Expression Langquage): 랭체인 표현언어
 Runnable 들을 조합하여 복잡한 로직을 만들 수 있게 하는 선언적 언어이다.
 예_ chain = component1 | component2 | component3

 프롬프트 -> 모델 -> 파서로 이어지는 간단한 예제를 확인
"""
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser
from langchain_anthropic import ChatAnthropic
import anthropic
import os
from dotenv import load_dotenv

load_dotenv()
api_key = os.getenv("ANTHROPIC_API_KEY")

prompt = ChatPromptTemplate.from_messages([
    ("human", "주어지는 문구에 대하여 50자 이내의 짧은 시를 한국어로 작성해줘:{word}")
])

# 모델 준비
model = ChatAnthropic(model="claude-3-5-haiku-latest")
parser = StrOutputParser()

chain = prompt | model | parser

# 실행하는 부분
result = chain.invoke({"word":"무야호 할아버지"})
print(result)