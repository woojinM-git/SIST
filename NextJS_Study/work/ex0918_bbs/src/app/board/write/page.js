"use client";

import Link from "next/link";
import { useState } from "react";

export default function Page(){

    // 서버 URL

    // 사용자가 입력한 값들을 하나의 객체로 저장할 곳
    const [bbs, setBbs] = useState({});

    function handleChange(e) { // 인자로 받는 객체는 현재 사용자가 입력하고 있는 객체를 의미
        const {name, value} = e.target; // 테이블에서 지정한 name="{name}"이 들어감
        setBbs({...bbs, [name]:value});
        console.log({...bbs, [name]:value});
    }

    function sendBbs(){
        // 비동기식 통신(스프링부트 서버 호출)
    }

    return(
        <div style={{width: "80%", margin: '10px auto'}}>
            <h1>글쓰기</h1>
            <hr/>
            <table className="t1">
                <tbody>
                    <tr>
                        <th>제목</th>
                        <td colSpan={3}>
                            <input type="text" id="title" name="title" onChange={handleChange}/>
                        </td>
                    </tr>
                    <tr>
                        <td>글쓴이</td>
                        <td colSpan={3}>
                            <input type="text" id="writer" name="writer" onChange={handleChange}/>
                        </td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td colSpan={3}>
                            <textarea cols={40} rows={5} id="content" name="content" onChange={handleChange}></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colSapn={4}>
                            <Link href="/bbs">
                                <button type="button">뒤로</button>
                            </Link>
                            &nbsp;&nbsp;
                            <Link href="">
                                <button type="button" onClick={sendBbs}>저장</button>
                            </Link>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}