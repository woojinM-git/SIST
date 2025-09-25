"use client"

import { Divider } from "@mui/material";
import Link from "next/link";
import styles from "../page.module.css";
import axios from "axios";
import { useEffect, useState } from "react";
import BbsTR from "@/components/BbsTR";

export default function Memvers(){

    let api_url = "/api/bbs"

    // 스프링 서버에서 전달되는 JSON 배열을 저장할 곳;
    const [list, setList] = useState([]);

    // 비동기식 통신을 수행하는 함수
    function getBbsList(){
        axios.get(api_url).then(function(json){
            // 서버로부터 결과를 받았을 때 수행
            // 서버로부터 넘어온 자원들은 모두 json.data에 있다.
            if(json.data.totalCount > 0){
                setList(json.data.data);
            }
        })
    }

    // 한번만 호출하는 함수
    useEffect(function (){
        getBbsList();
    },[]);

    // 총 페이지
    const [totalPage, setTotalPage] = useState(0);

    return(
        <div style={{width: "80%", margin: '10px auto'}}>
            <h1>게시판</h1>
            <Divider/>
            <table className="t1">
                <thead>
                    <tr>
                        <td colSpan={5} className={styles.no_border}>
                            <Link href='board/write'>
                                <button type="button">글쓰기</button>
                            </Link>
                        </td>
                    </tr>
                    <tr>
                        <th>기본키</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>등록일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                    {list.map(function (ar, i){
                        return(
                            <BbsTR key={i} 
                            b_idx={ar.b_idx}
                            title={ar.title}
                            writer={ar.writer}
                            write_date={ar.write_date}
                            hit={ar.hit}
                            />
                        );
                    })}
                </tbody>
            </table>
        </div>
    );
}