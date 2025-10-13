"use client"

import MyTable from "@/component/MyTable";
import axios from "axios";
import { useEffect, useState } from "react";

export default function Page(){
    // 서버 경로
    const api_url = "/api/emp/all"

    // 서버로 부터 전달되어 오는 자원을 저장할 곳
    const [list, setList] = useState([]);

    // 서버를 비동기식 통신하는 함수
    function getData(){
        axios.get(api_url).then((response) => {
            // response는 스프링부트쪽에서 보낸 정보다
            // 스프링에서 보낸 실제 자원은 data라는 변수에
            // 저장되어 넘어온다.
            setList(response.data.ar);
        });
    }

    //getData를 현재 페이지가 로드 될 때 단 한번 호출하자!
    useEffect(function(){
        getData(); // 실제 서버를 통신하는 함수호출
    }, []);


    return(
        <div className="list=bg">
            <MyTable ar={list}/>
        </div>
    );
}