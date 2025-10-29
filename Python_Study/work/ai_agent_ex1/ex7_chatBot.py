import anthropic
import os
from dotenv import load_dotenv

# .env 파일 로드
load_dotenv() # 현재 폴더 위치의 .env 파일을 읽어들임

# 환경변수 파일에서 API키 가져오기
api_key = os.getenv("ANTHROPIC_API_KEY")

client = anthropic.Anthropic(api_key=api_key)

def chatbot_response(msg):
    response = client.messages.create(
        model="claude-3-5-haiku-latest",
        max_tokens=1024,
        messages=msg
    )
    return response.content[0].text

if __name__ == "__main__":
    list = []
    while True: # 무한반복
        user_message = input("메시지:")
        # 위 user_message가 "exit"이면 종료
        if user_message.lower() == "exit":
            print("대화를 종료합니다.")
            break
        # 먼저 사용자가 입력한 문자열을 list에 추가해야 한다.
        list.append({"role":"user", "content":user_message})
        result = chatbot_response(list)
        print("챗봇:"+result)
        # 정확한 대화가 계속 진행되기 위해 LLM이 응답한
        # 내용도 추가해야 한다.
        list.append({"role":"assistant", "content":result})