"use client"

import { use, useEffect, useState } from "react";
import axios from "axios";
import Link from "next/link";
import { useParams } from "next/navigation";

export default function Memvers(){
    const {idx} = useParams();

    let api_url = `/api/bbs/${idx}`;

    // 스프링 서버에서 전달되는 자원 저장할 곳;
    const [vo, setVO] = useState({});

    // 비동기식 통신을 수행하는 함수
    function getBbs(){
         axios.get(api_url).then(function(res){
            console.log(res.data.msg);
            if(res.data.msg = "success") // 전달되어 온 결과의 msg가 succcess일 때만 set 호출
                setVO(res.data.data);
        });
    }

    // idx가 바뀔 때마다 호출하는 함수
    useEffect(function (){
        getBbs();
    },[idx]);

    return(
        <div style={{width: "80%", margin: '10px auto'}}>
            <h2>글 보기</h2>
            <hr/>
            <table className="t1">
                <tbody>
                    <tr>
                        <th>번호</th>
                        <td colSpan={3}>{idx}</td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td colSpan={3}>{vo.title}</td>
                    </tr>
                    <tr>
                        <th>글쓴이</th>
                        <td>{vo.writer}</td>
                        <th>조회수</th>
                        <td>{vo.hit}</td>
                    </tr>
                    <tr>
                        <th>등록일</th>
                        <td colSpan={3}>{vo.write_date}</td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td colSpan={3}>{vo.content}</td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colSpan={4}>
                            <Link href='/board'>뒤로</Link>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    );
}