"""
다음과 같은 패키디를 설치하고 재 시도를 수행하도록 유도해야 함

pip install tenacity
"""
import asyncio
import os
from anthropic import AsyncAnthropic
from dotenv import load_dotenv
import logging
import random
import atexit
import sys
from tenacity import retry, stop_after_attempt, wait_exponential, retry_if_exception_type

# 로깅 설정
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

# .env 파일 로드
load_dotenv()

# 값 가져오기
api_key = os.getenv("ANTHROPIC_API_KEY")
if not api_key:
    raise ValueError("ANTHROPIC_API_KEY가 설정되지 않았음")

async def simple_random_failure():
    # 50% 확률로 예외를 발생시키는 임의의 메서드
    if random.random() < 0.5:
        logger.warning("인위적으로 API 호출 실패 발생")
        raise ConnectionError("인위적으로 발생시킨 연결오류")
    # 약간 시간을 지연시킨다.
    await asyncio.sleep(random.uniform(0.1, 0.5))

# tenacity를 사용한 재시도 데코레이터 적용
@retry(
    retry=retry_if_exception_type(), # 모든 예외에 대해 재시도
    wait=wait_exponential(multiplier=1, min=2, max=10), # 재시도 시간 지연 계산 담당
                        # 기본단위 1초, 최소기다림 2초, 최대기다림 10초
    stop=stop_after_attempt(3), # 최대 재시도 3번 시도
    before_sleep=lambda retry_state: logger.warning(
        f"API호출 실패: {retry_state.outcome.exception()}, "
        f"{retry_state.attempt_number}번째 재시도 중..."
    )
)
async def call_claude(prompt:str, model:str = "claude-3-5-haiku-latest") -> str:
# model에 기본값을 claude-3-5-haiku-latest로 넣어 값을 넣어주지 않아도 잘 됌
    # Cluade API를 비동기식으로 호출
    logger.info(f"Claude API호출 시작: {model}")

    # 핵심: async with를 사용하여 클라이언트 자동정리
    async with AsyncAnthropic(api_key=api_key) as client:
        response = await client.messages.create(
            model=model,
            messages=[{"role":"user", "content":prompt}],
            max_tokens=1000,
        )
    logger.info(f"Claude API호출 완료: {model}")
    return response.content[0].text

async def main():
    print("동시에 API 호출하기 (재시도 로직 포함)")
    prompt = "Visual Studio Code를 포크해서 만들어진 통합 개발 환경 Cursor에 대해 간략하게 300자 내로 설명해봐"
    result = await call_claude(prompt)

    try:
        # gatther는 전체 작업 중 하나라도 실패하면 예외 발생
        claude_response = await asyncio.gather(result, return_exceptions=False)
        print(f"Claude API 호출 완료: {claude_response[0]}")
    except Exception as e:
        logger.error(f"Claude API 호출 실패: {e}")

def setup_clean_exit():
    # Windows에서 깔끔한 프로그램 종료를 위한 설정
    if sys.platform == "win32":
        asyncio.set_event_loop_policy(asyncio.WindowsProactorEventLoopPolicy())
        def cleanup():
            # 프로그램 종료시 리소스 정리
            try:
                loop = asyncio.get_event_loop()
                if not loop.is_closed():
                    # 실행 중인 모든 태스크 취소
                    tasks = asyncio.all_tasks(loop)
                    for task in tasks:
                        task.cancel()

                    # 취소된 태스크들 완료 대기
                    if tasks:
                        loop.run_until_complete(
                            asyncio.gather(*tasks, return_exceptions=True)
                        )
                    loop.close()
            except:
                pass # 정리과정에서 에러는 무시
        atexit.register(cleanup)

if __name__ == "__main__":
    # Windows asyncio 에러 방지
    setup_clean_exit()
    try:
        # 메인 함수 실행
        asyncio.run(main())
    except KeyboardInterrupt:
        looger.info("프로그램이 중단되었습니다.")
    finally:
        logger.info("프로그램 종료!")