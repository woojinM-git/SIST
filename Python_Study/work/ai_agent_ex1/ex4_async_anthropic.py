import asyncio
import os
from anthropic import AsyncAnthropic
from dotenv import load_dotenv

# .env 파일 로드
load_dotenv()

# 원하는 값을 가져오기
api_key = os.getenv("ANTHROPIC_API_KEY")
if not api_key:
    raise ValueError("ANTHROPIC_API_KEY가 설정되지 않았음!") # 의도적으로 예외발생

# 비동기 클라이언트 생성
# client = AsyncAnthropic(api_key=api_key) # 지난 시간에 생성된 객체
claude_client = AsyncAnthropic(api_key=api_key)

async def call_claude(prompt:str, model:str = "claude-3-5-haiku-latest") -> str:
# model에 기본값을 claude-3-5-haiku-latest로 넣어 값을 넣어주지 않아도 잘 됌
    # Cluade API를 비동기식으로 호출
    async with AsyncAnthropic(api_key=api_key) as client:
        response = await client.messages.create(
            model=model,
            messages=[{"role":"user", "content":prompt}],
            max_tokens=1000,
        )
    return response.content[0].text

async def main():
    prompt = "Visual Studio Code를 포크해서 만들어진 통합 개발 환경 Cursor에 대해 간략하게 300자 내로 설명해봐"
    result = await call_claude(prompt)
    print(result)

if __name__ == "__main__":
    # Windows asyncio 에러 방지
    import sys
    if sys.platform == "win32":
        asyncio.set_event_loop_policy(asyncio.WindowsProactorEventLoopPolicy())
    asyncio.run(main())