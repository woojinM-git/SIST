"""
    랭체인은 LLM을 효과적으로 활용하기 위한 프레임워크
    언어로는 파이썬과 자바스크립트로 지원한다.
    - 파이썬랭체인 : https://github.com/langchain-ai/langchain
    - JS랭체인 : https://github.com/langchain-ai/langchainjs

    랭체인의 핵심은
    프롬프트, 백터스토어, 아웃풋파서, 메모리, LLM도구 등을
    체인형태로 연결하여 다양하고 복잡한 작업을 단순화 시킨다.

    랭체인 모델은 LLM종류에 따라 다르다.
    [오픈API] : pip install langchain[openai]==0.3.27
    [앤트로픽] : pip install langchain[anthropic]==0.3.27
    [구글 Gemini모델] : pip install -q -U google-genai
        -q : quiet모드 (설치 과정을 간략하게 표시)
        -U : upgrade (이미 설치된 경우 최신 버전으로 업그레이드)
        [구글 langchain모듈] : pip install langchain-google-genai
"""
from dotenv import load_dotenv
from langchain.chat_models import init_chat_model
import random

load_dotenv()

if random.random() < 0.5:
    print("USing other AI model")
else:
    model = init_chat_model("claude-3-5-haiku-latest", model_provider="anthropic")
    result = model.invoke("RAG가 뭐야?")
    print(result)