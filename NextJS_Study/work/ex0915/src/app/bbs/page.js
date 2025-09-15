import BbsTR from "@/components/bbsTR";
export default function bbs() {
    return(
        <div>
            <h1 className="title">게시판</h1>
            <hr/>
            <div>
                <table className="t1">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>글쓴이</th>
                            <th>등록일</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        {/* JSX 주석 */}
                        {/* -- 다음은 Component를 활용한 예다. */}
                        <BbsTR/>
                    </tbody>
                </table>
            </div>
        </div>
    );
};