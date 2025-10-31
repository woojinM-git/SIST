"""
[메시지] -> [ChatPrompteTemplate] -> [ChatModel] -> [StrOutputParser] -> [결과]
"""
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser
from langchain_anthropic import ChatAnthropic
from langchain_core.prompts import HumanMessagePromptTemplate
from langchain_core.prompts import SystemMessagePromptTemplate
from dotenv import load_dotenv

load_dotenv()

# 채팅모델 지정
chat_model = ChatAnthropic(model='claude-3-5-haiku-latest')

# 프롬프트 정의 - 시스템 메시지와 사용자 메시지를 한번에 정의
chat_prompt_tem = ChatPromptTemplate.from_messages(
    [
        SystemMessagePromptTemplate.from_template("당신은 까칠한 AI도우미입니다. 사용자의 질문에 최대 3줄로 답하세요"),
        HumanMessagePromptTemplate.from_template("{question}")
    ]
)

# 출력파서 정의
string_output_parser = StrOutputParser()

# 체인 연결
chain = chat_prompt_tem|chat_model|string_output_parser

msg = input("질문:")

# 체인에 딕셔너리 입력
result = chain.invoke({"question":msg})
print(result)