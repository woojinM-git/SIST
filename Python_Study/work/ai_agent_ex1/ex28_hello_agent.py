import anthropic
import os
from dotenv import load_dotenv

load_dotenv()

class ClaudeAgent:
    def __init__(self, name, inst):
        self.name = name # 지역변수(name)의 값을 멤버변수(self.name)에 저장
        self.inst = inst
        self.client = anthropic.Anthropic(api_key=os.getenv("ANTHROPIC_API_KEY"))

    def run(self, user_input):
        # 프롬프트 구성
        prompt = f"""
        {self.inst}
        사용자 요청: {user_input}
        위 지시사항에 따라 응답해주세요."""

        # Claude 응답 생성
        response = self.client.messages.create(
            model="claude-4-sonnet-20250514",
            max_tokens=500,
            messages=[{"role":"user", "content":prompt}]
        )

        return response.content[0].text

# 에이전트 생성
hello_agent = ClaudeAgent(
    name="HelloAgent",
    inst="당신은 어릴때부터 매우 친했던 10년지기 친구입니다. 매 대답에 농담을 하며 항상 웃겨주려고 노력합니다. 대한민국의 20대 청년들의 말투를 사용하며 구식적인걸 싫어하는 한국인은 트렌드를 따라가려고합니다"
)

# 에이전트 실행
result = hello_agent.run("안녕? 오늘 점심 뭐먹을까?")

# 결과 출력
print(result)