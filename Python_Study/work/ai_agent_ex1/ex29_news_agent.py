import anthropic
import os
from dotenv import load_dotenv
from duckduckgo_search import DDGS

load_dotenv()

class ClaudeAgent:
    def __init__(self, name, inst, tools):
        self.name = name
        self.inst = inst
        self.tools = tools
        self.client = anthropic.Anthropic(api_key=os.getenv("ANTHROPIC_API_KEY"))

    def run(self, user_input):
        # 1. 도구 실행
        tool_results = []
        for tool in self.tools:
            try:
                result = tool(user_input)
                tool_results.append(result)
            except Exception as e:
                tool_results.append(f"도구 실행 오류:{e}")

        # 2. claude에게 전달할 프롬프트 구성
        tool_output = "\n\n".join(tool_results)
        prompt = f"""
        {self.inst}
        사용자 요청: {user_input}
        검색 결과:
        {tool_output}
        위 검색 결과를 바탕으로 답변해주세요."""

        #. Claude 응답 생성
        response = self.client.messages.create(
            model="claude-4-sonnet-20250514",
            max_tokens=1000,
            messages=[{"role":"user", "content":prompt}]
        )
        return response.content[0].text

# 도구 정의
def news_search(query:str) -> str:
    """ 덕덕고를 사용한 뉴스 검색 핸들러 함수 """
    try:
        # 덕덕고 검색 도구 사용
        results = DDGS().text(query, max_results=1)

        # 검색 결과가 있는 경우 포멧팅
        if results:
            return f"'{query}' 검색결과: \n{results}"
        else:
            return "검색 결과가 없습니다."
    except Exception as e:
        return f"검색 중 오류가 발생했습니다. [str{e}]"

# 에이전트 생성
news_agent = ClaudeAgent(
    name="NewsAgent",
    inst=(
        "당신은 한국의 유명 스트리머 조코딩 입니다 IT 관련 뉴스, 기술에 대한 주제를 다루며 해당 스트리머의 말투를 따라서 답변을 작성하세요. WebsearchTool을 사용하여 최신 뉴스를 검색하고, 3개의 기사 URL을 함께 알려주세요."
    ),
    tools=[news_search]
)

if __name__ == "__main__":
    # 에이전트 실행
    print("클로드 뉴스 검색 에이전트를 시작합니다")
    result = news_agent.run("최신 AI기술 뉴스를 검색해 주세요.")
    print(result)