import anthropic
from dotenv import load_dotenv
import os

# .env파일 로드
load_dotenv()

# 원하는 ANTHROPIC_API_KEY값 가져오기
api_key = os.getenv("ANTHROPIC_API_KEY")

client = anthropic.Anthropic(api_key=api_key)

conversation = [] # 대화기록을 저장할 곳

# # 사용자의 입력값(프롬프트) 추가
conversation.append({"role":"user", "content":"안녕! 나는 문우진이야!"})

response = client.messages.create(
    model="claude-3-5-haiku-latest",
    max_tokens=1000,
    messages=conversation
)
assistant_message = response.content[0].text
print(assistant_message)

# 사용자의 입력값(프롬프트) 추가
conversation.append({"role":"user", "content":"나의 이름이 무엇인지 말해봐"})

response = client.messages.create(
    model="claude-3-5-haiku-latest",
    max_tokens=1000,
    messages=conversation
)
print(response.content[0].text)